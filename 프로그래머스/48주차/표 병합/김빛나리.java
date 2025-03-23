import java.util.*;

class Solution {
    // 1~50까지 정수 -> 가로, 세로 -> 2500
    int[] parent = new int[2501];
    String[] value = new String[2501];
    
    public String[] solution(String[] commands) {
        //초기화
        for(int i=1;i<=2500;i++) {
            parent[i] = i;
            value[i] = "";
        }
        
        List<String> answer = new ArrayList<>();
        
        for(String command : commands) {
            // 띄어쓰기 기준 문자열 분리 -> 토큰화
            StringTokenizer st = new StringTokenizer(command);
            // UPDATE, MERGE, UNMERGE, PRINT
            String com = st.nextToken();

            if("UPDATE".equals(com)) {
                // "UPDATE value1 value2"인 경우
                if(st.countTokens() == 2) {
                    String select = st.nextToken();
                    String content = st.nextToken();
                    
                    // select와 동일한 값은 모두 content로 변경
                    for(int i=1;i<=2500;i++) {
                        if(select.equals(value[i])) value[i] = content;
                    }
                }
                // "UPDATE r c value"인 경우
                else {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    
                    String content = st.nextToken();
                    // 좌표를 숫자로 변경
                    int num = convertNum(r, c);
                    value[find(num)] = content;
                }
            } 
            else if ("MERGE".equals(com)) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                
                int n1 = convertNum(r1, c1);
                int n2 = convertNum(r2, c2);
                int root1 = find(n1);
                int root2 = find(n2);
                
                // 같은 그룹인 경우
                if(root1 == root2) continue;
                
                // 값을 가진 쪽으로 병합
                // .isBlank() = 문자열이 비어 있거나, 빈 공백으로만 이루어져 있으면, true 리턴
                String rootString = value[root1].isBlank() ? value[root2] : value[root1];
                value[root1] = "";
                value[root2] = "";
                union(root1, root2);
                value[root1] = rootString;
            } 
            else if("UNMERGE".equals(com)) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                int num = convertNum(r, c);
                int root = find(num);
                
                String rootString = value[root];
                value[root] = "";
                value[num] = rootString;
                
                List<Integer> deleteList = new ArrayList<>();
                for(int i=1;i<=2500;i++) {
                    // 해당 root를 parent로 가지는 셀
                    if(find(i) == root) deleteList.add(i);
                }
                
                // 초기 상태로 돌아가기
                for(Integer t : deleteList) {
                    parent[t] = t;
                }
            } 
            else if("PRINT".equals(com)) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                int num = convertNum(r, c);
                int root = find(num);
                
                if(value[root].isBlank()) answer.add("EMPTY");
                else answer.add(value[root]);
            }
        }

        return answer.toArray(new String[0]);
    }
    
    public int convertNum(int x, int y) {
        int result = 50 * (x - 1);
        
        return result + y;
    }
    
    // UNION-FIND 알고리즘
    public int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a != b) parent[b] = a;
    }
}