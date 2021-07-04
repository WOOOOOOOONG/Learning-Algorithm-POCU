package academy.pocu.comp3500.lab7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class Decryptor {
    String[] codeWords;
    String[] sortCodeWords;
    HashMap<String, ArrayList<String>> hmap = new HashMap<>();

    public Decryptor(final String[] codeWords) {
        lowerStr(codeWords);
        this.codeWords = codeWords;

        sortCodeWords = new String[codeWords.length];
        for (int i = 0; i < codeWords.length; i++) {
            char[] charArr = codeWords[i].toCharArray();
            strQuickSort(charArr);
            sortCodeWords[i] = String.valueOf(charArr);
        }

        for (int i = 0; i < sortCodeWords.length; i++) {
            if (hmap.get(sortCodeWords[i]) != null) {
                hmap.get(sortCodeWords[i]).add(codeWords[i]);
            } else {
                ArrayList<String> ar = new ArrayList<>();
                ar.add(codeWords[i]);
                hmap.put(sortCodeWords[i], ar);
            }
        }
    }

    public String[] findCandidates(final String word) {
        String word2 = word.toLowerCase();
        char[] charArr = word2.toCharArray();
        strQuickSort(charArr);
        word2 = word2.replace(word2, String.valueOf(charArr));

        ArrayList<String> ar = hmap.get(word2);

        if (ar == null) {
            return new String[0];
        } else {
            String[] answer = new String[ar.size()];
            for (int i = 0; i < ar.size(); i++) {
                answer[i] = ar.get(i);
            }
            return answer;
        }
    }

    public void strQuickSort(char[] str) {
        strQuickSortRecursive(str, 0, str.length - 1);
    }

    public void strQuickSortRecursive(char[] str, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = partition(str, start, end);

        strQuickSortRecursive(str, start, pivot - 1);
        strQuickSortRecursive(str, pivot + 1, end);
    }

    public int partition(char[] str, int start, int end) {
        int pivot = str[end];

        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (pivot > str[j]) {
                ++i;
                swap(str, i, j);
            }
        }

        int pivotPos = i + 1;
        swap(str, pivotPos, end);

        return pivotPos;
    }

    public void swap(char[] str, int start, int end) {
        char temp = str[start];
        str[start] = str[end];
        str[end] = temp;
    }

    public static void lowerStr(String[] str) {
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].toLowerCase();
        }
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
