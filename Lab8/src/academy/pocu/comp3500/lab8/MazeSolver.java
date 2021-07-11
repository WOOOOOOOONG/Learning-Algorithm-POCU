package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.List;

public final class MazeSolver {
    public static List<Point> findPath(final char[][] maze, final Point start) {
        List<Point> answer = new ArrayList<>();
        Point point = new Point(start.getY(), start.getX());
        int direction = -1; // 0,1,2,3 : 상하좌우

        answer.add(new Point(point.getY(), point.getX()));
        if (maze[point.getX()][point.getY()] == 'E') {
            return answer;
        }
        maze[point.getX()][point.getY()] = 'p';

        while (true) {
            System.out.println(point.getX() + ", " + point.getY());

            if (point.getX() > 0 && (maze[point.getX() - 1][point.getY()] == ' ' || maze[point.getX() - 1][point.getY()] == 'E')) {
                //System.out.println("hi1");
                direction = -1;
                point = new Point(point.getX() - 1, point.getY());
                answer.add(new Point(point.getY(), point.getX()));
                if (maze[point.getX()][point.getY()] == 'E') {
                    return answer;
                }
                maze[point.getX()][point.getY()] = 'p';
            } else if (point.getX() < maze.length && (maze[point.getX() + 1][point.getY()] == ' ' || maze[point.getX() + 1][point.getY()] == 'E')) {
                //System.out.println("hi2");
                direction = -1;
                point = new Point(point.getX() + 1, point.getY());
                answer.add(new Point(point.getY(), point.getX()));
                if (maze[point.getX()][point.getY()] == 'E') {
                    return answer;
                }
                maze[point.getX()][point.getY()] = 'p';
            } else if (point.getY() > 0 && (maze[point.getX()][point.getY() - 1] == ' ' || maze[point.getX()][point.getY() - 1] == 'E')) {
                //System.out.println("hi3");
                direction = -1;
                point = new Point(point.getX(), point.getY() - 1);
                answer.add(new Point(point.getY(), point.getX()));
                if (maze[point.getX()][point.getY()] == 'E') {
                    return answer;
                }
                maze[point.getX()][point.getY()] = 'p';
            } else if (point.getY() > 0 && (maze[point.getX()][point.getY() + 1] == ' ' || maze[point.getX()][point.getY() + 1] == 'E')) {
                //System.out.println("hi4");
                direction = -1;
                point = new Point(point.getX(), point.getY() + 1);
                answer.add(new Point(point.getY(), point.getX()));
                if (maze[point.getX()][point.getY()] == 'E') {
                    return answer;
                }
                maze[point.getX()][point.getY()] = 'p';
            } else if (direction != 1 && point.getX() > 0 && maze[point.getX() - 1][point.getY()] == 'p') {
                //System.out.println("hi5");
                direction = 0;
                point = new Point(point.getX() - 1, point.getY());
            } else if (direction != 0 && point.getX() < maze.length && maze[point.getX() + 1][point.getY()] == 'p') {
                //System.out.println("hi6");
                direction = 1;
                point = new Point(point.getX() + 1, point.getY());
            } else if (direction != 3 && point.getY() > 0 && maze[point.getX()][point.getY() - 1] == 'p') {
                //System.out.println("hi7");
                direction = 2;
                point = new Point(point.getX(), point.getY() - 1);
            } else if (direction != 2 && point.getY() > 0 && maze[point.getX()][point.getY() + 1] == 'p') {
                //System.out.println("hi8");
                direction = 3;
                point = new Point(point.getX(), point.getY() + 1);
            } else {
                //System.out.println("hi9");
                return new ArrayList<Point>();
            }
        }
    }
}