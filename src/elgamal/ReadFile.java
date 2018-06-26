package elgamal;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileReader;
import java.io.IOException;  
  
public class ReadFile {  
	
    public static String txt2String(File file) throws IOException{  
    	StringBuilder result = new StringBuilder(); 
        // 建立数据的输入通道  
        FileReader fileReader = new FileReader(file);  
        //建立缓冲字符数组读取文件数据  
        char[] buf = new char[1024];  
        int length = 0 ;
        
        while((length = fileReader.read(buf))!=-1){  
            result.append(new String(buf,0,length));  
        }  
    	// 返回一个String类型
        return result.toString();
    }  
      
    public static void main(String[] args) throws IOException{
    	// 需要读取的文件
        File file = new File("H:/test.txt");  
        System.out.println(txt2String(file));  
    }  
    
}  
