/**
 * Title: Test.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.script;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.InterFaceField;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.bo.ReturnCode;
import com.gigold.pay.autotest.email.MailSenderService;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.InterFaceFieldService;
import com.gigold.pay.autotest.service.InterFaceService;
import com.gigold.pay.autotest.service.RetrunCodeService;
import com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.util.common.StringUtil;
import com.github.pagehelper.PageInfo;

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
	private InterFaceService interFaceService;
	private RetrunCodeService retrunCodeService;
	private InterFaceFieldService interFaceFieldService;

	@Before
	public void setup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*Beans.xml");
		ifsysCheckThreadPool = (IfsysCheckThreadPool) SpringContextHolder.getBean(IfsysCheckThreadPool.class);
		mailSenderService = (MailSenderService) SpringContextHolder.getBean(MailSenderService.class);
		ifSysMockService = (IfSysMockService) SpringContextHolder.getBean(IfSysMockService.class);
		interFaceService = (InterFaceService) SpringContextHolder.getBean(InterFaceService.class);
		retrunCodeService = (RetrunCodeService) SpringContextHolder.getBean(RetrunCodeService.class);
		interFaceFieldService = (InterFaceFieldService) SpringContextHolder.getBean(InterFaceFieldService.class);
		// 初始化测试数据
		initData();
	}

	/**
	 * 
	 * Title: initData<br/>
	 * Description: 初始化测试数据 如果测试数据表中有对应返回码的测试数据则不添加 如果测试数据表中没有对应返回码的测试数据
	 * 则新增一条对应的测试数据
	 * 
	 * <br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月9日上午11:57:21
	 *
	 */
	public void initData() {
		ifsysCheckThreadPool.initMock();
	}

	@Test
	public void testAutoTest() {
		// ResulteData resulteData = ifsysCheckThreadPool.execute();
		// ifsysCheckThreadPool.execute();
	}

	@After
	/**
	 * 
	 * Title: testSendMail<br/>
	 * Description: 测试完成之后再发邮件的情况<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月7日下午4:27:30
	 *
	 */
	public void testSendMail() {
		// // List<IfSysMock> resulteMocks =
		// // ifSysMockService.filterMocksByFailed(); // 返回没通过测试的结果
		// List<IfSysMock> resulteMocks =
		// ifSysMockService.filterAllTestedMocks(); // 返回所有测试过的结果
		// for (int i = 0; i < resulteMocks.size(); i++) {
		// System.out.println(resulteMocks.get(i).getRealRspCode());
		// }
		//
		// List<String> addressTo = new ArrayList<String>();
		// // addressTo.add("xiebin163126@163.com");
		// addressTo.add("chenkuan@gigold.com");
		// // 设置收件人地址
		// mailSenderService.setTo(addressTo);
		// // 设置标题
		// mailSenderService.setSubject("来自独孤九剑接口自动化测试的邮件");
		// // 设置模版名
		// mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
		//
		// Map model = new HashMap();
		// model.put("resulteMocks", resulteMocks);
		// model.put("username", "陈宽");
		// // model.put("sys", "独孤九剑");
		// // model.put("pro", "产品1");
		// // model.put("interFace", "登录接口");
		// mailSenderService.sendWithTemplateForHTML(model);
		System.out.println("邮件发送成功！");
	}
}
