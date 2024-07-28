import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int[] nums;
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        K = Integer.valueOf(br.readLine());
        int cnt = (int) (Math.pow(2, K) - 1);
        nums = new int[cnt];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<cnt;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<K;i++) {
            node.add(new ArrayList<>());
        }

        // start, end, depth
        binarytree(0, nums.length-1, 0);

        for(int i=0;i<K;i++) {
            for(int n : node.get(i)) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }

    public static void binarytree(int start, int end,  int depth) {
        if(depth == K) return;

        int mid = (start + end) / 2;

        node.get(depth).add(nums[mid]);

        binarytree(start, mid-1, depth+1);
        binarytree(mid+1, end, depth+1);
    }
}
