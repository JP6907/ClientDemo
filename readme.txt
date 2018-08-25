
	实现登陆上传，多文件文本同时上传
2016.07.13 12:10
	实现单图片从服务器下载
2016.7.17 20：55
	增加getFileListTask，实现从服务器获取指定用户(手机号码)下文件的文件目录
2016.07.13 23：08
	DownLoadFileTask,从服务器指定URl资源地址下载任意形式文件
	
	******存在问题******
		图片下载正常，大文件如pdf文件，mp3文件下载时文件会变大，损坏，
	********************
			
2016.07.14 21:55
	实现获取指定用户(手机号码)在服务器上目录文件夹的目录并自动下载所有图片
	在 getFileListTask 的 onPostExecute 方法调用 DownLoadPicTask 任务，下载图片
2016.07.15 10:54
	重构代码
	根据用户手机号码和发帖时间获取图片数据
2016.07.16 22:48
	XmlHandle.getBBSPostDate    XmlHandle.getBBSReplyDate
	实现对帖子xml和回复xml格式的正确解析
	有微小错误    
2016.07.16 22：59
	修正微小错误
2016.07.16 23:11
	将 GetBBSRDataTask 类分开成 GetBBSPostDataTask 类和 GetBBSReplyDataTask 类
2016.07.18 16:05
	修改 HttpBBSAssist 类的uploadfile方法 改为
	uploadFile(String userName, String userPhone, String title, String content,List<File> files)
	********************
	若为
	uploadFile(String userName, String userPhone, String title, String content,File[] files)
	会出错
	********************
2016.07.18 17:25
	修改 UploadFileTask 增加数据返回 JSON 格式
2016.07.25 19:45
	传去本地最新时间
	实现下载最新帖子资源 包含内容和图片，并保存图片
	new GetBBSNewPostDataTask(BBSActivity.this, tvBBS).execute("2000-12-30 10:30:00");