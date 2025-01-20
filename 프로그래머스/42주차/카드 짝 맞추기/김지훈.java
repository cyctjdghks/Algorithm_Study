import java.util.*;

public class Solution {

    static class State {
        int r, c, enter, cnt;
        String board;

        State(int r, int c, int enter, int cnt, String board) {
            this.r = r;
            this.c = c;
            this.enter = enter;
            this.cnt = cnt;
            this.board = board;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return r == state.r && c == state.c && enter == state.enter && Objects.equals(board, state.board);
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, enter, board);
        }
    }

    public static int solution(int[][] board, int r, int c) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell);
            }
        }
        String boardStr = sb.toString();

        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        queue.add(new State(r, c, -1, 0, boardStr));

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.board.chars().filter(ch -> ch == '0').count() == 16) {
                return current.cnt;
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            for (int[] dir : directions) {
                int nr = current.r + dir[0];
                int nc = current.c + dir[1];

                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
                    queue.add(new State(nr, nc, current.enter, current.cnt + 1, current.board));
                }

                int[] ctrlMove = ctrlMove(current.r, current.c, dir[0], dir[1], current.board);
                queue.add(new State(ctrlMove[0], ctrlMove[1], current.enter, current.cnt + 1, current.board));
            }

            int position = current.r * 4 + current.c;
            if (current.board.charAt(position) != '0') {
                if (current.enter == -1) {
                    queue.add(new State(current.r, current.c, position, current.cnt + 1, current.board));
                } else if (current.board.charAt(current.enter) == current.board.charAt(position) && current.enter != position) {
                    String newBoard = current.board.replace(Character.toString(current.board.charAt(current.enter)), "0");
                    queue.add(new State(current.r, current.c, -1, current.cnt + 1, newBoard));
                }
            }
        }

        return -1; // Should not reach here
    }

    private static int[] ctrlMove(int r, int c, int dr, int dc, String board) {
        while (true) {
            r += dr;
            c += dc;

            if (r >= 0 && r < 4 && c >= 0 && c < 4) {
                if (board.charAt(r * 4 + c) != '0') {
                    return new int[]{r, c};
                }
            } else {
                return new int[]{r - dr, c - dc};
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {1, 0, 0, 3},
                {2, 0, 0, 0},
                {0, 0, 0, 2},
                {3, 0, 1, 0}
        };
        int r = 1, c = 0;

        System.out.println(solution(board, r, c));
    }
}
