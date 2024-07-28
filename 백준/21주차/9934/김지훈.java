import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int k;
    static int[] inputList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = st.countTokens();
        inputList = new int[l];
        for (int i = 0; i < inputList.length; i++) {
            int num = Integer.parseInt(st.nextToken());
            inputList[i] = num;
        }

        dfs(0,l-1, 0);

        StringBuilder sb = new StringBuilder();

        for (ArrayList<Integer> integers : tree) {
            for (int j = 0; j < integers.size() - 1; j++) {
                sb.append(integers.get(j)).append(" ");
            }
            sb.append(integers.get(integers.size() - 1)).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int start, int end, int depth) {
        if (start == end) {
            tree.get(depth).add(inputList[start]);
            return;
        }

        int mid = (start+end)/2;

        dfs(start,mid-1,depth+1);
        tree.get(depth).add(inputList[mid]);
        dfs(mid+1,end,depth+1);
    }
}
