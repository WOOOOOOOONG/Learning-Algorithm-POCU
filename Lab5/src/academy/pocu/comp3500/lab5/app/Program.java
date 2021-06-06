package academy.pocu.comp3500.lab5.app;

import academy.pocu.comp3500.lab5.Bank;
import academy.pocu.comp3500.lab5.KeyGenerator;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Program {

    public static void main(String[] args) {

        //System.out.println((BigInteger.valueOf(1).compareTo(BigInteger.valueOf(1).subtract(BigInteger.valueOf(1))) == 1 || BigInteger.valueOf(1).compareTo(BigInteger.valueOf(1).subtract(BigInteger.valueOf(1))) == 0));
        System.out.println(KeyGenerator.isPrime(BigInteger.valueOf(2476)));
    }

    private static byte[] decodeFromHexString(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            int firstDigit = Character.digit(hexString.charAt(i), 16);
            int secondDigit = Character.digit(hexString.charAt(i + 1), 16);
            bytes[i / 2] = (byte) ((firstDigit << 4) + secondDigit);
        }
        return bytes;
    }

    private static String encodeToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte oneByte : bytes) {
            result.append(String.format("%02x", oneByte));
        }
        return result.toString();
    }
}
