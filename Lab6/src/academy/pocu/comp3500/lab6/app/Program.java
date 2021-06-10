package academy.pocu.comp3500.lab6.app;

import academy.pocu.comp3500.lab6.League;
import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
        mainTest();
//        constructorTest();
//        findMatchOrNullTest();
//        getTopTest();
//        getBottomTest();
//        exampleTest();
//        joinAndLeaveTest();
    }

    private static void kdhTest1() {
        // join() 대량 정렬 확인
        {
            League league = new League();
            for (int i = 0; i < 10000; ++i) {
                league.join(new Player(i, "TEST", (int) (Math.random() * 1000)));
            }
            Player[] check = league.getBottom(10000);
            for (int i = 0; i < 9999; ++i) {
                assert (check[i].getRating() <= check[i + 1].getRating());
            }
        }
    }

    private static void kdhTest2() {
        // join() 대량 입력 시간 체크
        {
            long checkTime = System.currentTimeMillis();
            for (int x = 0; x < 100; ++x) {
                League league = new League();
                for (int i = 0; i < 10000; ++i) {
                    league.join(new Player(i, "TEST", (int) (Math.random() * 1000)));
                }
                Player[] check = league.getBottom(10000);
                for (int i = 0; i < 9999; ++i) {
                    assert (check[i].getRating() <= check[i + 1].getRating());
                }
            }
            System.out.println(System.currentTimeMillis() - checkTime + " ms");
        }
    }

    private static void kkrTest2() {
        {
            Player player001 = new Player(1, "player1", 4);
            Player player002 = new Player(2, "player2", 4);
            Player player003 = new Player(3, "player3", 5);
            Player player004 = new Player(4, "player4", 6);
            Player player005 = new Player(5, "player5", 6);
            Player player006 = new Player(6, "player6", 5);
            League league01 = new League(new Player[]{
                    player001, player002, player003, player004, player005,
                    player006
            }, false);
            assert (league01.leave(player006));
            assert (!league01.join(player003));
            assert (!league01.leave(player006));
            Player m = league01.findMatchOrNull(player003);
            assert (m != null && (m.getId() == player004.getId() || m.getId() == player005.getId()));
        }
        {
            Player player001 = new Player(1, "player1", 4);
            Player player002 = new Player(2, "player2", 4);
            Player player003 = new Player(3, "player3", 5);
            Player player004 = new Player(4, "player4", 6);
            Player player005 = new Player(5, "player5", 6);
            Player player006 = new Player(6, "player6", 5);
            League league01 = new League(new Player[]{
                    player001, player002, player003, player004, player005,
                    player006
            }, false);
            assert (league01.leave(player003));
            assert (!league01.join(player006));
            assert (!league01.leave(player003));
            Player m = league01.findMatchOrNull(player006);
            assert (m != null && (m.getId() == player004.getId() || m.getId() == player005.getId()));
        }
        {
            Player player001 = new Player(1, "player1", 4);
            Player player002 = new Player(2, "player2", 4);
            Player player003 = new Player(3, "player3", 5);
            Player player004 = new Player(4, "player4", 6);
            Player player005 = new Player(5, "player5", 6);
            Player player006 = new Player(6, "player6", 5);
            League league01 = new League(new Player[]{
                    player001, player002, player003, player004, player005,
                    player006
            }, false);
            assert (league01.leave(player001)); assert (!league01.leave(player001));
            Player m = league01.findMatchOrNull(player002);
            assert (m != null && (m.getId() == player003.getId() || m.getId() == player006.getId()));
            assert (league01.leave(player004)); assert (!league01.leave(player004));
            m = league01.findMatchOrNull(player005);
            assert (m != null && (m.getId() == player003.getId() || m.getId() == player006.getId()));
            assert (league01.leave(player003)); assert (!league01.leave(player003));
            m = league01.findMatchOrNull(player006);
            assert (m != null && (m.getId() == player005.getId()) || m.getId() == player006.getId());
            assert (league01.leave(player002)); assert (!league01.leave(player002));
            m = league01.findMatchOrNull(player005);
            assert (m != null && (m.getId() == player006.getId()));
            assert (league01.leave(player005)); assert (!league01.leave(player005));
            m = league01.findMatchOrNull(player006);
            assert (m == null);
        }
        {
            Player player001 = new Player(1, "player1", 4); //
            Player player002 = new Player(2, "player2", 4); //
            Player player003 = new Player(3, "player3", 5);
            Player player004 = new Player(4, "player4", 6); //
            Player player005 = new Player(5, "player5", 6); //
            Player player006 = new Player(6, "player6", 5);
            League league01 = new League(new Player[]{
                    player001, player002, player003, player004, player005,
                    player006
            }, false);
            assert (league01.leave(player004)); assert (!league01.leave(player004));
            Player m = league01.findMatchOrNull(player005);
            assert (m != null && (m.getId() == player003.getId() || m.getId() == player006.getId()));
            assert (league01.leave(player001)); assert (!league01.leave(player001));
            m = league01.findMatchOrNull(player003);
            assert (m != null && (m.getId() == player006.getId()));
            assert (league01.leave(player005)); assert (!league01.leave(player005));
            m = league01.findMatchOrNull(player003);
            assert (m != null && (m.getId() == player006.getId()));
            m = league01.findMatchOrNull(player006);
            assert (m != null && (m.getId() == player003.getId()));
            assert (league01.leave(player002)); assert (!league01.leave(player002));
            m = league01.findMatchOrNull(player003);
            assert (m != null && (m.getId() == player006.getId()));
            m = league01.findMatchOrNull(player006);
            assert (m != null && (m.getId() == player003.getId()));
            assert (league01.leave(player003)); assert (!league01.leave(player003));
            m = league01.findMatchOrNull(player006);
            assert (m == null);
        }
    }

    private static void mainTest() {
        // Constructors
        League emptyLeague = new League();

        Player[] emptyLeaguePlayers = emptyLeague.getTop(10);

        assert (emptyLeaguePlayers.length == 0);

        Player player1 = new Player(1, "player1", 4);
        Player player2 = new Player(2, "player2", 6);
        Player player3 = new Player(3, "player3", 6);
        Player player4 = new Player(4, "player4", 7);
        Player player5 = new Player(5, "player5", 10);
        Player player6 = new Player(6, "player6", 12);

        League league1 = new League(new Player[]{player1, player2, player3, player4, player5, player6}, true);
        League league2 = new League(new Player[]{player6, player4, player1, player2, player5, player3}, false);

        // findMatchOrNull()
        Player match = league1.findMatchOrNull(player2);
        assert (match.getId() == player3.getId());

        match = league1.findMatchOrNull(player4);
        assert (match.getId() == player2.getId() || match.getId() == player3.getId());

        match = league1.findMatchOrNull(player5);
        assert (match.getId() == player6.getId());

        // getTop(), getBottom()
        Player[] topPlayers = league2.getTop(3);

        assert (topPlayers[0].getId() == player6.getId());
        assert (topPlayers[1].getId() == player5.getId());
        assert (topPlayers[2].getId() == player4.getId());

        Player[] bottomPlayers = league2.getBottom(3);

        assert (bottomPlayers[0].getId() == player1.getId());
        assert ((bottomPlayers[1].getId() == player2.getId() && bottomPlayers[2].getId() == player3.getId())
                || (bottomPlayers[1].getId() == player3.getId() && bottomPlayers[2].getId() == player2.getId()));

        // join()
        boolean joinSuccess = league1.join(new Player(7, "player7", 9));
        assert (joinSuccess);

        joinSuccess = league1.join(new Player(1, "player1", 4));
        assert (!joinSuccess);

        // leave()
        boolean leaveSuccess = league1.leave(new Player(5, "player5", 10));
        assert (leaveSuccess);

        leaveSuccess = league1.leave(new Player(5, "player5", 10));
        assert (!leaveSuccess);
        System.out.println(leaveSuccess);
    }

    private static void constructorTest() {
        Random random = new Random();

        for (int testCount = 0; testCount < 1000; ++testCount) {
            int playersCount = random.nextInt(100);
            Player[] players = new Player[playersCount];

            for (int i = 1; i <= playersCount; ++i) {
                players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
            }

            League emptyLeague = new League();
            League unsortedLeague = new League(players, false);

            // TODO: sort players
            for (int i = 0; i < players.length; ++i) {
//                assert (players[i] == unsortedLeague.players[i]);
            }

            League sortedLeague = new League(players, true);
            for (int i = 0; i < players.length; ++i) {
//                assert (players[i] == unsortedLeague.players[i]);
            }
        }
    }

    private static void findMatchOrNullTest() {
        Random random = new Random();

        try {
            for (int testCount = 0; testCount < 1000; ++testCount) {
                int playersCount = random.nextInt(100);

                (new File("Lab6/src/academy/pocu/comp3500/lab6/app/test")).mkdir();
                FileWriter testWriter = new FileWriter(String.format("Lab6/src/academy/pocu/comp3500/lab6/app/test/testcase_%d.txt", testCount));
                Player[] players = new Player[playersCount];

                for (int i = 1; i <= playersCount; ++i) {
                    players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
                }
                System.out.println("그래서 뭔데 : " + players.length);
                League emptyLeague = new League();
                // TODO: sort players
                quicksortPlayer(players);
                League league = new League(players, true);


                for (int i = 0; i < 100; ++i) {
                    boolean isNullPlayer = random.nextBoolean();
                    boolean isExistingPlayer = random.nextBoolean();

                    ArrayList<Player> possibleOpponents = new ArrayList<>();
                    Player player;
                    int randomIndex;

                    if (isNullPlayer) {
                        player = null;
                    } else if (isExistingPlayer) {
                        randomIndex = random.nextInt(players.length);
                        player = players[randomIndex];
                    } else {
                        player = new Player(playersCount + i + 1, String.format("player%d", playersCount + i + 1), random.nextInt(100) + 1);

                        int minDiff = Integer.MAX_VALUE;
                        int minIndex = Integer.MIN_VALUE;
                        for (int j = 0; j < playersCount; ++j) {
                            if (Math.abs(players[j].getRating() - player.getRating()) == minDiff) {
                                possibleOpponents.add(players[j]);
                            } else if (Math.abs(players[j].getRating() - player.getRating()) < minDiff && minIndex < j) {
                                minDiff = Math.abs(players[j].getRating() - player.getRating());
                                minIndex = j;
                                possibleOpponents.clear();
                                possibleOpponents.add(players[j]);
                            }
                        }
                    }

                    for (Player candidate : possibleOpponents) {
                        testWriter.write(String.format("Player candidate_%s = new Player(%d, \"%s\", %d);\n", candidate.getName(), candidate.getId(), candidate.getName(), candidate.getRating()));
                        System.out.printf("Player candidate_%s = new Player(%d, \"%s\", %d);\n", candidate.getName(), candidate.getId(), candidate.getName(), candidate.getRating());
                    }

                    assert (emptyLeague.findMatchOrNull(player) == null);
                    Player opponent = league.findMatchOrNull(player);
                    assert (playersCount > 0 || (opponent == null));
                    if (opponent != null) {
                        testWriter.write(String.format("Player opponent1 = %s;\n", opponent.getName()));
                        System.out.printf("Player opponent1 = %s;\n", opponent.getName());
                        boolean hasOpponent = false;
                        for (Player candidate : possibleOpponents) {
                            if (candidate.getId() == opponent.getId()) {
                                hasOpponent = true;
                                break;
                            }
                        }
                        assert (hasOpponent);
                    }
                }
                testWriter.flush();
                testWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testC() {
        Random random = new Random();

//        try {
        for (int testCount = 0; testCount < 1000; ++testCount) {
            int playersCount = 1;
            Player[] players = new Player[playersCount];
//
//                (new File("Lab6/src/academy/pocu/comp3500/lab6/app/test")).mkdir();
//                FileWriter testWriter = new FileWriter(String.format("Lab6/src/academy/pocu/comp3500/lab6/app/test/testcase_%d.txt", testCount));

            for (int i = 1; i <= playersCount; ++i) {
                players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
            }

            League league = new League(players, true);

            for (Player player : players) {
//                    testWriter.write(String.format("Player %s = new Player(%d, \"%s\", %d);\n", player.getName(), player.getId(), player.getName(), player.getRating()));
                System.out.printf("Player %s = new Player(%d, \"%s\", %d);\n", player.getName(), player.getId(), player.getName(), player.getRating());
            }

//                testWriter.write(System.lineSeparator());
            System.out.println();

            for (int i = 0; i < 100; ++i) {
                boolean isNullPlayer = random.nextBoolean();
                boolean isTheSamePlayer = random.nextBoolean();
                Player player;
                if (isNullPlayer) {
                    player = null;
                } else {
                    if (isTheSamePlayer) {
                        player = players[0];
                    } else {
                        player = new Player(playersCount + i + 1, String.format("player%d", playersCount + i + 1), random.nextInt(100) + 1);
                        //                    testWriter.write(String.format("Player playerToFind%d = new Player(%d, \"%s\", %d);\n", i + 1, player.getId(), player.getName(), player.getRating()));
                        System.out.printf("Player playerToFind%d = new Player(%d, \"%s\", %d);\n", i + 1, player.getId(), player.getName(), player.getRating());
                    }
                }

                Player opponent = league.findMatchOrNull(player);
                if (isNullPlayer) {
                    assert (opponent == null);
                } else if (isTheSamePlayer) {
                    assert (opponent == null);
                } else {
                    assert (opponent != null);
//                        testWriter.write(String.format("Player opponent1 = %s;\n", opponent.getName()));
                    System.out.printf("Player opponent1 = %s;\n", opponent.getName());
                    boolean hasOpponent = false;
                    for (Player candidate : players) {
                        if (candidate.getId() == opponent.getId()) {
                            hasOpponent = true;
                            break;
                        }
                    }
                    assert (hasOpponent);
                }
            }
//                testWriter.flush();
//                testWriter.close();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void getTopTest() {
        Random random = new Random();

        for (int testCount = 0; testCount < 1000; ++testCount) {
            int playersCount = random.nextInt(100);
            Player[] players = new Player[playersCount];

            for (int i = 1; i <= playersCount; ++i) {
                players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
            }

            League emptyLeague = new League();
            // TODO: SORT players BEFORE TESTING!!
            quicksortPlayer(players);
            League league = new League(players, true);

            // count is in-bound
            for (int i = 0; i < playersCount; ++i) {
                Player[] topPlayers = league.getTop(i);

                for (int j = 0; j < i; ++j) {
                    assert (topPlayers[j].getId() == players[playersCount - 1 - j].getId());
                }

                topPlayers = emptyLeague.getTop(i);
                assert (topPlayers.length == 0);
            }

            // count is out-bound
            for (int i = 0; i < 100; ++i) {
                int outOfBoundCount = 0;
                while (0 <= outOfBoundCount && outOfBoundCount <= playersCount) {
                    outOfBoundCount = random.nextInt();
                }

                Player[] topPlayers = league.getTop(outOfBoundCount);
                if (outOfBoundCount < 0) {
                    assert (topPlayers.length == 0);
                } else {
                    assert (topPlayers.length == playersCount);
                    for (int j = 0; j < playersCount; ++j) {
                        assert (topPlayers[j].getId() == players[playersCount - 1 - j].getId());
                    }
                }

                topPlayers = emptyLeague.getTop(outOfBoundCount);
                assert (topPlayers.length == 0);
            }
        }
    }

    private static void getBottomTest() {
        Random random = new Random();

        for (int testCount = 0; testCount < 1000; ++testCount) {
            int playersCount = random.nextInt(100);
            Player[] players = new Player[playersCount];

            for (int i = 1; i <= playersCount; ++i) {
                players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
            }

            League emptyLeague = new League();
            // TODO: SORT players BEFORE TESTING!!
            quicksortPlayer(players);
            League league = new League(players, true);

            // count is in-bound
            for (int i = 0; i < playersCount; ++i) {
                Player[] bottomPlayers = league.getBottom(i);

                for (int j = 0; j < i; ++j) {
                    assert (bottomPlayers[j].getId() == players[j].getId());
                }

                bottomPlayers = emptyLeague.getBottom(i);
                assert (bottomPlayers.length == 0);
            }

            // count is out-bound
            for (int i = 0; i < 100; ++i) {
                int outOfBoundCount = 0;
                while (0 <= outOfBoundCount && outOfBoundCount <= playersCount) {
                    outOfBoundCount = random.nextInt();
                }

                Player[] bottomPlayers = league.getBottom(outOfBoundCount);

                if (outOfBoundCount < 0) {
                    assert (bottomPlayers.length == 0);
                } else {
                    assert (bottomPlayers.length == playersCount);
                    for (int j = 0; j < playersCount; ++j) {
                        assert (bottomPlayers[j].getId() == players[j].getId());
                    }
                }

                bottomPlayers = emptyLeague.getBottom(outOfBoundCount);
                assert (bottomPlayers.length == 0);
            }
        }
    }

    private static void joinAndLeaveTest() {
        Random random = new Random();

        try {
            for (int testCount = 0; testCount < 1000; ++testCount) {
                int playersCount = random.nextInt(100);

                FileWriter testWriter = new FileWriter(String.format("Lab6/src/academy/pocu/comp3500/lab6/app/test/testcase_%d.txt", testCount));
                Player[] players = new Player[playersCount];

                for (int i = 1; i <= playersCount; ++i) {
                    players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
                }

                League emptyLeague = new League();
                // TODO: sort players
                League league = new League(players, true);

                testWriter.write(String.format("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator()));
                System.out.printf("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator());
                for (int i = 0; i < playersCount; ++i) {
                    testWriter.write(String.format("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating()));
                    System.out.printf("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating());
                    testWriter.write(String.format("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator()));
                    System.out.printf("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator());
                }


                testWriter.write(String.format("League emptyLeague = new League();%s", System.lineSeparator()));
                System.out.printf("League emptyLeague = new League();%s", System.lineSeparator());

                testWriter.write(String.format("League league = new League(players, true);%s", System.lineSeparator()));
                System.out.printf("League league = new League(players, true);%s", System.lineSeparator());

                int joinCount = playersCount > 0 ? random.nextInt(playersCount) + 1 : 0;
                ArrayList<Player> additionPlayers = new ArrayList<>();

                testWriter.write(String.format("ArrayList<Player> additionPlayers = new ArrayList<>();%s", System.lineSeparator()));
                System.out.println("ArrayList<Player> additionPlayers = new ArrayList<>();");

                testWriter.write(System.lineSeparator());
                System.out.println();


                testWriter.write(String.format("boolean result = false;%s", System.lineSeparator()));
                System.out.printf("boolean result = false;%s", System.lineSeparator());

                for (int i = 0; i < joinCount; ++i) {
                    boolean isDuplicate = random.nextBoolean();

                    if (isDuplicate) {
                        int randomIndex = random.nextInt(playersCount);
                        Player duplicate = players[randomIndex];
                        testWriter.write(String.format("Player duplicate%d = players[%d];\n", i + 1, randomIndex));
                        System.out.printf("Player duplicate%d = players[%d];%s", i + 1, randomIndex, System.lineSeparator());
                        boolean result = league.join(duplicate);
                        testWriter.write(String.format("result = league.join(duplicate%d);%s", i + 1, System.lineSeparator()));
                        System.out.printf("result = league.join(duplicate%d);%s", i + 1, System.lineSeparator());
                        testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                        System.out.printf("// league result: %s\n", !result ? "true" : "false");
                        testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                        System.out.printf("assert (!result);%s", System.lineSeparator());
                        assert (!result);
                    } else {
                        boolean isNullPlayer = random.nextBoolean();
                        if (isNullPlayer) {
                            testWriter.write(String.format("Player playerToJoin%d = null;%s", i + 1, System.lineSeparator()));
                            System.out.printf("Player playerToJoin%d = null;%s", i + 1, System.lineSeparator());
                            boolean result = emptyLeague.join(null);
                            testWriter.write(String.format("result = emptyLeague.join(null);%s", System.lineSeparator()));
                            System.out.printf("result = emptyLeague.join(null);%s", System.lineSeparator());
                            testWriter.write(String.format("// empty league result: %s\n", !result ? "true" : "false"));
                            System.out.printf("// empty league result: %s\n", !result ? "true" : "false");

                            testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                            System.out.printf("assert (!result);%s", System.lineSeparator());
                            assert (!result);

                            result = league.join(null);
                            testWriter.write(String.format("result = league.join(null);%s", System.lineSeparator()));
                            System.out.printf("result = league.join(null);%s", System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", !result ? "true" : "false");

                            testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                            System.out.printf("assert (!result);%s", System.lineSeparator());
                            assert (!result);
                        } else {
                            Player playerToJoin = new Player(playersCount + i + 1, String.format("player%d", playersCount + i + 1), random.nextInt(100) + 1);
                            testWriter.write(String.format("Player playerToJoin%d = new Player(%d, \"%s\", %d);%s", i + 1, playerToJoin.getId(), playerToJoin.getName(), playerToJoin.getRating(), System.lineSeparator()));
                            System.out.printf("Player playerToJoin%d = new Player(%d, \"%s\", %d);%s", i + 1, playerToJoin.getId(), playerToJoin.getName(), playerToJoin.getRating(), System.lineSeparator());
                            additionPlayers.add(playerToJoin);

                            testWriter.write(String.format("additionPlayers.add(playerToJoin%d);%s", i + 1, System.lineSeparator()));
                            System.out.printf("additionPlayers.add(playerToJoin%d);%s", i + 1, System.lineSeparator());

                            boolean result = emptyLeague.join(playerToJoin);
                            testWriter.write(String.format("result = emptyLeague.join(playerToJoin%d);%s", i + 1, System.lineSeparator()));
                            System.out.printf("result = emptyLeague.join(playerToJoin%d);%s", i + 1, System.lineSeparator());
                            testWriter.write(String.format("// empty league result: %s\n", result ? "true" : "false"));
                            System.out.printf("// empty league result: %s\n", result ? "true" : "false");

                            testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                            System.out.printf("assert (result);%s", System.lineSeparator());
                            assert (result);

                            result = league.join(playerToJoin);
                            testWriter.write(String.format("result = league.join(playerToJoin%d);%s", i + 1, System.lineSeparator()));
                            System.out.printf("result = league.join(playerToJoin%d);%s", i + 1, System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", result ? "true" : "false");

                            testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                            System.out.printf("assert (result);%s", System.lineSeparator());
                            assert (result);
                        }
                    }
                }

                for (int i = 0; i < joinCount; ++i) {
                    if (!additionPlayers.isEmpty()) {
                        testWriter.write(String.format("Player playerToLeave%d = new Player(%d, \"%s\", %d);%s", i + 1, additionPlayers.get(additionPlayers.size() - 1).getId(), additionPlayers.get(additionPlayers.size() - 1).getName(), additionPlayers.get(additionPlayers.size() - 1).getRating(), System.lineSeparator()));
                        System.out.printf("Player playerToLeave%d = new Player(%d, \"%s\", %d);%s", i + 1, additionPlayers.get(additionPlayers.size() - 1).getId(), additionPlayers.get(additionPlayers.size() - 1).getName(), additionPlayers.get(additionPlayers.size() - 1).getRating(), System.lineSeparator());

                        boolean result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
                        testWriter.write(String.format("result = league.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator()));
                        System.out.printf("result = league.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator());
                        testWriter.write(String.format("// league result: %s\n", result ? "true" : "false"));
                        System.out.printf("// league result: %s\n", result ? "true" : "false");
                        testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                        System.out.println("assert (result);");
                        assert (result);

                        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
                        testWriter.write(String.format("result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator()));
                        System.out.printf("result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator());
                        testWriter.write(String.format("// empty league result: %s\n", result ? "true" : "false"));
                        System.out.printf("// empty league result: %s\n", result ? "true" : "false");
                        testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                        System.out.println("assert (result);");
                        assert (result);

                        additionPlayers.remove(additionPlayers.size() - 1);
                        testWriter.write(String.format("additionPlayers.remove(additionPlayers.size() - 1);%s", System.lineSeparator()));
                        System.out.println("additionPlayers.remove(additionPlayers.size() - 1);");
                    } else {
                        testWriter.write(String.format("Player playerToLeave%d = null;%s", i + 1, System.lineSeparator()));
                        System.out.printf("Player playerToLeave%d = null;%s", i + 1, System.lineSeparator());

                        boolean result = league.leave(null);
                        testWriter.write(String.format("result = league.leave(null);%s", System.lineSeparator()));
                        System.out.printf("result = league.leave(null);%s", System.lineSeparator());
                        testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                        System.out.printf("// league result: %s\n", !result ? "true" : "false");
                        testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                        System.out.println("assert (!result);");
                        assert (!result);

                        result = emptyLeague.leave(null);
                        testWriter.write(String.format("result = emptyLeague.leave(null);%s", System.lineSeparator()));
                        System.out.printf("result = emptyLeague.leave(null);%s", System.lineSeparator());
                        testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                        System.out.printf("// league result: %s\n", !result ? "true" : "false");
                        testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                        System.out.println("assert (!result);");
                        assert (!result);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFG() {
        Random random = new Random();

        try {
            for (int testCount = 0; testCount < 1000; ++testCount) {
                int playersCount = random.nextInt(100);

                FileWriter testWriter = new FileWriter("Lab6/src/academy/pocu/comp3500/lab6/app/test/testcase.txt");
                Player[] players = new Player[playersCount];

                for (int i = 1; i <= playersCount; ++i) {
                    players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
                }

                League emptyLeague = new League();
                // TODO: sort players
                League league = new League(players, true);

                testWriter.write(String.format("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator()));
                System.out.printf("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator());
                for (int i = 0; i < playersCount; ++i) {
                    testWriter.write(String.format("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating()));
                    System.out.printf("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating());
                    testWriter.write(String.format("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator()));
                    System.out.printf("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator());
                }


                testWriter.write(String.format("League emptyLeague = new League();%s", System.lineSeparator()));
                System.out.printf("League emptyLeague = new League();%s", System.lineSeparator());

                testWriter.write(String.format("League league = new League(players, true);%s", System.lineSeparator()));
                System.out.printf("League league = new League(players, true);%s", System.lineSeparator());

                int joinCount = random.nextInt(100);
                ArrayList<Player> additionPlayers = new ArrayList<>();

                testWriter.write(String.format("ArrayList<Player> additionPlayers = new ArrayList<>();%s", System.lineSeparator()));
                System.out.println("ArrayList<Player> additionPlayers = new ArrayList<>();");

                testWriter.write(System.lineSeparator());
                System.out.println();


                testWriter.write(String.format("boolean result = false;%s", System.lineSeparator()));
                System.out.printf("boolean result = false;%s", System.lineSeparator());

                for (int i = 0; i < joinCount; ++i) {
                    boolean isDuplicate = random.nextBoolean();
                    if (playersCount > 0 && isDuplicate) {
                        int randomIndex = random.nextInt(playersCount);
                        Player duplicate = players[randomIndex];
                        testWriter.write(String.format("Player duplicate%d = players[%d];\n", i + 1, randomIndex));
                        System.out.printf("Player duplicate%d = players[%d];%s", i + 1, randomIndex, System.lineSeparator());
                        boolean result = league.join(duplicate);
                        testWriter.write(String.format("result = league.join(duplicate%d);%s", i + 1, System.lineSeparator()));
                        System.out.printf("result = league.join(duplicate%d);%s", i + 1, System.lineSeparator());
                        testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                        System.out.printf("// league result: %s\n", !result ? "true" : "false");
                        testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                        System.out.printf("assert (!result);%s", System.lineSeparator());
                        assert (!result);
                    } else {
                        boolean isNullPlayer = random.nextBoolean();
                        if (isNullPlayer) {
                            boolean result = emptyLeague.join(null);
                            testWriter.write(String.format("result = emptyLeague.join(null);%s", System.lineSeparator()));
                            System.out.printf("result = emptyLeague.join(null);%s", System.lineSeparator());
                            testWriter.write(String.format("// empty league result: %s\n", !result ? "true" : "false"));
                            System.out.printf("// empty league result: %s\n", !result ? "true" : "false");

                            testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                            System.out.printf("assert (!result);%s", System.lineSeparator());
                            assert (!result);

                            result = league.join(null);
                            testWriter.write(String.format("result = league.join(null);%s", System.lineSeparator()));
                            System.out.printf("result = league.join(null);%s", System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", !result ? "true" : "false");

                            testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                            System.out.printf("assert (!result);%s", System.lineSeparator());
                            assert (!result);
                        } else {
                            Player playerToJoin = new Player(playersCount + i + 1, String.format("player%d", playersCount + i + 1), random.nextInt(100) + 1);
                            testWriter.write(String.format("Player playerToJoin%d = new Player(%d, \"%s\", %d);%s", i + 1, playerToJoin.getId(), playerToJoin.getName(), playerToJoin.getRating(), System.lineSeparator()));
                            System.out.printf("Player playerToJoin%d = new Player(%d, \"%s\", %d);%s", i + 1, playerToJoin.getId(), playerToJoin.getName(), playerToJoin.getRating(), System.lineSeparator());
                            additionPlayers.add(playerToJoin);

                            testWriter.write(String.format("additionPlayers.add(playerToJoin%d);%s", i + 1, System.lineSeparator()));
                            System.out.printf("additionPlayers.add(playerToJoin%d);%s", i + 1, System.lineSeparator());

                            boolean result = emptyLeague.join(playerToJoin);
                            testWriter.write(String.format("result = emptyLeague.join(playerToJoin%d);%s", i + 1, System.lineSeparator()));
                            System.out.printf("result = emptyLeague.join(playerToJoin%d);%s", i + 1, System.lineSeparator());
                            testWriter.write(String.format("// empty league result: %s\n", result ? "true" : "false"));
                            System.out.printf("// empty league result: %s\n", result ? "true" : "false");

                            testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                            System.out.printf("assert (result);%s", System.lineSeparator());
                            assert (result);

                            result = league.join(playerToJoin);
                            testWriter.write(String.format("result = league.join(playerToJoin%d);%s", i + 1, System.lineSeparator()));
                            System.out.printf("result = league.join(playerToJoin%d);%s", i + 1, System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", result ? "true" : "false");

                            testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                            System.out.printf("assert (result);%s", System.lineSeparator());
                            assert (result);
                        }
                    }
                }

                int leaveCount = random.nextInt(100);
                int additionalMaxSize = additionPlayers.size();
                for (int i = 0; i < leaveCount; ++i) {
                    if (!additionPlayers.isEmpty()) {
                        boolean result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
                        testWriter.write(String.format("result = league.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator()));
                        System.out.printf("result = league.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator());
                        testWriter.write(String.format("// league result: %s\n", result ? "true" : "false"));
                        System.out.printf("// league result: %s\n", result ? "true" : "false");
                        testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                        System.out.println("assert (result);");
                        assert (result);

                        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
                        testWriter.write(String.format("result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator()));
                        System.out.printf("result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));%s", System.lineSeparator());
                        testWriter.write(String.format("// empty league result: %s\n", result ? "true" : "false"));
                        System.out.printf("// empty league result: %s\n", result ? "true" : "false");
                        testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                        System.out.println("assert (result);");
                        assert (result);

                        additionPlayers.remove(additionPlayers.size() - 1);
                        testWriter.write(String.format("additionPlayers.remove(additionPlayers.size() - 1);%s", System.lineSeparator()));
                        System.out.println("additionPlayers.remove(additionPlayers.size() - 1);");
                    } else {
                        boolean isNullPlayerLeaving = random.nextBoolean();
                        if (isNullPlayerLeaving) {
                            boolean result = league.leave(null);
                            testWriter.write(String.format("result = league.leave(null);%s", System.lineSeparator()));
                            System.out.printf("result = league.leave(null);%s", System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", !result ? "true" : "false");
                            testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                            System.out.println("assert (!result);");
                            assert (!result);

                            result = emptyLeague.leave(null);
                            testWriter.write(String.format("result = emptyLeague.leave(null);%s", System.lineSeparator()));
                            System.out.printf("result = emptyLeague.leave(null);%s", System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", !result ? "true" : "false");
                            testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                            System.out.println("assert (!result);");
                            assert (!result);
                        } else {
                            boolean isNonExistingPlayerLeaving = random.nextBoolean();
                            if (isNonExistingPlayerLeaving) {
                                int randomIndex = (playersCount > 0 ? random.nextInt(playersCount) : 0) + playersCount + additionalMaxSize + 1;
                                int randomRating = random.nextInt(100) + 1;
                                Player playerToLeave = new Player(randomIndex, String.format("player%d", randomIndex), randomRating);
                                testWriter.write(String.format("Player playerToLeave%d = new Player(%d, \"player%d\", %d);%s", i + 1, randomIndex, randomIndex, randomRating, System.lineSeparator()));
                                System.out.printf("Player playerToLeave%d = new Player(%d, \"player%d\", %d);%s", i + 1, randomIndex, randomIndex, randomRating, System.lineSeparator());

                                boolean result = league.leave(playerToLeave);
                                testWriter.write(String.format("result = league.leave(playerToLeave%d);%s", i + 1, System.lineSeparator()));
                                System.out.printf("result = league.leave(playerToLeave%d);%s", i + 1, System.lineSeparator());
                                testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                                System.out.printf("// league result: %s\n", !result ? "true" : "false");
                                testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                                System.out.println("assert (!result);");
                                assert (!result);

                                result = emptyLeague.leave(playerToLeave);
                                testWriter.write(String.format("result = emptyLeague.leave(playerToLeave%d);%s", i + 1, System.lineSeparator()));
                                System.out.printf("result = emptyLeague.leave(playerToLeave%d);%s", i + 1, System.lineSeparator());
                                testWriter.write(String.format("// empty league result: %s\n", !result ? "true" : "false"));
                                System.out.printf("// empty league result: %s\n", !result ? "true" : "false");
                                testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                                System.out.println("assert (!result);");
                                assert (!result);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testG2() {
        Random random = new Random();

        try {
            for (int testCount = 0; testCount < 1000; ++testCount) {
                int playersCount = 2;

                FileWriter testWriter = new FileWriter(String.format("Lab6/src/academy/pocu/comp3500/lab6/app/test/testcase_%d.txt", testCount));
                Player[] players = new Player[playersCount];

                for (int i = 1; i <= playersCount; ++i) {
                    players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
                }

                // TODO: sort players
                League league = new League(players, true);

                testWriter.write(String.format("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator()));
                System.out.printf("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator());
                for (int i = 0; i < playersCount; ++i) {
                    testWriter.write(String.format("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating()));
                    System.out.printf("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating());
                    testWriter.write(String.format("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator()));
                    System.out.printf("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator());
                }


                testWriter.write(String.format("League emptyLeague = new League();%s", System.lineSeparator()));
                System.out.printf("League emptyLeague = new League();%s", System.lineSeparator());

                testWriter.write(String.format("League league = new League(players, true);%s", System.lineSeparator()));
                System.out.printf("League league = new League(players, true);%s", System.lineSeparator());

                testWriter.write(System.lineSeparator());
                System.out.println();

                testWriter.write(String.format("boolean result = false;%s", System.lineSeparator()));
                System.out.printf("boolean result = false;%s", System.lineSeparator());

                ArrayList<Player> playersArrayList = new ArrayList<>(Arrays.asList(players));
                while (!playersArrayList.isEmpty()) {
                    boolean isNonExistingPlayer = random.nextBoolean();
                    if (isNonExistingPlayer) {
                        int newId = random.nextInt(playersCount) + playersCount + 1;
                        Player playerToLeave = new Player(newId, String.format("player%d", newId), random.nextInt(100) + 1);
                        testWriter.write(String.format("Player playerToLeave%d = new Player(%d, \"%s\", %d);%s", newId, playerToLeave.getId(), playerToLeave.getName(), playerToLeave.getRating(), System.lineSeparator()));
                        System.out.printf("Player playerToLeave%d = new Player(%d, \"%s\", %d);%s", newId, playerToLeave.getId(), playerToLeave.getName(), playerToLeave.getRating(), System.lineSeparator());

                        boolean result = league.leave(playerToLeave);
                        testWriter.write(String.format("result = league.leave(playerToLeave%d);%s", newId, System.lineSeparator()));
                        System.out.printf("result = league.leave(playerToLeave%d);%s", newId, System.lineSeparator());
                        testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                        System.out.printf("// league result: %s\n", !result ? "true" : "false");

                        testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                        System.out.printf("assert (!result);%s", System.lineSeparator());
                        assert (!result);
                    } else {
                        boolean isNullPlayer = random.nextBoolean();
                        if (isNullPlayer) {
                            boolean result = league.leave(null);
                            testWriter.write(String.format("result = league.leave(null);%s", System.lineSeparator()));
                            System.out.printf("result = league.leave(null);%s", System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", !result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", !result ? "true" : "false");

                            testWriter.write(String.format("assert (!result);%s", System.lineSeparator()));
                            System.out.printf("assert (!result);%s", System.lineSeparator());
                            assert (!result);
                        } else {
                            int randomIndex = random.nextInt(playersArrayList.size());
                            Player duplicate = playersArrayList.get(randomIndex);
                            testWriter.write(String.format("Player duplicate%d = players[%d];\n", randomIndex, randomIndex));
                            System.out.printf("Player duplicate%d = players[%d];%s", randomIndex, randomIndex, System.lineSeparator());
                            boolean result = league.leave(duplicate);
                            testWriter.write(String.format("result = league.leave(duplicate%d);%s", randomIndex, System.lineSeparator()));
                            System.out.printf("result = league.leave(duplicate%d);%s", randomIndex, System.lineSeparator());
                            testWriter.write(String.format("// league result: %s\n", result ? "true" : "false"));
                            System.out.printf("// league result: %s\n", result ? "true" : "false");
                            testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                            System.out.printf("assert (result);%s", System.lineSeparator());
                            assert (result);
                            testWriter.write(String.format("playersArrayList.remove(randomIndex);%s", System.lineSeparator()));
                            System.out.println("playersArrayList.remove(randomIndex);");
                            playersArrayList.remove(randomIndex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testG6() {
        Random random = new Random();

        try {
            for (int testCount = 0; testCount < 1000; ++testCount) {
                int playersCount = random.nextInt(100);

                FileWriter testWriter = new FileWriter("Lab6/src/academy/pocu/comp3500/lab6/app/test/testcase.txt");
                Player[] players = new Player[playersCount];

                for (int i = 1; i <= playersCount; ++i) {
                    players[i - 1] = new Player(i, String.format("player%d", i), random.nextInt(100) + 1);
                }

                // TODO: sort players
                League league = new League(players, true);

                testWriter.write(String.format("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator()));
                System.out.printf("Player[] players = new Player[%d];%s", playersCount, System.lineSeparator());
                for (int i = 0; i < playersCount; ++i) {
                    testWriter.write(String.format("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating()));
                    System.out.printf("Player %s = new Player(%d, \"%s\", %d);\n", players[i].getName(), players[i].getId(), players[i].getName(), players[i].getRating());
                    testWriter.write(String.format("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator()));
                    System.out.printf("players[%d] = %s;%s", i, players[i].getName(), System.lineSeparator());
                }


                testWriter.write(String.format("League emptyLeague = new League();%s", System.lineSeparator()));
                System.out.printf("League emptyLeague = new League();%s", System.lineSeparator());

                testWriter.write(String.format("League league = new League(players, true);%s", System.lineSeparator()));
                System.out.printf("League league = new League(players, true);%s", System.lineSeparator());

                testWriter.write(System.lineSeparator());
                System.out.println();


                testWriter.write(String.format("boolean result = false;%s", System.lineSeparator()));
                System.out.printf("boolean result = false;%s", System.lineSeparator());

                testWriter.write(String.format("for (int i = 0; i < players.length; ++i) {%s", System.lineSeparator()));
                System.out.println("for (int i = 0; i < players.length; ++i) {");
                testWriter.write(String.format("result = league.leave(players[i]);%s", System.lineSeparator()));
                System.out.println("result = league.leave(players[i]);");
                testWriter.write(String.format("assert (result);%s", System.lineSeparator()));
                System.out.println("assert (result);");
                testWriter.write(String.format("}%s", System.lineSeparator()));
                System.out.println("}");

                for (int i = 0; i < players.length; ++i) {
                    boolean result = league.leave(players[i]);
                    assert (result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exampleG6Test() {
        {
            Player[] players = new Player[14];
            Player player14 = new Player(14, "player14", 8);
            players[0] = player14;
            Player player10 = new Player(10, "player10", 13);
            players[1] = player10;
            Player player12 = new Player(12, "player12", 16);
            players[2] = player12;
            Player player5 = new Player(5, "player5", 19);
            players[3] = player5;
            Player player8 = new Player(8, "player8", 20);
            players[4] = player8;
            Player player3 = new Player(3, "player3", 23);
            players[5] = player3;
            Player player7 = new Player(7, "player7", 27);
            players[6] = player7;
            Player player9 = new Player(9, "player9", 29);
            players[7] = player9;
            Player player6 = new Player(6, "player6", 44);
            players[8] = player6;
            Player player2 = new Player(2, "player2", 45);
            players[9] = player2;
            Player player4 = new Player(4, "player4", 67);
            players[10] = player4;
            Player player1 = new Player(1, "player1", 75);
            players[11] = player1;
            Player player13 = new Player(13, "player13", 91);
            players[12] = player13;
            Player player11 = new Player(11, "player11", 96);
            players[13] = player11;
            League emptyLeague = new League();
            League league = new League(players, true);

            boolean result = false;
            for (Player player : players) {
                result = league.leave(player);
                assert (result);
            }
        }
    }

    private static void exampleTest() {
        Player[] players = new Player[92];
        Player player22 = new Player(22, "player22", 4);
        players[0] = player22;
        Player player65 = new Player(65, "player65", 4);
        players[1] = player65;
        Player player4 = new Player(4, "player4", 5);
        players[2] = player4;
        Player player88 = new Player(88, "player88", 6);
        players[3] = player88;
        Player player33 = new Player(33, "player33", 8);
        players[4] = player33;
        Player player28 = new Player(28, "player28", 9);
        players[5] = player28;
        Player player12 = new Player(12, "player12", 9);
        players[6] = player12;
        Player player25 = new Player(25, "player25", 12);
        players[7] = player25;
        Player player2 = new Player(2, "player2", 13);
        players[8] = player2;
        Player player42 = new Player(42, "player42", 14);
        players[9] = player42;
        Player player58 = new Player(58, "player58", 14);
        players[10] = player58;
        Player player66 = new Player(66, "player66", 15);
        players[11] = player66;
        Player player23 = new Player(23, "player23", 16);
        players[12] = player23;
        Player player53 = new Player(53, "player53", 18);
        players[13] = player53;
        Player player50 = new Player(50, "player50", 19);
        players[14] = player50;
        Player player51 = new Player(51, "player51", 21);
        players[15] = player51;
        Player player45 = new Player(45, "player45", 21);
        players[16] = player45;
        Player player55 = new Player(55, "player55", 23);
        players[17] = player55;
        Player player62 = new Player(62, "player62", 23);
        players[18] = player62;
        Player player11 = new Player(11, "player11", 24);
        players[19] = player11;
        Player player83 = new Player(83, "player83", 25);
        players[20] = player83;
        Player player39 = new Player(39, "player39", 26);
        players[21] = player39;
        Player player91 = new Player(91, "player91", 28);
        players[22] = player91;
        Player player16 = new Player(16, "player16", 28);
        players[23] = player16;
        Player player77 = new Player(77, "player77", 29);
        players[24] = player77;
        Player player41 = new Player(41, "player41", 33);
        players[25] = player41;
        Player player73 = new Player(73, "player73", 33);
        players[26] = player73;
        Player player3 = new Player(3, "player3", 33);
        players[27] = player3;
        Player player63 = new Player(63, "player63", 34);
        players[28] = player63;
        Player player90 = new Player(90, "player90", 34);
        players[29] = player90;
        Player player71 = new Player(71, "player71", 35);
        players[30] = player71;
        Player player8 = new Player(8, "player8", 36);
        players[31] = player8;
        Player player1 = new Player(1, "player1", 37);
        players[32] = player1;
        Player player52 = new Player(52, "player52", 37);
        players[33] = player52;
        Player player24 = new Player(24, "player24", 38);
        players[34] = player24;
        Player player68 = new Player(68, "player68", 39);
        players[35] = player68;
        Player player61 = new Player(61, "player61", 44);
        players[36] = player61;
        Player player44 = new Player(44, "player44", 47);
        players[37] = player44;
        Player player79 = new Player(79, "player79", 47);
        players[38] = player79;
        Player player92 = new Player(92, "player92", 48);
        players[39] = player92;
        Player player20 = new Player(20, "player20", 49);
        players[40] = player20;
        Player player36 = new Player(36, "player36", 50);
        players[41] = player36;
        Player player5 = new Player(5, "player5", 51);
        players[42] = player5;
        Player player26 = new Player(26, "player26", 52);
        players[43] = player26;
        Player player47 = new Player(47, "player47", 55);
        players[44] = player47;
        Player player21 = new Player(21, "player21", 56);
        players[45] = player21;
        Player player9 = new Player(9, "player9", 59);
        players[46] = player9;
        Player player70 = new Player(70, "player70", 61);
        players[47] = player70;
        Player player43 = new Player(43, "player43", 64);
        players[48] = player43;
        Player player29 = new Player(29, "player29", 66);
        players[49] = player29;
        Player player67 = new Player(67, "player67", 66);
        players[50] = player67;
        Player player30 = new Player(30, "player30", 66);
        players[51] = player30;
        Player player38 = new Player(38, "player38", 66);
        players[52] = player38;
        Player player15 = new Player(15, "player15", 66);
        players[53] = player15;
        Player player57 = new Player(57, "player57", 68);
        players[54] = player57;
        Player player17 = new Player(17, "player17", 68);
        players[55] = player17;
        Player player86 = new Player(86, "player86", 68);
        players[56] = player86;
        Player player34 = new Player(34, "player34", 70);
        players[57] = player34;
        Player player40 = new Player(40, "player40", 71);
        players[58] = player40;
        Player player35 = new Player(35, "player35", 72);
        players[59] = player35;
        Player player37 = new Player(37, "player37", 72);
        players[60] = player37;
        Player player64 = new Player(64, "player64", 73);
        players[61] = player64;
        Player player87 = new Player(87, "player87", 74);
        players[62] = player87;
        Player player84 = new Player(84, "player84", 76);
        players[63] = player84;
        Player player27 = new Player(27, "player27", 76);
        players[64] = player27;
        Player player78 = new Player(78, "player78", 76);
        players[65] = player78;
        Player player18 = new Player(18, "player18", 77);
        players[66] = player18;
        Player player13 = new Player(13, "player13", 79);
        players[67] = player13;
        Player player14 = new Player(14, "player14", 80);
        players[68] = player14;
        Player player60 = new Player(60, "player60", 81);
        players[69] = player60;
        Player player6 = new Player(6, "player6", 81);
        players[70] = player6;
        Player player69 = new Player(69, "player69", 84);
        players[71] = player69;
        Player player72 = new Player(72, "player72", 84);
        players[72] = player72;
        Player player46 = new Player(46, "player46", 85);
        players[73] = player46;
        Player player7 = new Player(7, "player7", 85);
        players[74] = player7;
        Player player32 = new Player(32, "player32", 88);
        players[75] = player32;
        Player player31 = new Player(31, "player31", 88);
        players[76] = player31;
        Player player74 = new Player(74, "player74", 88);
        players[77] = player74;
        Player player59 = new Player(59, "player59", 89);
        players[78] = player59;
        Player player80 = new Player(80, "player80", 90);
        players[79] = player80;
        Player player48 = new Player(48, "player48", 91);
        players[80] = player48;
        Player player56 = new Player(56, "player56", 92);
        players[81] = player56;
        Player player10 = new Player(10, "player10", 92);
        players[82] = player10;
        Player player81 = new Player(81, "player81", 94);
        players[83] = player81;
        Player player85 = new Player(85, "player85", 95);
        players[84] = player85;
        Player player75 = new Player(75, "player75", 96);
        players[85] = player75;
        Player player54 = new Player(54, "player54", 96);
        players[86] = player54;
        Player player89 = new Player(89, "player89", 97);
        players[87] = player89;
        Player player49 = new Player(49, "player49", 97);
        players[88] = player49;
        Player player19 = new Player(19, "player19", 98);
        players[89] = player19;
        Player player76 = new Player(76, "player76", 98);
        players[90] = player76;
        Player player82 = new Player(82, "player82", 99);
        players[91] = player82;
        League emptyLeague = new League();
        League league = new League(players, true);
        ArrayList<Player> additionPlayers = new ArrayList<>();

        boolean result;
        Player duplicate1 = players[45];
        result = league.join(duplicate1);
// league result: true
        assert (!result);
        Player playerToJoin2 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player duplicate3 = players[9];
        result = league.join(duplicate3);
// league result: true
        assert (!result);
        Player playerToJoin4 = new Player(96, "player96", 50);
        additionPlayers.add(playerToJoin4);
        result = emptyLeague.join(playerToJoin4);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin4);
// league result: true
        assert (result);
        Player playerToJoin5 = new Player(97, "player97", 80);
        additionPlayers.add(playerToJoin5);
        result = emptyLeague.join(playerToJoin5);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin5);
// league result: true
        assert (result);
        Player duplicate6 = players[70];
        result = league.join(duplicate6);
// league result: true
        assert (!result);
        Player playerToJoin7 = new Player(99, "player99", 95);
        additionPlayers.add(playerToJoin7);
        result = emptyLeague.join(playerToJoin7);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin7);
// league result: true
        assert (result);
        Player playerToJoin8 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player duplicate9 = players[11];
        result = league.join(duplicate9);
// league result: true
        assert (!result);
        Player playerToJoin10 = new Player(102, "player102", 77);
        additionPlayers.add(playerToJoin10);
        result = emptyLeague.join(playerToJoin10);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin10);
// league result: true
        assert (result);
        Player playerToJoin11 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player playerToJoin12 = new Player(104, "player104", 17);
        additionPlayers.add(playerToJoin12);
        result = emptyLeague.join(playerToJoin12);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin12);
// league result: true
        assert (result);
        Player playerToJoin13 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player duplicate14 = players[13];
        result = league.join(duplicate14);
// league result: true
        assert (!result);
        Player playerToJoin15 = new Player(107, "player107", 25);
        additionPlayers.add(playerToJoin15);
        result = emptyLeague.join(playerToJoin15);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin15);
// league result: true
        assert (result);
        Player duplicate16 = players[71];
        result = league.join(duplicate16);
// league result: true
        assert (!result);
        Player duplicate17 = players[79];
        result = league.join(duplicate17);
// league result: true
        assert (!result);
        Player playerToJoin18 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player duplicate19 = players[32];
        result = league.join(duplicate19);
// league result: true
        assert (!result);
        Player playerToJoin20 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player playerToJoin21 = new Player(113, "player113", 25);
        additionPlayers.add(playerToJoin21);
        result = emptyLeague.join(playerToJoin21);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin21);
// league result: true
        assert (result);
        Player duplicate22 = players[6];
        result = league.join(duplicate22);
// league result: true
        assert (!result);
        Player duplicate23 = players[47];
        result = league.join(duplicate23);
// league result: true
        assert (!result);
        Player duplicate24 = players[1];
        result = league.join(duplicate24);
// league result: true
        assert (!result);
        Player duplicate25 = players[22];
        result = league.join(duplicate25);
// league result: true
        assert (!result);
        Player playerToJoin26 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player playerToJoin27 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player playerToJoin28 = new Player(120, "player120", 32);
        additionPlayers.add(playerToJoin28);
        result = emptyLeague.join(playerToJoin28);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin28);
// league result: true
        assert (result);
        Player playerToJoin29 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player duplicate30 = players[12];
        result = league.join(duplicate30);
// league result: true
        assert (!result);
        Player playerToJoin31 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player playerToJoin32 = null;
        result = emptyLeague.join(null);
// empty league result: true
        assert (!result);
        result = league.join(null);
// league result: true
        assert (!result);
        Player duplicate33 = players[85];
        result = league.join(duplicate33);
// league result: true
        assert (!result);
        Player duplicate34 = players[23];
        result = league.join(duplicate34);
// league result: true
        assert (!result);
        Player playerToJoin35 = new Player(127, "player127", 52);
        additionPlayers.add(playerToJoin35);
        result = emptyLeague.join(playerToJoin35);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin35);
// league result: true
        assert (result);
        Player duplicate36 = players[90];
        result = league.join(duplicate36);
// league result: true
        assert (!result);
        Player duplicate37 = players[3];
        result = league.join(duplicate37);
// league result: true
        assert (!result);
        Player playerToJoin38 = new Player(130, "player130", 61);
        additionPlayers.add(playerToJoin38);
        result = emptyLeague.join(playerToJoin38);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin38);
// league result: true
        assert (result);
        Player playerToJoin39 = new Player(131, "player131", 27);
        additionPlayers.add(playerToJoin39);
        result = emptyLeague.join(playerToJoin39);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin39);
// league result: true
        assert (result);
        Player duplicate40 = players[41];
        result = league.join(duplicate40);
// league result: true
        assert (!result);
        Player duplicate41 = players[54];
        result = league.join(duplicate41);
// league result: true
        assert (!result);
        Player playerToJoin42 = new Player(134, "player134", 2);
        additionPlayers.add(playerToJoin42);
        result = emptyLeague.join(playerToJoin42);
// empty league result: true
        assert (result);
        result = league.join(playerToJoin42);
// league result: true
        assert (result);
        Player duplicate43 = players[6];
        result = league.join(duplicate43);
// league result: true
        assert (!result);
        Player playerToLeave1 = new Player(134, "player134", 2);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: false
        assert (result);
        additionPlayers.remove(additionPlayers.size() - 1);
        Player playerToLeave2 = new Player(131, "player131", 27);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: false
        assert (result);
        additionPlayers.remove(additionPlayers.size() - 1);
        Player playerToLeave3 = new Player(130, "player130", 61);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: false
        assert (result);
        additionPlayers.remove(additionPlayers.size() - 1);
        Player playerToLeave4 = new Player(127, "player127", 52);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: false
        assert (result);
        additionPlayers.remove(additionPlayers.size() - 1);
        Player playerToLeave5 = new Player(120, "player120", 32);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: false
        assert (result);
        additionPlayers.remove(additionPlayers.size() - 1);
        Player playerToLeave6 = new Player(113, "player113", 25);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: false
        assert (result);
        additionPlayers.remove(additionPlayers.size() - 1);
        Player playerToLeave7 = new Player(107, "player107", 25);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: false
        assert (result);
        additionPlayers.remove(additionPlayers.size() - 1);
        Player playerToLeave8 = new Player(104, "player104", 17);
        result = league.leave(additionPlayers.get(additionPlayers.size() - 1));
// league result: true
        assert (result);
        result = emptyLeague.leave(additionPlayers.get(additionPlayers.size() - 1));
// empty league result: true
        assert (result);
    }

    private static void exampleTestFG() {
        {
            Player[] players = new Player[92];
            Player player77 = new Player(77, "player77", 1);
            players[0] = player77;
            Player player28 = new Player(28, "player28", 1);
            players[1] = player28;
            Player player35 = new Player(35, "player35", 4);
            players[2] = player35;
            Player player67 = new Player(67, "player67", 5);
            players[3] = player67;
            Player player51 = new Player(51, "player51", 5);
            players[4] = player51;
            Player player91 = new Player(91, "player91", 5);
            players[5] = player91;
            Player player3 = new Player(3, "player3", 6);
            players[6] = player3;
            Player player90 = new Player(90, "player90", 6);
            players[7] = player90;
            Player player80 = new Player(80, "player80", 7);
            players[8] = player80;
            Player player50 = new Player(50, "player50", 7);
            players[9] = player50;
            Player player16 = new Player(16, "player16", 9);
            players[10] = player16;
            Player player46 = new Player(46, "player46", 12);
            players[11] = player46;
            Player player9 = new Player(9, "player9", 12);
            players[12] = player9;
            Player player7 = new Player(7, "player7", 14);
            players[13] = player7;
            Player player12 = new Player(12, "player12", 15);
            players[14] = player12;
            Player player4 = new Player(4, "player4", 16);
            players[15] = player4;
            Player player82 = new Player(82, "player82", 17);
            players[16] = player82;
            Player player65 = new Player(65, "player65", 18);
            players[17] = player65;
            Player player8 = new Player(8, "player8", 20);
            players[18] = player8;
            Player player19 = new Player(19, "player19", 20);
            players[19] = player19;
            Player player45 = new Player(45, "player45", 21);
            players[20] = player45;
            Player player26 = new Player(26, "player26", 24);
            players[21] = player26;
            Player player92 = new Player(92, "player92", 25);
            players[22] = player92;
            Player player85 = new Player(85, "player85", 25);
            players[23] = player85;
            Player player72 = new Player(72, "player72", 26);
            players[24] = player72;
            Player player10 = new Player(10, "player10", 26);
            players[25] = player10;
            Player player60 = new Player(60, "player60", 28);
            players[26] = player60;
            Player player53 = new Player(53, "player53", 30);
            players[27] = player53;
            Player player36 = new Player(36, "player36", 31);
            players[28] = player36;
            Player player24 = new Player(24, "player24", 31);
            players[29] = player24;
            Player player5 = new Player(5, "player5", 33);
            players[30] = player5;
            Player player34 = new Player(34, "player34", 35);
            players[31] = player34;
            Player player32 = new Player(32, "player32", 35);
            players[32] = player32;
            Player player42 = new Player(42, "player42", 36);
            players[33] = player42;
            Player player6 = new Player(6, "player6", 37);
            players[34] = player6;
            Player player39 = new Player(39, "player39", 41);
            players[35] = player39;
            Player player63 = new Player(63, "player63", 41);
            players[36] = player63;
            Player player21 = new Player(21, "player21", 43);
            players[37] = player21;
            Player player40 = new Player(40, "player40", 45);
            players[38] = player40;
            Player player59 = new Player(59, "player59", 46);
            players[39] = player59;
            Player player43 = new Player(43, "player43", 47);
            players[40] = player43;
            Player player70 = new Player(70, "player70", 48);
            players[41] = player70;
            Player player2 = new Player(2, "player2", 50);
            players[42] = player2;
            Player player75 = new Player(75, "player75", 50);
            players[43] = player75;
            Player player38 = new Player(38, "player38", 50);
            players[44] = player38;
            Player player86 = new Player(86, "player86", 52);
            players[45] = player86;
            Player player27 = new Player(27, "player27", 53);
            players[46] = player27;
            Player player29 = new Player(29, "player29", 53);
            players[47] = player29;
            Player player61 = new Player(61, "player61", 54);
            players[48] = player61;
            Player player74 = new Player(74, "player74", 54);
            players[49] = player74;
            Player player66 = new Player(66, "player66", 55);
            players[50] = player66;
            Player player37 = new Player(37, "player37", 56);
            players[51] = player37;
            Player player52 = new Player(52, "player52", 59);
            players[52] = player52;
            Player player83 = new Player(83, "player83", 60);
            players[53] = player83;
            Player player87 = new Player(87, "player87", 60);
            players[54] = player87;
            Player player58 = new Player(58, "player58", 61);
            players[55] = player58;
            Player player81 = new Player(81, "player81", 61);
            players[56] = player81;
            Player player14 = new Player(14, "player14", 62);
            players[57] = player14;
            Player player23 = new Player(23, "player23", 62);
            players[58] = player23;
            Player player68 = new Player(68, "player68", 65);
            players[59] = player68;
            Player player47 = new Player(47, "player47", 67);
            players[60] = player47;
            Player player78 = new Player(78, "player78", 67);
            players[61] = player78;
            Player player57 = new Player(57, "player57", 68);
            players[62] = player57;
            Player player11 = new Player(11, "player11", 68);
            players[63] = player11;
            Player player48 = new Player(48, "player48", 70);
            players[64] = player48;
            Player player15 = new Player(15, "player15", 70);
            players[65] = player15;
            Player player22 = new Player(22, "player22", 71);
            players[66] = player22;
            Player player89 = new Player(89, "player89", 72);
            players[67] = player89;
            Player player62 = new Player(62, "player62", 73);
            players[68] = player62;
            Player player20 = new Player(20, "player20", 75);
            players[69] = player20;
            Player player56 = new Player(56, "player56", 79);
            players[70] = player56;
            Player player44 = new Player(44, "player44", 80);
            players[71] = player44;
            Player player73 = new Player(73, "player73", 81);
            players[72] = player73;
            Player player18 = new Player(18, "player18", 82);
            players[73] = player18;
            Player player54 = new Player(54, "player54", 84);
            players[74] = player54;
            Player player79 = new Player(79, "player79", 86);
            players[75] = player79;
            Player player49 = new Player(49, "player49", 86);
            players[76] = player49;
            Player player25 = new Player(25, "player25", 87);
            players[77] = player25;
            Player player55 = new Player(55, "player55", 87);
            players[78] = player55;
            Player player13 = new Player(13, "player13", 87);
            players[79] = player13;
            Player player76 = new Player(76, "player76", 90);
            players[80] = player76;
            Player player31 = new Player(31, "player31", 91);
            players[81] = player31;
            Player player41 = new Player(41, "player41", 92);
            players[82] = player41;
            Player player71 = new Player(71, "player71", 93);
            players[83] = player71;
            Player player84 = new Player(84, "player84", 93);
            players[84] = player84;
            Player player88 = new Player(88, "player88", 94);
            players[85] = player88;
            Player player30 = new Player(30, "player30", 95);
            players[86] = player30;
            Player player69 = new Player(69, "player69", 96);
            players[87] = player69;
            Player player64 = new Player(64, "player64", 96);
            players[88] = player64;
            Player player17 = new Player(17, "player17", 97);
            players[89] = player17;
            Player player1 = new Player(1, "player1", 98);
            players[90] = player1;
            Player player33 = new Player(33, "player33", 100);
            players[91] = player33;
            League emptyLeague = new League();
            League league = new League(players, true);
            ArrayList<Player> additionPlayers = new ArrayList<>();

            boolean result = false;
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player playerToJoin2 = new Player(94, "player94", 16);
            additionPlayers.add(playerToJoin2);
            result = emptyLeague.join(playerToJoin2);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin2);
// league result: true
            assert (result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate4 = players[62];
            result = league.join(duplicate4);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate9 = players[56];
            result = league.join(duplicate9);
// league result: true
            assert (!result);
            Player duplicate10 = players[28];
            result = league.join(duplicate10);
// league result: true
            assert (!result);
            Player playerToJoin11 = new Player(103, "player103", 92);
            additionPlayers.add(playerToJoin11);
            result = emptyLeague.join(playerToJoin11);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin11);
// league result: true
            assert (result);
            Player playerToJoin12 = new Player(104, "player104", 90);
            additionPlayers.add(playerToJoin12);
            result = emptyLeague.join(playerToJoin12);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin12);
// league result: true
            assert (result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate14 = players[88];
            result = league.join(duplicate14);
// league result: true
            assert (!result);
            Player duplicate15 = players[67];
            result = league.join(duplicate15);
// league result: true
            assert (!result);
            Player duplicate16 = players[37];
            result = league.join(duplicate16);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate18 = players[86];
            result = league.join(duplicate18);
// league result: true
            assert (!result);
            Player duplicate19 = players[76];
            result = league.join(duplicate19);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player playerToJoin23 = new Player(115, "player115", 30);
            additionPlayers.add(playerToJoin23);
            result = emptyLeague.join(playerToJoin23);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin23);
// league result: true
            assert (result);
            Player duplicate24 = players[72];
            result = league.join(duplicate24);
// league result: true
            assert (!result);
            Player playerToJoin25 = new Player(117, "player117", 63);
            additionPlayers.add(playerToJoin25);
            result = emptyLeague.join(playerToJoin25);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin25);
// league result: true
            assert (result);
            Player duplicate26 = players[5];
            result = league.join(duplicate26);
// league result: true
            assert (!result);
            Player playerToJoin27 = new Player(119, "player119", 38);
            additionPlayers.add(playerToJoin27);
            result = emptyLeague.join(playerToJoin27);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin27);
// league result: true
            assert (result);
            Player duplicate28 = players[81];
            result = league.join(duplicate28);
// league result: true
            assert (!result);
            Player playerToJoin29 = new Player(121, "player121", 9);
            additionPlayers.add(playerToJoin29);
            result = emptyLeague.join(playerToJoin29);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin29);
// league result: true
            assert (result);
            Player duplicate30 = players[54];
            result = league.join(duplicate30);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate32 = players[67];
            result = league.join(duplicate32);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player playerToJoin38 = new Player(130, "player130", 58);
            additionPlayers.add(playerToJoin38);
            result = emptyLeague.join(playerToJoin38);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin38);
// league result: true
            assert (result);
            Player duplicate39 = players[10];
            result = league.join(duplicate39);
// league result: true
            assert (!result);
            Player playerToJoin40 = new Player(132, "player132", 1);
            additionPlayers.add(playerToJoin40);
            result = emptyLeague.join(playerToJoin40);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin40);
// league result: true
            assert (result);
            Player playerToJoin41 = new Player(133, "player133", 4);
            additionPlayers.add(playerToJoin41);
            result = emptyLeague.join(playerToJoin41);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin41);
// league result: true
            assert (result);
            Player duplicate42 = players[39];
            result = league.join(duplicate42);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate44 = players[47];
            result = league.join(duplicate44);
// league result: true
            assert (!result);
            Player duplicate45 = players[45];
            result = league.join(duplicate45);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate47 = players[71];
            result = league.join(duplicate47);
// league result: true
            assert (!result);
            Player duplicate48 = players[75];
            result = league.join(duplicate48);
// league result: true
            assert (!result);
            Player duplicate49 = players[46];
            result = league.join(duplicate49);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate51 = players[4];
            result = league.join(duplicate51);
// league result: true
            assert (!result);
            Player duplicate52 = players[1];
            result = league.join(duplicate52);
// league result: true
            assert (!result);
            Player playerToJoin53 = new Player(145, "player145", 55);
            additionPlayers.add(playerToJoin53);
            result = emptyLeague.join(playerToJoin53);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin53);
// league result: true
            assert (result);
            Player duplicate54 = players[18];
            result = league.join(duplicate54);
// league result: true
            assert (!result);
            Player playerToJoin55 = new Player(147, "player147", 89);
            additionPlayers.add(playerToJoin55);
            result = emptyLeague.join(playerToJoin55);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin55);
// league result: true
            assert (result);
            Player playerToJoin56 = new Player(148, "player148", 82);
            additionPlayers.add(playerToJoin56);
            result = emptyLeague.join(playerToJoin56);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin56);
// league result: true
            assert (result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate58 = players[85];
            result = league.join(duplicate58);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate60 = players[83];
            result = league.join(duplicate60);
// league result: true
            assert (!result);
            Player playerToJoin61 = new Player(153, "player153", 77);
            additionPlayers.add(playerToJoin61);
            result = emptyLeague.join(playerToJoin61);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin61);
// league result: true
            assert (result);
            Player playerToJoin62 = new Player(154, "player154", 44);
            additionPlayers.add(playerToJoin62);
            result = emptyLeague.join(playerToJoin62);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin62);
// league result: true
            assert (result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate65 = players[3];
            result = league.join(duplicate65);
// league result: true
            assert (!result);
            Player playerToJoin66 = new Player(158, "player158", 69);
            additionPlayers.add(playerToJoin66);
            result = emptyLeague.join(playerToJoin66);
// empty league result: true
            assert (result);
            result = league.join(playerToJoin66);
// league result: true
            assert (result);
            Player duplicate67 = players[64];
            result = league.join(duplicate67);
// league result: true
            assert (!result);
            result = emptyLeague.join(null);
// empty league result: true
            assert (!result);
            result = league.join(null);
// league result: true
            assert (!result);
            Player duplicate69 = players[52];
            result = league.join(duplicate69);
// league result: true
            assert (!result);
            Player duplicate70 = players[54];
            result = league.join(duplicate70);
// league result: true
            assert (!result);
            Player duplicate71 = players[34];
            result = league.join(duplicate71);
// league result: true
            assert (!result);
            Player duplicate72 = players[85];
            result = league.join(duplicate72);
// league result: false
            assert (!result);
        }
    }

    private static void exampleTestG6() {
        Player[] players = new Player[92];
        Player player48 = new Player(48, "player48", 1);
        players[0] = player48;
        Player player4 = new Player(4, "player4", 2);
        players[1] = player4;
        Player player40 = new Player(40, "player40", 4);
        players[2] = player40;
        Player player76 = new Player(76, "player76", 6);
        players[3] = player76;
        Player player36 = new Player(36, "player36", 6);
        players[4] = player36;
        Player player91 = new Player(91, "player91", 8);
        players[5] = player91;
        Player player8 = new Player(8, "player8", 9);
        players[6] = player8;
        Player player70 = new Player(70, "player70", 10);
        players[7] = player70;
        Player player53 = new Player(53, "player53", 11);
        players[8] = player53;
        Player player73 = new Player(73, "player73", 12);
        players[9] = player73;
        Player player88 = new Player(88, "player88", 13);
        players[10] = player88;
        Player player86 = new Player(86, "player86", 13);
        players[11] = player86;
        Player player34 = new Player(34, "player34", 15);
        players[12] = player34;
        Player player51 = new Player(51, "player51", 15);
        players[13] = player51;
        Player player77 = new Player(77, "player77", 17);
        players[14] = player77;
        Player player28 = new Player(28, "player28", 19);
        players[15] = player28;
        Player player72 = new Player(72, "player72", 20);
        players[16] = player72;
        Player player24 = new Player(24, "player24", 20);
        players[17] = player24;
        Player player85 = new Player(85, "player85", 21);
        players[18] = player85;
        Player player83 = new Player(83, "player83", 22);
        players[19] = player83;
        Player player5 = new Player(5, "player5", 24);
        players[20] = player5;
        Player player55 = new Player(55, "player55", 24);
        players[21] = player55;
        Player player89 = new Player(89, "player89", 24);
        players[22] = player89;
        Player player75 = new Player(75, "player75", 24);
        players[23] = player75;
        Player player81 = new Player(81, "player81", 25);
        players[24] = player81;
        Player player17 = new Player(17, "player17", 25);
        players[25] = player17;
        Player player3 = new Player(3, "player3", 26);
        players[26] = player3;
        Player player80 = new Player(80, "player80", 27);
        players[27] = player80;
        Player player12 = new Player(12, "player12", 29);
        players[28] = player12;
        Player player87 = new Player(87, "player87", 30);
        players[29] = player87;
        Player player35 = new Player(35, "player35", 31);
        players[30] = player35;
        Player player7 = new Player(7, "player7", 31);
        players[31] = player7;
        Player player57 = new Player(57, "player57", 32);
        players[32] = player57;
        Player player63 = new Player(63, "player63", 32);
        players[33] = player63;
        Player player32 = new Player(32, "player32", 32);
        players[34] = player32;
        Player player82 = new Player(82, "player82", 32);
        players[35] = player82;
        Player player9 = new Player(9, "player9", 34);
        players[36] = player9;
        Player player39 = new Player(39, "player39", 34);
        players[37] = player39;
        Player player44 = new Player(44, "player44", 34);
        players[38] = player44;
        Player player54 = new Player(54, "player54", 35);
        players[39] = player54;
        Player player69 = new Player(69, "player69", 35);
        players[40] = player69;
        Player player74 = new Player(74, "player74", 36);
        players[41] = player74;
        Player player27 = new Player(27, "player27", 37);
        players[42] = player27;
        Player player31 = new Player(31, "player31", 39);
        players[43] = player31;
        Player player58 = new Player(58, "player58", 44);
        players[44] = player58;
        Player player16 = new Player(16, "player16", 46);
        players[45] = player16;
        Player player14 = new Player(14, "player14", 47);
        players[46] = player14;
        Player player21 = new Player(21, "player21", 48);
        players[47] = player21;
        Player player10 = new Player(10, "player10", 50);
        players[48] = player10;
        Player player13 = new Player(13, "player13", 52);
        players[49] = player13;
        Player player18 = new Player(18, "player18", 52);
        players[50] = player18;
        Player player33 = new Player(33, "player33", 55);
        players[51] = player33;
        Player player6 = new Player(6, "player6", 56);
        players[52] = player6;
        Player player68 = new Player(68, "player68", 56);
        players[53] = player68;
        Player player19 = new Player(19, "player19", 56);
        players[54] = player19;
        Player player65 = new Player(65, "player65", 56);
        players[55] = player65;
        Player player92 = new Player(92, "player92", 57);
        players[56] = player92;
        Player player84 = new Player(84, "player84", 58);
        players[57] = player84;
        Player player22 = new Player(22, "player22", 60);
        players[58] = player22;
        Player player56 = new Player(56, "player56", 61);
        players[59] = player56;
        Player player45 = new Player(45, "player45", 62);
        players[60] = player45;
        Player player90 = new Player(90, "player90", 66);
        players[61] = player90;
        Player player23 = new Player(23, "player23", 66);
        players[62] = player23;
        Player player52 = new Player(52, "player52", 66);
        players[63] = player52;
        Player player71 = new Player(71, "player71", 72);
        players[64] = player71;
        Player player30 = new Player(30, "player30", 73);
        players[65] = player30;
        Player player11 = new Player(11, "player11", 76);
        players[66] = player11;
        Player player59 = new Player(59, "player59", 76);
        players[67] = player59;
        Player player66 = new Player(66, "player66", 76);
        players[68] = player66;
        Player player37 = new Player(37, "player37", 77);
        players[69] = player37;
        Player player26 = new Player(26, "player26", 80);
        players[70] = player26;
        Player player43 = new Player(43, "player43", 81);
        players[71] = player43;
        Player player78 = new Player(78, "player78", 81);
        players[72] = player78;
        Player player67 = new Player(67, "player67", 82);
        players[73] = player67;
        Player player62 = new Player(62, "player62", 83);
        players[74] = player62;
        Player player38 = new Player(38, "player38", 84);
        players[75] = player38;
        Player player46 = new Player(46, "player46", 85);
        players[76] = player46;
        Player player61 = new Player(61, "player61", 85);
        players[77] = player61;
        Player player20 = new Player(20, "player20", 88);
        players[78] = player20;
        Player player15 = new Player(15, "player15", 88);
        players[79] = player15;
        Player player41 = new Player(41, "player41", 90);
        players[80] = player41;
        Player player79 = new Player(79, "player79", 90);
        players[81] = player79;
        Player player42 = new Player(42, "player42", 91);
        players[82] = player42;
        Player player25 = new Player(25, "player25", 91);
        players[83] = player25;
        Player player64 = new Player(64, "player64", 92);
        players[84] = player64;
        Player player50 = new Player(50, "player50", 92);
        players[85] = player50;
        Player player60 = new Player(60, "player60", 93);
        players[86] = player60;
        Player player47 = new Player(47, "player47", 93);
        players[87] = player47;
        Player player2 = new Player(2, "player2", 96);
        players[88] = player2;
        Player player1 = new Player(1, "player1", 98);
        players[89] = player1;
        Player player49 = new Player(49, "player49", 98);
        players[90] = player49;
        Player player29 = new Player(29, "player29", 100);
        players[91] = player29;

        League league = new League(players, true);
        ArrayList<Player> playerArrayList = new ArrayList<>(Arrays.asList(players));

        boolean result;
        for (Player player : playerArrayList) {
            System.out.printf("id: %d name: %s rating: %d %s", player.getId(), player.getName(), player.getRating(), System.lineSeparator());
            result = league.leave(player);
            assert (result);
        }

        assert (league.getTop(1).length == 0);
    }

    private static void kkrTest() {
        // Constructors
        League emptyLeague = new League();
        Player[] emptyLeaguePlayers = emptyLeague.getTop(10);
        assert (emptyLeaguePlayers.length == 0);
        Player player1 = new Player(1, "player1", 4);
        Player player2 = new Player(2, "player2", 6);
        Player player3 = new Player(3, "player3", 6);
        Player player4 = new Player(4, "player4", 7);
        Player player5 = new Player(5, "player5", 10);
        Player player6 = new Player(6, "player6", 12);
        Player player7 = new Player(7, "player7", 13);
        Player player8 = new Player(8, "player8", 14);
        League league1 = new League(new Player[]{player1, player2, player3, player4, player5, player6}, true);
        League league2 = new League(new Player[]{player6, player4, player1, player2, player5, player3}, false);
        // findMatchOrNull()
        Player match = league1.findMatchOrNull(player2);
        assert (match.getId() == player3.getId());
        match = league1.findMatchOrNull(player4);
        assert (match.getId() == player2.getId() || match.getId() == player3.getId());
        match = league1.findMatchOrNull(player5);
        assert (match.getId() == player6.getId());
        {
            System.out.println("C01");
            League league = new League(new Player[]{}, true);
            assert (league.findMatchOrNull(player2) == null);
        }
        {
            System.out.println("C02");
            League league = new League(new Player[]{player1}, true);
            Player m = league.findMatchOrNull(player2);
            assert (m.getId() == player1.getId());
        }
        {
            System.out.println("C02-2");
            League league = new League(new Player[]{player1}, true);
            Player m = league.findMatchOrNull(player1);
            assert (m == null);
        }
        {
            League league = new League(new Player[]{
                    player1, player2, player3, player4, player5, player6, player7, player8
            }, true);
            System.out.println("C03");
            Player m = league.findMatchOrNull(player1);
            assert (m.getId() == player2.getId() || m.getId() == player3.getId());
            System.out.println("C03-2");
            m = league.findMatchOrNull(player2);
            assert (m.getId() == player3.getId());
            System.out.println("C03-3");
            m = league.findMatchOrNull(player4);
            assert (m.getId() == player2.getId() || m.getId() == player3.getId());
            System.out.println("C03-4");
            m = league.findMatchOrNull(player4);
            assert (m.getId() == player2.getId() || m.getId() == player3.getId());
            System.out.println("C03-5");
            m = league.findMatchOrNull(player7);
            assert (m.getId() == player8.getId());
            System.out.println("C03-6");
            m = league.findMatchOrNull(player8);
            assert (m.getId() == player7.getId());
        }
    }

    private static void mskTest() {
        // leave
        {
            League league = new League();
            Player player1 = new Player(0, "player1", 4);
            Player player2 = new Player(1, "player2", 5);
            Player player3 = new Player(2, "player3", 5);
            Player player4 = new Player(3, "player4", 6);

            boolean leave = league.leave(player1);
            assert !leave;

            league = new League(new Player[]{player1, player2, player3, player4}, true);
            Player player5 = new Player(4, "player5", 5);
            leave = league.leave(player1);
            assert leave;
            leave = league.leave(player1);
            assert !leave;
            leave = league.leave(player2);
            assert leave;
            leave = league.leave(player2);
            assert !leave;
            leave = league.leave(player3);
            assert leave;
            leave = league.leave(player2);
            assert !leave;
        }
        {
            League league = new League();
            Player player1 = new Player(31, "player1", 3);
            Player player2 = new Player(41, "player2", 3);
            Player player3 = new Player(51, "player3", 3);
            Player player4 = new Player(61, "player4", 3);
            league.join(player1);
            league.join(player2);
            league.join(player3);
            league.join(player4);
            assert league.leave(player1);
            assert !league.leave(player1);
        }

        // join and leave
        {
            League league = new League();
            Player player1 = new Player(0, "player1", 4);
            Player player2 = new Player(1, "player2", 5);
            Player player3 = new Player(2, "player3", 5);
            Player player4 = new Player(3, "player4", 6);

            assert !league.leave(player1);
            assert league.join(player1);
            assert league.leave(player1);
            assert league.join(player2);
            assert league.join(player3);
            assert league.join(player4);
            assert league.leave(player2);
            assert league.leave(player3);
            assert league.leave(player4);

            league = new League(new Player[]{player1, player2, player3, player4}, true);
            Player player5 = new Player(4, "player5", 5);
            assert league.leave(player4);
            assert league.leave(player3);
            assert league.leave(player2);
            assert league.leave(player1);
            assert league.join(player1);
            assert league.join(player2);
            assert league.join(player3);
            assert league.join(player4);
        }
    }

    public static void quicksortPlayer(Player[] players) {
        quicksortPlayerRecursive(players, 0, players.length - 1);
    }

    public static void quicksortPlayerRecursive(Player[] players, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotPos = partition(players, left, right);

        quicksortPlayerRecursive(players, left, pivotPos - 1);
        quicksortPlayerRecursive(players, pivotPos + 1, right);
    }

    public static int partition(Player[] players, int left, int right) {
        int pivot = players[right].getRating();

        int i = (left - 1);
        for (int j = left; j < right; j++) {
            if (players[j].getRating() < pivot) {
                ++i;
                swapPlayer2(players, i, j);
            }
        }

        int pivotPos = i + 1;
        swapPlayer2(players, pivotPos, right);

        return pivotPos;
    }

    public static void swapPlayer2(Player[] players, int first, int second) {
        Player playerDummy = players[first];
        players[first] = players[second];
        players[second] = playerDummy;
    }
}
