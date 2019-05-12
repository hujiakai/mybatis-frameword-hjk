package com.harvey.mybatis.session;

import com.harvey.mybatis.executor.Executor;
import com.harvey.mybatis.executor.SimpleExecutor;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
	
	private Configuration configuration;

	public DefaultSqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
	}



	public SqlSession openSession() {
		SqlSession sqlSession = openSqlSeesionFromDataSource();
		return sqlSession;
	}

	private SqlSession openSqlSeesionFromDataSource() {
		Executor executor = new SimpleExecutor(configuration);
		return new DefaultSqlSession(configuration, executor);
	}

}
