package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

public class Node {
    private Player player;
    private Node left;
    private Node right;

    public Node(final Player player) {
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

    public static void removeRecursive(Node node, Player player) {
        Node removeNode = getNodeOrNull(node, player);

        if (removeNode == null) {
            return;
        }

        if (node.left != null) {
            node = node.left;
        }
        while (node.right != null) {
            node = node.right;
        }

        removeNode.player = node.player;
        node = null;
    }
}
