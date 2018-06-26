package elgamal;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileReader;
import java.io.IOException;  
  
public class ReadFile {  
	
    public static String txt2String(File file) throws IOException{  
    	StringBuilder result = new StringBuilder(); 
        // �������ݵ�����ͨ��  
        FileReader fileReader = new FileReader(file);  
        //���������ַ������ȡ�ļ�����  
        char[] buf = new char[1024];  
        int length = 0 ;
        
        while((length = fileReader.read(buf))!=-1){  
            result.append(new String(buf,0,length));  
        }  
    	// ����һ��String����
        return result.toString();
    }  
      
    public static void main(String[] args) throws IOException{
    	// ��Ҫ��ȡ���ļ�
        File file = new File("H:/test.txt");  
        System.out.println(txt2String(file));  
    }  
    
}  
