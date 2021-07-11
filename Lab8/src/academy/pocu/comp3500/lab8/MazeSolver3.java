package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class MazeSolver3 {
    private static Stack<Point> s = new Stack<>();

    public static List<Point> findPath(final char[][] maze, final Point start) {
        Point cur = start;
        s.push(cur);

        while (true) {
            if (maze[cur.getY()][cur.getX()] == 'E') {
                s.push(cur);
                s.remove(0);
                return s;
            }
            maze[cur.getY()][cur.getX()] = 'p';
            //printMaze(maze);

             if (cur.getY() > 0 && (maze[cur.getY()-1][cur.getX()] == ' ' || maze[cur.getY()-1][cur.getX()] == 'E')
                     && ( !(s.peek().getY() == cur.getY() - 1 && s.peek().getX() == cur.getX())) ) {
                 s.push(cur);
                 cur = new Point(cur.getX(), cur.getY() - 1);
            } else if (cur.getY() < maze.length - 1 && (maze[cur.getY() + 1][cur.getX()] == ' ' || maze[cur.getY() + 1][cur.getX()] == 'E')
                     && (!(s.peek().getY() == cur.getY() + 1 && s.peek().getX() == cur.getX()))) {
                 s.push(cur);
                 cur = new Point(cur.getX(), cur.getY() + 1);
            } else if (cur.getX() > 0 && (maze[cur.getY()][cur.getX() - 1] == ' ' || maze[cur.getY()][cur.getX() - 1] == 'E')
                     && (!(s.peek().getY() == cur.getY() && s.peek().getX() == cur.getX() - 1))) {
                 s.push(cur);
                 cur = new Point(cur.getX() - 1, cur.getY());
            } else if (cur.getX() < maze[0].length - 1 && (maze[cur.getY()][cur.getX() + 1] == ' '  || maze[cur.getY()][cur.getX() + 1] == 'E')
                     && (!(s.peek().getY() == cur.getY() && s.peek().getX() == cur.getX() + 1))) {
                 s.push(cur);
                 cur = new Point(cur.getX() + 1, cur.getY());
            }  else {
                 cur = new Point(s.peek().getY(), s.peek().getX());
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