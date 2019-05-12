package com.harvey.mybatis.sqlscript;

public class DefaultSqlSouce implements SqlSource {
	
	private String sqlTxt;
	
	private Class<?> parameterTypeClass;
	
	public DefaultSqlSouce(String sqlTxt, Class<?> parameterTypeClass) {
		this.sqlTxt = sqlTxt;
		this.parameterTypeClass = parameterTypeClass;
	}

	public BoundSql getBoundSql() {
		ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler(parameterTypeClass);
		GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
		// 获取解析之后的sql语句
		String sql = tokenParser.parse(sqlTxt);
		return new BoundSql(sql, tokenHandler.getParameterMappings());
	}

}
