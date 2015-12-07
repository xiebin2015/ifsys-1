/**
 * Title: MailTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.service.InterFaceFieldService;
import com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool;
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

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*Beans.xml");
		IfsysCheckThreadPool pool = (IfsysCheckThreadPool) SpringContextHolder.getBean(IfsysCheckThreadPool.class);
		long ss = System.currentTimeMillis();
		pool.execure();
		// 获取总的执行时间
		System.out.println(System.currentTimeMillis() - ss);
		System.out.println("成功！");

	}

}
