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
		//默认用SimpleExecutor执行器, 可以根据全局配置文件去生成不同的执行器
		Executor executor = new SimpleExecutor(configuration);
		return new DefaultSqlSession(configuration, executor);
	}

}
