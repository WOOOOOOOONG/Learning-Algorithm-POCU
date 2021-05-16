package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.Stack;

public class Program {

    public static void main(String[] args) {
        {
            Node root = LinkedList.insertAt(null, 0, 10);

            System.out.println(root.getData());
            while (root.getNextOrNull() != null) {
                root = root.getNextOrNull();
                System.out.println(root.getData());
            }

        }
    }
}