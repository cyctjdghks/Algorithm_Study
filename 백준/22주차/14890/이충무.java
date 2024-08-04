import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, l;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt=0;

        for(int i=0;i<n;i++){
            boolean r= checkR(i);
            boolean c = checkC(i);
            if(r) cnt++;
            if(c) cnt++;
//            System.out.println(r + " " + c);
        }
        System.out.println(cnt);
    }
    //높이차 1 , L칸 높이 같음,
    public static boolean checkR(int r) {
        boolean[] checkRamp = new boolean[n];
        for(int i=0;i<n-1;i++){
            int diff = map[r][i] - map[r][i+1];
            if(Math.abs(diff) > 1) return false;
            //왼쪽이 높은 경우
            if(diff == 1){
                //경사로 길이가 맵 길이를 넘으면
                if(i+l >= n) return false;
                for(int j=1;j<=l;j++){
                    //이미 경사로가 있으면
                    if(checkRamp[i+j]) return false;
                    //높이 차가 1이 아니면
                    if(map[r][i] != map[r][i+j] + 1) return false;
                    checkRamp[i+j]=true;
                }
            }
            else if(diff == -1){
                if(i+1-l < 0) return false;
                for(int j=0;j<l;j++){
                    if(checkRamp[i-j]) return false;
                    if(map[r][i + 1] != map[r][i-j] + 1) return false;
                    checkRamp[i-j]=true;
                }
            }
        }

        return true;
    }
    public static boolean checkC(int c) {
        boolean[] checkRamp = new boolean[n];
        for(int i=0;i<n-1;i++){
            int diff = map[i][c] - map[i+1][c];
//            System.out.println(map[i][c]);
//            System.out.println(map[i+1][c]);
            if(c == 2){
//                System.out.println("diff " + diff);
            }
            if(Math.abs(diff) > 1) return false;
            if(diff == 1){
                if(i+l >= n) return false;
                for(int j=1;j<=l;j++){
                    if(checkRamp[i+j]) return false;
                    if(map[i][c] != map[i+j][c] + 1) return false;
                    checkRamp[i+j]=true;
                }
            }
            else if(diff == -1){
//                System.out.println("i " + i+" l " + l);
                if(i+1-l < 0) return false;
                for(int j=0;j<l;j++){
//                    System.out.println("i " + i+" l " + l + " checkRamp[i-j] " + checkRamp[i-j] + " map[i + 1][c] " +map[i + 1][c] + "  map[i-j][c] + 1 " + map[i-j][c] + 1);
                    if(checkRamp[i-j]) return false;
                    if(map[i + 1][c] != map[i-j][c] + 1) return false;
                    checkRamp[i-j]=true;
                }
            }
        }
        return true;
    }
}
