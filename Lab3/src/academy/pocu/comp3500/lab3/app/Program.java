package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        {
            final int[] altitudes = new int[]{1, 2, 3, 4, 5, 6, 7, 4, 3, 2};
            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitudeTime == 6);
            System.out.println(maxAltitudeTime == 6);
            final int[] altitudes2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            final int maxAltitudeTime2 = MissionControl.findMaxAltitudeTime(altitudes2);
            assert (maxAltitudeTime2 == 9);
            System.out.println(maxAltitudeTime2 == 9);
            final int[] altitudes3 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            final int maxAltitudeTime3 = MissionControl.findMaxAltitudeTime(altitudes3);
            assert (maxAltitudeTime3 == 0);
            System.out.println(maxAltitudeTime3 == 0);
            final int[] altitudes4 = new int[]{3, 5, 9, 8, 7, 5, 5, 4, 3, 1};
            final int maxAltitudeTime4 = MissionControl.findMaxAltitudeTime(altitudes4);
            assert (maxAltitudeTime4 == 2);
            System.out.println(maxAltitudeTime4 == 2);
            final int[] altitudes5 = new int[]{3, 5, 9};
            final int maxAltitudeTime5 = MissionControl.findMaxAltitudeTime(altitudes5);
            assert (maxAltitudeTime5 == 2);
            System.out.println(maxAltitudeTime5 == 2);
            final int[] altitudes6 = new int[]{9, 7, 3};
            final int maxAltitudeTime6 = MissionControl.findMaxAltitudeTime(altitudes6);
            assert (maxAltitudeTime6 == 0);
            System.out.println(maxAltitudeTime6 == 0);
            final int[] altitudes7 = new int[]{3, 5};
            final int maxAltitudeTime7 = MissionControl.findMaxAltitudeTime(altitudes7);
            assert (maxAltitudeTime7 == 1);
            System.out.println(maxAltitudeTime7 == 1);
            final int[] altitudes8 = new int[]{5, 3};
            final int maxAltitudeTime8 = MissionControl.findMaxAltitudeTime(altitudes8);
            assert (maxAltitudeTime8 == 0);
            System.out.println(maxAltitudeTime8 == 0);
            final int[] altitudes9 = new int[]{5};
            final int maxAltitudeTime9 = MissionControl.findMaxAltitudeTime(altitudes9);
            assert (maxAltitudeTime9 == 0);
            System.out.println(maxAltitudeTime9 == 0);
        }
    }
}