package com.jittr.android.fs.utils;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.client.utils.URLEncodedUtils;

public class URLBuilder {

	
	public static String createUrl(String url, NVPair nvps[]) {
		
		String queryStr = createQueryStr(nvps);
		System.out.println("QueryStr "+queryStr);
		String finalUrl =url+"?"+queryStr;
		System.out.println(" Final Url returning :"+finalUrl);
        return finalUrl;
	}
	
	public static String createQueryStr(NVPair nvps[]) {
		
		String queryStr = "";
		for(int i=0;i<nvps.length;i++) {
			if(nvps[i].getValue() !=null && !"".equals(nvps[i].getValue())) {
				String tempPair = nvps[i].getName()+"="+URLEncoder.encode(nvps[i].getValue())+"&";
				queryStr = queryStr+tempPair;
			}
		}
		//Discard extra &
		System.out.println("Before queryStr "+queryStr);
		String finalStr = queryStr.substring(0, queryStr.length()-1);
		System.out.println("After finalStr "+finalStr);
		return finalStr;
	}
	
	public static String createUrl(String url, HashMap<String, String> criteria) {
		
		String queryStr = createQueryStr(criteria);
		System.out.println("QueryStr "+queryStr);
		String finalUrl =url+"?"+queryStr;
		System.out.println(" Final Url returning :"+finalUrl);
        return finalUrl;
	}
	
	public static String createQueryStr(HashMap<String, String> criteria) {
		
		String queryStr = "";
		Iterator<String> iterator = criteria.keySet().iterator(); 
		while(iterator.hasNext()) {
			String key = iterator.next();
			String value = criteria.get(key);
			if(!"".equals(value)) {
				String tempPair = key+"="+URLEncoder.encode(value)+"&";
				queryStr = queryStr+tempPair;
			}
		}
		System.out.println("Before queryStr "+queryStr);
		String finalStr = queryStr.substring(0, queryStr.length()-1);
		System.out.println("After finalStr "+finalStr);
		return finalStr;
	}
	
	
}
