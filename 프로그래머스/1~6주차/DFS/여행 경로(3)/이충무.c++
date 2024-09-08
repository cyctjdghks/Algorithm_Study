#include <string>
#include <vector>
#include<algorithm>
using namespace std;

vector<string> answer, depth;
bool check[10001] = {false};
void dfs(string start, vector<vector<string>>& t, int cnt, vector<string>& depth){
    
    //dfs로 넘어왔는데 그래프가 끊긴 경우는 리턴 결국 아래에서 도착점이 시작점이 경우가 없을 때
    if(depth.size() != cnt+1){
        return;
    }
    if(cnt == t.size()){
        answer= depth;
        return;
    }

    for(int i=0;i<t.size();i++){
        if(check[i] == true) continue;

        if(start == t[i][0] && check[i] == false){
            depth.push_back(t[i][1]);
            check[i]=true;
            dfs(t[i][1],t,cnt+1,depth);
            //출발지는 있는데 도착점이 시작점인 경우가 없는 경우
            //다시 미방문 처리 후 다음 지점 찾기
            check[i] = false;
            
        }
    }

    depth.pop_back();
}

vector<string> solution(vector<vector<string>> tickets) {
    depth.push_back("ICN");

    sort(tickets.begin(),tickets.end());

    dfs("ICN",tickets,0,depth);
    return answer;
}