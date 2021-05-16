package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.Stack;

public class Program {

    public static void main(String[] args) {
        {
            Stack stack = new Stack();

            stack.push(20);
            stack.push(21);
            stack.push(21);
            stack.push(21);
            stack.push(21);

            int pop = stack.pop();
            System.out.println(pop);
        }
    }
}