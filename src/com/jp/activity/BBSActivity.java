package com.jp.activity;

import com.jp.task.GetBBSNewPostDataTask;
import com.jp.task.GetBBSReplyDataTask;
import com.linfeng.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BBSActivity extends Activity {

	private Button  btnGetPostData;
	private Button btnGetReplyData;
	private TextView tvBBS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bbs);
		
		tvBBS = (TextView) this.findViewById(R.id.tv_bbs);
		btnGetPostData = (Button) this.findViewById(R.id.btn_get_postdata);
		btnGetReplyData = (Button) this.findViewById(R.id.btn_get_replydata);
		
		btnGetPostData.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				/**
				 * 获取帖子数据
				 * 		获取文本数据
				 * 		获取图片数据
				 */
				new GetBBSNewPostDataTask(BBSActivity.this, tvBBS)
											.execute("2000-12-30 10:30:00");
				
			}
		});
		btnGetReplyData.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				/**
				 * 获取回复数据
				 * 		获取文本数据
				 */
				new GetBBSReplyDataTask(BBSActivity.this, tvBBS)
				.execute("1");
				
			}
		});
	}
}
