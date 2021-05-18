package academy.pocu.comp3500.lab3;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class MissionControl {
    int maxIndex;

    private MissionControl() {
        maxIndex = -1;
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        /*for (int i = altitudes.length - 1; i > 0; i--) {
            if (altitudes[i] > altitudes[i - 1]) {
                return i;
            }

            if (i == 1) {
                return 0;
            }
        }*/

        MissionControl maxIndex = new MissionControl();
        int max = -9999999;
        quickSearchRecursive(altitudes, altitudes.length / 2, max, maxIndex, 0, altitudes.length - 1);
        return maxIndex.maxIndex;
    }

    // 어떤 배열이 들어왔을 때 해당 값의 좌/우측과 비교하여 큰 값인지 비교
    public static void quickSearchRecursive(final int[] altitudes, int mid, int max, MissionControl maxIndex, int l, int r) {
        if (mid < 0 || mid >= altitudes.length || l > r || r < l) {
            return;
        }
        int result = partition(altitudes, mid);

        if (result == -1) {
            quickSearchRecursive(altitudes, mid - ((r - l + 1) / 2), max, maxIndex, l, mid - 1);
        } else if (result == 0) {
            /*if (max < altitudes[mid]) {
                max = altitudes[mid];
                maxIndex = mid;
            }*/
            maxIndex.maxIndex = mid;
            return;
        } else if (result == 1) {
            quickSearchRecursive(altitudes, mid + ((r - mid + 1) / 2), max, maxIndex, mid + 1, r);
        }
    }
    // mid를 절반씩 증가/감소하는 방법
    // 10까지 인덱스 존재. 5부터 시작. 5 -> 7 -> 9 : max + ((length - max + 1) / 2)
    // 6,7,7    7 -

    public static int partition(final int[] altitudes, int mid) {
        if (mid - 1 >= 0 && altitudes[mid] < altitudes[mid - 1]) {
            return -1;
        } else if (mid + 1 <= altitudes.length - 1 && altitudes[mid] < altitudes[mid + 1]) {
            return 1;
        } else {
            return 0;
        }
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> newArray = new ArrayList<>();

        for (int i = 0; i < altitudes.length; i++) {
            if (targetAltitude < altitudes[i]) {
                break;
            }

            if (altitudes[i] == targetAltitude) {
                if (i == 0) {
                    break;
                }
                newArray.add(i);
            }
        }

        for (int i = altitudes.length - 1; i >= 0; i--) {
            if (targetAltitude < altitudes[i]) {
                break;
            }

            if (altitudes[i] == targetAltitude) {
                if (i == altitudes.length - 1) {
                    break;
                }
                newArray.add(i);
            }
        }

        return newArray;
    }
}