package com.jp.task;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import com.jp.ulit.Config;
import com.jp.ulit.WebUtil;
import com.jp.ulit.XmlHandle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;
/*
 * 将用户名和密码打包成xml格式
 * 用xml方式登陆
 */

public class XmlLoginTask extends AsyncTask {

	private String respMessage = null;
	private Activity mContext;
	private TextView mShowMessage;
	private ProgressDialog progressDialog;

	public XmlLoginTask(Activity context, TextView showMessage){
		mContext = context;
		mShowMessage = showMessage;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mContext);
		progressDialog.show();
	}

	@Override
	protected Object doInBackground(Object... params) {
		String result = "......";
		String reqMessage;
		try {
			reqMessage = XmlHandle.packXml("userName", (String) params[0], "passWord", (String) params[1]);
			// 发送请求, 并接收服务器响应
			respMessage = WebUtil.getXmlByWeb(reqMessage ,Config.XmlMethod);
			if (respMessage != null) {
				Map user = XmlHandle.praseXml(respMessage);
				String userName = (String) user.get("userName");
				String passWord = (String) user.get("passWord");
				result = "用户名(XML): " + userName + "\n" + "密码(XML): " + passWord;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			result = "Error!";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);

		mShowMessage.setText("XML方法: " + "\n" + result.toString());
		// 取消进度条
		progressDialog.cancel();
	}

}