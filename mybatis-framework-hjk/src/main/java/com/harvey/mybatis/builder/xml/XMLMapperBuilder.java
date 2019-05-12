package com.harvey.mybatis.builder.xml;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.harvey.mybatis.mapping.MappedStatement;
import com.harvey.mybatis.parsing.DocumentReader;
import com.harvey.mybatis.session.Configuration;
import com.harvey.mybatis.sqlscript.DefaultSqlSouce;
import com.harvey.mybatis.sqlscript.SqlSource;

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

	//解析mapper文件
	public void parse(InputStream inputStream) {
		//通过输入流生成document对象
		DocumentReader documentReader = new DocumentReader();
		Document document = documentReader.createDocument(inputStream);
		
		//解析根标签（mapper标签）
		parseMapperElement(document.getRootElement());
	}

	//解析mapper标签内容
	private void parseMapperElement(Element rootElement) {
		//获取命名空间
		String namespace = rootElement.attributeValue("namespace");
		
		List<Element> selectElements = rootElement.elements("select");
		if(null != selectElements && selectElements.size() > 0){
			for(Element selectEle : selectElements) {
				parseStatement(namespace, selectEle);
			}
		}
	}

	private void parseStatement(String namespace, Element element) {
		String id = element.attributeValue("id");
		
		String parameterType = element.attributeValue("parameterType");
		Class<?> parameterTypeClass = getTypeClass(parameterType);
		
		String resultType = element.attributeValue("resultType");
		Class<?> resultTypeClass = getTypeClass(resultType);
		
		String statementType = element.attributeValue("statementType");
		
		//获取标签内未解析的sql
		String sqlTxt = element.getTextTrim();
		SqlSource sqlSource = new DefaultSqlSouce(sqlTxt, parameterTypeClass);
		
		MappedStatement mappedStatement = new MappedStatement(id, parameterTypeClass, resultTypeClass, statementType, sqlSource);
		configuration.addMappedStatements(namespace + "." + id, mappedStatement);
	}

	private Class<?> getTypeClass(String type) {
		try {
			Class<?> clazz = Class.forName(type);
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
