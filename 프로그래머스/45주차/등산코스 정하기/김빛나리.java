import java.util.*;

class Solution {
    ArrayList<Node>[] list;
    
    class Node implements Comparable<Node>{
        int index;
        int intensity;

        Node(int index, int intensity){
            this.index = index;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.intensity, o.intensity);
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        list = new ArrayList[n + 1];

        for(int i=0;i<=n;i++) {
            list[i] = new ArrayList<>();
        }

        // 그래프 만들기
        for(int[] path : paths) {
            list[path[0]].add(new Node(path[1], path[2]));
            list[path[1]].add(new Node(path[0], path[2]));
        }
        
        // 모든 지점의 intesity -> max 값으로 초기화
        int[] intensities = new int[n + 1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 출입구 queue에 넣기 -> 출입구의 경우, intesity = 0
        for(int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensities[gate] = 0;
        }
        
        // 다익스트라 알고리즘
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            boolean isSummit = false;

            // 산봉우리일 경우
            for(int summit : summits) {
                if(cur.index == summit) {
                    isSummit = true;
                    break;
                }
            }
            if(isSummit) continue;

            // 현재 intensity가 저장된 intensity보다 커서 최소 갱신이 되지 않을 경우
            if(intensities[cur.index] < cur.intensity) continue;

            for(Node node : list[cur.index]) {
                int intensity = Math.max(cur.intensity, node.intensity);

                // intensity 최소가 되도록 갱신
                if(intensity < intensities[node.index]) {
                    intensities[node.index] = intensity;
                    pq.add(new Node(node.index, intensity));
                }
            }
        }
        
        int idx = 0;
        int minIntensity = Integer.MAX_VALUE;
        
        // 최소 등산 코스가 여러개이면 산봉우리 번호가 가장 낮은 등산코스 선택
        Arrays.sort(summits);
        
        for(int i=0;i<summits.length;i++) {
            if(intensities[summits[i]] < minIntensity) {
                minIntensity = intensities[summits[i]];
                idx = summits[i];
            }
        }

        return new int[]{idx, minIntensity};
    }
}