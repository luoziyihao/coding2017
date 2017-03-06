package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {
	
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 */
	@Override
	public Connection open(String url) throws ConnectionException {
		try {
			URL u = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) u.openConnection();
			InputStream is = httpURLConnection.getInputStream();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}

}
