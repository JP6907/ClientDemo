package com.jp.task;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jp.ulit.Config;
import com.jp.ulit.WebUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("rawtypes")
public class RegisterTask extends AsyncTask {

	private JSONArray respArray = null;
	private Activity mContext;
	private TextView mShowMessage;
	private ProgressDialog progressDialog;
	
	public RegisterTask(Activity context, TextView showMessage){
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
		JSONArray reqArray;
		try {
			reqArray = new JSONArray();
			reqArray.put(new JSONObject().put("USERNAME", params[0]).put("PASSWORD", params[1]));
			// 发送请求, 并接收服务器响应
			respArray = WebUtil.getJsonByWeb(reqArray, Config.RegisterMethod);
			if (respArray != null) {
				result = respArray.getJSONObject(0).getString("RESULT");
				Log.i("result", "register: " + result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			result = "TimeOut";
			e.printStackTrace();
		}

		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		progressDialog.cancel();
		Toast.makeText(mContext, "" + result, Toast.LENGTH_LONG).show();
		if(result.equals("true")){
			mShowMessage.setText("JSON方法: " + "\n" + "注册成功!");
		} else if(result.equals("false")){
			mShowMessage.setText("JSON方法: " + "\n" + "用户已存在,注册失败!");
		} else if(result.equals("TimeOut")){
			mShowMessage.setText("JSON方法: " + "\n" + "连接超时!");
		} else{
			mShowMessage.setText("JSON方法: " + "\n" + "Error");
		}
	}
}
