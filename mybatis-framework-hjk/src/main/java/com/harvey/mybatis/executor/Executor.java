package com.harvey.mybatis.executor;

import java.util.List;


import com.harvey.mybatis.mapping.MappedStatement;

public interface Executor {
	
	<E> List<E> Query(MappedStatement mappedStatement, Object parameter);

}
