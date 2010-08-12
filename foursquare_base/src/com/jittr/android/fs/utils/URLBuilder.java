package com.jittr.android.fs.utils;

import java.net.URLEncoder;

import org.apache.http.client.utils.URLEncodedUtils;

public class URLBuilder {

	
	public static String createUrl(String url, NVPair nvps[]) {
		
		String queryStr = "";
		for(int i=0;i<nvps.length;i++) {
			if(nvps[i].getValue() !=null && !"".equals(nvps[i].getValue())) {
				String tempPair = nvps[i].getName()+"="+nvps[i].getValue()+"&";
				queryStr = queryStr+tempPair;
			}
		}
		System.out.println("QueryStr "+queryStr);
		String finalUrl =url+"?"+queryStr;
		//ENcode url for spaces TBD
		System.out.println(" Final Url returning :"+finalUrl);
        return finalUrl;
	}
	
	
}
