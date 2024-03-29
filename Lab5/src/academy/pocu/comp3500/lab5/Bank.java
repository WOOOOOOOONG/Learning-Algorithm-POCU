package academy.pocu.comp3500.lab5;

import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
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
        if (amount <= 0 || amount > Long.MAX_VALUE) {
            return false;
        }
        int transfer = -1;
        int receiver = -1;
        boolean bTransferFind = false;
        boolean bReceiverFind = false;
        byte[] message;
        int messageLength;
        boolean bCompare = false;

        byte[] amountToBytes = ByteBuffer.allocate(Long.BYTES).putLong(amount).array();

        messageLength = from.length + to.length + amountToBytes.length;
        message = new byte[messageLength];

        // message : [from, to, amount]
        System.arraycopy(from, 0, message, 0, from.length);
        System.arraycopy(to, 0, message, from.length, to.length);
        System.arraycopy(amountToBytes, 0, message, from.length + to.length, amountToBytes.length);

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(message);
            byte[] byteData = digest.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            String sha256encoding = sb.toString();

            // signature 디코딩
            PublicKey pubKey = getPublicKey(from); // pubKey 생성
            byte[] decoding = decrypt(signature, pubKey);

            StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < decoding.length; i++) {
                sb2.append(Integer.toString((decoding[i] & 0xff) + 0x100, 16).substring(1));
            }
            String rsaDecoding = sb2.toString();

            if (sha256encoding.equals(rsaDecoding)) {
                bCompare = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (bCompare) {
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
        }

        if (!bTransferFind) {
            return false;
        } else if (!bReceiverFind) {
            byte[][] pKey = new byte[pubKeys.length][];
            for (int i = 0; i < pubKeys.length; i++) {
                pKey[i] = pubKeys[i];
            }

            long[] pBalance = new long[amounts.length];
            for (int i = 0; i < amounts.length; i++) {
                pBalance[i] = amounts[i];
            }

            pubKeys = new byte[pKey.length + 1][];
            for (int i = 0; i < pKey.length; i++) {
                pubKeys[i] = pKey[i];
            }
            pubKeys[pKey.length] = to;

            amounts = new long[pBalance.length + 1];
            for (int i = 0; i < pBalance.length; i++) {
                amounts[i] = pBalance[i];
            }

            amounts[transfer] -= amount;
            amounts[pBalance.length] = amount;

            return true;
        } else if (amounts[transfer] < amount) {
            return false; // 지갑에 돈이 존재하지 않거나 적을 때
        } else if (amounts[receiver] > Long.MAX_VALUE - amount) {
            return false; // 금액 수신 뒤 최대량보다 더 많다면
        } else {
            amounts[transfer] -= amount;
            amounts[receiver] += amount;

            return true;
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

    private static byte[] decrypt(byte[] ciphertext, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            byte[] plaintext = cipher.doFinal(ciphertext);

            return plaintext;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}