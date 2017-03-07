package com.coderising;

import java.io.InputStream;

import java.io.RandomAccessFile;

import java.net.HttpURLConnection;

import java.net.URL;

/**

 * 多线程下载

 * 1：使用RandomAccessFile在任意的位置写入数据。

 * 2：需要计算第一个线程下载的数据量，可以平均分配。如果不够平均时，

 *    则直接最后一个线程处理相对较少的数据

 * 3：必须要在下载之前准备好相同大小的文件，通过文件头获取

 */

public class MultiThreadDownload {

    public static void main(String[] args) throws Exception {

       //1：声明文件名和下载的地址

       String fileName = "sy_71272488121.jpg";

       String urlStr = "http://img05.tooopen.com/images/20140919";

       //2：声明Url

       URL url = new URL(urlStr+"/"+fileName);

       //3：获取连接

       HttpURLConnection con =

           (HttpURLConnection) url.openConnection();

       //4:设置请求方式

//       con.setRequestMethod("GET");

       //5:获取请求头,即文件的长度

       int length = con.getContentLength();//获取下载文件的长度，以计算每个线程应该下载的数据量。
       
       new MyDownThread(fileName, 0, length-1 ,url).start();

       //6：在指定的目录下，创建一个同等大小的文件

//       RandomAccessFile file = new RandomAccessFile("d:/"+fileName, "rw");//创建一个相同大小的文件。

       //7:设置文件大小，占位

//       file.setLength(length);//设置文件大小。

 

//       file.close();

       //8：定义线程个数
/*
       int size = 3;

       //9:计算每一个线程应该下载多少字节的数据，如果正好整除则最好，否则加1

       int block = length/size==0?length/size:length/size+1;//计算每个线程应该下载的数据量。


       System.err.println("每个线程应该下载："+block);

       //10：运行三个线程并计算从哪个字节开始到哪一个字节结束

       for(int i=0;i<size;i++){

           int start = i*block;

           int end = start+(block-1);//计算每一个线程的开始和结束字节。

 

         System.err.println(i+"="+start+","+end);

           new MyDownThread(fileName, start, end,url).start();

       }
*/
    }

    static class MyDownThread extends Thread{

       //定义文件名

       private String fileName;

       //定义从何地开始下载

       private int start;

       //定义下载到哪一个字节

       private int end;

       private URL url;

       public MyDownThread(String fileName,int start,int end,URL url){

           this.fileName=fileName;

           this.start=start;

           this.end=end;

           this.url=url;

       }

       @Override

       public void run() {

           try{

              //11:开始下载

              HttpURLConnection con =

                     (HttpURLConnection) url.openConnection();

              con.setRequestMethod("GET");

              //12：设置分段下载的请求头

              con.setRequestProperty("Range","bytes="+start+"-"+end);//设置从服务器上读取的文件块。

 

              //13:开始下载，需要判断206

              if(con.getResponseCode()==206){//访问成功，则返回的状态码为206。 

                  InputStream in = con.getInputStream();

                  //14:声明随机写文件对象，注意rwd是指即时将数据写到文件中，而不使用缓存区

                  RandomAccessFile out = new RandomAccessFile("d:/"+fileName,"rwd");

                  out.seek(start);//设置从文件的某个位置开始写数据。 

                  byte[] b=new byte[1024];

                  int len = 0;

                  while((len=in.read(b))!=-1){

                     out.write(b,0,len);

                  }

                  out.close();

                  in.close();

              }

              System.err.println(this.getName()+"执行完成");

           }catch(Exception e){

              throw new RuntimeException(e);

           }

       }

    }

}
