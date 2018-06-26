package elgamal;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class Main {
	
	private static final BigInteger ONE = BigInteger.ONE;
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		// ���ļ��ж�����Ϣm
		File file = new File("H:/test.txt");

		String m = ReadFile.txt2String(file);
		System.out.println("��Ϣm = " + m);

		int length = m.length();

		// ����Ĵ�����p
		BigInteger p = ElGamal.getPrime(60);
		System.out.println("p = " + p.toString());

		// �������ʱ�õ���p-1
		BigInteger p_MinusOne = p.subtract(ONE);
		System.out.println("p-1 = " + p_MinusOne.toString());

		// ö�ٱ���������Ԫg
		BigInteger g = null;
		while(g == null) {
			g = ElGamal.getG(p, p_MinusOne);
		}
		System.out.println("g = " + g.toString());

		// �����x,��������[2,p-1)
        BigInteger x = new BigInteger(length,new Random());
        System.out.println("x = " + x.toString());
        
        // y �� g^x�� mod p ��
        BigInteger y = g.modPow(x,p);
        System.out.println("y = " + y.toString());
        
        // ����Ĵ�����k,��������[2,p-1)
        BigInteger k = ElGamal.getPrime(length);
        System.out.println("k = " + k.toString());
        
        // k����Ԫ
        BigInteger k_Reverse = k.modInverse(p_MinusOne);
        System.out.println("k��p-1����Ԫ = " + k_Reverse.toString());
        
        // a �� g^k ( mod p )
        BigInteger a = g.modPow(k,p);
        System.out.println("a = " + a.toString());
        
        // ��ȫ��h(m)
        int hm = Math.abs(m.hashCode());
        String temp = String.valueOf(hm);
        BigInteger h_m = new BigInteger(temp);
        System.out.println("h(m) = " + h_m.toString());
        
        //��֤ y��a�η� * a��b�η� �� g��h(m)�η�  mod p ���
        BigInteger tmp1 = h_m.subtract(x.multiply(a));
        System.out.println("tmp1 = " + tmp1.toString());
        
        BigInteger tmp2 = tmp1.multiply(k_Reverse);
        System.out.println("tmp2 = " + tmp2.toString());
        
        BigInteger b = tmp2.mod(p_MinusOne);
        System.out.println("b = " + b.toString());
        
        System.out.println("��Ϣm������ǩ��Ϊ: " + a.toString() + "," + b.toString());
        
        BigInteger left = y.modPow(a, p).multiply(a.modPow(b, p)).mod(p);
        System.out.println("left = " + left.toString());
        
        int unsignedH_m = h_m.intValue();
        if(unsignedH_m < 0) {
        	unsignedH_m = -unsignedH_m;
        }
        BigInteger right = g.modPow(h_m, p);
        
        System.out.println("right = " + right.toString());
        
        if(left.toString().equals(right.toString())) {
        	System.out.println("ǩ����Ч��");
        } else {
        	System.out.println("ǩ����Ч��");
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("��������ʱ�䣺" + (endTime - startTime) + "ms");    //�����������ʱ��
	}
	
}
