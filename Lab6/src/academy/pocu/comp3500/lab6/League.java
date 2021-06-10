package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;

public class League {
    private Player[] players;
    Node root;
    private boolean bIsSort;

    public League() { }

    public League(final Player[] players, boolean bIsSort) {
        this.players = players;
        this.bIsSort = bIsSort;

        if (!bIsSort) {
            quicksortPlayer(players);
        }
        /*
        root = new Node(players[0]);
        for (int i = 1; i < players.length; i++) {
            Node.insertRecursive(root, players[i]);
        }
         */
    }

    /* 매칭 시스템
    1. 등급 같은 선수
    2. 등급이 가장 가까운 선수
    3. 등급이 가장 가까운 선수가 여러명이라면 높은 선수

    - 단순 순차 탐색
    1. 정렬이 안되었다면 정렬 -> (n log n) -> 생성자, join, leave에서 해결
    2. 현재 선수 찾기 -> n
    3. 앞뒤 비교해서 차이가 더 적거나, 둘다 같다면 뒤선수 선택 -> 1
    * */
    public Player findMatchOrNull(final Player player) {
        // 2. 현재 선수 찾기 -> O(n)
        int curPlayerOrder = -1;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getId() == player.getId()) {
                curPlayerOrder = i;
                break;
            }
        }

        // 3. 앞뒤 비교하여 차이가 더 적거나, 둘 다 같다면 뒤 선수 선택
        int pre = 999999;
        int back = 999999;
        if (players[curPlayerOrder - 1] != null) {
            pre = Math.abs(player.getRating() - players[curPlayerOrder - 1].getRating());
        }
        if (players[curPlayerOrder + 1] != null) {
            back = Math.abs(player.getRating() - players[curPlayerOrder + 1].getRating());
        }
        int result = pre < back ? curPlayerOrder - 1 : curPlayerOrder + 1;
        if (result == 999999) {
            return null;
        } else {
            return players[result];
        }
    }

    public Player[] getTop(final int count) {
        Player[] topPlayers = new Player[count];
        int playerIndex = 0;
        if (count > players.length || players.length == 0) {
            return null;
        }

        if (!bIsSort) {
            quicksortPlayer(players);
        }

        for (int i = players.length - 1; i > players.length - 1 - count; i--) {
            topPlayers[playerIndex++] = players[i];
        }
        return topPlayers;
    }

    public Player[] getBottom(final int count) {
        Player[] bottomPlayers = new Player[count];
        int playerIndex = 0;
        if (count > players.length) {
            return null;
        }

        if (!bIsSort) {
            quicksortPlayer(players);
        }

        for (int i = 0; i <= players.length - count; i++) {
            bottomPlayers[playerIndex++] = players[i];
        }
        return bottomPlayers;
    }

    public boolean join(final Player player) {


        return false;
    }

    public boolean leave(final Player player) {
        return false;
    }

    public void printPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].toString());
        }
        System.out.println();
    }

    public int binarySearch(Player[] players, int id, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (players[mid].getId() == id) {
            return mid;
        }

        if (id >= players[left].getId() && id <= players[mid].getId()) {
            return binarySearch(players, id, left, mid - 1);
        }
        return binarySearch(players, id, mid + 1, right);
    }

    public void quicksortPlayer(Player[] players) {
        quicksortPlayerRecursive(players, 0, players.length - 1);
    }

    public void quicksortPlayerRecursive(Player[] players, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(players, left, right);

        quicksortPlayerRecursive(players, left, pivotPos - 1);
        quicksortPlayerRecursive(players, pivotPos + 1, right);
    }

    public int partition(Player[] players, int left, int right) {
        int pivot = players[right].getRating();

        int i = (left - 1);
        for (int j = left; j < right; j++) {
            if (players[j].getRating() < pivot) {
                ++i;
                swapPlayer(players, i, j);
            }
        }

        int pivotPos = i + 1;
        swapPlayer(players, pivotPos, right);

        return pivotPos;
    }

    public void swapPlayer(Player[] players, int first, int second) {
        Player playerDummy = players[first];
        players[first] = players[second];
        players[second] = playerDummy;
    }
}