package com.harvey.mybatis.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.harvey.mybatis.mapping.MappedStatement;
import com.harvey.mybatis.session.Configuration;
import com.harvey.mybatis.sqlscript.BoundSql;
import com.harvey.mybatis.statement.DefaultResultSetHandler;
import com.harvey.mybatis.statement.PreparedStatementHandler;
import com.harvey.mybatis.statement.ResultHandler;
import com.harvey.mybatis.statement.StatementHandler;

public class SimpleExecutor implements Executor {
	
	private Configuration configuration;
	
	public SimpleExecutor(Configuration configuration) {
		this.configuration = configuration;
	}

	public <E> List<E> Query(MappedStatement mappedStatement, Object parameter) {
		BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql();
		Connection connection = this.getConnection();
		String statementType = mappedStatement.getStatementType();
		if(null == statementType || "PREPARED".equalsIgnoreCase(statementType)) {
			try {
				StatementHandler handler = new PreparedStatementHandler(boundSql, connection, parameter);
				//创建PreparedStatement
				PreparedStatement preparedStatement = (PreparedStatement) handler.prepare();
				//设置参数
				handler.parameterize(preparedStatement);
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getResultSet();
				ResultHandler resultHandler = new DefaultResultSetHandler(resultSet, mappedStatement.getResultTypeClass());
				return resultHandler.handleResultSets();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	private Connection getConnection() {
		DataSource dataSource = configuration.getDataSource();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	

}
