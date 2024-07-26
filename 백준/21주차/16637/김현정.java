import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int N;
	static int maxResult = Integer.MIN_VALUE;
	static ArrayList<Integer> integers = new ArrayList<>();
	static ArrayList<Character> characters = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		for(int i=0; i<N; i++){
			if (input[i] >= '0' && input[i] <= '9') {
				integers.add(input[i] - '0');
			}else{
				characters.add(input[i]);
			}
		}
		dfs(0, integers.get(0));
		System.out.println(maxResult);
	}

	private static void dfs(int currIdx, int total){
		if (currIdx == characters.size()) {
			maxResult = Math.max(maxResult, total);
			return;
		}

		//()
		dfs(currIdx + 1, cal(total, integers.get(currIdx + 1), characters.get(currIdx)));

		if (currIdx + 2 <= integers.size() - 1) {
			int next = cal(integers.get(currIdx + 1), integers.get(currIdx + 2), characters.get(currIdx + 1));
			dfs(currIdx + 2, cal(total, next, characters.get(currIdx)));
		}
	}

	private static int cal(int num1, int num2, char oper) {
		int ans = 0;
		switch (oper) {
			case '+':
				ans = (num1 + num2);
				break;
			case '-' :
				ans = (num1 - num2);
				break;
			case '*' :
				ans = (num1 * num2);
				break;
			default :
		}
		return ans;
	}
}
