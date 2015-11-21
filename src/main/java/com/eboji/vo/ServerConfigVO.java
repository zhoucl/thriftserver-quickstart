package com.eboji.vo;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TTransportFactory;

/**
 * Thrift服务的配置业务对象类
 * @author zhoucl
 *
 */
public class ServerConfigVO {
	/**
	 * 端口
	 */
	private int port;
	
	/**
	 * 超时时间
	 */
	private int clientTimeout;
	
	/**
	 * {@link TProtocolFactory}
	 */
	private TProtocolFactory tProtocolFactory;
	
	/**
	 * {@link TTransportFactory}
	 */
	private TTransportFactory tTransportFactory;
	
	/**
	 * {@link TMultiplexedProcessor}
	 */
	private TMultiplexedProcessor tMultiplexedProcessor;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getClientTimeout() {
		return clientTimeout;
	}
	public void setClientTimeout(int clientTimeout) {
		this.clientTimeout = clientTimeout;
	}
	public TProtocolFactory gettProtocolFactory() {
		return tProtocolFactory;
	}
	public void settProtocolFactory(TProtocolFactory tProtocolFactory) {
		this.tProtocolFactory = tProtocolFactory;
	}
	public TTransportFactory gettTransportFactory() {
		return tTransportFactory;
	}
	public void settTransportFactory(TTransportFactory tTransportFactory) {
		this.tTransportFactory = tTransportFactory;
	}
	public TMultiplexedProcessor gettMultiplexedProcessor() {
		return tMultiplexedProcessor;
	}
	public void settMultiplexedProcessor(TMultiplexedProcessor tMultiplexedProcessor) {
		this.tMultiplexedProcessor = tMultiplexedProcessor;
	}
}
