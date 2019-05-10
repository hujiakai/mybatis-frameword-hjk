/**
 * @author: harvey
 * @date: 2019年5月10日 下午4:10:17
 * @Description: TODO
 */
package com.harvey.mybatis.parsing;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * @author: harvey
 * @date: 2019年5月10日 下午4:10:17
 * @Description: TODO
 *
 */
public class DocumentReader {
	
	public Document createDocument(InputStream inputStream) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(inputStream);
			return document;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
