package com.eboji.bootstrap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
	BeanConfiguration.class, 		//spring 初始化bean相关的配置
	DatabaseConfiguration.class		//数据库加载相关的配置(mybatis等)
})
public class SpringConfiguration {

}
