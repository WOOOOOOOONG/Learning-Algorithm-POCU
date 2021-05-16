package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node root;
    private int totalIndex;

    public Queue() {
        root = null;
        totalIndex = 0;
    }

    public void enqueue(final int data) {
        if (root == null) {
            root = new Node(data);
            totalIndex++;
            return;
        }
        root = LinkedList.prepend(root, data);
        totalIndex++;
    }

    public int peek() {
        if (root == null) {
            return 0;
        }

        return totalIndex;
    }

    public int dequeue() {
        if (getSize() == -1) {
            return -1;
        }
        Node dummy = root;
        int removeData = root.getData();

        if (root.getNextOrNull() != null) {
            dummy = root.getNextOrNull();
            root.setNext(null);
            root = dummy;
        } else {
            root = null;
        }

        return removeData;
    }

    public int getSize() {
        if (root == null) {
            return 0;
        }

        return totalIndex;
    }
}