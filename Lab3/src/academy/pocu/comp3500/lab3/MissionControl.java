package academy.pocu.comp3500.lab3;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        int max = -999999;
        int maxIndex = -1;
        for (int i = 0; i < altitudes.length; i++) {
            if (altitudes[i] > max) {
                max = altitudes[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int i = 0; i < altitudes.length; i++) {
            if (altitudes[i] == targetAltitude) {
                newArray.add(i);
            }
        }

        return newArray;
    }

    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }

        return arr;
    }
}