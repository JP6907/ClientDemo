package com.jp.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jp.ulit.Config;
import com.jp.ulit.PostProfile;
import com.jp.ulit.Reply;
import com.jp.ulit.WebUtil;
import com.jp.ulit.XmlHandle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 获取论坛回复信息
 * @author Administrator
 *
 */
public class GetBBSReplyDataTask extends AsyncTask {


	private String respMessage = null;
	private Activity mContext;
	private TextView mShowMessage;
	private ProgressDialog progressDialog;
	private List<Reply> replyList = new ArrayList<Reply>();

	/**
	 * 
	 * @param context
	 *            Activity
	 * @param showMessage
	 *            显示数据的TextView
	 */
	public GetBBSReplyDataTask(Activity context, TextView showMessage) {
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
				String pointID = (String) params[0];
				reqMessage = XmlHandle.packXml("null", "null", "pointID", pointID);
				requestMethod = Config.GetBBSReplyDateMethod;
				respMessage = WebUtil.getXmlByWeb(reqMessage, requestMethod);
				if (respMessage != null) {
					replyList = XmlHandle.getBBSReplyDate(respMessage);
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
			Toast.makeText(mContext, "解析成功\n获得" + replyList.size() + "条数据", 
					       Toast.LENGTH_LONG).show();;
			StringBuilder sb = new StringBuilder();
				sb.append(replyList.toString());
			mShowMessage.setText(sb.toString());
		} else if (result.equals("Error!")) {
			Toast.makeText(mContext, "获取失败", Toast.LENGTH_LONG).show();
			mShowMessage.setText(respMessage);
		}
		// 取消进度条
		progressDialog.cancel();
	}

}