package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

import java.util.ArrayList;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        testF();
        myTest();

        /*
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
        System.out.println("maxTeamwork : " + maxTeamwork);
        printPlayers(outPlayers);
        */

        /*
        Player[] players = new Player[] {
                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 2", 5, 5, 17, 50),

                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 8", 33, 11, 3, 72),
                new Player("Player 9", 42, 15, 4, 56)
        };
        // n만큼 반복하는데 그 안에는, 남은 애들을 대상으로 정렬 한번 하고, 제일 큰 애들 k - 1개 골라온다.

        Player[] players = new Player[] {
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 7", 16, 7, 5, 77),

                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 8", 33, 11, 3, 72),
                new Player("Player 9", 42, 15, 4, 56)

                new Player("Player 1", 1, 2, 8, 22),
        };

        Player[] players = new Player[] {
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 9", 42, 15, 4, 56)

                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 8", 33, 11, 3, 72),

                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 5", 11, 3, 25, 54),
        };

        Player[] players = new Player[] {
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 9", 42, 15, 4, 56)
                new Player("Player 8", 33, 11, 3, 72),

                new Player("Player 4", 10, 9, 1, 88),

                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 6", 15, 4, 10, 40),
        };

        Player[] players = new Player[] {
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 9", 42, 15, 4, 56)
                new Player("Player 8", 33, 11, 3, 72),
                new Player("Player 4", 10, 9, 1, 88),


                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 2", 5, 5, 17, 50),
        };

        Player[] players = new Player[] {
                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 8", 33, 11, 3, 72),
                new Player("Player 9", 42, 15, 4, 56)
        };*/
    }

    public static void testF() {
        {
            System.out.print("ManDreamTeam: ");
            Random random = new Random();
            for (int testCount = 0; testCount < 1000; ++testCount) {
                Player[] players = new Player[random.nextInt(20) + 3];
//                Player[] players = new Player[3];
                for (int i = 0; i < players.length; ++i) {
                    players[i] = new Player(String.format("Player %d", i + 1),
                            random.nextInt(30),
                            random.nextInt(30),
                            random.nextInt(30),
                            random.nextInt(30));
                }
                int maxTeamWork = -1;
                ArrayList<Integer> firstPlayerIndices = new ArrayList<>();
                ArrayList<Integer> secondPlayerIndices = new ArrayList<>();
                ArrayList<Integer> thirdPlayerIndices = new ArrayList<>();
                for (int j = 0; j < players.length - 2; ++j) {
                    for (int k = j + 1; k < players.length - 1; ++k) {
                        for (int l = k + 1; l < players.length; ++l) {
                            int curTeamWork = players[j].getPassesPerGame() + players[k].getPassesPerGame() + players[l].getPassesPerGame();
                            int minAssist = Math.min(Math.min(players[j].getAssistsPerGame(), players[k].getAssistsPerGame()), players[l].getAssistsPerGame());
                            curTeamWork *= minAssist;
                            if (curTeamWork > maxTeamWork) {
                                maxTeamWork = curTeamWork;
                                firstPlayerIndices.clear();
                                secondPlayerIndices.clear();
                                thirdPlayerIndices.clear();
                                firstPlayerIndices.add(j);
                                secondPlayerIndices.add(k);
                                thirdPlayerIndices.add(l);
                                System.out.println("New Max Team Work: " + maxTeamWork);
                                System.out.println("New: " + players[firstPlayerIndices.get(0)].getName() + " pass: " + players[firstPlayerIndices.get(0)].getPassesPerGame() + " assist: " + players[firstPlayerIndices.get(0)].getAssistsPerGame());
                                System.out.println("New: " + players[secondPlayerIndices.get(0)].getName() + " pass: " + players[secondPlayerIndices.get(0)].getPassesPerGame() + " assist: " + players[secondPlayerIndices.get(0)].getAssistsPerGame());
                                System.out.println("New: " + players[thirdPlayerIndices.get(0)].getName() + " pass: " + players[thirdPlayerIndices.get(0)].getPassesPerGame() + " assist: " + players[thirdPlayerIndices.get(0)].getAssistsPerGame());
                            } else if (curTeamWork == maxTeamWork) {
                                firstPlayerIndices.add(j);
                                secondPlayerIndices.add(k);
                                thirdPlayerIndices.add(l);
                                System.out.println("Current Team Work: " + curTeamWork + " Team Work: " + maxTeamWork);
                                System.out.println("New: " + players[firstPlayerIndices.get(firstPlayerIndices.size() - 1)].getName() + " pass: " + players[firstPlayerIndices.get(firstPlayerIndices.size() - 1)].getPassesPerGame() + " assist: " + players[firstPlayerIndices.get(firstPlayerIndices.size() - 1)].getAssistsPerGame());
                                System.out.println("New: " + players[secondPlayerIndices.get(secondPlayerIndices.size() - 1)].getName() + " pass: " + players[secondPlayerIndices.get(secondPlayerIndices.size() - 1)].getPassesPerGame() + " assist: " + players[secondPlayerIndices.get(secondPlayerIndices.size() - 1)].getAssistsPerGame());
                                System.out.println("New: " + players[thirdPlayerIndices.get(thirdPlayerIndices.size() - 1)].getName() + " pass: " + players[thirdPlayerIndices.get(thirdPlayerIndices.size() - 1)].getPassesPerGame() + " assist: " + players[thirdPlayerIndices.get(thirdPlayerIndices.size() - 1)].getAssistsPerGame());
                            }
                        }
                    }
                }
                Player[] outPlayers = new Player[3];
                Player[] scratch = new Player[3];
                System.out.print(System.lineSeparator());
                for (Player player : players) {
                    System.out.println(player.getName() + " pass: " + player.getPassesPerGame() + " assist: " + player.getAssistsPerGame());
                }
                Player[] copyOfPlayers = players.clone();
                long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(copyOfPlayers, outPlayers, scratch);
                System.out.println("Max Team Work: " + maxTeamWork + " max team work?: " + maxTeamwork);
                for (Player player : outPlayers) {
                    System.out.println("Result: " + player.getName() + " pass: " + player.getPassesPerGame() + " assist: " + player.getAssistsPerGame());
                }
                boolean hasAnswer = false;
                for (int i = 0; i < firstPlayerIndices.size(); ++i) {
                    System.out.println((i + 1) + ": Expected: " + players[firstPlayerIndices.get(i)].getName() + " pass: " + players[firstPlayerIndices.get(i)].getPassesPerGame() + " assist: " + players[firstPlayerIndices.get(i)].getAssistsPerGame());
                    System.out.println((i + 1) + ": Expected: " + players[secondPlayerIndices.get(i)].getName() + " pass: " + players[secondPlayerIndices.get(i)].getPassesPerGame() + " assist: " + players[secondPlayerIndices.get(i)].getAssistsPerGame());
                    System.out.println((i + 1) + ": Expected: " + players[thirdPlayerIndices.get(i)].getName() + " pass: " + players[thirdPlayerIndices.get(i)].getPassesPerGame() + " assist: " + players[thirdPlayerIndices.get(i)].getAssistsPerGame());
                    Player player1 = getPlayerOrNull(outPlayers, players[firstPlayerIndices.get(i)].getName());
                    Player player2 = getPlayerOrNull(outPlayers, players[secondPlayerIndices.get(i)].getName());
                    Player player3 = getPlayerOrNull(outPlayers, players[thirdPlayerIndices.get(i)].getName());
                    hasAnswer = (player1 != null && player2 != null && player3 != null);
                    if (hasAnswer) {
                        break;
                    }
                }
                assert (maxTeamwork == (long) maxTeamWork);
                assert(hasAnswer);
            }
            System.out.println("SUCCESS");
        }
    }

    public static void myTest() {
        {
            Random random = new Random();
            for(int testCount = 0; testCount < 1000; ++testCount) {
                Player[] players = new Player[random.nextInt(7) + 1];
                for(int i = 0; i < players.length; ++i) {
                    players[i] = new Player(String.format("Player %d", i + 1),
                            random.nextInt(30),
                            random.nextInt(30),
                            random.nextInt(30),
                            random.nextInt(30));
                }
                for(int k = 1; k < players.length; ++k) {
                    Player[] outPlayers1 = new Player[k];
                    Player[] outPlayers2 = new Player[k];
                    Player[] scratch1 = new Player[k];
                    Player[] scratch2 = new Player[k];

                    long maxTeamWork1 = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers1, scratch1);
                    long maxTeamWork2 = PocuBasketballAssociation.findDreamTeam(players, k, outPlayers2, scratch2);
                    StringBuilder testCase = new StringBuilder("\ntestCase: \n");
                    for(Player player:players) {
                        testCase.append("new Player(").append("\"").append(player.getName()).append("\", ").
                                append(player.getPointsPerGame()).append(", ").
                                append(player.getAssistsPerGame()).append(", ").
                                append(player.getPassesPerGame()).append(", ").
                                append(player.getShootingPercentage()).append("),\n");
                    }
                    String K = "K: " + k + "\n\n";
                    StringBuilder playersStr = new StringBuilder("outPlayer1: \n");
                    for (Player item : outPlayers1) {
                        playersStr.append(item.getName()).append(",\n");
                    }
                    playersStr.append("\noutPlayer2:\n");
                    for (Player value : outPlayers2) {
                        playersStr.append(value.getName()).append(",\n");
                    }
                    String teamWorkNotEqual = "maxTeamWork1: " + maxTeamWork1 + ", " + "maxTeamWork2: " + maxTeamWork2;
                    for (Player player : outPlayers1) {
                        String str = testCase.toString() + playersStr + "\nPlayers: " + players.length + "\n" + K + teamWorkNotEqual;
                        assert maxTeamWork1 == maxTeamWork2 || (getPlayerOrNull(outPlayers2, player.getName()) != null) : str + "\ncouldn't find " + player.getName() + " in player 2\n";
                    }
                }
            }
        }
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

    private static Player getPlayerOrNull(final Player[] players, final String id) {
        for (Player player : players) {
            if (player.getName().equals(id)) {
                return player;
            }
        }

        return null;
    }
}
