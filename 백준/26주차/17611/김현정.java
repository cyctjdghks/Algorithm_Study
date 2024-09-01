import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Dot{
		int x;
		int y;

		Dot(int x, int y){
			this.x =x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Dot[] dots = new Dot[n];
		for(int i=0; i<n; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			dots[i] = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int[] arrVertical = new int[1000000 + 10];
		int[] arrHorizontal = new int[1000000 + 10];
		for(int i=1; i<=n; i++){
			Dot curr, pre;
			if(i == n){
				curr = dots[0]; pre = dots[i-1];
			}else{
				curr = dots[i]; pre = dots[i-1];
			}
			int preY = Math.min(pre.y, curr.y), currY = Math.max(pre.y, curr.y);
			int preX = Math.min(pre.x, curr.x), currX = Math.max(pre.x, curr.x);
			if(preY != currY){
				arrVertical[preY + 500000]++;
				arrVertical[currY + 500000 + 1]--;
			}
			if(preX != currX){
				arrHorizontal[preX + 500000]++;
				arrHorizontal[currX + 500000 + 1]--;
			}
		}

		int h=arrVertical[0], v=arrHorizontal[0];
		for(int i=1; i<arrVertical.length; i++){
			arrVertical[i] += arrVertical[i-1];
			arrHorizontal[i] += arrHorizontal[i-1];
			h = Math.max(arrVertical[i], h);
			v = Math.max(arrHorizontal[i], v);
		}

		System.out.println(Math.max(h, v));
	}
}
