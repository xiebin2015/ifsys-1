/**
 * Title: IfSysAutoTest.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.scripte.service;

import java.util.*;

import com.gigold.pay.autotest.bo.IfSysMock;
import com.gigold.pay.autotest.bo.IfSysRefer;
import com.gigold.pay.autotest.bo.InterFaceInfo;
import com.gigold.pay.autotest.dao.InterFaceDao;
import com.gigold.pay.autotest.email.MailSenderService;
import com.gigold.pay.autotest.service.IfSysMockHistoryService;
import com.gigold.pay.autotest.service.IfSysMockService;
import com.gigold.pay.autotest.service.IfSysStuffService;
import com.gigold.pay.autotest.service.InterFaceService;
import com.gigold.pay.autotest.threadpool.IfsysCheckThreadPool;
import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.bootstrap.SystemPropertyConfigure;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.autotest.bo.IfSysMockHistory;

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
	private IfSysMockHistoryService ifSysMockHistoryService;
	private InterFaceService interFaceService;
	private InterFaceDao interFaceDao;



	public IfSysAutoTest(){
		ifsysCheckThreadPool = (IfsysCheckThreadPool) SpringContextHolder.getBean(IfsysCheckThreadPool.class);
		mailSenderService = (MailSenderService) SpringContextHolder.getBean(MailSenderService.class);
		ifSysMockService = (IfSysMockService) SpringContextHolder.getBean(IfSysMockService.class);
		ifSysStuffService = (IfSysStuffService) SpringContextHolder.getBean(IfSysStuffService.class);
		ifSysMockHistoryService = (IfSysMockHistoryService) SpringContextHolder.getBean(IfSysMockHistoryService.class);
		interFaceService = (InterFaceService) SpringContextHolder.getBean(InterFaceService.class);
		interFaceDao = (InterFaceDao) SpringContextHolder.getBean(InterFaceDao.class);

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
		List<IfSysMock> resulteMocks = ifSysMockService.filterMocksByFailed();

		// 1.测试结果按接口分类
		Map<String,List<IfSysMock>> rstItfces = new TreeMap<>();
		for(IfSysMock ifSysMock:resulteMocks){
			// 判断结果分类中是否已经初始化过了,若没有则初始化
			String key  = String.valueOf(ifSysMock.getIfId());
			if(!rstItfces.containsKey(key)){
				rstItfces.put(key,new ArrayList<IfSysMock>()); // 键值格式为{"12":[1,2,3,4]}
			}
			// 增加mock
			rstItfces.get(key).add(ifSysMock);
		}


		// 2.分发收件人
		Map<String,List<List<IfSysMock>>> observers = new HashMap<>();
		for(String ifId:rstItfces.keySet()){
			// 每个接口的测试结果集 [1,2,3,4]
			List<IfSysMock> eachMockSet = rstItfces.get(ifId);

			// 获取接口的关注者
			List<IfSysMock> emailObjs = ifSysMockService.getInterfaceFollowShipById(Integer.parseInt(ifId));

			// 将接口结果集添加到收件人observers
			for(IfSysMock emailObj:emailObjs){ // 遍历单个接口的测试结果集,拿到测试结果
				String email = emailObj.getEmail(); // chenkuan@qq.com
				String Uname = emailObj.getUsername(); // chenkuan@qq.com
				String key = email+"::"+Uname;
				if(!observers.containsKey(key)){
					observers.put(key,new ArrayList<List<IfSysMock>>());//二维数组,每组为一个接口
				}
				observers.get(key).add(eachMockSet);// 对测试结果所包含的发件人去重,然后将相同的发件人所对应的mock所对应的接口进行关联
			}
		}

		// 3.发件
		for(String emailNuname:observers.keySet()){
			// 获取每个用户的结果
			List<List<IfSysMock>> ifOfmockSetList = observers.get(emailNuname);
			//收件人地址和姓名
			String email = emailNuname.split("::")[0].trim();
			String userName = emailNuname.split("::")[1].trim();
			// 设置收件人地址
			List<String> addressTo = new ArrayList<>();
			addressTo.add(email);
			mailSenderService.setTo(addressTo);
			mailSenderService.setSubject("来自独孤九剑接口自动化测试的邮件");
			mailSenderService.setTemplateName("mail.vm");// 设置的邮件模板
			// 发送结果
			Map<String,Object> model = new HashMap<>();
			model.put("ifOfmockSetList", ifOfmockSetList);
			model.put("userName", userName);
			//if(email.equals("chenkuan@gigold.com"))
			mailSenderService.sendWithTemplateForHTML(model);
		}
		System.out.println("邮件发送成功！");
	}

	public void sendAnalyMail(){
		int jnrCount = 15;
		// 发送结果分析
		List<IfSysMockHistory> recentRst = ifSysMockHistoryService.getNewestReslutOf(jnrCount);
		if(recentRst==null){System.out.println("查询最近的mocks查询结果为空");return;}
		Map< String,List<IfSysMockHistory> > mailBuffers = new HashMap<>();
		for(IfSysMockHistory history:recentRst){
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
		Comparator comparator = new Comparator<String>(){

			public int compare(String o1, String o2)
			{
				int n1 = Integer.parseInt(o1);
				int n2 = Integer.parseInt(o2);
				if (n1 == n2)
					return 0;
				else
					return n1-n2;
			}};
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
			Map<String,Map<String,Object>> eachIfSet = new HashMap<>();// {if1 : {null,null,[] },if2 : { }}
			for(IfSysMockHistory eachHisMock:histMocks){
				//{if1,test1,info1}
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
		Map<String,String> IfIDNameMap = new TreeMap<>(comparator);// id-名字映射
		Map<String,String> IfIDDsnrMap = new TreeMap<>(comparator); // id-设计者映射
		for (Iterator iter = HeadIFID.iterator(); iter.hasNext();) {
			String _ifId = String.valueOf(iter.next());
			IfIDNameMap.put(_ifId,_ifId);
		}
		// 去重 - 替换接口名
		Iterator<String> iter = IfIDNameMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			InterFaceInfo ifinfo=interFaceService.getInterFaceById(Integer.parseInt(key));
			if(ifinfo!=null){
				IfIDNameMap.put(key,ifinfo.getIfName());
				IfIDDsnrMap.put(key,ifinfo.getDsname());
			}
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

		// 计算当次覆盖率
		float CCprob,CCtot,IFtst,IFtot;
		CCprob=0;
		CCtot = lastRst.size();
		for(IfSysMockHistory eachRst:lastRst){
			if(!(eachRst.getTestResult().equals("1")||eachRst.getTestResult().equals("0")))
				CCprob++;
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
		String lastJNR = lastRst.get(0).getJrn();
		IFtst = initedDataSet.get(lastJNR).size();
		IFtot = interFaceDao.getAllIfSysCount();
		System.out.println(initedDataSet.get(lastJNR).size());

		// 计算当次覆盖率
		float CCcoverage = ( 1 - CCprob/CCtot );
		float IFcoverage = ( IFtst/IFtot );

		// 发送邮件
		String[] copyList = SystemPropertyConfigure.getProperty("mail.default.observer").split(",");
		List<String> copyTo = new ArrayList<String>();
		for(String email:copyList){
			System.out.println(email);
			copyTo.add(email);
		}
		mailSenderService.setTo(copyTo);
		// String userName= ifSysStuffService.getStuffByEmail(email).get(0).getUserName();
		mailSenderService.setSubject("来自独孤九剑接口自动化测试的邮件");
		mailSenderService.setTemplateName("copyMail.vm");// 设置的邮件模板
		// 发送结果
		Map<String,Object> model = new HashMap<>();
		model.put("initedDataSet", initedDataSet);// 所有数据
		model.put("IfIDNameMap", IfIDNameMap);// 表列头
		model.put("IfIDDsnrMap", IfIDDsnrMap);// 设计者映射
		model.put("OrderedHeadJNRSet", OrderedHeadJNRSet);//表行头
		//   model.put("userName", userName);
		// 最近一条JNR
		model.put("lastJNR", lastJNR);
		// 指标数据
		model.put("ifCount", ifCount);
		// model.put("caseCount", caseCount); //所有的
		model.put("caseCount", Math.round(mockCount));
		model.put("jnrCount", jnrCount);
		model.put("mockPassRate", (float)(Math.round(mockPassRate*100))/100);//保留两位
		model.put("CCcoverage", (float)(Math.round(CCcoverage*10000))/100);//保留两位
		model.put("IFcoverage", (float)(Math.round(IFcoverage*10000))/100);//保留两位
		mailSenderService.sendWithTemplateForHTML(model);
		System.out.println("邮件发送成功！");
	}
}
