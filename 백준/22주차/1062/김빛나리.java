import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static String[] word;
    static HashMap<Character, Boolean> map = new HashMap<Character, Boolean>();
    static ArrayList<Character> list = new ArrayList<Character>(Arrays.asList('a', 'c', 'i', 'n', 't'));
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        word = new String[N];

        for(int i=0;i<N;i++) {
            word[i] = br.readLine();
        }

        // 최소 5개(a, c, i, n, t)
        if(K < 5) {
            bw.write(0 + "\n");
            bw.close();
            return;
        }
        // 알파벳 모두 배울 수  경우
        if(K == 26) {
            bw.write(N + "\n");
            bw.close();
            return;
        }

        for(int i=0;i<list.size();i++) {
            map.put(list.get(i), true);
        }

        backtracking(0, 0);
            
        bw.write(max + "\n");
        bw.close();
    }

    public static void backtracking(int start, int len) {
        if(len == K - 5) {
            int cnt = 0;
            for(int i=0;i<N;i++) {
                boolean visited = true;

                for(int j=0;j<word[i].length();j++) {
                    char c = word[i].charAt(j);

                    if(list.contains(c)) continue;

                    if(!map.containsKey(c) || !map.get(c)) {
                        visited = false;
                        break;
                    }
                }

                if(visited) cnt++;
            }

            max = Math.max(max, cnt);
            return;
        }

        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for(int i=start;i<alphabet.length;i++) {
            if(!map.containsKey(alphabet[i]) || !map.get(alphabet[i])) {
                map.put(alphabet[i], true);
                backtracking(i, len+1);
                map.put(alphabet[i], false);
            }
        }
    }
}
