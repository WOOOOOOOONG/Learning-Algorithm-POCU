package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {
    public static int TEAM_SIZE = 4;
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

        long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers); // maxTeamwork: 210, outPlayers: [ Player 4, Player 2, Player 7 ]
        System.out.println(maxTeamwork);
        for (Player p : outPlayers) {
            System.out.println(p.getName());
        }
    }
}
