package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class Cracker {
    private User[] userTable;
    private String email;
    private String password;
    private int encoding; // 0: CRC32, 1: MD2, MD5, 3:SHA1, 4:SHA256

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;

        switch (userTable[0].getPasswordHash().length()) {
            case 9 :
                encoding = 0;
                break;
            case 24:
                encoding = 1;
                break;
            case 28:
                encoding = 3;
                break;
            case 44:
                encoding = 4;
                break;
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] answer = new String[userTable.length];

        if (encoding != 1) {
            for (int i = 0; i < userTable.length; i++) {
                answer[i] = rainbowTables[encoding].get(userTable[i].getPasswordHash());
            }
        } else {
            for (int i = 0; i < userTable.length; i++) {
                for (int j = encoding; j <= encoding + 1; j++) {
                    answer[i] = rainbowTables[j].get(userTable[i].getPasswordHash());

                    if (answer[i] != null) {
                        break;
                    }
                }
            }
        }

        /*
        for (int i = 0; i < userTable.length; i++) {
            for (int j = 0; j < rainbowTables.length; j++) {
                answer[i] = rainbowTables[j].get(userTable[i].getPasswordHash());

                if (answer[i] != null) {
                    break;
                }
            }
        }
        */

        return answer;
    }
}