#include <string>
#include <vector>

using namespace std;

int map[1001][1001];

//다시 풀기
int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int n = board.size();
    int m = board[0].size();
    
    for(int i=0;i<skill.size();i++) {
        int num = skill[i][5];
        if(skill[i][0] == 1) num = -num;
        
        map[skill[i][1]][skill[i][2]] += num;
        map[skill[i][3]+1][skill[i][4]+1] += num;
        map[skill[i][1]][skill[i][4]+1] -= num;
        map[skill[i][3]+1][skill[i][2]] -= num;
    }
    
    for(int i=0; i<n+1; ++i) {
        for(int j=0; j<m; ++j) {
            map[i][j+1] += map[i][j];
        }
    }
        
    for(int j=0; j<m+1; ++j) {
        for(int i=0; i<n; ++i) {
            map[i+1][j] += map[i][j];
        }
    }
    
    int answer = 0;
    for(int i=0; i<n; ++i) { 
        for(int j=0; j<m; ++j) {
            if(board[i][j] + map[i][j] > 0) answer++;
        }
    }
    
    return answer;
}