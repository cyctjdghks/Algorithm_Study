import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int k;
    static int[] arr;
    static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[(int) Math.pow(2, k) - 1];
        list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            list.add(new ArrayList<>());
        }
    }

    private static void solution() {
        search(0, arr.length - 1, 0);
        for (int i = 0; i < k; i++) {
            for (int j : list.get(i)) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void search (int start, int end, int depth) {
        if (depth == k) return;

        int mid = (start + end) / 2;
        list.get(depth).add(arr[mid]);
        search(start, mid - 1, depth + 1);
        search(mid + 1, end, depth + 1);
    }

}