package com.eboji.bootstrap;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.TZlibTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eboji.annotation.ThriftHandler;
import com.eboji.annotation.ThriftProcessor;
import com.eboji.bootstrap.config.SpringConfiguration;
import com.eboji.server.HsHaServer;
import com.eboji.server.NonblockingServer;
import com.eboji.server.Server;
import com.eboji.server.SimpleServer;
import com.eboji.server.ThreadPoolServer;
import com.eboji.server.ThreadedSelectorServer;
import com.eboji.service.HelloService;
import com.eboji.thrift.service.CmdService;
import com.eboji.util.ClassScanUtil;
import com.eboji.util.Constant;
import com.eboji.vo.ServerConfigVO;

public class Daemon {
	private static final Logger logger = LoggerFactory.getLogger(Daemon.class);
	
	private String host = "localhost";
    private int port = 9090;
    private String protocol_type = "binary";
    private String transport_type = "buffered";
    private String server_type = "simple";
    private boolean ssl = false;
    private int socketTimeout = 10000;
	
	private ServerConfigVO serverConfigVO = null;
	
	private TMultiplexedProcessor tMultiplexedProcessor = null;
	
	private static final Daemon daemon = new Daemon();

	private Daemon() {
	}
	
	public static Daemon getInstance() {
		return daemon;
	}

	private ApplicationContext context = null;
	
	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public void init() {
		initContext();
	}

	private void initContext() {
		setContext(null);
		setContext(new AnnotationConfigApplicationContext(SpringConfiguration.class));
		
		HelloService service = (HelloService)context.getBean("helloService");
		logger.info("========" + service.hello());
		
		initTMultiplexedProcessor();
		
		initServerConfig();
	}
	
	private void initTMultiplexedProcessor() {
		tMultiplexedProcessor = null;
		tMultiplexedProcessor = new TMultiplexedProcessor();
		
		List<Class<?>> processorList = new ArrayList<Class<?>>();
		List<Class<?>> handlerList = new ArrayList<Class<?>>();

		findProcessorAndHandler(processorList, handlerList);
		
		registerProcessor(processorList, handlerList);
	}

	private void initServerConfig() {
		if(serverConfigVO == null) {
			try {
				serverConfigVO = new ServerConfigVO();
				serverConfigVO.setPort(port);
				serverConfigVO.setClientTimeout(socketTimeout);
				serverConfigVO.settMultiplexedProcessor(tMultiplexedProcessor);
			
				TTransportFactory tTransportFactory = getTransportFactory();
				TProtocolFactory tProtocolFactory = getTprotocolFactory();
				
				serverConfigVO.settTransportFactory(tTransportFactory);
				serverConfigVO.settProtocolFactory(tProtocolFactory);
			} catch (Exception e) {
				logger.error("initialize server configuration failed!", e);
				System.exit(0);
			}
		}
	}
	
	private TTransportFactory getTransportFactory() {
		TTransportFactory tTransportFactory = null;
		
		if(transport_type.equals(Constant.TRANSPORT_BUFFERED)) {
			tTransportFactory = new TTransportFactory();
		} else if(transport_type.equals(Constant.TRANSPORT_FRAMED)) {
			tTransportFactory = new TFramedTransport.Factory();
		} else if(transport_type.equals(Constant.TRANSPORT_FASTFRAMED)) {
			tTransportFactory = new TFastFramedTransport.Factory();
		} else if(transport_type.equals(Constant.TRANSPORT_HTTP)) {
			String url = "http://" + daemon.getHost() + ":" + daemon.getPort() + "/CmdService";
			tTransportFactory = new THttpClient.Factory(url);
		} else if(transport_type.equals(Constant.TRANSPORT_ZLIB)) {
			tTransportFactory = new TZlibTransport.Factory();
		}
		
		return tTransportFactory;
	}
	
	private TProtocolFactory getTprotocolFactory() {
		TProtocolFactory tProtocolFactory = null;
		
		if (protocol_type.equals(Constant.PROTOCOL_JSON)) {
			tProtocolFactory = new TJSONProtocol.Factory();
		} else if (protocol_type.equals(Constant.PROTOCOL_COMPACT)) {
			tProtocolFactory = new TCompactProtocol.Factory();
		} else {
			tProtocolFactory = new TBinaryProtocol.Factory();
		}
		
		return tProtocolFactory;
	}
	
