package com.linfeng;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatActivity extends Activity {

	TextView tvContent;    //聊天内容
	Button btnSend;			//发送信息按钮
	EditText etMessage;		//输入文本框
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * 使输入框不被弹出的键盘挡住
		 */					
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_chat);
		
		tvContent = (TextView)this.findViewById(R.id.tv_content);
		etMessage = (EditText)this.findViewById(R.id.et_message);
		btnSend = (Button)this.findViewById(R.id.btn_sendMessage);
		btnSend.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
