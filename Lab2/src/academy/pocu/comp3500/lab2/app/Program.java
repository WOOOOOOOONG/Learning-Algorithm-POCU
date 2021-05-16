package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.Stack;

public class Program {

    public static void main(String[] args) {
        {
            Queue queue = new Queue();

            queue.enqueue(20); // queue: 20
            queue.enqueue(21); // queue: 20, 21
            System.out.println(queue.getSize());
        }
    }
}