package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.CRC32;

public final class Cracker {
    private User[] userTable;
    private String email;
    private String password;
    private String[] passwordToHash;
    private int encoding; // 0: CRC32, 1: MD2, 2: MD5, 3:SHA1, 4:SHA256

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
        passwordToHash = new String[5];

        try {
            // CRC32
            byte[] passwordToBytes = password.getBytes("UTF-8");
            CRC32 c = new CRC32();
            c.update(passwordToBytes);
            String checkSum = String.valueOf(c.getValue());
            passwordToHash[0] = checkSum;

            // MD2
            MessageDigest digest = MessageDigest.getInstance("MD2");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String base64 = Base64.getEncoder().encodeToString(hash);
            passwordToHash[1] = base64;

            // MD5
            MessageDigest digest2 = MessageDigest.getInstance("MD5");
            byte[] hash2 = digest2.digest(password.getBytes("UTF-8"));
            StringBuffer hexString2 = new StringBuffer();

            for (int i = 0; i < hash2.length; i++) {
                String hex = Integer.toHexString(0xff & hash2[i]);
                if (hex.length() == 1) {
                    hexString2.append('0');
                }
                hexString2.append(hex);
            }
            base64 = Base64.getEncoder().encodeToString(hash2);
            passwordToHash[2] = base64;

            // SHA-1
            MessageDigest digest3 = MessageDigest.getInstance("SHA-1");
            byte[] hash3 = digest3.digest(password.getBytes("UTF-8"));
            StringBuffer hexString3 = new StringBuffer();

            for (int i = 0; i < hash3.length; i++) {
                String hex = Integer.toHexString(0xff & hash3[i]);
                if (hex.length() == 1) {
                    hexString3.append('0');
                }
                hexString3.append(hex);
            }
            base64 = Base64.getEncoder().encodeToString(hash3);
            passwordToHash[3] = base64;

            // SHA-256
            MessageDigest digest4 = MessageDigest.getInstance("SHA-256");
            byte[] hash4 = digest4.digest(password.getBytes("UTF-8"));
            StringBuffer hexString4 = new StringBuffer();

            for (int i = 0; i < hash4.length; i++) {
                String hex = Integer.toHexString(0xff & hash4[i]);
                if (hex.length() == 1) {
                    hexString4.append('0');
                }
                hexString4.append(hex);
            }
            base64 = Base64.getEncoder().encodeToString(hash4);
            passwordToHash[4] = base64;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        for (int i = 0; i < userTable.length; i++) {
            if (userTable[i].getEmail().equals(email)) {
                if (userTable[i].getPasswordHash().equals(passwordToHash[0])) {
                    encoding = 0;
                } else if (userTable[i].getPasswordHash().equals(passwordToHash[1])) {
                    encoding = 1;
                } else if (userTable[i].getPasswordHash().equals(passwordToHash[2])) {
                    encoding = 2;
                } else if (userTable[i].getPasswordHash().equals(passwordToHash[3])) {
                    encoding = 3;
                } else if (userTable[i].getPasswordHash().equals(passwordToHash[4])) {
                    encoding = 4;
                }
                break;
            }
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] answer = new String[userTable.length];

        for (int i = 0; i < userTable.length; i++)
            answer[i] = rainbowTables[encoding].get(userTable[i].getPasswordHash());

        return answer;
    }
}