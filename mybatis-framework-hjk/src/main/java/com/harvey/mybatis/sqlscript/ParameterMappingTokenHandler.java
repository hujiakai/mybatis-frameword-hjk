package com.harvey.mybatis.sqlscript;

import java.util.ArrayList;
import java.util.List;

import com.harvey.mybatis.mapping.ParameterMapping;

public class ParameterMappingTokenHandler implements TokenHandler {
	
	private Class<?> parameterTypeClass;
	
	private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

	public ParameterMappingTokenHandler(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

	public String handleToken(String content) {
		ParameterMapping parameterMapping = new ParameterMapping(content, parameterTypeClass);
		parameterMappings.add(parameterMapping);
		return "?";
	}
	public Class<?> getParameterTypeClass() {
		return parameterTypeClass;
	}

	public void setParameterTypeClass(Class<?> parameterTypeClass) {
		this.parameterTypeClass = parameterTypeClass;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}
	
}
