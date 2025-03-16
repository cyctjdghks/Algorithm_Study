class Solution {
    static String[] Words;
    static String Target;
    static boolean[] v;
    static int answer;

    public int solution(String begin, String target, String[] words) {
        Target = target;
        Words = words;
        v = new boolean[words.length];
        answer = 0;

        dfs(begin, 0);
        return answer;
    }

    public static void dfs(String begin, int cnt) {
        if (begin.equals(Target)) {
            answer = cnt;
            return;
        }

        for (int i = 0; i < Words.length; i++) {
            if (v[i]) {
                continue;
            }

            int k = 0;
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == Words[i].charAt(j)) {
                    k++;
                }
            }

            if (k == begin.length() - 1) {
                v[i] = true;
                dfs(Words[i], cnt + 1);
                v[i] = false;
            }
        }
    }
}