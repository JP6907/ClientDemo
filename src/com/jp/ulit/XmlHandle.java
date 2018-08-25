package com.jp.ulit;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlHandle {
	/*
	 * 将两种信息打包成xml格式
	 */
	public static String packXml(String node1, String node1Value, String node2, String node2Value) {
		if (node1Value.equals("")) {
			node1Value = " ";
		}
		if (node2Value.equals("")) {
			node2Value = " ";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<Information>");
		sb.append("    <" + node1 + ">" + node1Value + "</" + node1 + ">");
		sb.append("    <" + node2 + ">" + node2Value + "</" + node2 + ">");
		sb.append("</Information>");
		return sb.toString();
	}

	/*
	 * 从xml文件中解析出用户名和密码
	 */
	public static Map praseXml(String xml) throws Exception {
		Map<String, String> user = new HashMap<String, String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));
		NodeList list = document.getElementsByTagName("loginInformation");
		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			String userName = element.getElementsByTagName("userName").item(0).getFirstChild().getNodeValue();
			System.out.println("userName:" + userName);
			String passWord = element.getElementsByTagName("passWord").item(0).getFirstChild().getNodeValue();
			System.out.println("passWord:" + passWord);
			user.put("userName", userName);
			user.put("passWord", passWord);
		}
		return user;
	}

	/*
	 * 将手机号码打包成xml文件
	 */
	public static String packPhoneXml(String userPhone) {
		if (userPhone.equals("")) {
			userPhone = " ";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<PhoneInformation>");
		sb.append("    <userphone>" + userPhone + "</userphone>");
		sb.append("</PhoneInformation>");
		return sb.toString();
	}

	/*
	 * 将xml文件中的图片名解析出来
	 */
	public static List getPicListByXml(String xml) throws Exception {
		List<String> picList = new ArrayList<String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));
		NodeList list = document.getElementsByTagName("PictureInformation");
		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			NodeList childNodes = element.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node node1 = childNodes.item(j);
				if ("picture".equals(node1.getNodeName())) {
					String pic = node1.getFirstChild().getNodeValue();
					System.out.println(pic);
					picList.add(pic);
				}
			}
		}
		return picList;
	}
	/**
	 * 从xml文件获取帖子数据
	 * @param xml
	 * @return List<PostProfile>
	 * @throws Exception
	 */
	
	public static List<PostProfile> getBBSPostDate(String xml) throws Exception {
		List<PostProfile> postProfileList = new ArrayList<PostProfile>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new InputSource(new StringReader(xml)));
		NodeList list = document.getElementsByTagName("BBSInformation");
		
			Element element = (Element) list.item(0);
			NodeList childNodes = element.getChildNodes();
			for(int k = 0 ;k<childNodes.getLength();k++){
				Node node = childNodes.item(k);
			//获得PostProfile节点
			if(node.getNodeName().equals("PostProfile")){
				NodeList postProfileChildNodeList = node.getChildNodes();
				PostProfile postProfile = new PostProfile(); 
				for (int j = 0; j < postProfileChildNodeList.getLength(); j++) {
					Node node1 = postProfileChildNodeList.item(j);
					if ("post_id".equals(node1.getNodeName())) {
						String post_id = node1.getFirstChild().getNodeValue();
						postProfile.post_id = Integer.valueOf(post_id);
					} else if ("post_title".equals(node1.getNodeName())) {
						String post_title = node1.getFirstChild().getNodeValue();
						postProfile.post_title = post_title;
					} else if ("post_text".equals(node1.getNodeName())) {
						String post_text = node1.getFirstChild().getNodeValue();
						postProfile.post_text = post_text;
					} else if ("post_publisher".equals(node1.getNodeName())) {
						String post_publisher = node1.getFirstChild().getNodeValue();
						postProfile.post_publisher = post_publisher;
					} else if ("post_replynum".equals(node1.getNodeName())) {
						String post_replynum = node1.getFirstChild().getNodeValue();
						postProfile.post_replynum = Integer.valueOf(post_replynum);
					} else if ("post_publishdt".equals(node1.getNodeName())) {
						String post_publishdt = node1.getFirstChild().getNodeValue();
						postProfile.post_publishdt = post_publishdt;
					} else if ("post_newdt".equals(node1.getNodeName())) {
						String post_newdt = node1.getFirstChild().getNodeValue();
						postProfile.post_newdt = post_newdt;
					} else if ("post_picture1".equals(node1.getNodeName())) {
						String post_picture1 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture1 = post_picture1;
					} else if ("post_picture2".equals(node1.getNodeName())) {
						String post_picture2 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture2 = post_picture2;
					} else if ("post_picture3".equals(node1.getNodeName())) {
						String post_picture3 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture3 = post_picture3;
					} else if ("post_picture4".equals(node1.getNodeName())) {
						String post_picture4 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture4 = post_picture4;
					} else if ("post_picture5".equals(node1.getNodeName())) {
						String post_picture5 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture5 = post_picture5;
					} else if ("post_picture6".equals(node1.getNodeName())) {
						String post_picture6 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture6 = post_picture6;
					} else if ("post_picture7".equals(node1.getNodeName())) {
						String post_picture7 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture7 = post_picture7;
					} else if ("post_picture8".equals(node1.getNodeName())) {
						String post_picture8 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture8 = post_picture8;
					} else if ("post_picture9".equals(node1.getNodeName())) {
						String post_picture9 = node1.getFirstChild().getNodeValue();
						postProfile.post_picture9 = post_picture9;
					} else if ("post_phone".equals(node1.getNodeName())) {
						String post_phone = node1.getFirstChild().getNodeValue();
						postProfile.post_phone = post_phone;
					}
				}
				postProfileList.add(postProfile);
			}
		}
		return postProfileList;
		
	}
	/**
	 * 获取帖子回复数据
	 * @param xml
	 * @return List<Reply>
	 * @throws Exception
	 */
	public static List<Reply> getBBSReplyDate(String xml) throws Exception {
		List<Reply> replyList = new ArrayList<Reply>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new InputSource(new StringReader(xml)));
		NodeList list = document.getElementsByTagName("BBSInformation");
		Element element = (Element) list.item(0);
		NodeList childNodes = element.getChildNodes();
		for(int i = 0;i<childNodes.getLength();i++){
			Node node = childNodes.item(i);
			if(node.getNodeName().equals("Reply")){
				//获得Reply节点
				NodeList replyChildNodeList = node.getChildNodes();
				Reply reply = new Reply(); 
				for(int j = 0;j<replyChildNodeList.getLength();j++){
					Node node1 = replyChildNodeList.item(j);
					if ("reply_id".equals(node1.getNodeName())) {
						String reply_id = node1.getFirstChild().getNodeValue();
						reply.reply_id = Integer.valueOf(reply_id);
					} else if ("reply_postid".equals(node1.getNodeName())) {
						String reply_postid = node1.getFirstChild().getNodeValue();
						reply.reply_postid = Integer.valueOf(reply_postid);;
					} else if ("reply_judge".equals(node1.getNodeName())) {
						String reply_judge = node1.getFirstChild().getNodeValue();
						reply.reply_judge = Integer.valueOf(reply_judge);;
					} else if ("reply_datetime".equals(node1.getNodeName())) {
						String reply_datetime = node1.getFirstChild().getNodeValue();
						reply.reply_datetime = reply_datetime;
					} else if ("reply_responder".equals(node1.getNodeName())) {
						String reply_responder = node1.getFirstChild().getNodeValue();
						reply.reply_responder = reply_responder;
					} else if ("reply_publisher".equals(node1.getNodeName())) {
						String reply_publisher = node1.getFirstChild().getNodeValue();
						reply.reply_publisher = reply_publisher;
					} else if ("reply_floor".equals(node1.getNodeName())) {
						String reply_floor = node1.getFirstChild().getNodeValue();
						reply.reply_floor = Integer.valueOf(reply_floor);
					} else if ("reply_text".equals(node1.getNodeName())) {
						String reply_text = node1.getFirstChild().getNodeValue();
						reply.reply_text = reply_text;
					}
				}
				replyList.add(reply);
			}
		}
		return replyList;		
	}
}
