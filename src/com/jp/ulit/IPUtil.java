package com.jp.ulit;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IPUtil {
	public static String getLocalIP(){
		 // IP为4个字节
	    String strIP = null;
	    try {
	        // 通用获得本地IP方法,
	        for (Enumeration<NetworkInterface> en = NetworkInterface
	                .getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf
	                    .getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                 
	                if (!inetAddress.isLoopbackAddress()
	                        && !inetAddress.isLinkLocalAddress()) {
	                    strIP = inetAddress.getHostAddress().toString();// 此方法正确
	                    //Log.v("breakPoint", "正在打印获得的本地IP地址");
	                    System.out.println("正在打印获得的本地IP地址==" + strIP);
	                    System.out
	                            .println("正在打印获得的本地IP地址inetAddress.getHostAddress()=="
	                                    + inetAddress.getHostAddress());
	                    System.out.println("inetAddress.getAddress()="
	                            + inetAddress.getAddress());
	                    return strIP;
	                }
	            }
	        }
	        return strIP;
	 
	    } catch (Exception e) {
	    }
	    return strIP;
	}
}
