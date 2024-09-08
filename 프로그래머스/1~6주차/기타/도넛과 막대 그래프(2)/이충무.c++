#include <string>
#include <vector>
#include<map>
#include<iostream>
using namespace std;

vector<vector<int>> node(1000001);
int income[1000001];
int outcome[1000001];
int graphD=0;
int graph1=0;
int graph8=0;
void dfs(int start,int cur,bool startVisited){
    if(cur == start && startVisited){
        graphD++;
        return ;
    }
    if(outcome[cur]==0){
        graph1++;
        return ;
    }
    if(outcome[cur] >= 2){
        graph8++;
        return ;
    }
    startVisited = true;
    
    for(auto nxt : node[cur]){
        dfs(start,nxt,startVisited);
    }
    
    return ;
    
}
vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer;
    
    for(int i=0;i<edges.size();i++){
        income[edges[i][1]]++;
        outcome[edges[i][0]]++;
        
        node[edges[i][0]].push_back(edges[i][1]);
    }
    
    for(int i=1; i<1000001; i++){  
		if(income[i] == 0 && outcome[i] >=2){  
			answer.push_back(i);  
		}  
	}  
    
    int start = answer[0];
    
    for(int i=0;i<node[start].size();i++){
        dfs(node[start][i],node[start][i],false);
    }
    
    
    answer.push_back(graphD);
    answer.push_back(graph1);
    answer.push_back(graph8);
    
    return answer;
}