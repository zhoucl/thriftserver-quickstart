package com.eboji.util;

/**
 * 常量工具类
 * @author zhoucl
 */
public class Constant {
	/**
	 * 指定扫描对外提供接口的包名
	 */
	public static final String THRIFT_SERVICE_PKG = "com.eboji.thrift.service";
	
	/**
	 * TTransport的类型常量
	 */
	public static final String TRANSPORT_BUFFERED = "buffered";
	
	public static final String TRANSPORT_FRAMED = "framed";
	
	public static final String TRANSPORT_FASTFRAMED = "fastframed";
	
	public static final String TRANSPORT_ZLIB = "zlib";
	
	public static final String TRANSPORT_HTTP = "http";
	
	/**
	 * TProtocol的类型常量
	 */
	public static final String PROTOCOL_JSON = "json";
	
	public static final String PROTOCOL_COMPACT = "compact";
	
	public static final String PROTOCOL_BINARY = "binary";
	
	/**
	 * Thrift服务的类型常量
	 */
	public static final String SERVER_SIMPLE = "simple";
	
	public static final String SERVER_NONBLOCKING = "nonblocking";
	
	public static final String SERVER_HSHA = "hsha";
	
	public static final String SERVER_THREADSELECTOR = "threaded-selector";
	
	public static final String SERVER_THREADPOOL = "thread-pool";
}
