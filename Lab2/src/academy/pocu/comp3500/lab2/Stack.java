package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab2.LinkedList;

public final class Stack {
    private Node root;
    private Node tail;
    private int totalIndex;

    public Stack() {
        root = null;
        totalIndex = 0;
    }

    public void push(final int data) {
        if (root == null) {
            root = new Node(data);
            tail = root;
            totalIndex++;
            return;
        }
        Node newNode = new Node(data);
        tail.setNext(newNode);
        tail = newNode;
        totalIndex++;
    }

    public int peek() {
        if (root == null) {
            return 0;
        }

        return tail.getData();
    }

    public int pop() {
        if (getSize() == 0) {
            return -1;
        }

        int removeData = tail.getData();
        tail = null;
        tail = LinkedList.getOrNull(root, LinkedList.getSize(root) - 1);
        totalIndex--;
        return removeData;
    }

    public int getSize() {
        if (root == null) {
            return 0;
        }

        return totalIndex;
    }
}