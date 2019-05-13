package com.harvey.mybatis.session;

import java.io.InputStream;

import com.harvey.mybatis.builder.xml.XMLConfigBuilder;

public class SqlSessionFactoryBuilder {
	
	public SqlSessionFactory build(InputStream inputStream) {
		XMLConfigBuilder builder = new XMLConfigBuilder();
		Configuration configuration = builder.parse(inputStream);
		SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
		return sqlSessionFactory;
	}
	

}
