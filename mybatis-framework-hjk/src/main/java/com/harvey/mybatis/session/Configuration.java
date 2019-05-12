package com.harvey.mybatis.session;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.harvey.mybatis.mapping.MappedStatement;

public class Configuration {
	
	private DataSource dataSource;
	
	private Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public MappedStatement getMappedStatements(String statement) {
		return mappedStatements.get(statement);
	}

	public void addMappedStatements(String statementId, MappedStatement mappedStatement) {
		this.mappedStatements.put(statementId, mappedStatement);
	}
	
	

}
