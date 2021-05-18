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

    public static int partition(final int[] altitudes, int mid) {
        if (mid - 1 >= 0 && altitudes[mid] < altitudes[mid - 1]) {
            return -1;
        } else if (mid + 1 <= altitudes.length - 1 && altitudes[mid] < altitudes[mid + 1]) {
            return 1;
        } else {
            return 0;
        }
    }

    // 원하는 값 찾는법 : 배열 중 가장 큰 값을 찾고, 그 값을 기준으로 왼쪽/오른쪽에서 타겟을 찾아 새 ArrayList에 추가 후 반환한다
    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> newArray = new ArrayList<>();
        MissionControl maxIndex = new MissionControl();
        int max = -9999999;

        // 1. 가장 큰 인덱스 찾기
        quickSearchRecursive(altitudes, altitudes.length / 2, max, maxIndex, 0, altitudes.length - 1);
        // 2. 가장 큰 인덱스 기준 왼쪽에서 목표값 찾기
        partition2(altitudes, newArray, 0, 0, maxIndex.maxIndex / 2, maxIndex.maxIndex, targetAltitude);
        // 3. 가장 큰 인덱스 기준 오른쪽에서 목표값 찾기
        partition2(altitudes, newArray, 1, maxIndex.maxIndex + 1, (maxIndex.maxIndex + altitudes.length) / 2, altitudes.length - 1, targetAltitude);

        // 4. 찾은 값 반환
        return newArray;
    }

    // version = 1 : 오른쪽 값 찾을 때, version = 0 : 왼쪽 값 찾을 때
    public static void partition2(final int[] altitudes, ArrayList<Integer> newArray, int version, int l, int mid, int r, int find) {
        if (l > r) {
            return;
        }
        if (version == 0) {
            if (altitudes[mid] == find) {
                newArray.add(mid);
            } else if (altitudes[mid] > find) {
                partition2(altitudes, newArray, 0, l, mid - ((r - l) / 2), mid - 1, find);
            } else if (altitudes[mid] < find) {
                partition2(altitudes, newArray, 0, mid + 1, mid + ((r - mid + 1) / 2), r, find);
            }
        } else if (version == 1) {
            if (altitudes[mid] == find) {
                newArray.add(mid);
            } else if (altitudes[mid] > find) {
                partition2(altitudes, newArray, 1, mid + 1, mid + ((r - mid + 1) / 2), r, find);
            } else if (altitudes[mid] < find) {
                partition2(altitudes, newArray, 1, l, mid - ((r - l) / 2), mid - 1, find);
            }
        }
    }
}