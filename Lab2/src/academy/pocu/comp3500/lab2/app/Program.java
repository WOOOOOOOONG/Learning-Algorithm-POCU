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
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);

            root = LinkedList.removeAt(root, 0); // root: 11, list: 11 -> 12 -> 13
            root = LinkedList.removeAt(root, 1); // root: 11, list: 11 -> 13
            root = LinkedList.removeAt(root, -1);
            root = LinkedList.removeAt(root, 5);
            root = LinkedList.removeAt(root, 0);
            root = LinkedList.removeAt(root, 0);
            root = LinkedList.removeAt(root, 0);

            System.out.println(root);
            while (root.getNextOrNull() != null) {
                root = root.getNextOrNull();
                System.out.println(root.getData());
            }
        }
    }
}