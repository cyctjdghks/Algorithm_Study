#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;
typedef long long ll;
vector<vector<int>> arr;

int n;
int result;
int cnt;
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,-1,0,1 };
void bfs(vector<vector<bool>> &visited,int a,int b) {
    queue<pair<int, int>> q;
    q.push({ a,b });
    visited[a][b] = true;
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.push({ nx,ny });
                }
            }
        }
    }
    
}
void init(int h) {
   vector<vector<bool>> visited(n,vector<bool>(n,false));
   cnt = 0;
   for (int i = 0; i < n; i++) {
       for (int j = 0; j < n; j++) {
           if (arr[i][j] <= h) {
               visited[i][j] = true;
           }
       }
   }
   for (int i = 0; i < n; i++) {
       for (int j = 0; j < n; j++) {
           if (!visited[i][j]) {
               bfs(visited, i, j);
               cnt++;
           }
           
       }
   }

   if (cnt > result) result = cnt;
    
}
int main()
{
    
    cin >> n;
    int maxH = 0;
    for (int i = 0; i < n; i++) {
        vector<int> tmp;
        for (int j = 0; j < n; j++) {
            int x;
            cin >> x;
            if (maxH < x) maxH = x;
            tmp.push_back(x);
        }
        arr.push_back(tmp);
    }

    for (int i = 0; i <= maxH; i++) {
        init(i);
    }


    cout << result;
}
