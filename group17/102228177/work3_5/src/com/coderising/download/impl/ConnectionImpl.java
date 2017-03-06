package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private URL url;
	private HttpURLConnection con;
	private RandomAccessFile file;
	private InputStream is;
	public ConnectionImpl(String urlPath) {
		try {
			//声明URL
			this.url = new URL(urlPath);
			//获取链接
			con = (HttpURLConnection) url.openConnection();
			//设置请求方式
			con.setRequestMethod("GET");
		    //在指定的目录下，创建一个同等大小的文件
	        file = new RandomAccessFile("d:/testDown", "rw");//创建一个相同大小的文件。
	        //设置文件大小，占位
	        file.setLength(getContentLength());
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
		
		
		return null;
	}
	/**
	 * 得到数据内容的长度
	 * @return
	 */
	@Override
	public int getContentLength() {
		
		return con.getContentLength();
	}

	/**
	 * 关闭连接
	 */
	@Override
	public void close() {
		if(file != null){
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
