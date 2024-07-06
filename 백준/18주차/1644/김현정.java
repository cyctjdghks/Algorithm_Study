import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] number = new boolean[N + 1];
		for (int i = 2; i < (number.length / 2); i++) {
			if(number[i])	continue;
			for (int m = 2; i * m < number.length; m++) {
				number[i * m] = true;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 2; i < number.length; i++) {
			if(!number[i])	list.add(i);
		}

		int count = 0;
		int left = 0, right = 0, sum = 0;
		while (true) {
			if(sum >= N) sum -= list.get(left++);
			else if(right == list.size())	break;
			else sum += list.get(right++);
			if(sum == N) count++;
		}
		System.out.println(count);
	}
}
