/**
 * Title: Test.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.script;

import java.util.*;

import com.alibaba.dubbo.common.json.JSON;
import com.gigold.pay.autotest.bo.IfSysMockHistory;
import com.gigold.pay.autotest.bo.IfSysStuff;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.service.IfSysMockHistoryService;
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
import com.gigold.pay.autotest.service.InterFaceService;
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

    private MailSenderService mailSenderService;
    private IfSysStuffService ifSysStuffService;
    private IfSysMockHistoryService ifSysMockHistoryService;
    private InterFaceService interFaceService;

    @Before
    public void setup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*Beans.xml");
        mailSenderService = (MailSenderService) SpringContextHolder.getBean(MailSenderService.class);
        ifSysStuffService = (IfSysStuffService) SpringContextHolder.getBean(IfSysStuffService.class);
        ifSysMockHistoryService = (IfSysMockHistoryService) SpringContextHolder.getBean(IfSysMockHistoryService.class);
        interFaceService = (InterFaceService) SpringContextHolder.getBean(InterFaceService.class);
    }


    @Test
    public void testAutoTest() {
        int jnrCount = 15;
        // 发送结果分析
        List<IfSysMockHistory> recentRst = ifSysMockHistoryService.getNewestReslutOf(jnrCount);

        Map< String,List<IfSysMockHistory> > mailBuffers = new HashMap();
        for(int i=0;i<recentRst.size();i++){
            IfSysMockHistory history = recentRst.get(i);
            String JNR = history.getJrn();

            // 为每个接收者包装信件
            if(mailBuffers.containsKey(JNR)&&mailBuffers.get(JNR).size()!=0){
                mailBuffers.get(JNR).add(history);
            }else{
                List<IfSysMockHistory> histories = new ArrayList<IfSysMockHistory>();
                histories.add(history);
                mailBuffers.put(JNR,histories);
            }
        }

        // 重新格式化结果数据
        Iterator entries = mailBuffers.entrySet().iterator();
        Map<String,Map> initedDataSet = new TreeMap<>();
        ArrayList<String> HeadIFID = new ArrayList<>();
        // 通过率
        int _testCnt = 0;

        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            // 每一批的批号
            String JNR = (String)entry.getKey();
            // 每一批的所有数据
            List<IfSysMockHistory> histMocks = (List<IfSysMockHistory>)entry.getValue();//[{if1,test1,info1},{if1,test2,info2}]

            // 遍历所有数据,并分装到eachIfset
            Map<String,Map<String,Object>> eachIfSet = new HashMap();// {if1 : {null,null,[] },if2 : { }}
            for(int i=0;i<histMocks.size();i++){
                IfSysMockHistory eachHisMock = histMocks.get(i);//{if1,test1,info1}

                //判断eachIfSet是否已经有该接口的数据,若没有,则新建
                String ifId = String.valueOf(eachHisMock.getIfId());
                if(!eachIfSet.containsKey(ifId)){
                    eachIfSet.put(ifId,new HashMap<String,Object>());
                    eachIfSet.get(ifId).put("ifPassRate",new Float(0)); //后面计算
                    String ifName = eachHisMock.getIfName();
                    eachIfSet.get(ifId).put("ifName",(ifName!=null)?ifName:"0"); //取接口名,取不到则为0
                    eachIfSet.get(ifId).put("ifTestData",new ArrayList<IfSysMockHistory>());
                }

                // 同时初始化表列
                HeadIFID.add(ifId);

                // 当前接口的所有原始结果数据存放点
                List<IfSysMockHistory> ifTestData = ((List<IfSysMockHistory>)eachIfSet.get(ifId).get("ifTestData"));
                ifTestData.add(eachHisMock);

                // 实时计算当前接口通过率
                float rstSiz = ifTestData.size();//当前单接口集合大小
                if(rstSiz!=0){
                    float preRst = (float) (eachIfSet.get(ifId).get("ifPassRate"));
                    float nowRst = eachHisMock.getTestResult().equals("1")?1:0;
                    float _rate = ((rstSiz-1)*preRst+nowRst)/rstSiz;
                    eachIfSet.get(ifId).put("ifPassRate",(float)(Math.round(_rate*100))/100); //实时计算
                    _testCnt++;
                }else{
                    eachIfSet.get(ifId).put("ifPassRate","没有测试数据,无法计算");
                }
            }
            initedDataSet.put(JNR,eachIfSet); //拼装
        }
        // 格式化结束


        // 去重 - HeadIFID 去重/排序
        Map<String,String> IfIDNameMap = new TreeMap<String,String>();
        for (Iterator iter = HeadIFID.iterator(); iter.hasNext();) {
            String _ifId = String.valueOf(iter.next());
            int intId = Integer.parseInt(_ifId);
            IfIDNameMap.put(_ifId,_ifId);
        }
        // 去重 - 替换接口名
        Iterator<String> iter = IfIDNameMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            IfIDNameMap.put(key,interFaceService.getInterFaceById(Integer.parseInt(key)).getIfName());
            System.out.println(key);
        }
        // 去重 - 结束

        // JNR集合
        Set<String> OrderedHeadJNRSet = initedDataSet.keySet();

        // 接口总数
        int ifCount = IfIDNameMap.size();
        // 用例总数
        int caseCount = _testCnt;
        // 计算通过率 - 格式化数据
        List<IfSysMockHistory> lastRst = ifSysMockHistoryService.getNewestReslutOf(1);//最近一批数据
        float mockCount = lastRst.size();
        float _passRate = 0;
        for(int i=0;i<lastRst.size();i++){
            IfSysMockHistory eachRst = lastRst.get(i);
//            // 初始化接口初始为 通过状态
//            String strIfId = String.valueOf(eachRst.getId());
//            if(!newestPassRate.containsKey(strIfId)){
//                newestPassRate.put(strIfId,1);
//            }

            // 获取本条mock的测试结果
            _passRate += (eachRst.getTestResult().equals("1")?1:0);
//            // 获取每个接口的当前状态
//            int eachRstNowStat = newestPassRate.get(strIfId);
//            // 若一直是通过状态,则写最新状态(一票否决状态)
//            if(eachRstNowStat==1){
//                newestPassRate.put(strIfId,nowrst);
//            }
        }
        // 计算通过率 - 按mock算
        float mockPassRate = 100*_passRate/mockCount;


        // 发送邮件
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
            model.put("initedDataSet", initedDataSet);// 所有数据
            model.put("IfIDNameMap", IfIDNameMap);// 表列头
            model.put("OrderedHeadJNRSet", OrderedHeadJNRSet);//表行头
            model.put("userName", userName);
            // 指标数据
            model.put("ifCount", ifCount);
            model.put("caseCount", caseCount);
            model.put("jnrCount", jnrCount);
            model.put("mockPassRate", (float)(Math.round(mockPassRate*100))/100);//保留两位
            mailSenderService.sendWithTemplateForHTML(model);
        }
        System.out.println("邮件发送成功！");

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
        // 结束
    }
}
