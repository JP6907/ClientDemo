package com.jp.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.jp.ulit.Config;
import com.jp.ulit.WebUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class ParamsLoginTask extends AsyncTask {

	private String result = "......";
	private Activity context;
	private TextView showMessage;
	private ProgressDialog progressDialog;
	
	public ParamsLoginTask(Activity context, TextView showMessage){
		this.context = context;
		this.showMessage = showMessage;
	}
	@Override
	protected void onPreExecute() {
		// 鍔犺浇progressDialog
		progressDialog = new ProgressDialog(context);
		progressDialog.show();
	}

	@Override
	protected Object doInBackground(Object... params) {
		String result = "......";

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("username", (String)params[0]));
		paramList.add(new BasicNameValuePair("password", (String)params[1]));
		// params.add(new BasicNameValuePair("flag","0"));
		try {
			result = WebUtil.getParamsByWeb(paramList , Config.ParamsMethod);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			result = "Error!";
			e.printStackTrace();
		}

		return result;
	}

	@Override
	protected void onPostExecute(Object result) {

		showMessage.setText("Params方法:" + "\n" + result.toString());
		// 取消进度条
		progressDialog.cancel();
	}

}