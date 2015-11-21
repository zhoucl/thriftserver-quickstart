package com.eboji;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.eboji.thrift.service.HelloWorldService;

public class Client {
	public static void main(String[] args) {
		try {
//			TSocket socket = new TSocket("192.168.4.155", 9090);
			TSocket socket = new TSocket("localhost", 9090);
			socket.setSocketTimeout(20000);
//			TTransport transport = new TFramedTransport(socket);
//			TTransport transport = new TFastFramedTransport(socket);
			TTransport transport = socket;
			TProtocol tProtocol = new TBinaryProtocol(transport);
			TMultiplexedProtocol tMultiplexedProtocol = new TMultiplexedProtocol(tProtocol, "HelloWorldService");
			HelloWorldService.Client client = new HelloWorldService.Client(tMultiplexedProtocol);
			transport.open();
			for(int i = 0; i < 100; i++) {
				System.out.println(client.add(10, 20));
			}
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
