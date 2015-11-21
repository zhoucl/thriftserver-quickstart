package com.eboji.bootstrap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:spring-mybatis.xml"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DatabaseConfiguration {

}
