package elgamal;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class Main {
	
	private static final BigInteger ONE = BigInteger.ONE;
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		// 从文件中读入消息m
		File file = new File("H:/test.txt");

		String m = ReadFile.txt2String(file);
		System.out.println("消息m = " + m);

		int length = m.length();

		// 随机的大素数p
		BigInteger p = ElGamal.getPrime(60);
		System.out.println("p = " + p.toString());

		// 下面计算时用到的p-1
		BigInteger p_MinusOne = p.subtract(ONE);
		System.out.println("p-1 = " + p_MinusOne.toString());

		// 枚举遍历求生成元g
		BigInteger g = null;
		while(g == null) {
			g = ElGamal.getG(p, p_MinusOne);
		}
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
        
        // 求安全的h(m)
        int hm = Math.abs(m.hashCode());
        String temp = String.valueOf(hm);
        BigInteger h_m = new BigInteger(temp);
        System.out.println("h(m) = " + h_m.toString());
        
        //验证 y的a次方 * a的b次方 ≡ g的h(m)次方  mod p 相等
        BigInteger tmp1 = h_m.subtract(x.multiply(a));
        System.out.println("tmp1 = " + tmp1.toString());
        
        BigInteger tmp2 = tmp1.multiply(k_Reverse);
        System.out.println("tmp2 = " + tmp2.toString());
        
        BigInteger b = tmp2.mod(p_MinusOne);
        System.out.println("b = " + b.toString());
        
        System.out.println("消息m的数字签名为: " + a.toString() + "," + b.toString());
        
        BigInteger left = y.modPow(a, p).multiply(a.modPow(b, p)).mod(p);
        System.out.println("left = " + left.toString());
        
        int unsignedH_m = h_m.intValue();
        if(unsignedH_m < 0) {
        	unsignedH_m = -unsignedH_m;
        }
        BigInteger right = g.modPow(h_m, p);
        
        System.out.println("right = " + right.toString());
        
        if(left.toString().equals(right.toString())) {
        	System.out.println("签名有效！");
        } else {
        	System.out.println("签名无效！");
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	}
	
}
