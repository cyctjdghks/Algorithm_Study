
class Solution {
    static class Node {
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    int n;  int m;
    Node destRed = null, destBlue = null;
    int[][] map;
    int[] dX = {0, 1, 0, -1}, dY = {1, 0, -1, 0};
    int minDepth = Integer.MAX_VALUE;
    public int solution(int[][] maze) {
        //init
        map = maze;
        n = maze.length; m =maze[0].length;
        boolean[][] rVisited = new boolean[n][m];
        boolean[][] bVisited = new boolean[n][m];
        Node r = null, b = null;
        for(int i=0; i<n; i++){
            for(int j=0; j<map[i].length; j++){
                if(map[i][j] == 1){
                    rVisited[i][j] = true;
                    r = new Node(i, j);
                }
                if(map[i][j] == 2){
                    bVisited[i][j] = true;
                    b = new Node(i, j);
                }
                if(map[i][j] == 3)  destRed = new Node(i, j);
                if(map[i][j] == 4)  destBlue = new Node(i, j);
            }
        }
        dfs(r, b, rVisited, bVisited, 1);
        return minDepth == Integer.MAX_VALUE ? 0 : minDepth;
    }

    private void dfs(Node currRed, Node currBlue, boolean[][] rVisited, boolean[][] bVisited, int depth) {
        if (depth > minDepth) return;
        if (isMatch(currRed, destRed) && isMatch(currBlue, destBlue)) {
            minDepth = Math.min(minDepth, depth - 1);
            return;
        }

        //red
        if (isMatch(currRed, destRed)) {
            for(int i=0; i<dX.length; i++){
                int nextBlueX = currBlue.x + dX[i], nextBlueY = currBlue.y + dY[i];
                if (isAvailable(bVisited, nextBlueX, nextBlueY) && !isMatch(currRed, nextBlueX, nextBlueY)) {
                    bVisited[nextBlueX][nextBlueY] = true;
                    dfs(currRed, new Node(nextBlueX, nextBlueY), rVisited, bVisited, depth + 1);
                    bVisited[nextBlueX][nextBlueY] = false;
                }
            }
        } else if (isMatch(currBlue, destBlue)) {
            for(int i=0; i<dX.length; i++){
                int nextRedX = currRed.x + dX[i], nextRedY = currRed.y + dY[i];
                if (isAvailable(rVisited, nextRedX, nextRedY) && !isMatch(currBlue, nextRedX, nextRedY)) {
                    rVisited[nextRedX][nextRedY] = true;
                    Node nextRed = new Node(nextRedX, nextRedY);
                    dfs(nextRed, currBlue, rVisited, bVisited, depth + 1);
                    rVisited[nextRedX][nextRedY] = false;
                }
            }
        } else {
            for (int i = 0; i < dX.length; i++) {
                int nextRedX = currRed.x + dX[i], nextRedY = currRed.y + dY[i];
                if (isAvailable(rVisited, nextRedX, nextRedY)) {
                    rVisited[nextRedX][nextRedY] = true;
                    Node nextRed = new Node(nextRedX, nextRedY);

                    //blue
                    for (int j = 0; j < dX.length; j++) {
                        int nextBlueX = currBlue.x + dX[j], nextBlueY = currBlue.y + dY[j];
                        Node nextBlue = new Node(nextBlueX, nextBlueY);
                        if (isAvailable(bVisited, nextBlueX, nextBlueY) && !isMatch(nextRed, nextBlue)
                            && !(isMatch(nextRed, currBlue) && isMatch(nextBlue, currRed))
                        ) {
                            bVisited[nextBlueX][nextBlueY] = true;
                            dfs(nextRed, nextBlue, rVisited, bVisited, depth + 1);
                            bVisited[nextBlueX][nextBlueY] = false;
                        }
                    }
                    rVisited[nextRedX][nextRedY] = false;
                }
            }
        }
    }

    //check map 충돌여부, visited 여부, out of range
    private boolean isAvailable(boolean[][] isVisited, int x, int y){
        return x>=0 && x<n && y>=0 && y<m && !isVisited[x][y] && map[x][y] != 5;
    }

    //Dot - (x, y) 매칭 여부 확인
    private boolean isMatch(Node node, int x, int y){
        return node != null && node.x == x && node.y == y;
    }

    private boolean isMatch(Node nodeA, Node nodeB){
        return nodeA.x == nodeB.x && nodeA.y == nodeB.y;
    }
}
