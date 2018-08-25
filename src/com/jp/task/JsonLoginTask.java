package com.jp.task;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jp.ulit.Config;
import com.jp.ulit.WebUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class JsonLoginTask extends AsyncTask {

	private JSONArray respArray = null;
	private Activity mContext;
	private TextView mShowMessage;
	private ProgressDialog progressDialog;
	
	public JsonLoginTask(Activity context, TextView showMessage){
		this.mContext = context;
		this.mShowMessage = showMessage;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mContext);
		progressDialog.show();
	}

	@Override
	protected Object doInBackground(Object... params) {
		int result = -1;
		JSONArray reqArray;
		try {
			reqArray = new JSONArray();
			reqArray.put(new JSONObject().put("USERNAME", params[0]).put("PASSWORD", params[1]));
			// 发送请求, 并接收服务器响应
			respArray = WebUtil.getJsonByWeb(reqArray , Config.JsonMethod);
			if (respArray != null) {
//				String userName = respArray.getJSONArray(0).getString("USERNAME");
//				String passWord = respArray.getJSONArray(0).getString("PASSWORD");
				result = respArray.getJSONObject(0).getInt("RESULT");
				Log.i("result", "login: " + result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			result = Config.TimeOut;
			e.printStackTrace();
		}

		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		progressDialog.cancel();
		Toast.makeText(mContext, "" + result, Toast.LENGTH_LONG).show();
		if(result.equals(-1)){
			mShowMessage.setText("JSON方法: " + "\n" + "......");
		} else if(result.equals(Config.UserNotExit)){
			mShowMessage.setText("JSON方法: " + "\n" + "用户不存在!");
		} else if(result.equals(Config.WrongPassWord)){
			mShowMessage.setText("JSON方法: " + "\n" + "密码错误!");
		} else if(result.equals(Config.LoginSuccess)){
			mShowMessage.setText("JSON方法: " + "\n" + "登录成功!");
		} else if(result.equals(Config.TimeOut)){
			mShowMessage.setText("JSON方法: " + "\n" + "连接超时!");
		}
	}
}
