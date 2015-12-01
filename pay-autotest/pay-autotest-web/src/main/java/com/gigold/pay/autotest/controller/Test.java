/**
 * Title: MailTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.framework.base.SpringContextHolder;

/**
 * Title: Test<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月25日下午4:11:06
 *
 */
public class Test {

	
	/**
	 * Title: main<br/>
	 * Description: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月25日下午4:11:06
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/spring/*Beans.xml");
		IfSysMockService ss=(IfSysMockService)SpringContextHolder.getBean("ifSysMockService");
		//IfSysMockService ss = (IfSysMockService) context.getBean("ifSysMockService");
		List list=ss.getIfSysMock();
		
		
		
		
		System.out.println(list.size());

		System.out.println("成功！");

	}

}
