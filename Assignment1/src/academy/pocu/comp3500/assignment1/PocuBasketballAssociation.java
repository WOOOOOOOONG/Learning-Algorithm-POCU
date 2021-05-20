package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.Player;
import academy.pocu.comp3500.assignment1.pba.GameStat;

public final class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
        // 1. 플레이어 이름 선정 -> O(n^2)
        int playerIndex = 0;
        for (int i = 0; i < gameStats.length; i++) {
            if (playerIndex == outPlayers.length) {
                break;
            }

            String playerName = gameStats[i].getPlayerName();
            boolean bOverlap = false;
            for (int j = 0; j < outPlayers.length; j++) {
                if (playerName.equals(outPlayers[j].getName())) {
                    bOverlap = true;
                    break;
                }
            }

            if (!bOverlap) {
                outPlayers[playerIndex++].setName(playerName);
            }
        }

        // 각각의 합 계산 -> O(n^2)
        for (int i = 0; i < outPlayers.length; i++) {
            String name = outPlayers[i].getName();
            int scoreSum = 0;
            int assistSum = 0;
            int passSum = 0;
            int goalAttemptSum = 0;
            int goalSum = 0;
            int gameCount = 0;

            for (int j = 0; j < gameStats.length; j++) {
                if (gameStats[j].getPlayerName().equals(name)) {
                    scoreSum += gameStats[j].getPoints();
                    assistSum += gameStats[j].getAssists();
                    passSum += gameStats[j].getNumPasses();
                    goalAttemptSum += gameStats[j].getGoalAttempts();
                    goalSum += gameStats[j].getGoals();
                    gameCount++;
                }
            }

            outPlayers[i].setPointsPerGame(scoreSum / gameCount);
            outPlayers[i].setAssistsPerGame(assistSum / gameCount);
            outPlayers[i].setPassesPerGame(passSum / gameCount);
            outPlayers[i].setShootingPercentage(100 * goalSum / goalAttemptSum);
        }
    }

    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        int diff = 999999;
        int topPlayerIndex = -1;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getPointsPerGame() > targetPoints) {
                if (players[i].getPointsPerGame() - targetPoints <= diff) {
                    diff = players[i].getPointsPerGame() - targetPoints;
                    topPlayerIndex = i;
                } else {
                    break;
                }
            } else {
                if (targetPoints - players[i].getPointsPerGame() <= diff) {
                    diff = targetPoints - players[i].getPointsPerGame();
                    topPlayerIndex = i;
                } else {
                    break;
                }
            }
        }

        return players[topPlayerIndex];
    }

    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        int diff = 999999;
        int topPlayerIndex = -1;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getShootingPercentage() > targetShootingPercentage) {
                if (players[i].getShootingPercentage() - targetShootingPercentage <= diff) {
                    diff = players[i].getShootingPercentage() - targetShootingPercentage;
                    topPlayerIndex = i;
                } else {
                    break;
                }
            } else {
                if (targetShootingPercentage - players[i].getShootingPercentage() <= diff) {
                    diff = targetShootingPercentage - players[i].getShootingPercentage();
                    topPlayerIndex = i;
                } else {
                    break;
                }
            }
        }

        return players[topPlayerIndex];
    }

    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers, final Player[] scratch) {
        long maxTeamwork = -999999;
        int player1Index = -1;
        int player2Index = -1;
        int player3Index = -1;

        for (int i = 0; i < players.length - 2; i++) {
            for (int j = i + 1; j < players.length; j++) {
                for (int k = j + 1; k < players.length; k++) {
                    long teamwork = 0;
                    int passSum = 0;
                    int assistMin = 999999;

                    passSum += players[i].getPassesPerGame();
                    passSum += players[j].getPassesPerGame();
                    passSum += players[k].getPassesPerGame();

                    if (players[i].getAssistsPerGame() < assistMin) {
                        assistMin = players[i].getAssistsPerGame();
                    }
                    if (players[j].getAssistsPerGame() < assistMin) {
                        assistMin = players[j].getAssistsPerGame();
                    }
                    if (players[k].getAssistsPerGame() < assistMin) {
                        assistMin = players[k].getAssistsPerGame();
                    }

                    teamwork = passSum * assistMin;
                    if (maxTeamwork < teamwork) {
                        player1Index = i;
                        player2Index = j;
                        player3Index = k;
                        maxTeamwork = teamwork;
                    }
                }
            }
        }

        outPlayers[0] = players[player1Index];
        outPlayers[1] = players[player2Index];
        outPlayers[2] = players[player3Index];

        return maxTeamwork;
    }

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers, final Player[] scratch) {
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players, final Player[] scratch) {
        return -1;
    }
}