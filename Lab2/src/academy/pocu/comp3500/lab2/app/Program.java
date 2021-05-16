package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.Stack;

public class Program {

    public static void main(String[] args) {
        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            Node node = LinkedList.getOrNull(root, 0); // node: 10
            System.out.println(node.getData());
            node = LinkedList.getOrNull(root, 5); // node: 11
            System.out.println(node);

            /*
            System.out.println(node.getData());
            while (node.getNextOrNull() != null) {
                node = node.getNextOrNull();
                System.out.println(node.getData());
            }
             */
        }
    }
}