import java.util.*;

public class Solution {

    static final int BOARD_SIZE = 4;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int[][] board;
    static Node[][] cardPositions;
    static boolean[] cardExists;
    static boolean[] isCardSelected;
    static int numCards;
    static int minMoves;

    public static int solution(int[][] inputBoard, int startX, int startY) {
        board = inputBoard;
        cardPositions = new Node[7][2];
        cardExists = new boolean[7];
        isCardSelected = new boolean[7];
        numCards = 0;
        minMoves = Integer.MAX_VALUE;

        init();

        dfs(0, 0, new Node(startX, startY));

        return minMoves;
    }

    private static void init() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int card = board[i][j];
                if (card != 0) {
                    if (!cardExists[card]) {
                        cardExists[card] = true;
                        numCards++;
                        cardPositions[card][0] = new Node(i, j);
                    } else {
                        cardPositions[card][1] = new Node(i, j);
                    }
                }
            }
        }
    }

    private static void dfs(int totalMoves, int depth, Node currentPosition) {
        if (depth == numCards) {
            minMoves = Math.min(minMoves, totalMoves);
            return;
        }

        for (int card = 1; card <= 6; card++) {
            if (cardExists[card] && !isCardSelected[card]) {
                isCardSelected[card] = true;

                for (int order = 0; order < 2; order++) {
                    Node firstCard = cardPositions[card][order];
                    Node secondCard = cardPositions[card][(order + 1) % 2];

                    int moves = calculateMoves(currentPosition, firstCard) +
                                calculateMoves(firstCard, secondCard) + 2;

                    board[firstCard.x][firstCard.y] = 0;
                    board[secondCard.x][secondCard.y] = 0;

                    dfs(totalMoves + moves, depth + 1, secondCard);

                    board[firstCard.x][firstCard.y] = card;
                    board[secondCard.x][secondCard.y] = card;
                }

                isCardSelected[card] = false;
            }
        }
    }

    private static int calculateMoves(Node start, Node end) {
        Queue<Node> queue = new ArrayDeque<>();
        int[][] v = new int[BOARD_SIZE][BOARD_SIZE];

        queue.add(start);
        v[start.x][start.y] = 1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == end.x && current.y == end.y) {
                return v[current.x][current.y] - 1;
            }

            for (int d = 0; d < 4; d++) {
                int nextX = current.x + dx[d];
                int nextY = current.y + dy[d];

                if (isInBound(nextX, nextY) && v[nextX][nextY] == 0) {
                    v[nextX][nextY] = v[current.x][current.y] + 1;
                    queue.add(new Node(nextX, nextY));
                }
            }

            for (int d = 0; d < 4; d++) {
                int nextX = current.x;
                int nextY = current.y;

                while (isInBound(nextX, nextY)) {
                    nextX += dx[d];
                    nextY += dy[d];

                    if (!isInBound(nextX, nextY)) {
                        nextX -= dx[d];
                        nextY -= dy[d];
                        break;
                    }

                    if (board[nextX][nextY] != 0) {
                        break;
                    }
                }

                if (v[nextX][nextY] == 0) {
                    v[nextX][nextY] = v[current.x][current.y] + 1;
                    queue.add(new Node(nextX, nextY));
                }
            }
        }

        return v[end.x][end.y] - 1;
    }

    private static boolean isInBound(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
