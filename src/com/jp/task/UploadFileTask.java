package com.jp.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jp.ulit.Config;
import com.jp.ulit.HttpAssist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
/*
 * 上传文件到服务器upload文件夹
 */
public class UploadFileTask extends AsyncTask{

	private Activity mContext;
	private ProgressDialog progressDialog;
	private String responMessage;
	
	public UploadFileTask(Activity context){
		this.mContext = context;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mContext);
		progressDialog.show();
	}

	@Override
	protected Object doInBackground(Object... params) {
		String result = null;
		List<File> files = new ArrayList<File>();
		try{
			files.add((File)params[0]);
			files.add((File)params[0]);
		}catch(Exception e){
			result = "GetFileFailed";
		}
		try {
//			String url = "http://192.168.191.1:8090/MyServer/servlet/UploadFileServlet";
			responMessage = HttpAssist.uploadFile(Config.UriAPI + Config.UpLoadFileMethod,
									"Peng", "15521374237", "Hello", "Hello World!", files);
//			responMessage = HttpAssist.uploadFile(url,
//					"Peng", "15521374237", "Hello", "Hello World!", files);
			JSONArray jsonArray = new JSONArray(responMessage);
			String returnResult = jsonArray.getJSONObject(0).getString("RESULT");
			String returnTime = jsonArray.getJSONObject(0).getString("TIME");
			String returnID = jsonArray.getJSONObject(0).getString("ID");
			
			responMessage = "Reuslt:" + returnResult + "\n" + 
							"Time:" + returnTime + "\n" + 
							"ID:" + returnID;
			result = HttpAssist.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("e", e.toString());
			result = HttpAssist.TimeOut;
		}
		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		progressDialog.cancel();
		//Toast.makeText(mContext, "" + result, Toast.LENGTH_LONG).show();
		if(result.equals(HttpAssist.SUCCESS)){
			Toast.makeText(mContext, "发送成功!\n" + responMessage, Toast.LENGTH_LONG).show();
		} else if(result.equals(HttpAssist.FAILURE)){
			Toast.makeText(mContext, "发送失败!", Toast.LENGTH_LONG).show();
		} else if(result.equals(HttpAssist.TimeOut)){
			Toast.makeText(mContext, "连接服务器超时!", Toast.LENGTH_LONG).show();
		} else if(result.equals("GetFileFailed")){
			Toast.makeText(mContext, "获取文件失败!", Toast.LENGTH_LONG).show();
		}
	}
}
