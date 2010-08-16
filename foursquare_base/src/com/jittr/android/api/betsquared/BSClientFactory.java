package com.jittr.android.api.betsquared;

import com.jittr.android.fs.core.FSClientInterface;
import com.jittr.android.fs.impl.FSClientAPIImpl;

/**
* @author rg230v
*
*/


public class BSClientFactory {
	

	private BSClientFactory() {
		
	}
	static BSClientInterface createXMLClient(String usrName,String pwd) {
		BSClientInterface bs = new BSClientAPIImpl("xml",usrName,pwd);
		return bs;
		
	}
	static BSClientInterface createJSONClient(String usrName,String pwd) {
		BSClientInterface bs = new BSClientAPIImpl("json",usrName,pwd);
		return bs;
	}
	
	
}
