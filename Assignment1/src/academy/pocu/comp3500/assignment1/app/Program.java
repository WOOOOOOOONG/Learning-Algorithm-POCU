package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {
    public static void main(String[] args) {
        Player[] players = new Player[] {
                new Player("Player 2", 5, 12, 14, 50),
                new Player("Player 6", 15, 2, 5, 40),
                new Player("Player 5", 11, 1, 11, 54),
                new Player("Player 4", 10, 3, 51, 88),
                new Player("Player 7", 16, 8, 5, 77),
                new Player("Player 1", 1, 15, 2, 22),
                new Player("Player 3", 7, 5, 8, 66)
        };

        Player[] outPlayers = new Player[3];
        Player[] scratch = new Player[3];

        long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch); // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]

        printMaxTeamwork(outPlayers, maxTeamwork);

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
