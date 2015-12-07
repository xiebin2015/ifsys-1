package com.gigold.pay.autotest.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.gigold.pay.framework.base.SpringContextHolder;
import com.gigold.pay.framework.core.Domain;
import com.gigold.pay.framework.core.log.DomainLogger;

import net.sf.json.JSONObject;

/**
 * 
 * Title: HttpClientService<br/>
 * Description: 调用汇添富接口<br/>
 * Company: gigold<br/>
 * 
 * @author xiebin
 * @date 2015年11月10日下午4:54:14
 *
 */
@Service
@SuppressWarnings("restriction")
public class HttpClientService extends Domain{
	// 建立http连接超时，单位毫秒
	private static int CONNECT_TIMEOUT = 30 * 1000;
	private static int SO_TIMEOUT = 30 * 1000;
	private static String CHARSET="UTF-8";

	/**
	 * 
	 * Title: setTimeOut<br/>
	 * Description: 设置超时<br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月10日上午10:52:47
	 *
	 * @param httpclient
	 */
	public void setTimeOut(HttpClient httpclient) {
		// 请求超时
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
		// 读取超时
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

	}

	/**
	 * 
	 * Title: setHeader<br/>
	 * Description: 设置请求头<br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月10日上午10:54:56
	 *
	 * @param httpPost
	 */
	public void setHeader(HttpPost httpPost) {
		httpPost.setHeader("accept", "*/*");
		httpPost.setHeader("connection", "Keep-Alive");
		httpPost.setHeader("Content-Type", "application/json");
	}

	/**
	 * 
	 * Title: httpPost<br/>
	 * Description: <br/>
	 * @author xiebin
	 * @date 2015年12月5日下午4:47:29
	 *
	 * @param url
	 * @param postData
	 * @return
	 */
	public String httpPost(String url, String postData) {
		String responseData = "";
		HttpClient httpclient = getHttpClient();

		HttpPost httppost = createPostMethed(url);
		// 设置超时
		setTimeOut(httpclient);
		setHeader(httppost);

		try {
			setRequestParams(httppost, postData);
		} catch (UnsupportedEncodingException e1) {
			debug("请求参数中存在非法字符");
			e1.printStackTrace();
		}

		try {
			HttpResponse response = httpclient.execute(httppost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				/* 读返回数据 */
				responseData = EntityUtils.toString(response.getEntity(), CHARSET);
			} else {
				debug("服务器响应失败 : 返回状态" + statusCode);
			}
		} catch (ClientProtocolException e) {
			debug("服务器响应失败");
		} catch (IOException e) {
			debug("服务器响应失败");
		}

		return responseData;
	}


	

	/**
	 * 
	 * Title: httpClientRequest<br/>
	 * POST 请求访问接口 交互数据格式 JSON: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月6日下午1:40:14
	 *
	 * @param url
	 * @param dto
	 * @return
	 */
	public Map<String, Object> httpClientRequest(String url, Map<String, ?> paramMap) {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(paramMap);
		// 获取HTF请求报文
		String requestBody ="";
		String responseBody = "";
		// 调用httpClient访问htf接口
		if (StringUtils.isNotBlank(requestBody)) {
			responseBody = ((HttpClientService) SpringContextHolder.getBean(HttpClientService.class)).httpPost(url,
					requestBody);
		}
		return responseMap;
	}

	public HttpPost createPostMethed(String url) {
		HttpPost httpPost = new HttpPost(url);
		return httpPost;
	}

	public HttpGet createGettMethed(String url) {
		return new HttpGet(url);
	}

	public HttpClient getHttpClient() {
		return new DefaultHttpClient();
	}

	/**
	 * 
	 * Title: setProxy<br/>
	 * 有需要则设置代理: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月6日下午1:41:54
	 *
	 * @param httpclient
	 * @param ip
	 * @param port
	 */
	public void setProxy(DefaultHttpClient httpclient, String proxyHost, int proxyPort, String userName,
			String password) {
		httpclient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort),
				new UsernamePasswordCredentials(userName, password));
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);
		httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);

	}

	/**
	 * 
	 * Title: setRequestParams<br/>
	 * 设置请求参数 和请求头: <br/>
	 * 
	 * @author xiebin
	 * @date 2015年11月6日下午1:41:18
	 *
	 * @param httppost
	 * @param dto
	 * @throws UnsupportedEncodingException
	 */
	public void setRequestParams(HttpPost httppost, String requestData) throws UnsupportedEncodingException {

		StringEntity entity = new StringEntity(requestData, CHARSET);// 解决中文乱码问题
		entity.setContentEncoding(CHARSET);
		httppost.setEntity(entity);
	}

	
	
	

}
