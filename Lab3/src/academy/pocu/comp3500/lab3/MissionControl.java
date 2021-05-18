package academy.pocu.comp3500.lab3;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        for (int i = altitudes.length - 1 ; i > 0; i--) {
            if (altitudes[i] > altitudes[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> newArray = new ArrayList<>();

        for (int i = 0; i < altitudes.length; i++) {
            if (targetAltitude < altitudes[i]) {
                break;
            }

            if (altitudes[i] == targetAltitude) {
                newArray.add(i);
            }
        }

        for (int i = altitudes.length - 1; i >= 0; i--) {
            if (targetAltitude < altitudes[i]) {
                break;
            }

            if (altitudes[i] == targetAltitude) {
                newArray.add(i);
            }
        }

        return newArray;
    }
}