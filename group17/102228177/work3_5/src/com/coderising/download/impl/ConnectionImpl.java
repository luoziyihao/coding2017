package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL url;
	private HttpURLConnection con;
	private InputStream is;
	
	public ConnectionImpl(String urlPath) {
		try {
			//声明URL
			this.url = new URL(urlPath);
			//获取链接
			con = (HttpURLConnection) url.openConnection();
			//设置请求方式
			con.setRequestMethod("GET");
	        is = con.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 给定开始和结束位置， 读取数据， 返回值是字节数组
	 * @param startPos 开始位置， 从0开始
	 * @param endPos 结束位置
	 * @return
	 */
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte[] buf = new byte[endPos-startPos];
		is.read(buf, startPos, startPos);
		return buf;
	}
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	@Override
	public int getContentLength() {
		int code;
		try {
			code = con.getResponseCode();
			if (code==200){
				//服务器返回内容的长度，本质就是文件的长度
				return con.getContentLength();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 关闭连接
	 */
	@Override
	public void close() {
		
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
