package com.jp.ulit;

public class Config {
	
		//public static String CloudIP = "123.207.117.75";
	
		public static String IP = "192.168.23.1";
		public static final String PORT6 = "8088";
		public static final String PORT7 = "8090";
		public static final String PORT8 = "8092";
		public static String PORT = PORT8;
    	public static String UriAPI ="http://" + IP + ":" + PORT + "/MyServer/servlet/";
    	public static final String ParamsMethod = "ParamsServlet";
    	public static final String JsonMethod = "JosnLoginServlet";
    	public static final String XmlMethod = "XmlLoginServlet";
    	public static final String RegisterMethod = "RegisterServlet";
    	public static final String UploadMethod = "UploadServlet";
    	public static final String UploadImageMethod = "UploadImageServlet";
    	public static final String UpLoadFileMethod = "UploadFileServlet";
    	public static final String FilePostMethod = "FilePostServlet";
    	public static final String GetFileListMethod = "GetFileListMethodServlet";
    	public static final String GetBBSPostDateMethod = "GetPostDataServlet";
    	public static final String GetBBSReplyDateMethod = "GetReplyDataServlet";
    	
    	public static final String DownBBSPicPath = "http://" + IP + ":" + PORT + 
    											"/MyServer/upload/BBS";
    	
    	public static int UserExit = 1;
    	public static int UserNotExit = 2;
    	public static int RegisterSuccess = 3;
    	public static int WrongPassWord = 4;
    	public static int LoginSuccess = 5;
    	public static int TimeOut = 6;
   
}
	