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
    // private int encoding; // 0: CRC32, 1: MD2, 2:MD5, 3:SHA1, 4:SHA256

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] answer = new String[userTable.length];

        for (int i = 0; i < userTable.length; i++) {
            for (int j = 0; j < rainbowTables.length; j++) {
                answer[i] = rainbowTables[j].get(userTable[i].getPasswordHash());

                if (answer[i] != null) {
                    break;
                }
            }
        }

        return answer;
    }
}