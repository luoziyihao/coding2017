package com.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	@Override
	public void run(){	
		RandomAccessFile file = null; 
		try {
			 //在指定的目录下，创建一个同等大小的文件
			file = new RandomAccessFile("d:/testDown.jpg", "rw");
	        //设置文件大小，占位
	        file.setLength(conn.getContentLength());
	        
			byte[] bs = conn.read(startPos, endPos);
			
			file.seek(startPos);//设置从文件的某个位置开始写数据。
			
			file.write(bs);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
