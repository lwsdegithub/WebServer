import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(Constant.DEFAULT_PORT);
			while(true) {
				Socket socket=serverSocket.accept();
				System.out.println("有客户端连接");
				System.out.println("文件列表："+Util.getFiles());
				
				MyThread myThread=new MyThread(socket);
				myThread.start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
