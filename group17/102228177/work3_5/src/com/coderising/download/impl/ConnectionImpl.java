package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	public HttpURLConnection con;
	private InputStream is;
	private RandomAccessFile out;
	
	public ConnectionImpl(String urlPath) {
		try {
			//声明URL
			URL	url = new URL(urlPath);
			//获取链接
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			//获取输入流
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
		
		 //设置分段下载的请求头

//        con.setRequestProperty("Range","bytes="+startPos+"-"+startPos);//设置从服务器上读取的文件块。
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        byte[] b=new byte[1024];

        int len = 0;

        while((len=is.read(b))!=-1){
        	outputStream.write(b,0,len);
        }
       
        return outputStream.toByteArray();
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
		if(out != null){
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
