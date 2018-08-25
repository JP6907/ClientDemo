package com.jp.activity;

import java.io.File;

import com.jp.task.DownLoadFileTask;
import com.jp.task.DownLoadPicTask;
import com.jp.task.JsonLoginTask;
import com.jp.task.ParamsLoginTask;
import com.jp.task.RegisterTask;
import com.jp.task.UploadFileTask;
import com.jp.task.XmlLoginTask;
import com.jp.task.getFileListTask;
import com.jp.ulit.Config;
import com.jp.ulit.FileUtil;
import com.jp.ulit.IPUtil;
import com.linfeng.ChatActivity;
import com.linfeng.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

	EditText et_name;
	EditText et_pwd;
	TextView show_login;
	TextView tvUploadFilePath;
	TextView uploadInfo;
	Button btn_login;
	Button btn_register;
	Button btnJsonLogin;
	Button btnXmlLogin;
	Button btnDownloadPic;
	Button btnChoosePic;
	Button btnUploadFile;
	Button btnChooseFile;
	Button btnChatRoom;
	ProgressDialog progressDialog;
	Context mContext;

	ImageView downLoadImage;
	TextView tvFileList;
	Button btnGetFileList;
	Button btnDownLoadFiles;
	Button btnGetBBSData;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mContext = getApplicationContext();
		btn_login = (Button) findViewById(R.id.btn_login);
		et_name = (EditText) findViewById(R.id.et_name);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		show_login = (TextView) findViewById(R.id.show_login);
		uploadInfo = (TextView) this.findViewById(R.id.upload_info);
		tvUploadFilePath = (TextView) this.findViewById(R.id.upload_file_path);
		downLoadImage = (ImageView) this.findViewById(R.id.download_image);
		tvFileList = (TextView) this.findViewById(R.id.tv_filelist);
		btnGetFileList = (Button) this.findViewById(R.id.btn_get_filelist);
		btnDownLoadFiles = (Button) this.findViewById(R.id.btn_download_files);
		btnGetBBSData = (Button) this.findViewById(R.id.btn_bbs);
		btnChatRoom = (Button) this.findViewById(R.id.btn_chat);

		btn_login.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				// 通过AsyncTask类提交数捿 异步显示
				new ParamsLoginTask(MainActivity.this, show_login).execute(et_name.getText().toString(),
						et_pwd.getText().toString());
			}
		});

		btnJsonLogin = (Button) this.findViewById(R.id.btn_json_login);
		btnJsonLogin.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new JsonLoginTask(MainActivity.this, show_login).execute(et_name.getText().toString(),
						et_pwd.getText().toString());
			}

		});
		btnXmlLogin = (Button) this.findViewById(R.id.btn_xml_login);
		btnXmlLogin.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new XmlLoginTask(MainActivity.this, show_login).execute(et_name.getText().toString(),
						et_pwd.getText().toString());
			}

		});
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new RegisterTask(MainActivity.this, show_login).execute(et_name.getText().toString(),
						et_pwd.getText().toString());
			}

		});

		btnChoosePic = (Button) this.findViewById(R.id.btn_choose_pic);
		btnChoosePic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * ѡձͼƬ
				 */
			}
		});

		btnDownloadPic = (Button) this.findViewById(R.id.btn_download_pic);
		btnDownloadPic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Ђ՘ͼƬ
				 */
				final String urlPath = "http://192.168.191.1:8090/MyServer/upload/test.jpg";
				String url = "http://192.168.191.1:8090/MyServer/upload/BBS/15521374237/21051030103000/pic1.jpg";
				new DownLoadPicTask(MainActivity.this, true, downLoadImage,
									true, "15521374237", "2016-07-15 09:44:55" ,"test.jpg").execute(urlPath);

			}
		});

		btnChooseFile = (Button) this.findViewById(R.id.btn_choose_file);
		btnChooseFile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new FileUtil(MainActivity.this).showFileChooser();
			}

		});
		btnUploadFile = (Button) this.findViewById(R.id.btn_upload_file);
		btnUploadFile.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 上传图片
				 */
				String filePath = tvUploadFilePath.getText().toString().trim();
				File file = new File(tvUploadFilePath.getText().toString().trim());
				if (!file.exists()) {
					Toast.makeText(MainActivity.this, "文件不存在！", Toast.LENGTH_LONG).show();
				} else {
					new UploadFileTask(MainActivity.this).execute(file);
				}
			}
		});
		
		btnGetFileList.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				// 获取资源列表并下载
				String userPhone = "15521374237";
				String postDate = "2016-07-15 09:44:55";
				new getFileListTask(MainActivity.this, tvFileList).execute(userPhone,postDate);
				/**
				 * 在onPostExecute函数调用DownLoadPicTask启动下载任务
				 */

			}
		});
		
		btnGetBBSData.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				/**
				 * BBS
				 */	
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, BBSActivity.class);
				startActivity(intent);
			}
		});
		btnChatRoom.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				/**
				 * Chating Rom
				 */
				
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ChatActivity.class);
				startActivity(intent);
			}
		});
		
		btnDownLoadFiles.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				// Ђ՘נτݾ
				File path = new File("/sdcard/DownloadTest/15521374237");
				if (!path.exists()) {// Ŀ¼һզ՚׵ܘfalse
					path.mkdirs();// ԴݨһٶĿ¼
				}
				File file = new File("/sdcard/DownloadTest/15521374237/" + "test.jpg");
				if(file.exists()){
					file.delete();
				}
				String filePath = "/sdcard/DownloadTest/15521374237/test.jpg";
				//String url = "http://192.168.23.1:8090/MyServer/upload/test.jpg";
				String url = Config.DownBBSPicPath + "/15521374237/test.jpg";
				new DownLoadFileTask(MainActivity.this, filePath).execute(url);
			}
		});
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case FileUtil.FILE_SELECT_CODE:
			if (resultCode == RESULT_OK) {
				// Get the Uri of the selected file
				Uri uri = data.getData();
				String path = FileUtil.getPath(this, uri);
				tvUploadFilePath.setText(path);
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.btn_get_ip:
			Config.IP = IPUtil.getLocalIP(); //获取当前IP地址
			show_login.setText(Config.IP + ":" + Config.PORT);
			Toast.makeText(this, "当前IP为：" + Config.IP, Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_set_ip:
			final EditText et = new EditText(this);
			new AlertDialog.Builder(this).setTitle("设置IP地址").setView(et)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							Config.IP = et.getText().toString();
							show_login.setText(Config.IP + ":" + Config.PORT);
							Toast.makeText(MainActivity.this, "当前IP为:" + Config.IP, Toast.LENGTH_LONG).show();
						}
					}).setNegativeButton("取消", null).show();
			break;
		case R.id.btn_tomcat6:
			Config.PORT = Config.PORT6;
			show_login.setText(Config.IP + ":" + Config.PORT);
			Toast.makeText(this, "选择Tomcat6，当前的IP地址为" + Config.PORT6, Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_tomcat7:
			Config.PORT = Config.PORT7;
			show_login.setText(Config.IP + ":" + Config.PORT);
			Toast.makeText(this, "选择Tomcat7，当前的IP地址为" + Config.PORT7, Toast.LENGTH_LONG).show();
			break;
		default:
		}
		return super.onOptionsItemSelected(item);
	}

}
