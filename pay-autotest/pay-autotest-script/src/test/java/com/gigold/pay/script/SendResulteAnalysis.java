/**
 * Title: Test.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.json.JSON;
import com.gigold.pay.autotest.bo.IfSysStuff;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.email.MailSenderService;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.IfSysStuffService;
import com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool;
import com.gigold.pay.framework.base.SpringContextHolder;

/**
 * Title: Test<br/>
 * Description: <br/>
 * Company: gigold<br/>
 *
 * @author xiebin
 * @date 2015年12月5日下午3:37:06
 *
 */
public class SendResulteAnalysis {

    private IfsysCheckThreadPool ifsysCheckThreadPool;
    private MailSenderService mailSenderService;
    private IfSysMockService ifSysMockService;
    private IfSysStuffService ifSysStuffService;

    //@Before
    public void setup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*Beans.xml");
        ifsysCheckThreadPool = (IfsysCheckThreadPool) SpringContextHolder.getBean(IfsysCheckThreadPool.class);
        mailSenderService = (MailSenderService) SpringContextHolder.getBean(MailSenderService.class);
        ifSysMockService = (IfSysMockService) SpringContextHolder.getBean(IfSysMockService.class);
        ifSysStuffService = (IfSysStuffService) SpringContextHolder.getBean(IfSysStuffService.class);
    }


    //@Test
    public void testAutoTest() {
        // 发送结果分析
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
            //model.put("resulteMocks", resulteMocks);
            model.put("userName", userName);
            mailSenderService.sendWithTemplateForHTML(model);
        }
        System.out.println("邮件发送成功！");

    }

    //@After
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
        // 结束
    }
}
