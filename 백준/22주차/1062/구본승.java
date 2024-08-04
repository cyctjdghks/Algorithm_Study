import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K, ans = 0;
	static boolean[] learn = new boolean[27];
	static int[] wordBit;
	static int startbit;
	static String[] words;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (K - 5 < 0) {
			System.out.println(0);
			return;
		} else K -= 5;
		words = new String[N];
		wordBit = new int[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
			for (int j = 0; j < words[i].length(); j++) {
				wordBit[i] |= 1 << words[i].charAt(j) - 'a';
			}
		}
		//System.out.println(Integer.toBinaryString(wordBit[0]));
		learn['a'-'a'] = true;
		learn['n'-'a'] = true;
		learn['t'-'a'] = true;
		learn['i'-'a'] = true;
		learn['c'-'a'] = true;
		startbit |= 1 << 'a'-'a';
		startbit |= 1 << 'n'-'a';
		startbit |= 1 << 't'-'a';
		startbit |= 1 << 'i'-'a';
		startbit |= 1 << 'c'-'a';
		
		combi(0, 0, startbit);
		System.out.println(ans);
	}

	private static void combi(int cnt, int start, int bit) {
		if (cnt == K) {
			ans = Math.max(ans, isRead(bit));
			return;
		}
		
		for (int i = start; i < 27; i++) {
			if (learn[i]) continue;
			learn[i] = true;
			combi(cnt + 1, i + 1, bit | 1 << i);
			learn[i] = false;
		}
	}

	private static int isRead(int bit) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if ((wordBit[i] & ~bit) == 0) cnt++;
		}
		return cnt;
	}
}
