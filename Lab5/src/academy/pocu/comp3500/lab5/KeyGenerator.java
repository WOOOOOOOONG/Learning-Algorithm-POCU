package academy.pocu.comp3500.lab5;

import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
    public static boolean isPrime(final BigInteger number) {
        if (number.intValue() <= 0) {
            return false;
        }
        if (isProbablePrime(number, 100)) {
            return true;
        }

        return false;
    }

    public static boolean isProbablePrime(BigInteger n, int k) {
        if (n.compareTo(new BigInteger("3")) < 0)
            return true;
        int s = 0;
        BigInteger d = n.subtract(BigInteger.ONE); // n-1
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) { //?
            s++;                          //?
            d = d.divide(BigInteger.TWO);            //?
        }
        for (int i = 0; i < k; i++) {    //LOOP: repeat k times
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.ONE)); //?
            BigInteger x = a.modPow(d, n);  //x = a^d mod n
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) // if x=1 or x = n-1, then do next LOOP
                continue;
            int r = 1;
            for (; r < s; r++) { // for r = 1..s-1
                x = x.modPow(BigInteger.TWO, n);  //x = x ^ 2 mod n
                if (x.equals(BigInteger.ONE))     //if x = 1, return false (composite
                    return false;
                if (x.equals(n.subtract(BigInteger.ONE))) //if x= n-1, look at the next a
                    break;
            }
            if (r == s) // None of the steps made x equal n-1.
                return false; //we've exhausted all of our a values, probably composite
        }
        return true; //probably prime
    }

    //this method is just to generate a random int
    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
        Random rnd = new Random();
        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }
}