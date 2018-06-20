package elgamal;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class Main {
	
	private static final BigInteger ONE = BigInteger.ONE;
	
	public static void main(String[] args) throws IOException {
		File file = new File("H:/test.txt");

		String m = ReadFile.txt2String(file);
		System.out.println("消息m = " + m);

		int length = m.length();

		// 随机的大素数p
		BigInteger p = ElGamal.getPrime(length);
		System.out.println("p = " + p.toString());

		// 下面计算时用到的p-1
		BigInteger p_MinusOne = p.subtract(ONE);
		System.out.println("p-1 = " + p_MinusOne.toString());

		// 枚举遍历求生成元g
		BigInteger g = null;
		g = ElGamal.getG(p, p_MinusOne);
		System.out.println("g = " + g.toString());

		// 随机数x,满足区间[2,p-1)
        BigInteger x = new BigInteger(length,new Random());
        System.out.println("x = " + x.toString());
        
        // y ≡ g^x（ mod p ）
        BigInteger y = g.modPow(x,p);
        System.out.println("y = " + y.toString());
        
        // 随机的大素数k,满足区间[2,p-1)
        BigInteger k = ElGamal.getPrime(length);
        System.out.println("k = " + k.toString());
        
        // k的逆元
        BigInteger k_Reverse = k.modInverse(p_MinusOne);
        System.out.println("k对p-1的逆元 = " + k_Reverse.toString());
        
        // a ≡ g^k ( mod p )
        BigInteger a = g.modPow(k,p);
        System.out.println("a = " + a.toString());
        
        // h(m)
        int hm = Math.abs(m.hashCode());
        String temp = String.valueOf(hm);
        String hmm = temp.substring(1, 5);
        BigInteger h_m = new BigInteger(hmm);
        System.out.println("h(m) = " + h_m.toString());
        
        BigInteger tmp1 = h_m.subtract(x.multiply(a));
        System.out.println("tmp1 = " + tmp1.toString());
        
        BigInteger tmp2 = tmp1.multiply(k_Reverse);
        System.out.println("tmp2 = " + tmp2.toString());
        
        BigInteger b = tmp2.mod(p_MinusOne);
        System.out.println("b = " + b.toString());
        
        System.out.println("消息m的数字签名为: " + a.toString() + "," + b.toString());
        
        BigInteger left = y.pow(a.intValue()).multiply(a.pow(b.intValue())).mod(p);
        System.out.println("left = " + left.toString());
        
        int unsignedH_m = h_m.intValue();
        if(unsignedH_m < 0) {
        	unsignedH_m = -unsignedH_m;
        }
        BigInteger right = g.pow(unsignedH_m).mod(p);
        
        System.out.println("right = " + right.toString());
        
	}
	
}
