import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyThread extends Thread {
	private Socket socket;
	public MyThread(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		InputStream inputStream=null;
		OutputStream outputStream=null;
		try {
			inputStream=socket.getInputStream();
			outputStream=socket.getOutputStream();
			
			String request=Util.getStringFromInputStream(inputStream);
			String url=Util.getUrlFromRequestHead(request);
			
			System.out.println("请求的文件:"+url.substring(1));
			//url为/或/index.html
			Response response=new Response(outputStream, url);
			response.send();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//处理完成，关闭流
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
