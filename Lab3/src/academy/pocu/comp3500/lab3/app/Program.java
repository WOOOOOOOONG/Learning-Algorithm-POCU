package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        {
            final int[] altitudes = new int[] {};

            final int maxAltitudeTime = MissionControl.findMaxAltitudeTime(altitudes);

            //System.out.println(maxAltitudeTime);
        }

        {
            final int[] altitudes = new int[] { 5,4,3,2,1 };

            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 4);

            for (int i = 0; i < bounds.size(); i++) {
                System.out.print(bounds.get(i) + " ");
            }
            System.out.println();
            assert (bounds.size() == 2);

            assert (bounds.get(0) == 1);
            assert (bounds.get(1) == 9);

            bounds = MissionControl.findAltitudeTimes(altitudes, 5);
            for (int i = 0; i < bounds.size(); i++) {
                System.out.print(bounds.get(i) + " ");
            }
            System.out.println();

            assert (bounds.size() == 1);

            assert (bounds.get(0) == 4);
        }
    }
}