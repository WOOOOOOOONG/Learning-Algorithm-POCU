package academy.pocu.comp3500.lab5;

import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
    public static boolean isPrime(final BigInteger n) {
        if (n.intValue() <= 0) {
            return false;
        }

        BigInteger[] alist = {BigInteger.valueOf(2), BigInteger.valueOf(325), BigInteger.valueOf(9375), BigInteger.valueOf(28178), BigInteger.valueOf(450775), BigInteger.valueOf(9780504), BigInteger.valueOf(1795265022)};

        if (n.compareTo(BigInteger.ONE) == 0 || n.compareTo(BigInteger.ONE) == -1)
            return false;
        if (n.compareTo(new BigInteger("10000000000")) == 0 || n.compareTo(new BigInteger("10000000000")) == -1) {
            for (BigInteger i = BigInteger.TWO; i.multiply(i).compareTo(n) == 0 || i.multiply(i).compareTo(n) == -1; i = i.add(BigInteger.ONE)) {
                if (n.mod(i).compareTo(BigInteger.ZERO) == 0)
                    return false;
            }
            return true;
        }

        for (BigInteger a : alist)
            if (!millerRabin(n, a))
                return false;
        return true;
    }

    // calculate (x + y) % m; overflow-safe
    public static BigInteger addmod(BigInteger x, BigInteger y, BigInteger m) {
        x = x.mod(m);
        y = y.mod(m);

        if ((x.compareTo(x.subtract(y)) == 1 || x.compareTo(x.subtract(y)) == 0)) {
            return x.subtract(m.subtract(y));
        } else {
            return x.add(y);
        }
    }

    // calculate (x * y) % m; overlow-safe
    public static BigInteger mulmod(BigInteger x, BigInteger y, BigInteger m) {
        x = x.mod(m);
        y = y.mod(m);
        BigInteger r = BigInteger.ZERO;
        while (y.compareTo(BigInteger.ZERO) == 1) {
            if (y.mod(BigInteger.TWO).compareTo(BigInteger.ONE) == 1)
                r = addmod(r, x, m);
            x = addmod(x, x, m);
            y = y.divide(BigInteger.TWO);
        }
        return r;
    }

    // calculate x^y % m; overflow-safe
    static BigInteger powmod(BigInteger x, BigInteger y, BigInteger m) {
        x = x.mod(m);
        BigInteger r = BigInteger.ONE;
        while (y.compareTo(BigInteger.ZERO) == 1) {
            if (y.mod(BigInteger.TWO).compareTo(BigInteger.ONE) == 0)
                r = mulmod(r, x, m);
            x = mulmod(x, x, m);
            y = y.divide(BigInteger.TWO);
        }
        return r;
    }

    // true for probable prime, false for composite
    public static boolean millerRabin(BigInteger n, BigInteger a) {
        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
            if (powmod(a, d, n).compareTo(n.subtract(BigInteger.ONE)) == 0)
                return true;
            d = d.divide(BigInteger.TWO);
        }
        BigInteger tmp = powmod(a, d, n);
        return tmp.compareTo(n.subtract(BigInteger.ONE)) == 0 || tmp.compareTo(BigInteger.ONE) == 0;
    }
}