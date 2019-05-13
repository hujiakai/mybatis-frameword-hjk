package com.harvey.mybatis.statement;

import java.sql.SQLException;
import java.util.List;

public interface ResultHandler {
	
	public <E> List<E> handleResultSets() throws SQLException;

}
