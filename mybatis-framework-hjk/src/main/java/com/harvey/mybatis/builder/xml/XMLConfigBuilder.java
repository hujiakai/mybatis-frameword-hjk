package com.harvey.mybatis.builder.xml;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;

import com.harvey.mybatis.parsing.DocumentReader;
import com.harvey.mybatis.session.Configuration;

/**
 * 
 * @author Administrator
 *
 */
public class XMLConfigBuilder {
	
	private Configuration configuration;
	
	
	public XMLConfigBuilder(Configuration configuration) {
		this.configuration = configuration;
	}


	
	public Configuration parse(InputStream inputStream) {
		//创建document对象（全局配置文件）
		DocumentReader documentReader = new DocumentReader();
		Document document = documentReader.createDocument(inputStream);
		
		
		//解析document，封装到configuration
		parseConfiguration(document.getRootElement());
		
		
		return configuration;
	}


	//解析全局配置文件的根节点configuration
	private void parseConfiguration(Element rootElement) {
		
		//解析全局配置文件中environments标签
		parseEnvironmentsElement(rootElement.element("environments"));
		
		//解析全局配置文件中mappers标签
		parseMappersElement(rootElement.element("mappers"));
	}


	//解析全局配置文件中environments标签
	@SuppressWarnings("unchecked")
	private void parseEnvironmentsElement(Element element) {
		String environmentDefaultIdVal = element.attributeValue("default");
		
		//获取所有environment标签
		List<Element> environmentElements = element.elements();
		for(Element environmentEle : environmentElements) {
			String envId = environmentEle.attributeValue("id");
			if(environmentDefaultIdVal.equals(envId)) {
				createDataSource(environmentEle);
			}
		}
	}


	//解析environment标签，创建datasource
	@SuppressWarnings("unchecked")
	private void createDataSource(Element element) {
		Element dataSourceElement = element.element("dataSource");
		String dataSourceType = element.attributeValue("type");
		
		Properties properties = new Properties();
		List<Element> propertyElements = dataSourceElement.elements("property");
		for(Element propertyEle : propertyElements) {
			String name = propertyEle.attributeValue("name");
			String value = propertyEle.attributeValue("value");
			properties.setProperty(name, value);
		}
		
		if("DBCP".equals(dataSourceType)) {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));
			configuration.setDataSource(dataSource);
		}
	}
	
	

}
