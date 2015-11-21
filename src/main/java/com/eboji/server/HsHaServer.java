package com.eboji.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HsHaServer implements Server {
	private static final Logger logger = LoggerFactory
			.getLogger(HsHaServer.class);

	private static final Server hsHaServer = new HsHaServer();

	private HsHaServer() {
	}

	public static final Server getInstance() {
		return hsHaServer;
	}

	@Override
	public TServer createServer(int port, int clientTimeout,
			TProtocolFactory tProtocolFactory,
			TTransportFactory tTransportFactory,
			TMultiplexedProcessor tMultiplexedProcessor) {
		logger.info("Starting the Hsha server...");

		TServer server = null;
		try {
			TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(
					port, clientTimeout);
			THsHaServer.Args args = new THsHaServer.Args(serverSocket);

			args.protocolFactory(tProtocolFactory);
			args.transportFactory(tTransportFactory);
			args.processor(tMultiplexedProcessor);

			server = new THsHaServer(args);
		} catch (Exception e) {
			logger.error("Hsha server initilized error!\n" + e.getMessage());
		}

		return server;
	}

	@Override
	public void stopServer(TServer server) {
		server.stop();

		logger.info("Hsha server stopped success!");
	}

}
