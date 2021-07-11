package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class MazeSolver {
    private static Stack<Point> s = new Stack<>();

    public static List<Point> findPath(final char[][] maze, final Point start) {
        Point cur = start;
        s.push(cur);

        while (true) {
            if (maze[cur.getX()][cur.getY()] == 'E') {
                s.push(new Point(cur.getY(), cur.getX()));
                s.remove(0);
                return s;
            }
            maze[cur.getX()][cur.getY()] = 'p';
             printMaze(maze);

             if ((maze[cur.getX() - 1][cur.getY()] == ' ' || maze[cur.getX() - 1][cur.getY()] == 'E') 
                     && (!(s.peek().getX() == cur.getX() - 1 && s.peek().getY() == cur.getY()))) {
                 s.push(cur);
                 cur = new Point(cur.getX() - 1, cur.getY());
            } else if ((maze[cur.getX() + 1][cur.getY()] == ' ' || maze[cur.getX() + 1][cur.getY()] == 'E') && (!(s.peek().getX() == cur.getX() + 1 && s.peek().getY() == cur.getY()))) {
                 s.push(cur);
                 cur = new Point(cur.getX() + 1, cur.getY());
            } else if ((maze[cur.getX()][cur.getY() - 1] == ' ' || maze[cur.getX()][cur.getY() - 1] == 'E') && (!(s.peek().getX() == cur.getX() && s.peek().getY() == cur.getY() - 1))) {
                 s.push(cur);
                 cur = new Point(cur.getX(), cur.getY() - 1);
            } else if ((maze[cur.getX()][cur.getY() + 1] == ' '  || maze[cur.getX()][cur.getY() + 1] == 'E') && (!(s.peek().getX() == cur.getX() && s.peek().getY() == cur.getY() + 1))) {
                 s.push(cur);
                 cur = new Point(cur.getX(), cur.getY() + 1);
            }  else {
                 cur = new Point(s.peek().getX(), s.peek().getY());
                 s.pop();
             }

             if (s.isEmpty()) {
                 break;
             }
        }

        //findPath(maze, new Point(start.getX() - 1, start.getY()));
        return s;
    }

    public static void printMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}