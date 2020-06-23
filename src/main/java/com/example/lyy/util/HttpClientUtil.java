package com.example.lyy.util;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/**
 * HTTP请求相关工具类
 * 
 * @author dongj
 */

public class HttpClientUtil
{
	
	private final static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 发送 get请求
	 */
	@SuppressWarnings("rawtypes")
	public static String doHttpGet(Map paramMap)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(paramMap.get("url"));

		if(paramMap.get("param") != null)
		{
			String param = paramMap.get("param").toString();

			sb.append("?id=");

			if(paramMap.get("isUrlEncoded") != null && Integer.parseInt(paramMap.get("isUrlEncoded").toString()) == 1)
			{
//				sb.append(parameterizeForGet(param));
			}
			else
			{
				sb.append(param);
			}
		}

		String url = sb.toString();
		log.info("===============请求路径" + url);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try
		{
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			log.info("executing  request get:" + httpget.getURI());

			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try
			{
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				log.info("--------------------------------------");
				log.info("响应状态:"+response.getStatusLine());

				if(entity != null)
				{


					String entityString = EntityUtils.toString(entity, "UTF-8").trim();

					log.info("响应内容:"+entityString);


					httpget.releaseConnection();

					return entityString;
				}
//				System.out.println("------------------------------------");
			}
			finally
			{
				response.close();
			}
		}
		catch(ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭连接,释放资源
			try
			{
				httpclient.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	

	
	
	/**
	 * 发送回调请求并返回回调响应
	 * @param url 回调url
	 * @param json  json格式报文字符串
	 * @return	回调响应的报文字符串
	 */
	public static String callPost(String url, String json) {
		URL u = null;
		HttpURLConnection con = null;
		String responseStr = "";
		UUID uuid = UUID.randomUUID();
		String tradeno = uuid.toString().replaceAll("-", "");
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setConnectTimeout(30000);
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(json);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
			return  "{\"resp_head\":{\"radeno\":\""+tradeno+"\",\"retcode\":\"0\",\"subcode\":\"9001\",\"message\":\"连接异常\"}}";
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取相应内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  "{\"resp_head\":{\"radeno\":\""+tradeno+"\",\"retcode\":\"0\",\"subcode\":\"9002\",\"message\":\"读取内容异常\"}}";
		}
		if ((responseStr=buffer.toString())!= null) {

		}
		System.out.println(responseStr);
		return responseStr;
	}

}