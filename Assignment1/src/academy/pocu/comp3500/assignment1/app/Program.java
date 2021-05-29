package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

import java.util.ArrayList;
import java.util.Random;


public class Program {
    public static void main(String[] args) {
        {
            GameStat[] gameStats = new GameStat[] {
                    new GameStat("Player 1", 1, 13, 5, 6, 10, 1),
                    new GameStat("Player 2", 2, 5, 2, 5, 0, 10),
                    new GameStat("Player 1", 3, 12, 6, 9, 8, 5),
                    new GameStat("Player 3", 1, 31, 15, 40, 5, 3),
                    new GameStat("Player 2", 1, 3, 1, 3, 12, 2),
                    new GameStat("Player 1", 2, 11, 6, 11, 9, 3),
                    new GameStat("Player 2", 3, 9, 3, 3, 1, 11),
                    new GameStat("Player 3", 4, 32, 15, 51, 4, 2),
                    new GameStat("Player 4", 3, 44, 24, 50, 1, 1),
                    new GameStat("Player 1", 4, 11, 5, 14, 8, 3),
                    new GameStat("Player 2", 4, 5, 1, 3, 1, 9),
            };

            Player[] players = new Player[] {
                    new Player(),
                    new Player(),
                    new Player(),
                    new Player()
            };

            PocuBasketballAssociation.processGameStats(gameStats, players);

            Player player = getPlayerOrNull(players, "Player 1");
            assert (player != null);
            assert (player.getPointsPerGame() == 11);
            assert (player.getAssistsPerGame() == 8);
            assert (player.getPassesPerGame() == 3);
            assert (player.getShootingPercentage() == 55);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);
            assert (player.getPointsPerGame() == 5);
            assert (player.getAssistsPerGame() == 3);
            assert (player.getPassesPerGame() == 8);
            assert (player.getShootingPercentage() == 50);

            player = getPlayerOrNull(players, "Player 3");
            assert (player != null);
            assert (player.getPointsPerGame() == 31);
            assert (player.getAssistsPerGame() == 4);
            assert (player.getPassesPerGame() == 2);
            assert (player.getShootingPercentage() == 32);

            player = getPlayerOrNull(players, "Player 4");
            assert (player != null);
            assert (player.getPointsPerGame() == 44);
            assert (player.getAssistsPerGame() == 1);
            assert (player.getPassesPerGame() == 1);
            assert (player.getShootingPercentage() == 48);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 5", 11, 12, 6, 77),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70)
            };

            Player player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 12);
            assert (player.getName().equals("Player 5"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 5);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerPointsPerGame(players, 13);
            assert (player.getName().equals("Player 6"));
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 4", 10, 10, 15, 25),
                    new Player("Player 2", 5, 2, 11, 31),
                    new Player("Player 3", 7, 4, 7, 44),
                    new Player("Player 1", 1, 5, 1, 60),
                    new Player("Player 6", 15, 0, 12, 61),
                    new Player("Player 7", 16, 8, 2, 70),
                    new Player("Player 5", 11, 12, 6, 77)
            };

            Player player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 28);
            assert (player.getName().equals("Player 2"));

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 58);
            assert (player.getName().equals("Player 1"));

            player = PocuBasketballAssociation.findPlayerShootingPercentage(players, 72);
            assert (player.getName().equals("Player 7"));
        }

        {
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

            long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch);

            assert (maxTeamwork == 219);

            Player player = getPlayerOrNull(players, "Player 4");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 3");
            assert (player != null);
        }

        {
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

            final int TEAM_SIZE = 4;

            Player[] outPlayers = new Player[TEAM_SIZE];
            Player[] scratch = new Player[TEAM_SIZE];

            long maxTeamwork = PocuBasketballAssociation.findDreamTeam(players, TEAM_SIZE, outPlayers, scratch);

            assert (maxTeamwork == 171);

            Player player = getPlayerOrNull(players, "Player 5");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 6");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 2");
            assert (player != null);

            player = getPlayerOrNull(players, "Player 7");
            assert (player != null);
        }

        {
            Player[] players = new Player[] {
                    new Player("Player 1", 2, 5, 10, 78),
                    new Player("Player 2", 10, 4, 5, 66),
                    new Player("Player 3", 3, 3, 2, 22),
                    new Player("Player 4", 1, 9, 8, 12),
                    new Player("Player 5", 11, 1, 12, 26),
                    new Player("Player 6", 7, 2, 10, 15),
                    new Player("Player 7", 8, 15, 3, 11),
                    new Player("Player 8", 5, 7, 13, 5),
                    new Player("Player 9", 8, 2, 7, 67),
                    new Player("Player 10", 1, 11, 0, 29),
                    new Player("Player 11", 2, 6, 9, 88)
            };

            Player[] tempPlayers = new Player[players.length];

            int k = PocuBasketballAssociation.findDreamTeamSize(players, tempPlayers);

            assert (k == 6);
        }
    }

    private static Player getPlayerOrNull(final Player[] players, final String id) {
        for (Player player : players) {
            if (player.getName().equals(id)) {
                return player;
            }
        }

        return null;
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
}
