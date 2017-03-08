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
		RandomAccessFile out = null;
		try {
			//每次都要创建新的RandomAccessFile对象
            //声明随机写文件对象，注意rwd是指即时将数据写到文件中，而不使用缓存区
            out = new RandomAccessFile("d:/test.jpg","rwd");
            
			out.seek(startPos);//设置从文件的某个位置开始写数据。 
			  
			out.write(conn.read(startPos, endPos));
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
