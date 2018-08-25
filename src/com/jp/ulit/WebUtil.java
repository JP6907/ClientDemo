package com.jp.ulit;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class WebUtil {

	private static final int REQUEST_TIMEOUT = 10 * 1000;
	private static final int SO_TIMEOUT = 8 * 1000;
	private static final String BOUNDARY = "---------------------------7db1c523809b2"; // 数据分割�?

	/**
	 * 请求服务器并得到响应
	 * 
	 * @param methodName
	 * @param params
	 * @return 返回json对象
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONArray getJsonByWeb(JSONArray params,String Method) throws IOException, JSONException {
		JSONArray json = null;
		HttpPost httpPost = new HttpPost(Config.UriAPI + Method);
		httpPost.setEntity(new StringEntity(params.toString(), "utf-8"));
		HttpClient httpClient = getHttpClient();
		HttpResponse response = httpClient.execute(httpPost);
		System.out.println("getStatuCode: " + response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() == 200) {
			String resMsg = EntityUtils.toString(response.getEntity());
			json = new JSONArray(resMsg);
		}
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
		return json;
	}

	public static String getXmlByWeb(String xml,String method) throws IOException, JSONException {
		String resMsg = "......";
		HttpPost httpPost = new HttpPost(Config.UriAPI + method);
		//HttpPost httpPost = new HttpPost("http://123.207.117.75:8092/DoorServer/servlet/GetPostDataServlet");
		httpPost.setEntity(new StringEntity(xml, "utf-8"));
		HttpClient httpClient = getHttpClient();
		HttpResponse response = httpClient.execute(httpPost);
		System.out.println("getStatuCode: " + response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() == 200) {
			resMsg = EntityUtils.toString(response.getEntity());
		}
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
		return resMsg;
	}

	public static String getParamsByWeb(List<NameValuePair> params,String method) throws IOException {
		String result = null;
		HttpPost httpRequest = new HttpPost(Config.UriAPI + method);
		httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
		// 获取响应服务器的数据
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			// 利用字节数组流和包装的绑定数据
			byte[] data = new byte[2048];
			// 再创建字节数组输入流对象
			data = EntityUtils.toByteArray((HttpEntity) httpResponse.getEntity());
			// 绑定字节流和数据包装流
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			DataInputStream dis = new DataInputStream(bais);
			// 将字节数组中的数据还原成原来的各种数据类型
			result = new String(dis.readUTF());
		}
		return result;
	}

	private static HttpClient getHttpClient() {
		HttpParams params = new BasicHttpParams();
		params.setParameter("charset", "utf-8");
		HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT);
		return new DefaultHttpClient(params);
	}
}