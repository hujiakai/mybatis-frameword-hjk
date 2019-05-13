package com.harvey.mybatis.statement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler implements ResultHandler {

	private ResultSet resultSet;

	private Class<?> resultTypeClass;

	public DefaultResultSetHandler(ResultSet resultSet, Class<?> resultTypeClass) {
		this.resultSet = resultSet;
		this.resultTypeClass = resultTypeClass;
	}

	//把结果集映射成对象
	public List<Object> handleResultSets() throws SQLException {
		List<Object> result = new ArrayList<Object>();
		try {
			while (resultSet.next()) {
				Object obj = resultTypeClass.newInstance();
				ResultSetMetaData metaData = resultSet.getMetaData();
				int count = metaData.getColumnCount();
				for (int i = 1; i < count; i++) {
					String columnName = metaData.getColumnName(i);
					Field field = resultTypeClass.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, resultSet.getObject(i));
				}
				result.add(obj);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
