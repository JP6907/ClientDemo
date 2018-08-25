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
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 将服务器上指定URL地址的图片下载下来
 * 构造参数
 * 参数1 Activity
 * 参数2 isShowImage  是否显示图片
 * 参数3 ImageView 列表  图片 显示
 * 参数4 isSaveImage  是否保存图片
 * 参数5 指定用户  用户手机号码
 * 参数6 保存文件名
 * 执行参数
 * 参数1 下载地址  
 */

public class DownLoadPicTask extends AsyncTask {
	
	/*
	 * 默认保存路径
	 */
	private final static String DefaultSavePath = "/sdcard/DownloadTest";

	private Activity mContext;
	private ProgressDialog progressDialog;
	private ImageView imageView;
	private String userPhone;
	private String fileName;
	private String postDate;
	private boolean isShowImage;
	private boolean isSaveImage;
	
	/**
	 * 
	 * @param context     Activity
	 * @param isShowImage	是否显示图片
	 * @param imageView		显示图片的imageview
	 * @param isSaveImage	是否保存图片
	 * @param userPhone		用户手机号码
	 * @param postDate		发表日期
	 * @param fileName		保存文件名
	 */
	public DownLoadPicTask(Activity context, boolean isShowImage, ImageView imageView, boolean isSaveImage,
			String userPhone, String postDate, String fileName) {
		this.mContext = context;
		this.isShowImage = isShowImage;
		this.imageView = imageView;
		this.isSaveImage = isSaveImage;
		this.userPhone = userPhone;
		this.fileName = fileName;
		this.postDate = postDate;
		
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mContext);
		progressDialog.show();
	}

	@Override
	protected Object doInBackground(Object... params) {
		Bitmap result = null;

		try {
			result = downImage((String) params[0]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("e", e.toString());
			result = null;
		}
		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		progressDialog.cancel();
		if (result != null) {
			Toast.makeText(mContext, "下载成功!", Toast.LENGTH_SHORT).show();
			if (isShowImage) {
				imageView.setImageBitmap((Bitmap) result);
			}
			if(isSaveImage){
				boolean saveResult = saveBitmap((Bitmap)result,userPhone,postDate,fileName);
				if(saveResult){
					Toast.makeText(mContext, "保存成功!", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(mContext, "保存失败!", Toast.LENGTH_SHORT).show();
				}
			}
		} else {
			Toast.makeText(mContext, "下载失败!", Toast.LENGTH_SHORT).show();
		}
	}

	private Bitmap downImage(String uriPic) {
		URL imageUrl = null;
		Bitmap bitmap = null;
		try {
			imageUrl = new URL(uriPic);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
			return bitmap;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存到指定用户的文件夹下
	 */
	private boolean saveBitmap(Bitmap bitmap, String userPhone, String postDate, String fileName){
		boolean result = false;
		File path = new File(this.DefaultSavePath);
		if (!path.exists()) {// 目录不存在返回false
			path.mkdirs();// 创建一个目录
		}
		//文件夹创建不允许出现":" 
		//去除所有 空格 "-" ":"
		postDate = postDate.replaceAll("[:,\\s+,-]", "");
		File userPah = new File(this.DefaultSavePath + "/" + userPhone + "/ " + postDate);
		if (!userPah.exists()) {
			userPah.mkdirs();// 创建一个目录
		}
		OutputStream outputStream;
		BufferedOutputStream bos = null;
		try {
			outputStream = new FileOutputStream(this.DefaultSavePath + "/" + 
								userPhone + "/ " + postDate + "/" + fileName);
			bos = new BufferedOutputStream(outputStream);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
			
			result = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bos.flush();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
