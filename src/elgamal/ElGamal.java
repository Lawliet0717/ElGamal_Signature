package elgamal;

import java.math.BigInteger;
import java.lang.Math;
import java.util.Random;
import java.io.*;

public class ElGamal {

    private static final BigInteger ONE = BigInteger.ONE;
    
	// 获取随机大素数
    public static BigInteger getPrime(int bitLenth) {
        BigInteger p = BigInteger.probablePrime(bitLenth, new Random());
        while(!p.isProbablePrime(6)) {
            p.nextProbablePrime();
        }
        return p;
    }
    
    // 枚举遍历求生成元g
    public static BigInteger getG(BigInteger p, BigInteger p_MinusOne) {
    	BigInteger g = null;
    	
    	outterLoop: for (int i = 2; i < 50; i++) {
			for (int x1 = 1; x1 <= Integer.valueOf(p_MinusOne.toString()); x1++) {
				String str1 = String.valueOf(i);
				String str2 = String.valueOf(x1);
				BigInteger tmp1 = new BigInteger(str1);
				BigInteger tmp2 = new BigInteger(str2);
				if (tmp1.modPow(tmp2, p).compareTo(ONE) == 0 && tmp2.compareTo(p_MinusOne) == -1) {
					break;
				} else if (tmp1.modPow(tmp2, p).compareTo(ONE) == 0 && tmp2.compareTo(p_MinusOne) == 0) {
					g = tmp1;
					break outterLoop;
				}
			}
		}
    	
    	return g;
    }
    
}