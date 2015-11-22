package com.eboji.thrift.service.impl;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eboji.annotation.ThriftHandler;
import com.eboji.service.HelloService;
import com.eboji.thrift.service.HelloWorldService.Iface;

@Component("HelloWorldService")
@ThriftHandler("HelloWorldService")
public class HelloWorldServiceImpl implements Iface {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);
	
	@Autowired
	HelloService helloService;
	
	@Override
	public int add(int a, int b) throws TException {
		int ret = a + b;
		logger.info(a + "+" + b + "=" + ret);
		return  ret;
	}

	@Override
	public int sub(int a, int b) throws TException {
		int ret = a - b;
		logger.info(a + "-" + b + "=" + ret);
		return  ret;
	}

}
