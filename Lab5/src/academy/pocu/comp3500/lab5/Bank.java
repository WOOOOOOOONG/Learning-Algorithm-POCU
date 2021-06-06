package academy.pocu.comp3500.lab5;

import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.LinkedList;

public class Bank {
    private byte[][] pubKeys;
    private long[] amounts;

    public Bank(byte[][] pubKeys, final long[] amounts) {
        this.pubKeys = pubKeys;
        this.amounts = amounts;
    }

    public long getBalance(final byte[] pubKey) {
        for (int i = 0; i < pubKeys.length; i++) {
            for (int j = 0; j < pubKeys[i].length; j++) {
                if (pubKeys[i][j] != pubKey[j]) {
                    break;
                } else if (j == pubKeys[i].length - 1 && pubKeys[i][j] == pubKey[j]) {
                    return amounts[i];
                }
            }
        }

        return 0;
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        int transfer = -1;
        int receiver = -1;
        boolean bTransferFind = false;
        boolean bReceiverFind = false;
        byte[] message;
        int messageLength;
        int messageIndex = 0;

        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(amount);
        byte[] amountToBytes = buffer.array();

        messageLength = from.length + to.length + amountToBytes.length;
        message = new byte[messageLength];

        System.arraycopy(from, 0, message, 0, from.length);
        System.arraycopy(to, 0, message, from.length, to.length);
        System.arraycopy(amountToBytes, 0, message, from.length + to.length, amountToBytes.length);

        /*
        for (int i = 0; i < from.length; i++) {
            message[messageIndex++] = from[i];
        }
        for (int i = 0; i < to.length; i++) {
            message[messageIndex++] = to[i];
        }
        for (int i = 0; i < amountToBytes.length; i++) {
            message[messageIndex++] = amountToBytes[i];
        }
        for (int i = 0; i < message.length; i++) {
            System.out.print(message[i]);
        }
        System.out.println();
        */

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(message);
            StringBuffer hexString = new StringBuffer(); // sha256([from, to, amount])

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // pubKey 생성
            PublicKey pubKey = getPublicKey(from);
            // signature 디코딩
            String decoding = decrypt(signature, pubKey);

            System.out.println(decoding);
            System.out.println(hexString);
        } catch (Exception e) {
            System.out.println(e);
        }

        // from, to의 인덱스 구해 각각 인출/송금 계산
        for (int i = 0; i < pubKeys.length; i++) {
            if (bTransferFind) {
                break;
            }

            for (int j = 0; j < pubKeys[i].length; j++) {
                if (pubKeys[i][j] != from[j]) {
                    break;
                } else if (j == pubKeys[i].length - 1 && pubKeys[i][j] == from[j]) {
                    transfer = i;
                    bTransferFind = true;
                }
            }
        }

        for (int i = 0; i < pubKeys.length; i++) {
            if (bReceiverFind) {
                break;
            }

            for (int j = 0; j < pubKeys[i].length; j++) {
                if (pubKeys[i][j] != to[j]) {
                    break;
                } else if (j == pubKeys[i].length - 1 && pubKeys[i][j] == to[j]) {
                    receiver = i;
                    bReceiverFind = true;
                }
            }
        }

        if (transfer == -1 || receiver == -1) {
            return false;
        } else {
            amounts[transfer] -= amount;
            amounts[receiver] += amount;

            return true;
        }
    }

    private static String decrypt(byte[] ciphertext, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            byte[] plaintext = cipher.doFinal(ciphertext);

            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static PublicKey getPublicKey(byte[] from) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(from);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            return pubKey;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static KeyPair getKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

            generator.initialize(2048, new SecureRandom());
            KeyPair pair = generator.generateKeyPair();

            return pair;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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