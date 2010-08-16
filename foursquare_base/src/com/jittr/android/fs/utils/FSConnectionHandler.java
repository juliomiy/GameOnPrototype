package com.jittr.android.fs.utils;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;



import twitter4j.internal.http.BASE64Encoder;

/**
 * @author rg230v
 *
 */

public class FSConnectionHandler {

	
	String username = null;
	String pwd  = null;
	
	public FSConnectionHandler(String aUserName, String aPwd){
       this.username = aUserName;
       this.pwd = aPwd;
       System.setProperty("http.proxyHost", "ftpproxy.wdc.cingular.net");
       System.setProperty("http.proxyPort", "8080");

    }

        
    public String getContent(URL fsUrl) {
    	
    	HttpURLConnection connection = null;	
    	try {
    		//Open and set up the  connection
    		connection = (HttpURLConnection)fsUrl.openConnection();  
    		connection.setRequestProperty("Content-Type","application/xml"); 
    		connection.setRequestMethod("GET");
    		connection.setDoOutput(true);
    		String cred = username+":"+pwd;
    		String encoding = BASE64Encoder.encode(cred.getBytes());
    		connection.setRequestProperty ("Authorization", "Basic " + encoding);
    		connection.setRequestProperty("Content-Type","application/xml");
    		connection.setRequestProperty("User-Agent", "JITTR/Gameon_"+Constants.GameOn_Version);
    		//Connect
    		connection.connect();
    		//Get Response Code
    		Log.d("Log ","Response code :"+connection.getResponseCode());
    		//Read Content
    		BufferedReader rd  = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
    		 StringBuilder sb = new StringBuilder();
    		 String line = null;
    		  while ((line = rd.readLine()) != null)   {
    			  sb.append(line + '\n');
    		  }
    	    Log.d(" ",sb.toString());
    		
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
        	if(connection !=null)
        		connection.disconnect();
        }
    }
    
    public String submitToFs(URL fsUrl,NVPair nvps[]) {
    	HttpURLConnection connection = null;
    	try {
    		connection = (HttpURLConnection)fsUrl.openConnection();  
    		connection.setRequestMethod("POST");
    		connection.setDoOutput(true);
    		String cred = username+":"+pwd;
    		String encoding = BASE64Encoder.encode(cred.getBytes());
    		connection.setRequestProperty ("Authorization", "Basic " + encoding);
    		//connection.setRequestProperty("Content-Type","application/xml");
    		connection.setRequestProperty("User-Agent", "JITTR/Gameon_"+Constants.GameOn_Version);
    		
    		String queryStr = URLBuilder.createQueryStr(nvps);
    		Log.d("", queryStr);
    		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
    		out.write(queryStr);
    		out.flush();
    		out.close();
    		
    		//Get Response Code
    		Log.d("Log ","Response code :"+connection.getResponseCode());
    		//Read Content
    		BufferedReader rd  = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
    		 StringBuilder sb = new StringBuilder();
    		 String line = null;
    		  while ((line = rd.readLine()) != null)   {
    			  sb.append(line + '\n');
    		  }
    	    Log.d(" ",sb.toString());
    		
    		return sb.toString();
    		
    	}
    	catch(Exception e) {
    		return null;
    	}
    	finally {
    		if(connection !=null)
        		connection.disconnect();
    	}
    	
    }

}
