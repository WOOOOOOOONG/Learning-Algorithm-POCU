package academy.pocu.comp3500.lab9;

import java.util.Arrays;

public class PyramidBuilder {
    public static int findMaxHeight(final int[] widths, int statue) {
        int answer = 0;
        int lastDigit = 0;
        int sum = 0;
        int digit = 0;
        Integer[] arr = new Integer[widths.length];
        for (int i = 0; i < widths.length; i++) {
            arr[i] = widths[i];
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            digit++;

            if (sum > statue) {
                if (digit > lastDigit && digit > 1) {
                    sum = 0;
                    lastDigit = digit;
                    digit = 0;
                    answer++;
                }
            }
        }

        return answer;
    }
}