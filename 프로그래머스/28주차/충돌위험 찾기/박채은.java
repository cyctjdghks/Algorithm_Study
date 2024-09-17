import java.util.*;

class Solution {
    int R = 1, C = 1;
    Map<Integer, Point> pointMap = new HashMap<>();

    class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int solution(int[][] points, int[][] routes) {
        // 1. point map 생성
        for(int i=0;i<points.length;i++){
            R = Math.max(R, points[i][0]);
            C = Math.max(C, points[i][1]);
            pointMap.put(i+1, new Point(points[i][1], points[i][0]));
        }

        List<List<Point>> allShortestRoute = new ArrayList<>();
        // 경로들의 min, max 길이
        int maxLen = 1;

        // 2. 각 route의 최단 경로 구하기
        for(int route[] : routes){
            List<Point> shortestRoute = findShortestRoute(route);
            allShortestRoute.add(shortestRoute);
            maxLen = Math.max(maxLen, shortestRoute.size());
        }

        int result = 0;
        // 3. 충돌이 발생하는 지점 찾기
        for(int i=0;i<maxLen;i++){
            Map<Point, Integer> crashCnt = new HashMap<>();
            for(int j=0;j< allShortestRoute.size();j++){
                Point crashPoint = (allShortestRoute.get(j).size() < i+1) ? null : allShortestRoute.get(j).get(i);
                if(crashPoint == null){
                    continue;
                }
                if(!crashCnt.containsKey(crashPoint)){
                    crashCnt.put(crashPoint, 1);
                }
                else{
                    crashCnt.put(crashPoint,crashCnt.get(crashPoint) + 1);
                }
            }
            // crashCnt.get() >= 2이면 충돌 지점으로 추가한다.
            for(Integer cnt : crashCnt.values()){
                if(cnt >= 2){
                    result+=1;
                }
            }
        }
        return result;
    }

    public List<Point> findShortestRoute(int route[]){
        List<Point> routeList = new ArrayList<>();
        // r 좌표가 변하는 이동 > c 좌표가 변하는 이동 (y좌표부터)
        for(int i = 0;i<route.length - 1;i++){
            Point start = pointMap.get(route[i]);
            Point end = pointMap.get(route[i+1]);

            int cur_x = start.x;
            int cur_y = start.y;

            if(routeList.isEmpty()){
                routeList.add(new Point(cur_x, cur_y));
            }

            // 1. r 좌표가 먼저 이동
            while (cur_y != end.y){
                if(cur_y > end.y){
                    cur_y -=1;
                }
                else{
                    cur_y +=1;
                }
                routeList.add(new Point(cur_x, cur_y));
            }

            // 2. c 좌표 이동

            while (cur_x != end.x){
                if(cur_x > end.x){
                    cur_x -=1;
                }
                else{
                    cur_x +=1;
                }
                routeList.add(new Point(cur_x, cur_y));
            }
        }
        return routeList;
    }
}