package com.harvey.mybatis.sqlscript;

public class GenericTokenParser {
	
	private String openToken;
	
	private String closeToken;
	
	private TokenHandler tokenHandler;

	public GenericTokenParser(String openToken, String closeToken, TokenHandler tokenHandler) {
		this.openToken = openToken;
		this.closeToken = closeToken;
		this.tokenHandler = tokenHandler;
	}

	//解析${}和#{}
	public String parse(String sqlTxt) {

	    if (sqlTxt == null || sqlTxt.isEmpty()) {
	      return "";
	    }
	    // search open token
	    int start = sqlTxt.indexOf(openToken, 0);
	    if (start == -1) {
	      return sqlTxt;
	    }
	    char[] src = sqlTxt.toCharArray();
	    int offset = 0;
	    final StringBuilder builder = new StringBuilder();
	    StringBuilder expression = null;
	    while (start > -1) {
	      if (start > 0 && src[start - 1] == '\\') {
	        // this open token is escaped. remove the backslash and continue.
	        builder.append(src, offset, start - offset - 1).append(openToken);
	        offset = start + openToken.length();
	      } else {
	        // found open token. let's search close token.
	        if (expression == null) {
	          expression = new StringBuilder();
	        } else {
	          expression.setLength(0);
	        }
	        builder.append(src, offset, start - offset);
	        offset = start + openToken.length();
	        int end = sqlTxt.indexOf(closeToken, offset);
	        while (end > -1) {
	          if (end > offset && src[end - 1] == '\\') {
	            // this close token is escaped. remove the backslash and continue.
	            expression.append(src, offset, end - offset - 1).append(closeToken);
	            offset = end + closeToken.length();
	            end = sqlTxt.indexOf(closeToken, offset);
	          } else {
	            expression.append(src, offset, end - offset);
	            offset = end + closeToken.length();
	            break;
	          }
	        }
	        if (end == -1) {
	          // close token was not found.
	          builder.append(src, start, src.length - start);
	          offset = src.length;
	        } else {
	          builder.append(tokenHandler.handleToken(expression.toString()));
	          offset = end + closeToken.length();
	        }
	      }
	      start = sqlTxt.indexOf(openToken, offset);
	    }
	    if (offset < src.length) {
	      builder.append(src, offset, src.length - offset);
	    }
	    return builder.toString();
	  
		
	}
	
	

}
