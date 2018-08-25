package com.jp.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

/*
 * 将服务器上指定URL地址的文件下载下来
 * 构造参数
 * 参数1 Activity
 * 参数2 文件保存地址 saveFiiePath
 * 执行参数
 * 参数1 下载地址  
 */

public class DownLoadFileTask extends AsyncTask {

	private Activity mContext;
	private ProgressDialog progressDialog;
	private String saveFiiePath;
	

	public DownLoadFileTask(Activity context, String saveFiiePath) {
		this.mContext = context;
		this.saveFiiePath = saveFiiePath;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mContext);
		progressDialog.show();
	}

	@Override
	protected Object doInBackground(Object... params) {
		boolean result = false;

		try {
			result = downFile((String) params[0]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("e", e.toString());
		}
		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		progressDialog.cancel();
		if (result.equals(Boolean.TRUE)) {
			Toast.makeText(mContext, "下载成功!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(mContext, "下载失败!", Toast.LENGTH_LONG).show();
		}
	}

	public boolean downFile(String uri) {
		boolean result = false;
		URL fileUrl = null;
		try {
			fileUrl = new URL(uri);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			File file = new File(saveFiiePath);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = new byte [1024];
			while(is.read(b)!=-1){
				fos.write(b);
			}
			is.close();
			fos.close();
			conn.disconnect();
			result = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
