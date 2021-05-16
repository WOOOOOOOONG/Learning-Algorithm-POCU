package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;
import academy.pocu.comp3500.lab2.LinkedList;

public final class Stack {
    private Node root;

    public Stack() {
        root = null;
    }

    public void push(final int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        LinkedList.append(root, data);
    }

    public int peek() {
        if (root == null) {
            return 0;
        }

        Node dummy = root;
        while (dummy.getNextOrNull() != null) {
            dummy = dummy.getNextOrNull();
        }
        return dummy.getData();
    }

    public int pop() {
        if (getSize() == 0) {
            return -1;
        }
        int size = getSize();
        int removeData = peek();
        LinkedList.removeAt(root, size - 1);

        return removeData;
    }

    public int getSize() {
        if (root == null) {
            return 0;
        }

        Node dummy = root;
        int index = 1;
        while (dummy.getNextOrNull() != null) {
            dummy = dummy.getNextOrNull();
            index++;
        }

        return index;
    }
}