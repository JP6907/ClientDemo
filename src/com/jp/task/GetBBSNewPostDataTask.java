package com.jp.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jp.ulit.Config;
import com.jp.ulit.PostProfile;
import com.jp.ulit.WebUtil;
import com.jp.ulit.XmlHandle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 获取论坛发贴信息
 * 
 * @author Administrator
 *
 */
public class GetBBSNewPostDataTask extends AsyncTask {

	private String respMessage = null;
	private Activity mContext;
	private TextView mShowMessage;
	private ProgressDialog progressDialog;
	private List<PostProfile> postProfileList = new ArrayList<PostProfile>();

	/**
	 * 
	 * @param context
	 *            Activity
	 * @param showMessage
	 *            显示数据的TextView
	 */
	public GetBBSNewPostDataTask(Activity context, TextView showMessage) {
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
		String result = null;
		String reqMessage;
		try {
			// 发送请求, 并接收服务器响应
			String requestMethod = null;// 请求的方法名
			String latestDate = (String) params[0];
			reqMessage = XmlHandle.packXml("null", "null", "latestDate", latestDate);
			requestMethod = Config.GetBBSPostDateMethod;
			respMessage = WebUtil.getXmlByWeb(reqMessage, requestMethod);
			if (respMessage != null) {
				postProfileList = XmlHandle.getBBSPostDate(respMessage);
				result = "Success";
			}

		} catch (IOException e) {
			result = "Error!";
			e.printStackTrace();
		} catch (Exception e) {
			result = "Error!";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (result.equals("Success")) {
			Toast.makeText(mContext, "解析成功\n获得" + postProfileList.size() + "条数据", Toast.LENGTH_LONG).show();
			StringBuilder sb = new StringBuilder();
			sb.append(postProfileList.toString());
			mShowMessage.setText(sb.toString() + "\n");
			
			mShowMessage.append(respMessage + "\n");
			
			for (int i = 0; i < postProfileList.size(); i++) {

				PostProfile postProfile = postProfileList.get(i);
				// 9张图片
				if (!postProfile.post_picture1.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");
					
//					String u = "http://123.207.117.75:8092/DoorServer/Upload/BBS/" 
//							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture1;
					
					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture1;

					mShowMessage.append(urlPath + "\n");

					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture1).execute(urlPath);
				}
				if (!postProfile.post_picture2.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture2;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture2).execute(urlPath);
				}
				if (!postProfile.post_picture3.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture3;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture3).execute(urlPath);
				}
				if (!postProfile.post_picture4.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture4;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture4).execute(urlPath);
				}
				if (!postProfile.post_picture5.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture5;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture5).execute(urlPath);
				}
				if (!postProfile.post_picture6.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture6;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture6).execute(urlPath);
				}
				if (!postProfile.post_picture7.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture7;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture7).execute(urlPath);
				}
				if (!postProfile.post_picture8.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture8;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture8).execute(urlPath);
				}
				if (!postProfile.post_picture9.equals("null")) {
					String time = postProfile.post_publishdt.replaceAll("[:,\\s+,-]", "");

					String urlPath = "http://192.168.23.1:8090/MyServer/upload/BBS" + "/"
							+ postProfile.post_phone + "/" + time + "/" + postProfile.post_picture9;

					mShowMessage.append(urlPath + "\n");
					new DownLoadPicTask(mContext, false, null, true, postProfile.post_phone,
							postProfile.post_publishdt, postProfile.post_picture9).execute(urlPath);
				}
			}

		} else if (result.equals("Error!")) {
			Toast.makeText(mContext, "获取失败", Toast.LENGTH_LONG).show();
			mShowMessage.setText(respMessage);
		}
		// 取消进度条
		progressDialog.cancel();
	}

}