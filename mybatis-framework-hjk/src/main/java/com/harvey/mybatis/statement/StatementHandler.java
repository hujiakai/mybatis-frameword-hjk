package com.harvey.mybatis.statement;

import java.sql.SQLException;
import java.sql.Statement;


import java.sql.PreparedStatement;

public interface StatementHandler {
	
	Statement prepare() throws SQLException;
	
	void parameterize(PreparedStatement statement) throws SQLException;
	
}
