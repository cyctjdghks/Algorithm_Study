import java.util.*;

class Solution {
    Set<String> set = new HashSet<>();
    public int solution(String begin, String target, String[] words) {
        // words안에 target과 같은 단어가 있는지 확인
        set = new HashSet<>(Arrays.asList(words));
        if (!set.contains(target)) {
            return 0;
        }

        return bfs(begin, target, words);
    }

    public int bfs(String begin, String target, String[] words){
        Queue<String> que = new LinkedList<>();
        int cnt = 0;

        que.add(begin);

        while(!que.isEmpty()){
            for (int i=0; i<que.size(); i++){
                String s = que.poll();

                // target와 일치하는 경우
                if(s.equals(target)){
                    return cnt;
                }

                for(String word : set.toArray(new String[set.size()])){
                    // 하나의 알파벳만 다른 경우
                    if(canConvert(s, word)){
                        que.add(word);
                        set.remove(word);
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }

    public boolean canConvert(String cur, String word){
        int diff = 0;
        for(int i=0;i< word.length(); i++){
            if(cur.charAt(i) != word.charAt(i)){
                diff+=1;
            }
        }
        if(diff == 1){
            return true;
        }
        return false;
    }
}