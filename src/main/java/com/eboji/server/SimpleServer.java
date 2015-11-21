package com.eboji.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleServer implements Server {
	private static final Logger logger = LoggerFactory.getLogger(SimpleServer.class);
	
	private static final Server simpleServer = new SimpleServer();
	
	private SimpleServer() { }
	
	public static final Server getInstance() {
		return simpleServer;
	}
	
	public TServer createServer(int port, int clientTimeout, TProtocolFactory tProtocolFactory,
			TTransportFactory tTransportFactory, TMultiplexedProcessor tMultiplexedProcessor) {
		logger.info("Starting the simple server...");
		
		TServer server = null;
		try {
			TServerTransport serverTransport = new TServerSocket(port, clientTimeout);
			TServer.Args args = new TServer.Args(serverTransport);
			
			args.protocolFactory(tProtocolFactory);
			args.transportFactory(tTransportFactory);
			args.processor(tMultiplexedProcessor);

			server = new TSimpleServer(args);
		} catch (TTransportException e) {
			logger.error("simple server initilized error!\n" + e.getMessage());
		}
		
		return server;
	}
	
	public void stopServer(TServer server) {
		server.stop();
		
		logger.info("simple server stopped success!");
	}
}
