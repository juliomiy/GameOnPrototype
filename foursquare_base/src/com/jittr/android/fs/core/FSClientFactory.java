package com.jittr.android.fs.core;

import com.jittr.android.fs.impl.FSClientAPIImpl;

/**
 * @author rg230v
 *
 */

public class FSClientFactory {

	private FSClientFactory() {
		
	}
	static FSClientInterface createXMLClient(String usrName,String pwd) {
		FSClientAPIImpl fs = new FSClientAPIImpl("xml",usrName,pwd);
		return fs;
		
	}
	static FSClientInterface createJSONClient(String usrName,String pwd) {
		FSClientAPIImpl fs = new FSClientAPIImpl("json",usrName,pwd);
		return fs;
	}
}
