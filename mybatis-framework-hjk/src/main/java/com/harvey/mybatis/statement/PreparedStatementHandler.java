package com.harvey.mybatis.statement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.harvey.mybatis.mapping.ParameterMapping;
import com.harvey.mybatis.sqlscript.BoundSql;

public class PreparedStatementHandler implements StatementHandler {
	
	private BoundSql boundSql;
	
	private Connection connection;
	
	private Object parameterObject;
	
	private List<Class<?>> baiscDataTypeClasses = new ArrayList<Class<?>>() {
		{
			add(Integer.class);
			add(Byte.class);
			add(Short.class);
			add(Long.class);
			add(Double.class);
			add(Float.class);
			add(Date.class);
			add(String.class);
		}
	};
	

	public PreparedStatementHandler(BoundSql boundSql, Connection connection, Object parameterObject) {
		this.boundSql = boundSql;
		this.connection = connection;
		this.parameterObject = parameterObject;
	}


	@Override
	public Statement prepare() throws SQLException {
		String sql = boundSql.getSql();
		// 预编译
		Statement statement = connection.prepareStatement(sql);
		return statement;
	}


	//设置参数
	@Override
	public void parameterize(PreparedStatement statement) throws SQLException{
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if(null != parameterMappings) {
			for(int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				//映射文件中参数类型（parameterType）
				Class<?> parameterTypeClass = parameterMapping.getParameterTypeClass();
				//参数名称
				String parameterName = parameterMapping.getParameterName();
				
				//如果参数类型为基本数据类型则直接设置值，否则根据参数名匹配
				if(baiscDataTypeClasses.contains(parameterTypeClass)) {
					statement.setObject(i + 1, parameterObject);
				}else {
					try {
						Field field = parameterTypeClass.getDeclaredField(parameterName);
						field.setAccessible(true);
						Object value = field.get(parameterObject);
						statement.setObject(i + 1, value);
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}

}
