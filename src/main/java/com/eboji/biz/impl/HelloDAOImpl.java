package com.eboji.biz.impl;

import org.springframework.stereotype.Component;

import com.eboji.biz.HelloDAO;

@Component("helloDAO")
public class HelloDAOImpl implements HelloDAO {
	@Override
	public String hello() {
		return "hello";
	}
}
