import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int size = 0, m = 0, n = 0;
    static int[] APiece, BPiece;
    static boolean visited[];
    static int startIdx = 0;
    static ArrayList<Integer> ASumList = new ArrayList<>();
	static ArrayList<Integer> BSumList = new ArrayList<>();
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        APiece = new int[m];
        BPiece = new int[n];

        for(int i=0;i<m;i++) {
            APiece[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0;i<n;i++) {
            BPiece[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0;i<m;i++) {
			visited = new boolean[m];
			visited[i] = true;
            startIdx = i;
			setSumList(APiece[i], i+1, APiece, ASumList);
        }

        for(int i=0;i<n;i++) {
			visited = new boolean[n];
			visited[i] = true;
            startIdx = i;
			setSumList(BPiece[i], i+1, BPiece, BSumList);
        }

        // 각 피자의 조각을 사용하지 않는 경우
        ASumList.add(0);
        BSumList.add(0);

        Collections.sort(ASumList);
        Collections.sort(BSumList);

        count();
        
        bw.write(cnt + "\n");
        bw.close();
        br.close();
    }

    // 부분합 만들기
    public static void setSumList(int sum, int idx, int[] arr, ArrayList list) {
		// 현재 인덱스가 마지막인 경우, 원형이기 때문에 다시 처음으로
		if(idx == arr.length) idx = 0;
		
		list.add(sum);
		
        // 아직 값이 더해진 피자조각이 아니고
        // 원형을 다 돌지 않았고
        // 지금까지 더한 값이 손님이 구매하고자 하는 피자크기보다 작거나 같은 경우
		if(visited[idx] == false && idx != startIdx - 1 && sum <= size) {
			visited[idx] = true;
			setSumList(sum+arr[idx], idx+1, arr, list);
		}
        else return;
	}

    // 투 포인터로 부분합들을 하나는 작은 수부터, 하나는 큰 수부터 더해보며 계산하기
	public static void count() {
		int left = 0;
		int right = BSumList.size() - 1;
		
		while(left < ASumList.size() && 0 <= right) {
			int lv = ASumList.get(left);
			int rv = BSumList.get(right);
			
			// 두 부분합의 합이 손님이 구매하고자 하는 피자크기와 같은 경우
			if(lv + rv == size) {
				int lc = 0;

				while(left < ASumList.size() && ASumList.get(left) == lv) {
					lc++;
					left++;
				}
				
				int rc = 0;
				while(right >= 0 && BSumList.get(right) == rv) {
					rc++;
					right--;
				}
				
				cnt += lc * rc;
			}
            // 두 부분합의 합이 손님이 구매하고자 하는 피자크기보다 작은 경우
            else if(lv + rv < size) left++;
            // 두 부분합의 합이 손님이 구매하고자 하는 피자크기보다 큰 경우
			else right--;
		}
	}
}
