import java.util.*;

public class Solution {

    private static Queue<int[]>[] memo;
    private static int routeCount;
    private static int collisionCount;

    public int solution(int[][] points, int[][] routes) {
        routeCount = routes.length;
        memo = new LinkedList[routeCount];
        for (int i = 0; i < routeCount; i++) {
            memo[i] = new LinkedList<>();
        }

        calculateRoutes(points, routes); // Calculate routes
        calculateCollisions(); // Calculate collisions

        return collisionCount;
    }

    private static void calculateCollisions() {
        int completedRoutes = 0;
        while (completedRoutes != routeCount) {
            int[][] grid = new int[101][101];
            completedRoutes = 0;

            for (int i = 0; i < routeCount; i++) {
                if (memo[i].isEmpty()) {
                    completedRoutes++;
                    continue;
                }
                int[] currentPosition = memo[i].poll();
                grid[currentPosition[0]][currentPosition[1]]++;
            }

            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (grid[i][j] > 1) {
                        collisionCount++; // Collision detected
                    }
                }
            }
        }
    }

    private static void calculateRoutes(int[][] points, int[][] routes) {
        for (int i = 0; i < routeCount; i++) {
            int[] startPoint = points[routes[i][0] - 1];
            int x = startPoint[0];
            int y = startPoint[1];
            memo[i].add(new int[]{x, y});

            for (int j = 1; j < routes[i].length; j++) {
                int[] nextPoint = points[routes[i][j] - 1];
                int targetX = nextPoint[0];
                int targetY = nextPoint[1];

                moveAlongRoute(i, x, y, targetX, targetY);
                x = targetX;
                y = targetY;
            }
        }
    }

    private static void moveAlongRoute(int routeIndex, int x, int y, int targetX, int targetY) {
        int deltaX = targetX - x;
        int deltaY = targetY - y;

        while (deltaX != 0) {
            if (deltaX > 0) {
                x++;
                deltaX--;
            } else {
                x--;
                deltaX++;
            }
            memo[routeIndex].add(new int[]{x, y});
        }

        while (deltaY != 0) {
            if (deltaY > 0) {
                y++;
                deltaY--;
            } else {
                y--;
                deltaY++;
            }
            memo[routeIndex].add(new int[]{x, y});
        }
    }
}
