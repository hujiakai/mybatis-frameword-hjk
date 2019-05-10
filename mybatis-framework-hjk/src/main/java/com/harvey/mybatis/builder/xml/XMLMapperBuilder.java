package com.harvey.mybatis.builder.xml;

import java.io.InputStream;

import com.harvey.mybatis.session.Configuration;

/**
 * @author: harvey
 * @date: 2019年5月10日 下午6:50:46
 * @Description: TODO
 */
public class XMLMapperBuilder {
	
	private Configuration configuration;

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public XMLMapperBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	public void parse(InputStream inputStream) {
		
	}
	
	

}
