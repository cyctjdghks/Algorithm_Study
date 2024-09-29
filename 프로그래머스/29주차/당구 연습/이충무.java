import java.util.*;

class Solution {
    
    static class Point{
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        Point border = new Point(m,n);
        Point start = new Point(startX,startY);
        
        for(int i = 0; i < balls.length ; i++){
            int[] ball = balls[i];
            List<Point> list = transfunc(border, start, new Point(ball[0], ball[1]));
            int minNum = Integer.MAX_VALUE;
            for(Point point : list){
                int dis = calc(start, point);
                
                minNum = Math.min(minNum, dis);
            }
            answer[i] = minNum;
        }
        
        return answer;
    }
    
    private List<Point> transfunc(Point border, Point start, Point ball){
        List<Point> list = new ArrayList<>();
        
        if(!(start.x == ball.x && start.y < ball.y)) list.add(new Point(ball.x, 2 * border.y - ball.y ));
        if(!(start.y == ball.y && start.x < ball.x)) list.add(new Point(2 * border.x - ball.x, ball.y));
        if(!(start.x == ball.x && start.y > ball.y)) list.add(new Point(ball.x, ball.y * -1));
        if(!(start.y == ball.y && start.x > ball.x)) list.add(new Point(ball.x * -1 , ball.y));
        
        return list;
    }
    
    private int calc(Point start, Point ball){
        int x1 = Math.max(start.x, ball.x);
        int x2 = Math.min(start.x, ball.x);
        int y1 = Math.max(start.y, ball.y);
        int y2 = Math.min(start.y, ball.y);
        
        return (int)Math.pow(x1 - x2, 2) + (int)Math.pow(y1 - y2, 2);
    }
}