package com.eboji.bootstrap;

import org.apache.thrift.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TServerStartThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(TServerStartThread.class);
	
	private TServer tserver = null;
	
	public TServerStartThread(TServer tserver) {
		this.tserver = tserver;
	}
	
	@Override
	public void run() {
		try {
			if(tserver != null) {
				tserver.serve();
			}
		} catch (Exception e) {
			logger.warn("serve warn: " + e.getMessage());
		}
	}
}
