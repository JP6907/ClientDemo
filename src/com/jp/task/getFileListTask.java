package com.jp.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.jp.activity.MainActivity;
import com.jp.ulit.Config;
import com.jp.ulit.WebUtil;
import com.jp.ulit.XmlHandle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 根据手机号码获取服务器上对应文件夹中文件名列表
 * 同时将列表下面的图片全部下载下来
 */

public class getFileListTask extends AsyncTask {

	private Activity mContext;
	private ProgressDialog progressDialog;
	private TextView textView;
	private List<String> fileNames = new ArrayList<String>();
	private String userPhone;
	private String postDate;

	public getFileListTask(Activity context, TextView textView) {
		this.mContext = context;
		this.textView = textView;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mContext);
		progressDialog.show();
	}

	@Override
	protected Object doInBackground(Object... params) {
		String result = "0";

		userPhone = (String) params[0];
		postDate = (String) params[1];
		// params.add(new BasicNameValuePair("flag","0"));
		try {
			String requsetMessage = XmlHandle.packXml("userPhone", userPhone, "postDate", postDate);
			result = WebUtil.getXmlByWeb(requsetMessage, Config.GetFileListMethod);
			fileNames = XmlHandle.getPicListByXml(result);
			result = "1";
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			result = "Error!";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		progressDialog.cancel();
		if (result.equals("1")) {
			textView.setText(fileNames.size() + "\n");
			for (String fileName : fileNames) {
				textView.append(fileName + "\n");
			}
		} else if (result.equals("Error!")) {
			Toast.makeText(mContext, "网络连接异常!", Toast.LENGTH_LONG).show();
		} else if (result.equals("0")) {
			Toast.makeText(mContext, "获取失败!", Toast.LENGTH_LONG).show();
		}

		// 文件夹创建不允许出现":"
		// 去除所有 空格 "-" ":"
		postDate = postDate.replaceAll("[:,\\s+,-]", "");
		/**
		 * 启动DownLoadPicTask下载图片
		 */
		for (String fileName : fileNames) {
			final String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/" + userPhone + "/" + postDate
					+ "/" + fileName;
			new DownLoadPicTask(mContext, false, null, true, userPhone, postDate, fileName).execute(urlPath);
		}
	}
}
