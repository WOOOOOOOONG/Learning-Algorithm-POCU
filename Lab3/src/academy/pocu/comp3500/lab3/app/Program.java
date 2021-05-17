package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

        ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 2); // bounds: [ 1, 9 ]

        bounds = MissionControl.findAltitudeTimes(altitudes, 5); // bounds: [ 4 ]
        for (int i = 0; i < bounds.size(); i++) {
            System.out.print(bounds.get(i) + " ");
        }

    }
}