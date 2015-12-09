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
		initMock();
	}

	/**
	 * 
	 * Title: initMock<br/>
	 * Description: 初始化测试数据<br/>
	 * 
	 * @author xiebin
	 * @date 2015年12月9日上午11:57:21
	 *
	 */
	public void initMock() {
		// 当前页
		int curPageNum = 1;
		// 总页数
		int pages = 1;
		// 分页获取接口信息
		while (curPageNum <= pages) {
			PageInfo<InterFaceInfo> pageInfo = interFaceService.getAllIfSys(curPageNum);
			List<InterFaceInfo> ifsyslist = pageInfo.getList();
			// 遍历接口信息 获取其对应的返回码信息
			for (InterFaceInfo interFaceInfo : ifsyslist) {
				// 获取接口请求字段的JSON展示字符串
				InterFaceField interFaceField = (InterFaceField) SpringContextHolder.getBean(InterFaceField.class);
				interFaceField.setIfId(interFaceInfo.getId());
				interFaceField.setFieldFlag("1");
				String reqJson = interFaceFieldService.getJsonStr(interFaceField);
				if (StringUtil.isNotBlank(reqJson)) {
					interFaceInfo.setReqJsonStr(reqJson);
				}
				// 获取接口响应字段的JSON展示字符串
				interFaceField.setFieldFlag("2");
				String rspJson = interFaceFieldService.getJsonStr(interFaceField);
				if (StringUtil.isNotBlank(rspJson)) {
					interFaceInfo.setRspJsonStr(rspJson);
				}
				
				List<ReturnCode> returnList = retrunCodeService.getReturnCodeByIfId(interFaceInfo.getId());
				// 遍历返回码 更新测试数据
				for (ReturnCode rscdObj : returnList) {
					IfSysMock ifSysMock = (IfSysMock) SpringContextHolder.getBean(IfSysMock.class);
					ifSysMock.setIfId(interFaceInfo.getId());
					ifSysMock.setRspCodeId(rscdObj.getId());
					ifSysMock.setRequestJson(interFaceInfo.getReqJsonStr());
					ifSysMock.setResponseJson(interFaceInfo.getRspJsonStr());
					ifSysMockService.createIfSysMock(ifSysMock);
				}

			}
			pages = pageInfo.getPages();
			curPageNum++;
		}
	}

	@Test
	public void testAutoTest() {
		// ResulteData resulteData = ifsysCheckThreadPool.execure();
		//ifsysCheckThreadPool.execure();
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
//		// List<IfSysMock> resulteMocks =
//		// ifSysMockService.filterMocksByFailed(); // 返回没通过测试的结果
//		List<IfSysMock> resulteMocks = ifSysMockService.filterAllTestedMocks(); // 返回所有测试过的结果
//		for (int i = 0; i < resulteMocks.size(); i++) {
//			System.out.println(resulteMocks.get(i).getRealRspCode());
//		}
//
//		List<String> addressTo = new ArrayList<String>();
//		// addressTo.add("xiebin163126@163.com");
//		addressTo.add("chenkuan@gigold.com");
//		// 设置收件人地址
//		mailSenderService.setTo(addressTo);
//		// 设置标题
//		mailSenderService.setSubject("来自独孤九剑接口自动化测试的邮件");
//		// 设置模版名
//		mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
//
//		Map model = new HashMap();
//		model.put("resulteMocks", resulteMocks);
//		model.put("username", "陈宽");
//		// model.put("sys", "独孤九剑");
//		// model.put("pro", "产品1");
//		// model.put("interFace", "登录接口");
//		mailSenderService.sendWithTemplateForHTML(model);
		System.out.println("邮件发送成功！");
	}
}
