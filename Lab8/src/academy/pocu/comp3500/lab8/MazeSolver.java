package academy.pocu.comp3500.lab8;

import academy.pocu.comp3500.lab8.maze.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class MazeSolver {
    private static Stack<Point> stack = new Stack<>();

    public static List<Point> findPath(final char[][] maze, final Point start) {
        Point cur = start;
        stack.push(new Point(cur.getY(), cur.getX()));

        while (true) {
            if (maze[cur.getX()][cur.getY()] == 'E') {
                stack.push(new Point(cur.getY(), cur.getX()));
                stack.remove(0);
                return stack;
            }
            maze[cur.getX()][cur.getY()] = 'p';
            printMaze(maze);

             if ((maze[cur.getX() - 1][cur.getY()] == ' ' || maze[cur.getX() - 1][cur.getY()] == 'E') && (!(stack.peek().getX() == cur.getX() - 1 && stack.peek().getY() == cur.getY()))) {
                 stack.push(new Point(cur.getY(), cur.getX()));
                 cur = new Point(cur.getX() - 1, cur.getY());
            } else if ((maze[cur.getX() + 1][cur.getY()] == ' ' || maze[cur.getX() + 1][cur.getY()] == 'E') && (!(stack.peek().getX() == cur.getX() + 1 && stack.peek().getY() == cur.getY()))) {
                 stack.push(new Point(cur.getY(), cur.getX()));
                 cur = new Point(cur.getX() + 1, cur.getY());
            } else if ((maze[cur.getX()][cur.getY() - 1] == ' ' || maze[cur.getX()][cur.getY() - 1] == 'E') && (!(stack.peek().getX() == cur.getX() && stack.peek().getY() == cur.getY() - 1))) {
                 stack.push(new Point(cur.getY(), cur.getX()));
                 cur = new Point(cur.getX(), cur.getY() - 1);
            } else if ((maze[cur.getX()][cur.getY() + 1] == ' '  || maze[cur.getX()][cur.getY() + 1] == 'E') && (!(stack.peek().getX() == cur.getX() && stack.peek().getY() == cur.getY() + 1))) {
                 stack.push(new Point(cur.getY(), cur.getX()));
                 cur = new Point(cur.getX(), cur.getY() + 1);
            }  else {
                 cur = new Point(stack.peek().getY(), stack.peek().getX());
                 stack.pop();
             }

             if (stack.isEmpty()) {
                 break;
             }
        }

        //findPath(maze, new Point(start.getX() - 1, start.getY()));
        return stack;
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