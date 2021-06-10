package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class Node {
    private Player player;
    private Node left;
    private Node right;

    public Node(final Player player) {
        this.player = player;
    }

    public void setData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public static Node getNodeOrNull(Node node, Player player) {
        if (node == null) {
            return null;
        }

        if (node.getPlayer().getId() == player.getId()) {
            return node;
        }

        if (player.getId() < node.player.getId()) {
            return getNodeOrNull(node.left, player);
        }

        return getNodeOrNull(node.right, player);
    }

    public static Node getNodeParent(Node node, Player player) {
        if (node == null) {
            return null;
        }

        if ((node.left != null && node.left.getPlayer().getId() == player.getId())
                || (node.right != null && node.right.getPlayer().getId() == player.getId())) {
            return node;
        }

        if (player.getId() < node.player.getId()) {
            return getNodeParent(node.left, player);
        }

        return getNodeParent(node.right, player);

    }

    public static Node insertRecursive(final Node node, Player player) {
        if (node == null) {
            return new Node(player);
        }

        if (player.getId() < node.player.getId()) {
            node.left = insertRecursive(node.left, player);
        } else {
            node.right = insertRecursive(node.right, player);
        }

        return node;
    }

    /* 삭제
    1. 삭제할 위치 탐색
    2. 삭제할 위치의 왼쪽 하위 트리의 가장 큰 값 찾기
    3. 둘 교환
    4. 리프노드 삭제
     */
    public static Node removeRecursive(Node node, Player player) {
        Node rootNode = node;
        Node findNode = Node.getNodeOrNull(rootNode, player);
        Node tempNode;

        if (findNode.left != null) {
            tempNode = getPredecessor(findNode.left);
        } else if(findNode.right != null) {
            tempNode = getSuccessor(findNode.right);
        } else {
            Node parent = getNodeParent(rootNode, findNode.getPlayer());
            if (parent.left != null && parent.left.getPlayer().getId() == player.getId()) {
                parent.left = null;
            } else if (parent.right != null && parent.right.getPlayer().getId() == player.getId()) {
                parent.right = null;
            }
            return rootNode;
        }

        if (rootNode.getPlayer().getId() == player.getId()) {
            rootNode = null;
            return tempNode;
        } else {
            findNode.setData(tempNode.player);
            Node parent = getNodeParent(rootNode, tempNode.getPlayer());
            if (tempNode != null) {
                if (parent.getPlayer().getId() < tempNode.getPlayer().getId()) {
                    parent.right = tempNode;
                } else {
                    parent.left = tempNode;
                }
            } else {
                if (parent.left != null && parent.left.getPlayer().getId() == tempNode.player.getId()) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.getPlayer().getId() == tempNode.player.getId()) {
                    parent.right = null;
                }
            }
            return rootNode;
        }
    }

    public static Node getPredecessor(Node node) {
        if (node.right != null) {
            return getPredecessor(node.right);
        }

        return node;
    }

    public static Node getSuccessor(Node node) {
        if (node.left != null) {
            return getSuccessor(node.left);
        }

        return node;
    }

    public static void traverseInOrder(Node node) {
        if (node == null) {
            return;
        }

        traverseInOrder(node.left);
        System.out.print(node.getPlayer().getId() + " ");
        traverseInOrder(node.right);
    }

    public static void traversePreOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getPlayer().getId() + " ");
        traversePreOrder(node.left);
        traversePreOrder(node.right);
    }
}
