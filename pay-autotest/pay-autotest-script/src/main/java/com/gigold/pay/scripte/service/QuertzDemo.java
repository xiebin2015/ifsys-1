/**
 * Title: QuertzDemo.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.scripte.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title: QuertzDemo<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月15日上午9:44:47
 *
 */
public class QuertzDemo {

	/**
	 * Title: main<br/>
	 * Description: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月15日上午9:44:47
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String config[] = new String[] {
				
				"classpath*:spring/spring-quartz-config.xml"
				};
		ApplicationContext context = new ClassPathXmlApplicationContext(config);

	}

}
