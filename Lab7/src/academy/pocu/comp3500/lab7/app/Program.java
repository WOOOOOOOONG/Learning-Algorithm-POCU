package academy.pocu.comp3500.lab7.app;

import academy.pocu.comp3500.lab7.Decryptor;

public class Program {

    public static void main(String[] args) {
        String[] codeWords = new String[]{"cat", "CATS", "AcTS", "SCAN", "acre", "aNTS"};

        Decryptor decryptor = new Decryptor(codeWords);

        String[] candidates = decryptor.findCandidates("cat");

        assert (candidates.length == 1);
        assert (candidates[0].equals("cat"));
        printStr(candidates);

        candidates = decryptor.findCandidates("race");

        assert (candidates.length == 1);
        assert (candidates[0].equals("acre"));
        printStr(candidates);

        candidates = decryptor.findCandidates("ca");

        assert (candidates.length == 0);
        printStr(candidates);

        candidates = decryptor.findCandidates("span");

        assert (candidates.length == 0);
        printStr(candidates);

        candidates = decryptor.findCandidates("ACT");

        assert (candidates.length == 1);
        assert (candidates[0].equals("cat"));
        printStr(candidates);

        candidates = decryptor.findCandidates("cats");

        assert (candidates.length == 2);
        assert (candidates[0].equals("cats") || candidates[0].equals("acts"));
        assert (candidates[1].equals("cats") || candidates[1].equals("acts"));
        printStr(candidates);

        candidates = decryptor.findCandidates("SCAt");

        assert (candidates.length == 2);
        assert (candidates[0].equals("cats") || candidates[0].equals("acts"));
        assert (candidates[1].equals("cats") || candidates[1].equals("acts"));
        printStr(candidates);
    }

    public static void printStr(String[] str) {
        System.out.print("[");
        for (int i = 0; i < str.length; i++) {
            if (!(i == str.length - 1)) {
                System.out.print("\"" + str[i] + "\", ");
            } else {
                System.out.print("\"" + str[i] + "\"");
            }
        }
        System.out.println("]");
    }
}
