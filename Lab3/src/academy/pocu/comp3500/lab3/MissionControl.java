package academy.pocu.comp3500.lab3;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    // 퀵 정렬 사용
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

    /* findAltitudeTimes 해법
    *  1. 퀵정렬하여 배열을 새로 생성 (new int[][] 형식으로 생성, [0][i]에 인덱스, [1][i]에 값)
    *  2. 이진탐색
    *  3. 이진 탐색 이후 찾은 값의 왼쪽과 오른쪽값을 살펴 같은 값이 나올 때까지 newArray에 저장
    *  시간 복잡도 : (log n * log n) + targetAltitude 수
    * */
    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> newArray = new ArrayList<>();
        for (int i = 0; i < altitudes.length; i++) {
            if (altitudes[i] == targetAltitude) {
                newArray.add(i);
            }
        }

        return newArray;
    }

    private static void quickSortRecursive(int[][] arrays, int left, int right) {
    }

    /* 이진탐색 해법
    *  1. 배열의 가장 중간 값을 인덱스로 설정한다
    *  2. 찾고자 하는 값이 배열의 중간이면 탐색 완료, 아니라면 찾는 값이 오른쪽/왼쪽에 있는지 찾아 다시 실행시킨다.
    *  3. 값을 찾을 때까지 반복한다.
    * */
    public static int binarySearch(ArrayList<Integer> arr, int l, int mid, int r, int value) {
        if (l >= r) {
            return -1;
        }

        if (arr.get(mid) == value) {
            return mid;
        }

        if (arr.get(mid) > value) {
            return binarySearch(arr, l, (l + r) / 2, mid, value);
        } else if (arr.get(mid) < value) {
            return binarySearch(arr, mid, mid + ((r - mid + 1) / 2), r, value);
        }

        return 1;
    }
}