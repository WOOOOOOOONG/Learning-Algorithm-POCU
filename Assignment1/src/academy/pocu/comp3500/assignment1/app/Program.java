package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {
    public static void main(String[] args) {
        Player[] players = new Player[] {
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 9", 42, 15, 4, 56),
                new Player("Player 8", 33, 11, 3, 72),
        };

        int k = 4;
        Player[] outPlayers = new Player[4];
        Player[] scratch = new Player[k];

        long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers, scratch); // maxTeamwork: 171, outPlayers: [ Player 6, Player 5, Player 2, Player 7 ]

        printPlayers(players);
    }

    public static void printGameStat(GameStat[] gameStat) {
        for (int i = 0; i < gameStat.length; i++) {
            System.out.println("{ \"" + gameStat[i].getPlayerName() + "\" }");
        }
    }

    public static void printPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println("{ \"" + players[i].getName() + "\", pointsPerGame: " + players[i].getPointsPerGame()
                    + ", assistsPerGame: " + players[i].getAssistsPerGame() + ", passesPerGame: " + players[i].getPassesPerGame()
                    + ", shootingPercentage: " + players[i].getShootingPercentage());
        }
    }

    public static void printPlayer(Player player) {
        System.out.println("{ \"" + player.getName() + "\", pointsPerGame: " + player.getPointsPerGame()
                + ", assistsPerGame: " + player.getAssistsPerGame() + ", passesPerGame: " + player.getPassesPerGame()
                + ", shootingPercentage: " + player.getShootingPercentage());
    }

    public static void printMaxTeamwork(Player[] players, long maxTeamwork) {
        System.out.print("maxTeamwork : " + maxTeamwork + ", outPlayers: [ ");
        for (int i = 0; i < players.length; i++) {
            if (i == players.length - 1) {
                System.out.print(players[i].getName());
            } else {
                System.out.print(players[i].getName() + ", ");
            }
        }
        System.out.println(" ]");
    }
}
