#include <string>
#include <vector>
#include<queue>
#include<algorithm>
#include<iostream>
using namespace std;
bool visited[101][101] = {false,};

int dx[4]= {1,0,-1,0};
int dy[4] = {0,1,0,-1};
int bfs(vector<string> maps,int a,int b){
    queue<pair<int,int>> q;
    q.push(make_pair(a,b));
    visited[a][b]=true;
    
    int sum = maps[a][b]-'0';
    
    while(!q.empty()){
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && ny >= 0 && nx < maps.size() && ny < maps[0].size()){
                if(visited[nx][ny] || maps[nx][ny]== 'X'){
                   continue;  
                }
                q.push(make_pair(nx,ny));
                visited[nx][ny]=true;
                sum+=maps[nx][ny]-'0';
            }
        }
        
    }
    return sum;
    
}
vector<int> solution(vector<string> maps) {
    

    for(int i=0;i<maps.size();i++){
        for(int j=0;j<maps[i].size();j++){
            if(maps[i][j] == 'X') continue;
            if(!visited[i][j]) {
                // cout << i << " " << j << endl;
                answer.push_back(bfs(maps,i,j));
            }
                
        }
    }
    
    if(answer.size() == 0) answer.push_back(-1);
    sort(answer.begin(),answer.end());
    
    return answer;
}