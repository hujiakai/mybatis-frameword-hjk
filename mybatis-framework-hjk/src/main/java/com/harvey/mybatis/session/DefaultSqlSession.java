package com.harvey.mybatis.session;

import java.util.List;

import com.harvey.mybatis.executor.Executor;
import com.harvey.mybatis.mapping.MappedStatement;

public class DefaultSqlSession implements SqlSession {
	
	private Configuration configuration;
	
	private Executor executor;
	
	public DefaultSqlSession(Configuration configuration, Executor executor) {
		super();
		this.configuration = configuration;
		this.executor = executor;
	}



	public <T> T selectOne(String statement, Object parameter) throws Exception {
		List<T> list = this.selectList(statement, parameter);
		if(null != list) {
			if(list.size() == 1) {
				return list.get(0);
			}else {
				throw new Exception("Expected one result, but found: " + list.size());
			}
		}else {
			return null;
		}
		
	}

	private <E> List<E> selectList(String statement, Object parameter) {
		MappedStatement mappedStatement = configuration.getMappedStatements(statement);
		return executor.Query(mappedStatement, parameter);
	}

}
