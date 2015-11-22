package com.eboji.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolServer implements Server {
	private static final Logger logger = LoggerFactory
			.getLogger(ThreadPoolServer.class);

	private static final Server threadedPoolServer = new ThreadPoolServer();

	private ThreadPoolServer() {
	}

	public static final Server getInstance() {
		return threadedPoolServer;
	}
	
	@Override
	public TServer createServer(int port, int clientTimeout,
			TProtocolFactory tProtocolFactory,
			TTransportFactory tTransportFactory,
			TMultiplexedProcessor tMultiplexedProcessor) {
		logger.info("Starting the Threaded Pool server...");
		
		TServer server = null;
		try {
			TServerTransport serverSocket = new TServerSocket(port, clientTimeout);
			TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverSocket);

			args.protocolFactory(tProtocolFactory);
			args.processor(tMultiplexedProcessor);
			
			ExecutorService executorService = Executors.newFixedThreadPool(5);
			args.executorService(executorService);
			args.minWorkerThreads(10);
			args.maxWorkerThreads(20);

			server = new TThreadPoolServer(args);
		} catch (Exception e) {
			logger.error("Threaded Pool server initilized error!\n"
					+ e.getMessage());
		}
		
		return server;
	}

	@Override
	public void stopServer(TServer server) {
		server.stop();

		logger.info("Threaded Pool server stopped success!");
	}

}