	private void findProcessorAndHandler(List<Class<?>> processorList, 
			List<Class<?>> handlerList) {
		ClassScanUtil cps = new ClassScanUtil();
		Set<Class<?>> clazzSet = cps.getPackageAllClasses(Constant.THRIFT_SERVICE_PKG, true);
		List<Class<?>> clazzList = new ArrayList<Class<?>>(clazzSet);
		
		for(Class<?> clazz : clazzList) {
			if(clazz.getAnnotation(ThriftProcessor.class) != null) {
				processorList.add(clazz);
				logger.info("Loading service: " + clazz.getCanonicalName());
			}
			if(clazz.getAnnotation(ThriftHandler.class) != null) {
				handlerList.add(clazz);
				logger.info("Loading service implememt: " + clazz.getCanonicalName());
			}
		}
	}
	
	private void registerProcessor(List<Class<?>> processorList, 
			List<Class<?>> handlerList) {
		try {
			for(Class<?> clazz : processorList) {
				Class<?> processor = Class.forName(clazz.getCanonicalName() + "$Processor");
				Class<?> iface = Class.forName(clazz.getCanonicalName() + "$Iface");
				Constructor<?> constructor = processor.getConstructor(iface);
				
				for(Class<?> clazzHandler : handlerList) {
					if(clazz.getAnnotation(ThriftProcessor.class).value().equals(clazzHandler.getSimpleName())) {
						TProcessor tProcessor = (TProcessor)constructor.newInstance(
								context.getBean(clazzHandler.getAnnotation(ThriftHandler.class).value()));
						String serviceName = clazzHandler.getAnnotation(ThriftHandler.class).value();
						
						tMultiplexedProcessor.registerProcessor(serviceName, tProcessor);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error("thrift service register fail!", e);
			System.exit(0);
		}
	}
	
	public void start() {
		TServer tServer = null;
		Server server = null;
		
		if(server_type.equals(Constant.SERVER_SIMPLE)) {
			server = (Server)SimpleServer.getInstance();
		} else if(server_type.equals(Constant.SERVER_NONBLOCKING)) {
			server = (Server)NonblockingServer.getInstance();
		} else if(server_type.equals(Constant.SERVER_HSHA)) {
			server = (Server)HsHaServer.getInstance();
		} else if(server_type.equals(Constant.SERVER_THREADSELECTOR)) {
			server = (Server)ThreadedSelectorServer.getInstance();
		} else if(server_type.equals(Constant.SERVER_THREADPOOL)) {
			server = (Server)ThreadPoolServer.getInstance();
		}
		
		tServer = server.createServer(
				serverConfigVO.getPort(), serverConfigVO.getClientTimeout(),
				serverConfigVO.gettProtocolFactory(), serverConfigVO.gettTransportFactory(),
				serverConfigVO.gettMultiplexedProcessor());
		
		if(tServer != null) {
			TServerStartThread st = new TServerStartThread(tServer);
			st.start();
		} else {
			System.exit(0);
		}
	}

	public void restart() {

	}

	public void stop() {
		int step = 0;
		try {
			TTransport tTransport = getTransport();
			TProtocol tProtocol = getProtocol(tTransport);
			TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(tProtocol, "CmdService");
			CmdService.Client client = new CmdService.Client(tMultiplexedProtocol);
			
			step++;
			tTransport.open();
			step++;
			client.stop();
			step++;
		} catch (Exception e) {
			if(step <= 1)
				logger.error("connecting the server failed!", e);
			if(step > 1)
				logger.error("server has been closed!", e);
		}
	}
	
	private TTransport getTransport() throws Exception {
		TTransport tTransport = null;
		
		TSocket socket = new TSocket(host, port);
		if(transport_type.equals(Constant.TRANSPORT_BUFFERED)) {
			tTransport = socket;
		} else if(transport_type.equals(Constant.TRANSPORT_FRAMED)) {
			tTransport = new TFramedTransport(socket);
		} else if(transport_type.equals(Constant.TRANSPORT_FASTFRAMED)) {
			tTransport = new TFastFramedTransport(socket);
		} else if(transport_type.equals(Constant.TRANSPORT_HTTP)) {
			String url = "http://" + daemon.getHost() + ":" + daemon.getPort() + "/CmdService";
			tTransport = new THttpClient(url);
		}
		
		return tTransport;
	}
	
	private TProtocol getProtocol(TTransport tTransport) {
		TProtocol tProtocol = null;
		
		if (protocol_type.equals(Constant.PROTOCOL_JSON)) {
			tProtocol = new TJSONProtocol(tTransport);
		} else if (protocol_type.equals(Constant.PROTOCOL_COMPACT)) {
			tProtocol = new TCompactProtocol(tTransport);
		} else {
			tProtocol = new TBinaryProtocol(tTransport);
		}
		
		return tProtocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol_type() {
		return protocol_type;
	}

	public void setProtocol_type(String protocol_type) {
		this.protocol_type = protocol_type;
	}

	public String getTransport_type() {
		return transport_type;
	}

	public void setTransport_type(String transport_type) {
		this.transport_type = transport_type;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public String getServer_type() {
		return server_type;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}
}
