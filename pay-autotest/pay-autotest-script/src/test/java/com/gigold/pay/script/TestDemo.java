/**
 * Title: Test.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.script;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.service.IfSysMockService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gigold.pay.autotest.email.MailSenderService;
import com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool;
import com.gigold.pay.framework.base.SpringContextHolder;
import java.util.*;
/**
 * Title: Test<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月5日下午3:37:06
 *
 */
public class TestDemo {

	private IfsysCheckThreadPool ifsysCheckThreadPool;
	private MailSenderService mailSenderService;
	private IfSysMockService ifSysMockService;


	@Before
	public void setup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*Beans.xml");
		ifsysCheckThreadPool = (IfsysCheckThreadPool) SpringContextHolder.getBean(IfsysCheckThreadPool.class);
		mailSenderService = (MailSenderService) SpringContextHolder.getBean(MailSenderService.class);
		ifSysMockService = (IfSysMockService) SpringContextHolder.getBean(IfSysMockService.class);
	}

	@Test
	public void testAutoTest() {
//		ResulteData resulteData = ifsysCheckThreadPool.execure();
		ifsysCheckThreadPool.execure();
	}

	@After
	/**
	 * 
	 * Title: testSendMail<br/>
	 * Description: 测试完成之后再发邮件的情况<br/>
	 * @author xiebin
	 * @date 2015年12月7日下午4:27:30
	 *
	 */
	public void testSendMail() {
		//List<IfSysMock> resulteMocks = ifSysMockService.filterMocksByFailed(); // 返回没通过测试的结果

		List<IfSysMock> resulteMocks = ifSysMockService.filterAllTestedMocks(); // 返回所有测试过的结果
		for(int i=0;i<resulteMocks.size();i++){
			System.out.println(resulteMocks.get(i).getIfName());
		}
//		 List<String> addressTo = new ArrayList<String>();
//		 // addressTo.add("xiebin163126@163.com");
//		 addressTo.add("xiebin@gigold.com");
//		 // 设置收件人地址
//		 mailSenderService.setTo(addressTo);
//		 // 设置标题
//		 mailSenderService.setSubject("来自独孤九剑接口自动化测试的邮件");
//		 // 设置模版名
//		 mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
//		 Map model = new HashMap();
//		 model.put("username", "zhansan");
//		 model.put("sys", "独孤九剑");
//		 model.put("pro", "产品1");
//		 model.put("interFace", "登录接口");
//		 mailSenderService.sendWithTemplateForHTML(model);
//		 System.out.println("邮件发送成功！");
	}
}
