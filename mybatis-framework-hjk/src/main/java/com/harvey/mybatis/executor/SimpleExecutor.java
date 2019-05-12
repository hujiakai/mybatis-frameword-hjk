package com.harvey.mybatis.executor;

import java.util.List;

import com.harvey.mybatis.mapping.MappedStatement;
import com.harvey.mybatis.session.Configuration;
import com.harvey.mybatis.sqlscript.BoundSql;

public class SimpleExecutor implements Executor {
	
	private Configuration configuration;

	public SimpleExecutor(Configuration configuration) {
		this.configuration = configuration;
	}

	public <E> List<E> Query(MappedStatement mappedStatement, Object parameter) {
		BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql();
		return null;
	}
	
	

}
