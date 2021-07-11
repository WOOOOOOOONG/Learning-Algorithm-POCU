package academy.pocu.comp3500.lab8.app;

import academy.pocu.comp3500.lab8.MazeSolver;
import academy.pocu.comp3500.lab8.maze.Point;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        char[][] maze8x6 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', ' ', 'x', ' ', 'E', ' ', 'x'},
                {'x', 'x', ' ', 'x', ' ', 'x', 'x', 'x'},
                {'x', ' ', ' ', ' ', ' ', 'x', ' ', 'x'},
                {'x', 'x', ' ', 'x', ' ', ' ', ' ', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}
        };

        List<Point> result = MazeSolver.findPath(maze8x6, new Point(2, 2));

        print(result);
    }

    public static void print(List<Point> result) {
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1) {
                System.out.print("Point(" + result.get(i).getX() + ", " + result.get(i).getY() + ")");
            } else {
                System.out.print("Point(" + result.get(i).getX() + ", " + result.get(i).getY() + "), ");
            }
        }
        System.out.println("]");
    }
}
