package academy.pocu.comp3500.lab7;

import java.util.LinkedList;

public class Node {
    private String data;
    private LinkedList<Node> children;

    public Node(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public LinkedList<Node> getChildre() {
        return this.children;
    }

    public void insert(Node node, String data) {
        char[] chars = data.toCharArray();


    }

    public void remove() {

    }

    public String[] bfs() {
        String[] result = new String[1];
        return result;
    }
}
