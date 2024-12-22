import java.util.*;
import java.util.stream.*;
class Solution {
    static int size;     // 주사위 개수
    static int [][] d;   // 완전탐색에 사용할 dice copy

    static int [] memo;

    static HashMap<int[], ArrayList<Integer>> map = new HashMap<>(); // 조합 기록 맵

    public int[] solution(int[][] dice) {
        size = dice.length;

        d = new int [size][6];
        // 완전탐색에 사용할 dice copy
        for(int i = 0; i < size; i++){
            d[i] = Arrays.copyOf(dice[i], 6);
        }

        // 주사위 조합 구하기 func1
        memo = new int [size/2];
        func1(0, 1);

        int max = 0;
        int [] answer = new int [size/2];

        List<int[]> list = map.keySet().stream().collect(Collectors.toList());

        // 정렬 또한 시간을 많이 잡아 먹는다. 정렬을 했는지 체크 용도 set
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < list.size(); i++){
            int [] key = list.get(i);
            for(int j = 0; j < list.size(); j++){
                if(i == j) continue;
                int [] key2 = list.get(j);
                boolean flag = true;

                // 조합 검증 로직
                for(int k = 0; k < key.length; k++){
                    for(int l = 0; l < key2.length; l++){
                        if(key[k] == key2[l]){
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                }
                if(!flag) continue;

                ArrayList<Integer> compare1 = map.get(key);
                ArrayList<Integer> compare2 = map.get(key2);

                // 정렬 검증 로직
                if(!set.contains(i)){
                    Collections.sort(compare1);
                    set.add(i);
                }
                if(!set.contains(j)){
                    Collections.sort(compare2);
                    set.add(j);
                }

                // 이분탐색 시작
                int win = 0;
                for(int k = 0; k < compare1.size(); k++){
                    int target = compare1.get(k);

                    int start = 0;
                    int end   = compare1.size();
                    while (start < end){
                        int mid = (start + end) / 2;
                        if(target <= compare2.get(mid)){
                            end = mid;
                        }
                        else start = mid + 1;
                    }
                    win += end;
                }
                // 승수가 가장 많은 조합을 answer에 기록
                if(max < win){
                    answer = key;
                    max = win;
                }
            }
        }

        return answer;
    }

    public static void func1(int depth, int cur){
        if(depth == size/2){
            // 해당 주사위를 통해서 주사위 조합 구하기 func2, func3
            func2(memo);
            return;
        }
        for(int i = cur; i <= size; i++){
            memo[depth] = i;
            func1(depth + 1, i + 1);
        }
    }
    public static void func2(int[] input){
        int [] temp = Arrays.copyOf(input, input.length);
        // 배열 자체를 key에 넣기
        map.put(temp, new ArrayList<>());
        func3(input, 0, 0, temp);
    }
    public static void func3(int[] input, int depth, int sum, int[] temp){
        if(depth == size/2){
            map.get(temp).add(sum);
            return;
        }
        for(int i = 0; i < 6; i++){
            func3(input, depth + 1, sum + d[input[depth]-1][i], temp);
        }
    }
}