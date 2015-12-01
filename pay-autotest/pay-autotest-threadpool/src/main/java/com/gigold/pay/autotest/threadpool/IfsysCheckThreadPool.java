/**
 * Title: SimpleThreadPool.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.autotest.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title: SimpleThreadPool<br/>
 * Description: <br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月27日下午5:46:16
 *
 */
public class IfsysCheckThreadPool {
	// 测试
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());

		long ss = System.currentTimeMillis();
		// 获取核心数
		int n = Runtime.getRuntime().availableProcessors();
		System.out.println(n);
		ExecutorService executor = Executors.newFixedThreadPool(n + 1);
		// ExecutorService executor = Executors.newFixedThreadPool(1);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new CheckThread(null, "" + i);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("Finished all threads");
		System.out.println(System.currentTimeMillis() - ss);
	}

}