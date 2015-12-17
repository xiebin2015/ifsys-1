/**
 * Title: IfSysAutoTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.scripte.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.email.MailSenderService;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.IfSysStuffService;
import com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.Domain;

/**
 * Title: IfSysAutoTest<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年12月15日上午9:40:02
 *
 */
public class IfSysAutoTest extends Domain {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private IfsysCheckThreadPool ifsysCheckThreadPool;
	private MailSenderService mailSenderService;
	private IfSysMockService ifSysMockService;
	private IfSysStuffService ifSysStuffService;

	public IfSysAutoTest(){
		ifsysCheckThreadPool = (IfsysCheckThreadPool) SpringContextHolder.getBean(IfsysCheckThreadPool.class);
		mailSenderService = (MailSenderService) SpringContextHolder.getBean(MailSenderService.class);
		ifSysMockService = (IfSysMockService) SpringContextHolder.getBean(IfSysMockService.class);
		ifSysStuffService = (IfSysStuffService) SpringContextHolder.getBean(IfSysStuffService.class);
	}
	public void work() {
		debug("Quartz的任务调度！！！");
		autoTest();
		sendMail();
		System.out.println("work");
		debug("一次的任务调度！！！");
	}
	
	public void autoTest() {
		ifsysCheckThreadPool.execute();
	}
	
	public void sendMail() {

		// 返回所有测试过的结果
		List<IfSysMock> resulteMocks = ifSysMockService.filterAllTestedMocks();

		// 1.格式化信封
		Map< String,List<IfSysMock> > mailBuffers = new HashMap();
		for(int i=0;i<resulteMocks.size();i++){
			// 遍历每个接口的关系
			int interfaceId = resulteMocks.get(i).getIfId();
			List<IfSysMock> relationShip = ifSysMockService.getInterfaceFollowShipById(interfaceId);
			for(int j=0;j<relationShip.size();j++){
				String email = relationShip.get(j).getEmail();
				String userName = relationShip.get(j).getUsername();
				//为每条mock加工关注者数据
				IfSysMock eachMock = resulteMocks.get(i);
				eachMock.setUsername(userName);
				// 为每个接收者包装信件
				if(mailBuffers.containsKey(email)&&mailBuffers.get(email).size()!=0){
					mailBuffers.get(email).add(eachMock);
				}else{
					List<IfSysMock> mock = new ArrayList<IfSysMock>();
					mock.add(eachMock);
					mailBuffers.put(email,mock);
				}
			}
		}
		// 2.分发收件人
		Iterator entries = mailBuffers.entrySet().iterator();
		while (entries.hasNext()) {

			Map.Entry entry = (Map.Entry) entries.next();

			String email = (String)entry.getKey();
			List<IfSysMock> mocks = (List<IfSysMock>)entry.getValue();
			// 设置收件人地址
			List<String> addressTo = new ArrayList<String>();
			addressTo.add(email);
			mailSenderService.setTo(addressTo);
			
			String userName= "";
			try{
				ifSysStuffService.getStuffByEmail(email).get(0).getUserName();
			}catch(Exception e){
				userName="";
			}
			
			mailSenderService.setSubject("来自独孤九剑接口自动化测试的邮件");
			mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
			// 发送结果
			Map model = new HashMap();
			model.put("resulteMocks", mocks);
			model.put("userName", userName);
			mailSenderService.sendWithTemplateForHTML(model);

		}

		// 3.抄送收件人
		String[] copyList = SystemPropertyConfigure.getProperty("mail.default.observer").split(",");
		for(int i=0;i<copyList.length;i++){
			String email = copyList[i];
			System.out.println(email);
			List<String> copyTo = new ArrayList<String>();
			copyTo.add(email);
			mailSenderService.setTo(copyTo);
			String userName= ifSysStuffService.getStuffByEmail(email).get(0).getUserName();
			mailSenderService.setSubject("来自独孤九剑接口自动化测试的邮件");
			mailSenderService.setTemplateName("copyMail.vm");// 设置的邮件模板
			// 发送结果
			Map model = new HashMap();
			model.put("resulteMocks", resulteMocks);
			model.put("userName", userName);
			mailSenderService.sendWithTemplateForHTML(model);
		}





		System.out.println("邮件发送成功！");
	}
	
	
}
