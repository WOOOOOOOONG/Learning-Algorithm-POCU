package academy.pocu.comp3500.lab5;

import java.math.BigInteger;

public class KeyGenerator {
    public static boolean isPrime(final BigInteger number) {
        for (int i = 2; i * i <= number.intValue(); i++) {
            if (number.intValue() % i == 0)
                return false;
        }

        return true;
    }
}