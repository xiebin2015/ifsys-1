/**
 * Title: LOGIN_API.java<br/>
 * Description: 吉高宝登录接口功能测试<br/>
 * Copyright: Copyright (c) 2015<br/>
 * Company: gigold<br/>
 *
 */
package com.gigold.pay.script;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.sf.json.JSONObject;



/**
 * Title: LOGIN_API<br/>
 * Description: 吉高宝登录接口功能测试demo<br/>
 * Company: gigold<br/>
 * @author hailongchen
 * @date 2015-11-23下午9:12:30
 *
 */


public class WIT {
    URL url;
    URI uri;
    String action;
    CloseableHttpClient httpclient;
    HttpPost httppost;
    CloseableHttpResponse response;
    HttpEntity rspentity;
    
    @Before
    public void target() throws MalformedURLException {
        
        url = new URL("http://182.92.170.189:8081/");
        
        //创建httpclient实例
        httpclient = HttpClients.createDefault();
        
        //指定post的资源路径
        this.action = "user/login.do";
        
        try {
            uri = new URI(url+action);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
//    public void loginByErrAcc(){
//        /*
//         * 输入错误的账号进行登录
//         */
//        JSONObject jsondata1 = new JSONObject();
//        jsondata1.put("mobileNum", "11111111111");
//    }
    
    public void loginByNormal() {
        
        /*
         * 输入正确合法的账号密码进行登录
         * 
         * */
        
        //定义请求参数，其为JSON对象
        JSONObject jsondata = new JSONObject();
        jsondata.put("mobileNum", "18674892363");  
        jsondata.put("loginPwd", "a123456");  
        
        //初始化httppost和entity
        httppost = new HttpPost(uri);
        StringEntity reqentity = null;
        
        //将json对象转成string,以作为报文传输
        try {
            reqentity = new StringEntity(jsondata.toString());
//            System.out.println("JSON content is :" + jsondata.toString());     //打印出请求的数据
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        reqentity.setContentEncoding("UTF-8");     //定义编码格式为UTF-8
        reqentity.setContentType("application/json");    //发送json数据需要设置contentType
        httppost.setEntity(reqentity);
        
        // 发起post请求，并检查返回结果
        try {
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity rspentity = response.getEntity();
         
            String result = EntityUtils.toString(rspentity,"UTF-8");
            
            // 将实际返回且转换为String的结果转成了json对象，然后再在断言中获取其中的一个键值对的value
            JSONObject realRes = JSONObject.fromObject(result);
            
            // 断言结果
            Assert.assertEquals("成功登录", "成功", realRes.get("rspInf"));
            
            //打印返回结果
            
            System.out.println("Response content:" + result);
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    
    @After
    public void release() throws IOException{
        // 关闭httpclient，释放连接
        httpclient.close();
    }
    
}
