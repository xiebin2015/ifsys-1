/**
 * Title: WorkerThread.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.threadpool;

import java.util.List;

/**
 * Title: CheckThread<br/>
 * Description: 用来检测接口的线程<br/>
 * Company: gigold<br/>
 * @author xiebin
 * @date 2015年11月27日下午5:45:51
 *
 */
public class CheckThread implements Runnable {  
    
    private String command;  
    //存放待检测的接口列表
    private List list;
       
    public CheckThread(List  list,String s){  
    	this.list=list;
        this.command=s;  
    }  
   
    @Override  
    public void run() {  
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);  
        processCommand();  
        System.out.println(Thread.currentThread().getName()+" End.");  
    }  
   
    private void processCommand() {  
    	//写检测接口的代码
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
   
    @Override  
    public String toString(){  
        return this.command;  
    }  
} 
