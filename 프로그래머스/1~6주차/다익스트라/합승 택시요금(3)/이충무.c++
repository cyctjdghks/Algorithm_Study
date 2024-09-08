#include <string>
#include <vector>
#include <queue>
#include<iostream>
#define INF 1000000
using namespace std;



//vector<pair<int, int>> e;
struct cmp {
    bool operator() (pair<int, int> a, pair<int, int> b) {
        return a.second > b.second; // min-heap
    }
};

void dijkstra(vector<vector<int>>& g,int n,int start,int d[]){

    priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> pq;
    d[start] = 0;
    pq.push(make_pair(start, 0));
    
    while(!pq.empty()){
        int cur = pq.top().first;
        int cost = pq.top().second;
        //cout << cur;
        pq.pop();
        for(int i=1;i<=n;i++){
            if (g[cur][i] == 0)
                continue;
            int startToNext = g[cur][i] + cost;
            if(d[i] > startToNext){
                d[i] = startToNext;
                pq.push(make_pair(i,startToNext));
            }
        }
        // for(int i=1;i<=n;i++){
        //     if(d[i] > cost + g[cur][i]){
        //         d[i] = cost + g[cur][i];
        //         //cout << d[i];
        //         pq.push(make_pair(i, cost + g[cur][i]));
        //     }
        // }
        
        
    }
    
}


int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int answer = 0;
    vector<vector<int>> g(n + 1, vector<int>(n + 1, 0));
    //int d[201];
    //vector<int> d(n+1, INF);
    int d[201];
    int da[201];
    int db[201];
    for(int i=1;i<n+1;i++){
        d[i] = INF;
        da[i] = INF;
        db[i] = INF;
    }
    
    for(int i=0;i<fares.size();i++){
        int start = fares[i][0];
        int end = fares[i][1];
        int cost = fares[i][2];
        g[start][end] = cost;
        g[end][start] = cost;
        
    }
    dijkstra(g, n, s,d);
    dijkstra(g, n, a,da);
    dijkstra(g, n, b,db);
    answer= 1e9;
    
    for (int i = 1; i <= n; ++i) {
        int result = d[i] + da[i] + db[i];
        if (answer > result)
            answer = result;
    }
    
    
    
    
    
    return answer;
}