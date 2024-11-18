class Solution {
    static int[] map;
    static int[][] graph;
    static boolean[] visited;
    static int maxSheep = 1;
    public int solution(int[] info, int[][] edges) {
        map = info;
        graph = edges;
        visited = new boolean[info.length];
        visited[0] = true;

        dfs(1, 0);

        return maxSheep;
    }

    public void dfs(int sheepCnt, int wolfCnt){
        if(maxSheep < sheepCnt){
            maxSheep = sheepCnt;
        }
        if(sheepCnt <= wolfCnt) return;


        for(int[] g : graph){
            int parent = g[0];
            int child = g[1];
            if(visited[parent] && !visited[child]){
                visited[child] = true;
                if(map[child] == 0){
                    dfs(sheepCnt+1, wolfCnt);
                }
                else{
                    dfs(sheepCnt, wolfCnt+1);
                }
                visited[child] = false;
            }
        }
    }
}