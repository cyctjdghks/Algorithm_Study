#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int parent[101];

bool compare(vector<int> &v1, vector<int> &v2){
    return v1[2] < v2[2];
}

int getParent(int a){
    if(a == parent[a]){
        return parent[a];
    }
    else
        return parent[a] = getParent(parent[a]);
}

void unionParent(int a, int b){
    a = getParent(a);
    b = getParent(b);
    
    if(a > b){
        parent[a] = b;
    }
    else{
        parent[b] = a;
    }
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    sort(costs.begin(), costs.end(), compare);
    
    for(int i = 0 ; i < n ; i++){
        parent[i] = i;
    }
    
    for(int i = 0 ; i < costs.size() ; i++){
        int from = costs[i][0];
        int to = costs[i][1];
        int cost = costs[i][2];
        
        if(getParent(from) != getParent(to)){
            unionParent(from, to);
            answer += cost;
        }
    }
    
    return answer;
}