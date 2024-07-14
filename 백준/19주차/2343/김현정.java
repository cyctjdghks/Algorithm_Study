import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] course;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		course = new int[N];
		long sum = 0;
		for (int i = 0; i < N; i++) {
			course[i] = Integer.parseInt(st.nextToken());
			sum += course[i];
		}

		long left = 1, right = sum;
		while(left <= right){
			long mid = (left + right)/2;
			if(checkBlueray(N, M, mid)){
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
		System.out.println(left);
	}

	private static boolean checkBlueray(int N, int M, long size){
		int currSize = 0, currCount = 1;
		for (int i = 0; i < N; i++) {
			if(course[i] > size)	return false;
			if(currSize + course[i] > size){
				currCount++;
				currSize = course[i];
			}else{
				currSize += course[i];
			}

			if(currCount > M)	return false;
		}
		return true;
	}
}
