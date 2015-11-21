package com.eboji.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eboji.biz.HelloDAO;
import com.eboji.dao.HomeMapper;
import com.eboji.pojo.Home;
import com.eboji.pojo.HomeExample;
import com.eboji.service.HelloService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Service("helloService")
public class HelloServiceImpl implements HelloService {
	private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
	
	@Autowired
	private HelloDAO helloDAO;
	
	@Autowired
	private HomeMapper homeMapper;
	
	@Override
	public String hello() {
		HomeExample he = new HomeExample();
		he.createCriteria().andIdEqualTo(1);
		List<Home> home = homeMapper.selectByExample(he, new PageBounds());
		logger.info(home != null ? (home.size() > 0 ? home.get(0).getHomedesc() : "") : "none");
		return helloDAO.hello();
	}
}
