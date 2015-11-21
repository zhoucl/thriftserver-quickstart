package com.eboji.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TTransportFactory;

public interface Server {
	TServer createServer(int port, int clientTimeout, TProtocolFactory tProtocolFactory,
			TTransportFactory tTransportFactory, TMultiplexedProcessor tMultiplexedProcessor);
	
	public void stopServer(TServer server);
}
