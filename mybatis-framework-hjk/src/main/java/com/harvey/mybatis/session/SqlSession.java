package com.harvey.mybatis.session;

public interface SqlSession {
	
	<T> T selectOne(String statement, Object parameter) throws Exception;

}
