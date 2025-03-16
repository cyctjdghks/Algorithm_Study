import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // words 리스트를 Set으로 변환하여 탐색 속도를 높임
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        // target이 words에 없으면 변환할 수 없음
        if (!wordSet.contains(target)) return 0;

        // BFS를 위한 큐 (단어)
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);

        // 방문한 단어를 저장할 Set
        Set<String> visited = new HashSet<>();
        visited.add(begin);

        int answer = 0; // 변환 횟수

        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 레벨의 단어 개수
            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                // target에 도달하면 변환 횟수 반환
                if (word.equals(target)) return answer;

                // 현재 단어에서 한 글자만 바꾼 단어 찾기
                for (String nextWord : wordSet) {
                    if (!visited.contains(nextWord) && isOneLetterDifferent(word, nextWord)) {
                        queue.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            answer++; // BFS 단계 증가
        }

        return 0; // 변환할 수 없는 경우
    }

    // 두 단어가 한 글자만 다른지 확인하는 함수
    private boolean isOneLetterDifferent(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
                if (count > 1) return false; // 두 개 이상 다르면 false
            }
        }
        return count == 1;
    }
}
