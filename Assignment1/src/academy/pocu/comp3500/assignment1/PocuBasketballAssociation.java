package academy.pocu.comp3500.assignment1;

import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class PocuBasketballAssociation {
    private PocuBasketballAssociation() {
    }

    /* GameStat 정보를 이용해 Player 내 선수별 경기 점수를 구하기
    * 1. 플레이어 수만큼 반복
    * 2. 플레이어가 출전한 경기를 모두 찾아 득점수, 어시스트수, 패스수, 슛 성공률 합 계산 및 출전한 경기 수 저장
    * 3. 플레이어의 평균 득점, 어시스트, 패스, 슛 성공률을 outPlayers에 저장
    * 4. 모든 플레이어를 대상으로 위 절차를 실행
    * */
    public static void processGameStats(final GameStat[] gameStats, final Player[] outPlayers) {
        String playerNames[]            = new String[outPlayers.length];
        int playerNamesIndex            = 0;
        int playersPoints[]             = new int[outPlayers.length];
        int playersAssists[]            = new int[outPlayers.length];
        int playersPasses[]             = new int[outPlayers.length];
        // int playersShootingPercentage[] = new int[outPlayers.length];
        int playersGoals[]              = new int[outPlayers.length];
        int playersTryGoals[]           = new int[outPlayers.length];
        int playersVisitGame[]          = new int[outPlayers.length];

        // 플레이어 이름 저장
        for (int i = 0; i < gameStats.length; i++) {
            boolean bIsSaved = false;
            for (int j = 0; j < playerNames.length; j++) {
                // System.out.println(i + ", " + j + ", " + playerNames.length);
                if (playerNames[j] != null) {
                    if (gameStats[i].getPlayerName().equals(playerNames[j])) {
                        bIsSaved = true;
                        break;
                    }
                }
            }

            if (!bIsSaved) {
                playerNames[playerNamesIndex++] = gameStats[i].getPlayerName();
            }
        }

        for (int i = 0; i < playerNames.length; i++) {
            for (int j = 0; j < gameStats.length; j++) {
                if (playerNames[i].equals(gameStats[j].getPlayerName())) {
                    playersPoints[i]   += gameStats[j].getPoints();
                    playersAssists[i]  += gameStats[j].getAssists();
                    playersPasses[i]   += gameStats[j].getNumPasses();
                    playersGoals[i]    += gameStats[j].getGoals();
                    playersTryGoals[i] += gameStats[j].getGoalAttempts();
                    playersVisitGame[i]++;
                }
            }
        }

        for (int i = 0; i < playerNames.length; i++) {
            String playerName      = playerNames[i];
            int avgScore           = playersPoints[i] / playersVisitGame[i];
            int avgAssist          = playersAssists[i] / playersVisitGame[i];
            int avgPass            = playersPasses[i] / playersVisitGame[i];
            int shootingPercentage = 100 * playersGoals[i] / playersTryGoals[i];

            outPlayers[i] = new Player(playerName, avgScore, avgAssist, avgPass, shootingPercentage);
        }
    }

    /* 제공하는 players중 평균 점수가 targetPoints와 가장 비슷한 플레이어 객체를 반환하기
    * 1. min 변수에 플레이어의 평균 점수와 targetPoints와의 차이를 계산하며, 해당 인덱스를 minIndex에 저장
    * 2. 해당 인덱스의 플레이어를 반환
    * */
    public static Player findPlayerPointsPerGame(final Player[] players, int targetPoints) {
        int min = 999999;
        int minIndex = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].getPointsPerGame() >= targetPoints) {
                if (players[i].getPointsPerGame() - targetPoints <= min) {
                    min = players[i].getPointsPerGame() - targetPoints;
                    minIndex = i;
                }
            } else {
                if (targetPoints - players[i].getPointsPerGame() <= min) {
                    min = targetPoints - players[i].getPointsPerGame();
                    minIndex = i;
                }
            }
        }

        return players[minIndex];
    }

    /* 제공하는 players중 평균 점수와 targetShootingPercentage가 가장 비슷한 플레이어 객체를 반환하기
    * */
    public static Player findPlayerShootingPercentage(final Player[] players, int targetShootingPercentage) {
        int min = 999999;
        int minIndex = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].getShootingPercentage() >= targetShootingPercentage) {
                if (players[i].getShootingPercentage() - targetShootingPercentage <= min) {
                    min = players[i].getShootingPercentage() - targetShootingPercentage;
                    minIndex = i;
                }
            } else {
                if (targetShootingPercentage - players[i].getShootingPercentage() <= min) {
                    min = targetShootingPercentage - players[i].getShootingPercentage();
                    minIndex = i;
                }
            }
        }

        return players[minIndex];
    }

    /* 팀워크(평균 패스 * 어시스트)가 뛰어난 선수 3인방의 팀워크 점수 합을 반환하며, outPlayers에 해당 선수 정보 넣기
    * 1. 뛰어난 3인방 구하기
    * 2. outPlayers에 3인방 대입
    * 3. 3인방의 팀워크 합 반환
    * */
    public static long find3ManDreamTeam(final Player[] players, final Player[] outPlayers) {
        int[][][] teamworks = new int[players.length][players.length][players.length];
        int[] top1  = {-1, -1, -1};
        int sumPass     = 0;
        int minAssist   = 0;
        int result      = 0;
        int max = 0;

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players.length; j++) {
                for (int k = 0; k < players.length; k++) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }
                    sumPass = players[i].getPassesPerGame() + players[j].getPassesPerGame() + players[k].getPassesPerGame();
                    minAssist = min(players[i].getAssistsPerGame(), players[j].getAssistsPerGame(), players[k].getAssistsPerGame());

                    teamworks[i][j][k] = sumPass * minAssist;
                }
            }
        }

        for (int i = 0; i < players.length; i++) {
            max = 0;
            for (int j = 0; j < players.length; j++) {
                for (int k = 0; k < players.length; k++) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }

                    if (teamworks[i][j][k] > max) {
                        max = teamworks[i][j][k];
                        top1[0] = i;
                        top1[1] = j;
                        top1[2] = k;
                    }
                }
            }
        }

        outPlayers[0] = players[top1[0]];
        outPlayers[1] = players[top1[1]];
        outPlayers[2] = players[top1[2]];

        return teamworks[top1[0]][top1[1]][top1[2]];
    }

    /* k명으로 구성된 드림팀
    * 1. 뛰어난 k인방 구하기
    *  1-1) 저장된 배열에 해당하지 않으면서 가장 큰 수 저장
    * 2. outPLayers에 k인방 대입
    * 3. k인방의 팀워크 합 변환
    * */

    public static long findDreamTeam(final Player[] players, int k, final Player[] outPlayers) {
        int[][] teamworks = new int[players.length][k+1];
        int[] teamworkIndex;
        int[] top1  = {-1, -1, -1};
        int sumPass     = 0;
        int minAssist   = 0;
        int result      = 0;
        int max = 0;
/*
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players.length; j++) {
                for (int l = 0; l < players.length; l++) {
                    if (i == j || i == l || j == l) {
                        continue;
                    }
                    sumPass = players[i].getPassesPerGame() + players[j].getPassesPerGame() + players[k].getPassesPerGame();
                    minAssist = min(players[i].getAssistsPerGame(), players[j].getAssistsPerGame(), players[k].getAssistsPerGame());

                    teamworks[i][j][k] = sumPass * minAssist;
                    teamworks[teamworkIndex][0] = i;
                }
            }
        }

        for (int i = 0; i < players.length; i++) {
            max = 0;
            for (int j = 0; j < players.length; j++) {
                for (int k = 0; k < players.length; k++) {
                    if (i == j || i == k || j == k) {
                        continue;
                    }

                    if (teamworks[i][j][k] > max) {
                        max = teamworks[i][j][k];
                        top1[0] = i;
                        top1[1] = j;
                        top1[2] = k;
                    }
                }
            }
        }

        outPlayers[0] = players[top1[0]];
        outPlayers[1] = players[top1[1]];
        outPlayers[2] = players[top1[2]];
*/
        return -1;
    }

    public static int min(int num1, int num2, int num3) {
        int min = 999999;

        if (min > num1) {
            min = num1;
        }
        if (min > num2) {
            min = num2;
        }
        if (min > num3) {
            min = num3;
        }

        return min;
    }

    public static int contains(int[] numbers, int findNumber) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == findNumber) {
                return i;
            }
        }
        return -1;
    }

    public static int findDreamTeamSize(final Player[] players) {
        return -1;
    }
}
