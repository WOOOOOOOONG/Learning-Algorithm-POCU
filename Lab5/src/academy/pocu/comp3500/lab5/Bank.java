package academy.pocu.comp3500.lab5;

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
}