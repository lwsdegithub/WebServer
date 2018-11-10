import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class Response {
	private OutputStream outputStream;
	private String url;
	public Response(OutputStream outputStream,String url) {
		this.outputStream=outputStream;
		this.url=url;
	}
	//响应
	public void send() {
		//如果url为"/"，则返回index.html
		String response=null;
		//请求根目录
		if (url.equals("/")) {
			this.url="/index.html";
			response=getHeadFromCode(Constant.STATUS_200)+getContentFromUrl();
			this.writeStringToOutputStream(response);
		}else {
			//请求普通文件
			if (Util.getFiles().contains(url.substring(1))) {
				response=getHeadFromCode(Constant.STATUS_200)+getContentFromUrl();
				this.writeStringToOutputStream(response);
			}else{
				//文件不存在
				this.url="/404.html";
				response=getHeadFromCode(Constant.STATUS_404)+getContentFromUrl();
				this.writeStringToOutputStream(response);
			}
		} 
	}
	//把整个报文写进outputStream；
	private void writeStringToOutputStream(String string) {
		try {
			byte[] bs= string.getBytes();
			outputStream.write(bs);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				//刷新输出流
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//codeMessage为404 Not Found或者200 OK，返回的是HTTP返回头部
	private String getHeadFromCode(String statusMessage) {
		Date date=new Date();
		// \r \n分别为回车换行
		String string = "HTTP/1.1 "+statusMessage+"\r\n"+
						"Connection: close\r\n"+
						"Date: "+date.toGMTString()+"\r\n"+
						"Server: Apache/2.2.3 (CentOS)\r\n"+
						"Last-Modified: Tue, 09 Aug 2018 15:11:03 GMT\r\n"+
						"Content-Type: text/html\r\n"+
						"\r\n";
		return string;
	}
	
	//从服务器中获取文件，也就是HTTP返回体
	private String getContentFromUrl() {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("./src/content"+url);
			StringBuffer stringBuffer = new StringBuffer(2048);
			int i = 0;
			byte[] buffer = new byte[2048];
			try {
				i = fileInputStream.read(buffer);
				//System.out.println(i);
				//System.out.println(buffer);
			} catch (IOException e) {
				e.printStackTrace();
				i = -1;
			}
			for (int j = 0; j < i; j++) {
				stringBuffer.append((char) (buffer[j]));
			}
			return stringBuffer.toString();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "error";
		
	}
}
