import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int score1 = 0;
        int score2 = 0;
        int time1 = 0;
        int time2 = 0;
        int cur_sec = 0;
        int prev_sec = 0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int team = Integer.parseInt(st.nextToken());
            String time = st.nextToken();
            cur_sec = changeTime(time);

            if(score1 > score2){
                time1 += (cur_sec - prev_sec);
            } else if (score1 < score2) {
                time2 += (cur_sec - prev_sec);
            }

            if(team == 1){
                score1 +=1;
            } else if (team ==2) {
                score2+=1;
            }
            prev_sec = changeTime(time);
        }

        if(score1 > score2){
            time1 += (48*60 - cur_sec);
        } else if (score2 > score1) {
            time2 += (48*60 - cur_sec);
        }

        System.out.println(String.format("%02d:%02d", time1/60, time1%60));
        System.out.println(String.format("%02d:%02d", time2/60, time2%60));
    }
    public static int changeTime(String time){
        String[] t = time.split(":");
        int sec = Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]);
        return sec;
    }
}