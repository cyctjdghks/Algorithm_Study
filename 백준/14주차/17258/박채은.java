import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Time{
    int time; // 기간
    int cnt; // 그 기간동안의 사람 수
    public Time(int time, int cnt){
        this.cnt = cnt;
        this.time = time;
    }
}

public class Main {
    static int n, m, k, t;
    static int[] cnt = new int[301];
    static int[][] dp = new int[301][301];
    static List<Time> list = new ArrayList<>();

    public static int DP(int idx, int friendsNum, int prevFriends){
        if(idx == list.size()){
            return 0;
        }
        if(dp[idx][friendsNum] != 0){
            return dp[idx][friendsNum];
        }

        // 필요한 인원 = 욱제가 기대하는 최소 인원 - 해당 기간동안의 인원
        int needPeople = Math.max(0, t-list.get(idx).cnt);
        // 가능한 인원
        int inputPeople = 0;
        if(needPeople - prevFriends > 0){
            inputPeople = needPeople - prevFriends;
        }

        int ret;
        // 친구를 투입하는 경우
        if(friendsNum - inputPeople >= 0){
            int left = friendsNum - inputPeople;
            ret = Math.max(DP(idx+1, left, needPeople) + list.get(idx).time, DP(idx+1, friendsNum, 0));
        }
        else{ // 투입하지 않는 경우
            ret = DP(idx+1, friendsNum, 0);
        }
        dp[idx][friendsNum] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 진행되는 시간
        m = Integer.parseInt(st.nextToken()); // 파티에 초대한 사람
        k = Integer.parseInt(st.nextToken()); // 영선이 친구 수
        t = Integer.parseInt(st.nextToken()); // 기대하는 최소 인원

        int[][] attend = new int[m][2];
        int result = 0;

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            attend[i][0] = Integer.parseInt(st.nextToken());
            attend[i][1] = Integer.parseInt(st.nextToken());
            for(int j=attend[i][0];j<attend[i][1];j++){
                cnt[j] +=1;
            }
        }

        // 같은 명수가 언제 존재하는지 파악
        // [2,0] - [2,1] - [2,2]
        int curNum = cnt[1];
        int count = 1;
        for(int i=2;i<=n;i++){
            if(curNum != cnt[i]){ // 명 수가 달라지는 경우
                list.add(new Time(count, curNum));
                count = 0;
                curNum = cnt[i];
            }
            count+=1;
        }
        list.add(new Time(count, curNum));

        result = DP(0, k,0);

        System.out.println(result);
    }
}