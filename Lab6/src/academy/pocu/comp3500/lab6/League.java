package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

public class League {
    private ArrayList<Player> players;
    Node root;
    private boolean bIsSort;

    public League() {
        players = new ArrayList<Player>();
    }

    public League(final Player[] players, boolean bIsSort) {
        this.players = new ArrayList<Player>();
        for (int i = 0; i < players.length; i++) {
            this.players.add(players[i]);
        }
        this.bIsSort = bIsSort;

        if (players.length >= 1) {
            root = new Node(players[0]);
            if (players.length > 1) {
                for (int i = 1; i < players.length; i++) {
                    Node.insertRecursive(root, players[i]);
                }
            }
        }
    }

    public Player findMatchOrNull(final Player player) {
        if (player == null) {
            return null;
        }

        // 1. 정렬이 안되었으면 정렬
        if (!bIsSort) {
            quicksortPlayer(players);
        }

        // 2. 현재 선수 찾기 -> O(log n)
        int curPlayerOrder = findPlayer(players, player.getId(), player.getRating(), 0, players.size() - 1);
        if (curPlayerOrder == -1) {
            return null;
        } else {
            return players.get(curPlayerOrder);
        }
    }

    public Player[] getTop(final int count) {
        if ((count == 0) || (players.size() == 0) || count < 0 || players == null) {
            return new Player[0];
        }

        if (!bIsSort) {
            quicksortPlayer(players);
        }

        if (count > 1045000000 || count > players.size()) {
            Player[] newPlayers = toTopPlayerArray(players);
            return newPlayers;
        }

        Player[] topPlayers = new Player[count];
        int playerIndex = 0;

        for (int i = players.size() - 1; i > players.size() - 1 - count; i--) {
            topPlayers[playerIndex++] = players.get(i);
        }
        return topPlayers;
    }

    public Player[] getBottom(final int count) {
        if ((count == 0) || (players.size() == 0) || count < 0 || players == null) {
            return new Player[0];
        }

        if (!bIsSort) {
            quicksortPlayer(players);
        }

        if (count > 1045000000 || count > players.size()) {
            Player[] newPlayers = toBottomPlayerArray(players);
            return newPlayers;
        }

        Player[] bottomPlayers = new Player[count];
        int playerIndex = 0;

        for (int i = 0; i < count; i++) {
            bottomPlayers[playerIndex++] = players.get(i);
        }
        return bottomPlayers;
    }

    public boolean join(final Player player) {
        //System.out.print("삽입 전 : ");Node.traverseInOrder(root);
        if (Node.getNodeOrNull(root, player) != null) {
            return false;
        }

        Node.insertRecursive(root, player);
        players.add(player);
        bIsSort = false;

        return true;
    }

    public boolean leave(final Player player) {
        //System.out.println("삭제할 데이터 : " + player.getId());
        if (Node.getNodeOrNull(root, player) != null) {
            //System.out.print("삭제 전 : "); Node.traverseInOrder(root);
            //System.out.println();
            root = Node.removeRecursive(root, player);
            for (int i = 0; i < players.size(); i++) {
                if (player.getId() == players.get(i).getId()) {
                    players.remove(i);
                    bIsSort = false;
                    //System.out.print("삭제 후 : "); Node.traverseInOrder(root);
                    //System.out.println();
                    break;
                }
            }

            return true;
        }

        return false;
    }

    public void printPlayers() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).toString());
        }
    }

    public int findPlayer(ArrayList<Player> players, int id, int rating, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (players.get(mid).getRating() == rating) {
            if (players.get(mid).getId() != id) {
                return mid;
            } else {
                int pre = 999999;
                int back = 999999;
                if ((mid - 1) >= 0) {
                    pre = Math.abs(rating - players.get(mid - 1).getRating());
                }
                if ((mid + 1) < players.size()) {
                    back = Math.abs(rating - players.get(mid + 1).getRating());
                }

                int result = -1;
                if (pre != 999999 || back != 999999) {
                    result = pre < back ? mid - 1 : mid + 1;
                    return result;
                } else {
                    return -1;
                }
            }
        }

        if (rating < players.get(mid).getRating()) {
            return findPlayer(players, id, rating, left, mid - 1);
        } else {
            return findPlayer(players, id, rating, mid + 1, right);
        }
    }

    public void quicksortPlayer(ArrayList<Player> players) {
        quicksortPlayerRecursive(players, 0, players.size() - 1);
    }

    public void quicksortPlayerRecursive(ArrayList<Player> players, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(players, left, right);

        quicksortPlayerRecursive(players, left, pivotPos - 1);
        quicksortPlayerRecursive(players, pivotPos + 1, right);
    }

    public int partition(ArrayList<Player> players, int left, int right) {
        int pivot = players.get(right).getRating();

        int i = (left - 1);
        for (int j = left; j < right; j++) {
            if (players.get(j).getRating() < pivot) {
                ++i;
                swapPlayer(players, i, j);
            }
        }

        int pivotPos = i + 1;
        swapPlayer(players, pivotPos, right);

        return pivotPos;
    }

    public void swapPlayer(ArrayList<Player> players, int first, int second) {
        Player playerDummy = players.get(first);
        players.set(first, players.get(second));
        players.set(second, playerDummy);
    }

    public Player[] toTopPlayerArray(ArrayList<Player> players) {
        Player[] newPlayers = new Player[players.size()];

        for (int i = 0; i < players.size(); i++) {
            newPlayers[i] = players.get(players.size() - 1 - i);
        }

        return newPlayers;
    }

    public Player[] toBottomPlayerArray(ArrayList<Player> players) {
        Player[] newPlayers = new Player[players.size()];

        for (int i = 0; i < players.size(); i++) {
            newPlayers[i] = players.get(i);
        }

        return newPlayers;
    }
}