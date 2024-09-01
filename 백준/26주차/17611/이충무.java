import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] countX;
    static int[] countY;
    static Point[] point;
    static class Point{
        int x,y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        countX = new int[1000001];
        countY = new int[1000001];

        n = Integer.parseInt(st.nextToken());
        point= new Point[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + 500000;
            int y = Integer.parseInt(st.nextToken()) + 500000;
            point[i]= new Point(x,y);
        }

        int startX = point[0].x;
        int startY = point[0].y;

        for(int i=0;i<n-1;i++){
            int curY = point[i].y;
            int curX = point[i].x;
            int nextX = point[i+1].x;
            int nextY = point[i+1].y;

            if(curX == nextX){
                plusCountY(Math.min(curY,nextY),Math.max(curY,nextY));
            }
            else{
                plusCountX(Math.min(curX,nextX),Math.max(curX,nextX));
            }
        }

        int endX = point[point.length-1].x;
        int endY = point[point.length-1].y;
        if(endX == startX){
            plusCountY(Math.min(endY,startY),Math.max(endY,startY));
        }
        else{
            plusCountX(Math.min(endX,startX),Math.max(endX,startX));
        }

        int result=0;
        for(int i=0;i<1000001;i++){
            result = Math.max(result, Math.max(countX[i], countY[i]));
        }
        System.out.println(result);

    }
    static void plusCountX(int x1,int x2){
        for (int i = x1; i < x2; i++) {
            countX[i]++;
        }
    }
    static void plusCountY(int y1,int y2){
        for (int i = y1; i < y2; i++) {
            countY[i]++;
        }
    }


    //누적합 적용해보기
    //https://yabmoons.tistory.com/707

}
