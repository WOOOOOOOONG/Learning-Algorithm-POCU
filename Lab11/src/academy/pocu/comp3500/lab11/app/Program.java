package academy.pocu.comp3500.lab11.app;

import academy.pocu.comp3500.lab11.BallBoy;
import academy.pocu.comp3500.lab11.data.Point;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        Point[] points = {
                new Point(20, 15),
                new Point(20, 48),
                new Point(0, 63)
        };

        List<Point> path = BallBoy.findPath(points);
        // [0, 0], [3, 4], [0, 0]

        for (int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i));
        }
    }
}
