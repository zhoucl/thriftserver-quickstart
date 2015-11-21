package com.eboji.thrift.service.impl;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eboji.annotation.ThriftHandler;
import com.eboji.service.HelloService;
import com.eboji.thrift.service.HelloWorldService.Iface;

@Component("HelloWorldService")
@ThriftHandler("HelloWorldService")
public class HelloWorldServiceImpl implements Iface {
	@Autowired
	HelloService helloService;
	
	@Override
	public int add(int a, int b) throws TException {
		System.out.println("----add---" + helloService.hello());
		System.out.println("----add---");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return  a + b;
	}

	@Override
	public int sub(int a, int b) throws TException {
		System.out.println("----sub---");
		System.out.println("----sub---");
		return a - b;
	}

}
