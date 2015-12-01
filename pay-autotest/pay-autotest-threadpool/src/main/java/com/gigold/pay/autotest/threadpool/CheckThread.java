/**
 * Title: WorkerThread.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.threadpool;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.springframework.beans.factory.annotation.Autowired;

import com.gigold.pay.autotest.service.IfSysMockService;

/**
 * Title: CheckThread<br/>
 * Description: 用来检测接口的线程<br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月27日下午5:45:51
 *
 */
public class CheckThread implements Runnable {  
	IfSysMockService ifSysMockService;
    
    //存放接口列表
    private List<Map<String,Object>> ifsyslist;
       
    public CheckThread(IfSysMockService ifSysMockService,List<Map<String,Object>>  ifsyslist){  
    	this.ifsyslist=ifsyslist;
    	this.ifSysMockService= ifSysMockService;
    }  
   
    @Override  
    public void run() {  
        System.out.println(Thread.currentThread().getName()+" Start. Command = ");  
        processCommand();  
        System.out.println(Thread.currentThread().getName()+" End.");  
    }  
    //处理接口测试
    private void processCommand() {  
    	//写检测接口的代码
    	for(Map<String,Object> ifmap:ifsyslist){
    		int ifId=Integer.parseInt(String.valueOf(ifmap.get("ID")));
    		List<Map<String,Object>> mockList=ifSysMockService.getMockInfoByIfId(ifId);
    		for(Map<String,Object> mockmap:mockList){
    			//设置 接口名、接口描述、接口所属系统名、产品名、接口请求地址，服务器地址
    			mockmap.put("IF_NAME", ifmap.get("IF_NAME"));
    			mockmap.put("IF_DESC", ifmap.get("IF_DESC"));
    			mockmap.put("SYS_NAME", ifmap.get("SYS_NAME"));
    			mockmap.put("PRO_NAME", ifmap.get("PRO_NAME"));
    			mockmap.put("IF_URL", ifmap.get("IF_URL"));
    			mockmap.put("ADDRESS_URL", ifmap.get("ADDRESS_URL"));
    			//调用 httpclient代码访问接口
    			
    		}
    		
    	}
    }  
   
} 
