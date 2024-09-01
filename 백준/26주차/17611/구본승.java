import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        //첫 꼭지점 정보 저장
        int[][] sum = new int[1000001][2];
        int x = Integer.parseInt(st.nextToken()) + 500000;
        int y = Integer.parseInt(st.nextToken()) + 500000;
        //시작 꼭지점 변수로 저장
        int startX = x;
        int startY = y;
        //단순직각다각형 만들면서 누적합 진행
        for(int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            int curX = Integer.parseInt(st.nextToken()) + 500000;
            int curY = Integer.parseInt(st.nextToken()) + 500000;
            //수직 선분일 때
            if(x == curX){
                yLineSearch(sum, y, curY);
            }else{	//수평 선분일 때
                xLineSearch(sum, x, curX);
            }
            //이전 꼭지점 변경
            x = curX;
            y = curY;
        }
        //마지막 꼭지점과 첫 번째 곡지점 연결
        if(x == startX) {
            yLineSearch(sum, y, startY);
        }else{
            xLineSearch(sum, x, startX);
        }
        int result = 0;
        //최대값 탐색
        for(int i=0;i<1000001;i++){
            result = Math.max(result, Math.max(sum[i][0], sum[i][1]));
        }
        //최대 교차점의 개수 BufferedWriter 저장
        bw.write(String.valueOf(result));
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //수직 선분일 때 누적합 진행하는 함수
    static void xLineSearch(int[][] sum, int x, int curX){
        if(x > curX) {		//↓
            for (int j = curX; j < x; j++) {
                sum[j][0]++;
            }
        }else{		// ↑
            for (int j = x; j < curX; j++) {
                sum[j][0]++;
            }
        }
    }
    //수평 선분일 때 누적합 진행하는 함수
    static void yLineSearch(int[][] sum, int y, int curY){
        if(y > curY){		//←
            for(int j=curY;j<y;j++){
                sum[j][1]++;
            }
        }else{		//→
            for(int j=y;j<curY;j++){
                sum[j][1]++;
            }
        }
    }
}