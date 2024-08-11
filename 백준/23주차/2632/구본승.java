import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, m, n, ans;
    static Map<Integer,Integer> A, B;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        A = get(m);
        B = get(n);
    }

    private static void solution() {
        ans = A.getOrDefault(N, 0) + B.getOrDefault(N, 0);
        for(int size : A.keySet()){
            if(size >= N){
                continue;
            }

            ans += A.get(size) * B.getOrDefault(N - size,0);
        }

        System.out.println(ans);
    }

    public static Map<Integer,Integer> get(int s) throws Exception {
        int[] pizza = new int[s*2];
        int sum = 0;

        for(int i=0;i<s;i++){
            int size = Integer.parseInt(br.readLine());

            sum+=size;
            pizza[i] = size;
            pizza[i+s] = size;
        }

        Map<Integer,Integer> pizzaPeiceCountMap = new HashMap<>();
        pizzaPeiceCountMap.put(sum,1);

        for(int i=0;i<s;i++){
            sum = 0;
            for(int j=i;j<i+s-1;j++){
                sum += pizza[j];
                pizzaPeiceCountMap.put(sum,pizzaPeiceCountMap.getOrDefault(sum,0)+1);
            }
        }

        return pizzaPeiceCountMap;
    }
}