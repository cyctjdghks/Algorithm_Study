import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] str;
		str = br.readLine().toCharArray();

		int maxLength = 0;
		LinkedList<Integer> stack = new LinkedList<>();
		stack.addLast(-1);
		for (int i = 0; i < n; i++) {
			if(str[i] == '('){
				stack.addLast(i);
			}else{
				stack.removeLast();
				if (stack.isEmpty()) {
					stack.addLast(i);
					continue;
				}
				int index = stack.getLast();
				maxLength = Math.max(maxLength, i - index);
			}
		}

		System.out.println(maxLength);
	}
}
