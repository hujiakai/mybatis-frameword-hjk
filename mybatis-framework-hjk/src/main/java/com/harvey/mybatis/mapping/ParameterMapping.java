package com.harvey.mybatis.mapping;

public class ParameterMapping {
	
	private String parameterName;
	
	private Class<?> parameterTypeClass;

	public ParameterMapping(String parameterName, Class<?> parameterTypeClass) {
		super();
		this.parameterName = parameterName;
		this.parameterTypeClass = parameterTypeClass;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}

	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}
	
}
