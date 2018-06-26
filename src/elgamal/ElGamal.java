package elgamal;

import java.math.BigInteger;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class ElGamal {

    private static final BigInteger ONE = BigInteger.ONE;
    
	// ��ȡ�����������bitLenthΪ����
    public static BigInteger getPrime(int bitLenth) {
    	
        BigInteger p = BigInteger.probablePrime(bitLenth, new Random());
        while(!p.isProbablePrime(6)) {
            p.nextProbablePrime();
        }
        return p;
    }
    
    // ö�ٱ���������Ԫg
    public static BigInteger getG(BigInteger p, BigInteger p_MinusOne) {
    	
    	BigInteger g = null;
    	BigInteger div = new BigInteger("2");
    	BigInteger temp = p_MinusOne;
    	
    	// �½�һ��list������p-1���������ֽ�
    	ArrayList<String> list = new ArrayList<>();
    	
    	// ��ֽ��������Ĺ���
    	for(int i = 0; i < 100; i++) {
    		// ���������
			if(p_MinusOne.isProbablePrime(6)){
				list.add(p_MinusOne.toString());
				break;
			}
			
			if(p_MinusOne.mod(div).toString().equals("0")) {
				p_MinusOne = p_MinusOne.divide(div);
			} else {
				div = div.nextProbablePrime();
				p_MinusOne = p_MinusOne.divide(div);
			}
			
			if(p_MinusOne.toString().equals("0")) {
				break;
			}
			
			if(list.contains(div.toString())) {
				continue;
			} else {
				list.add(div.toString());
			}
    	}
    	
    	// ����ѭ���������Ԫg�Ĺ���
    	outterLoop: for (int i1 = 2; i1 < 100; i1++) {
    		// �ڲ�ѭ��
			for(int index = 0; index < list.size(); index++) {
				String exp = list.get(index); // 2
				String str = String.valueOf(i1); // g
				
				BigInteger inner_g = new BigInteger(str);
				
				BigInteger x = new BigInteger(exp);
			
				BigInteger tmp = inner_g.modPow(temp.divide(x), p);
				
				if(tmp.toString().equals("1") ) {
					break;
				}
				
				if(index == list.size() - 1) {
					g = inner_g;
					break outterLoop;
				}
			}
		}
    	// ���õ�������Ԫg
    	return g;
    }
    

}