import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    // 주어진 M의 범위(2^30-1)를 초과하지 않는 가장 큰 수
    static int[] messi = new int[42];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int M = Integer.parseInt(br.readLine());

        // Messi
        messi[1] = 5;
        // Messi Gimossi
        messi[2] = 13;

        int idx = 3;
        while(true) {
            messi[idx] = messi[idx-1] + 1 + messi[idx-2];

            // N이 충분히 커진 경우
            if(messi[idx] > M) break;

            idx++;
        }

        String result = find(M, idx);

        bw.write(result+ "\n");
        bw.close();
        br.close();
    }

    public static String find(int M, int idx) {
        if(idx == 1) return Character.toString("Messi".charAt(M-1));
        else if(idx == 2) {
            if(M == 6) return "Messi Messi Gimossi";
            else return Character.toString("Messi Gimossi".charAt(M-1));
        }

        if(M <= messi[idx-1]) return find(M, idx-1);
        else if(M == messi[idx-1] + 1) return "Messi Messi Gimossi";
        else if(M > messi[idx-1] + 1) return find(M - (messi[idx-1]+1), idx-2);

        return "";
    }
}
