package academy.pocu.comp3500.lab3.app;

import academy.pocu.comp3500.lab3.MissionControl;

import java.sql.Array;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        ArrayList<Integer> altitudes = new ArrayList<>();
        altitudes.add(1); altitudes.add(2);
        altitudes.add(3); altitudes.add(4);
        altitudes.add(5); altitudes.add(6);
        altitudes.add(7); altitudes.add(8);

        int result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 1);
        System.out.println(result);
        result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 2);
        System.out.println(result);
        result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 3);
        System.out.println(result);
        result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 4);
        System.out.println(result);
        result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 5);
        System.out.println(result);
        result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 6);
        System.out.println(result);
        result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 7);
        System.out.println(result);
        result = MissionControl.binarySearch(altitudes, 0, altitudes.size() / 2, altitudes.size()-1, 8);
        System.out.println(result);

    }
}