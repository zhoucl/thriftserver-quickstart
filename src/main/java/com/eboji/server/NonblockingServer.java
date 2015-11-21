package com.eboji.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonblockingServer implements Server {
	private static final Logger logger = LoggerFactory
			.getLogger(NonblockingServer.class);

	private static final Server nonBlockingServer = new NonblockingServer();

	private NonblockingServer() {
	}

	public static final Server getInstance() {
		return nonBlockingServer;
	}

	@Override
	public TServer createServer(int port, int clientTimeout,
			TProtocolFactory tProtocolFactory,
			TTransportFactory tTransportFactory,
			TMultiplexedProcessor tMultiplexedProcessor) {
		logger.info("Starting the non blocking server...");

		TServer server = null;
		try {
			TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(
					port, clientTimeout);
			TNonblockingServer.Args args = new TNonblockingServer.Args(
					serverSocket);

			args.protocolFactory(tProtocolFactory);
			args.transportFactory(tTransportFactory);
			args.processor(tMultiplexedProcessor);

			server = new TNonblockingServer(args);
		} catch (Exception e) {
			logger.error("Nonblocking server initilized error!\n"
					+ e.getMessage());
		}

		return server;
	}

	@Override
	public void stopServer(TServer server) {
		server.stop();

		logger.info("Nonblocking server stopped success!");
	}

}
