import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Util {
	//���������л�ȡ����
	public static String getStringFromInputStream(InputStream inputStream) {
		StringBuffer stringBuffer = new StringBuffer(2048);
		int i = 0;
		byte[] buffer = new byte[2048];
		try {
			i = inputStream.read(buffer);
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
		// �᷵�����µ�������
		/***
		 * GET /index.html HTTP/1.1 Host: localhost:8080 Connection: keep-alive
		 * Cache-Control: max-age=0 Upgrade-Insecure-Requests: 1 User-Agent: Mozilla/5.0
		 * (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)
		 * Chrome/68.0.3440.84 Safari/537.36 Accept:
		 * text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*\/*;q=0.8
		 * Accept-Encoding: gzip, deflate, br Accept-Language: zh-CN,zh;q=0.9,en;q=0.8
		 */
	}
	
	//���������л�ȡ����URL
	public static String getUrlFromRequestHead(String string) {
		//��һ�е������ո�֮��
		//����GET����
		int index1=string.indexOf("/");
		int index2=string.indexOf(" ", index1+1);
		return string.substring(index1, index2);
	}
	
	//��ȡ��ǰ·���������ļ����б�
	public static List<String> getFiles(){
		List<String> fileNames=new ArrayList<>();
		//��Ҫ��html�ļ����浽content�ļ�����
		File file=new File("./src/content");
		for(File f : file.listFiles()) {
			fileNames.add(f.getName());
		}
		return fileNames;
	}
	
}
