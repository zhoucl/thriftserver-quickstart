package com.eboji.thrift.service.impl;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.eboji.annotation.ThriftHandler;
import com.eboji.thrift.service.CmdService.Iface;

/**
 * Thrift服务提供的默认接口类实现
 * @author zhoucl
 */
@Component("CmdService")
@ThriftHandler("CmdService")
public class CmdServiceImpl implements Iface {
	private static final Logger logger = LoggerFactory.getLogger(CmdServiceImpl.class);
	
	/**
	 * 停止Thrift服务
	 */
	@Override
	public void stop() throws TException {
		logger.info("Server is stopping...");
		System.exit(0);
	}

}
