package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

import java.sql.Array;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

        final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes); // maxAltitudeTime: 6
        System.out.println(maxAltitudeTime);
    }

    public static void puff(int n, int a, int b)
    {
        if (n <= 0) {
            return;
        }

        System.out.println(String.format("%d %d %d", n, a, b)); // 4 6 4

        puff(n - 3, a, b + n); // 1 6 8
        puff(n - 1, b, a + n); // 3 4 10 -> 2 10 7 -> 1 7 12 -> 1 10 7
        puff(n - 2, b, a + n); // 2 4 10 -> 1 10 6
    }
    // 스택 프레임 : 현재 리턴되지 않고 가지고 있는 값
    // 재귀 함수 : 위와 아래에 있는 재귀들도 조건이 맞다면 실행시킴. (재귀의 재귀가 가능하다는 뜻, 본인 재귀함수의 리턴값에도 다시 가능)
    // 해당 함수의 최대 스택 프레임 : 4 6 4 -> 3 4 10 -> 2 10 7 -> 1 7 12 -> 1 10 7 : 5개

    /*
    * 1. 정수 n개의 집합 S와 정수 x를 받아온다.
    * 2. 새로운 S의 부분 집합을 만든다. ^n
    * 3. 2번의 부분 집합에 들어 있는 정수들을 모두 더한다. -> n
    * 4. 3번의 값이 x와 일치하는지 비교한다.
    * 5. 일치하지 않다면 다시 2번 단계로 돌아간다.
    * */

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = 999999;
            int minIndex = -1;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = min;
            arr[minIndex] = temp;
        }

        return arr;
    }

    // 다음 값을 알맞은 위치에 삽입한다고 하여 삽입 정렬
    public static int[] insertionSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;

                j--;

                if (j < 1) {
                    break;
                }
            }
        }

        return arr;
    }

}