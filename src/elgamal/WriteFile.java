package elgamal;

import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;

public class WriteFile {
	
	/**
	 * 
	 * @param str  Ŀ��·��
	 */
	public static void writeFile(String str, String destination){
		File file = null;
		FileWriter fw = null;
		file = new File(destination);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			fw.write(str);
			System.out.println("д���ݳɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {  
		
		String str = "H:/test.txt";
		writeFile("hahaha", str);
		
    }  
}
