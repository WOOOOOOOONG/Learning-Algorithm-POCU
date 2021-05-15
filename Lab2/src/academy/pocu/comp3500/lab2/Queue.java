package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class Queue {
    private Node root;

    public Queue() {
        root = null;
    }

    public void enqueue(final int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        root = LinkedList.prepend(root, data);
    }

    public int peek() {
        if (root == null) {
            return 0;
        }

        return root.getData();
    }

    public int dequeue() {
        if (getSize() == -1) {
            return -1;
        }
        int removeData = root.getData();
        root = LinkedList.removeAt(root, 0);

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