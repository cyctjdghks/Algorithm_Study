#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;

int result = 9999999;
vector<vector<vector<int>>> visited;
int scv3[6][3] = {
    {1,3,9},{1,9,3},{3,1,9},{3,9,1},{9,1,3},{9,3,1}
};
struct hp {
    int a, b, c;
};
int bfs(int a, int b, int c) {
    queue<hp> q;
    visited[a][b][c] = 1;
    q.push({ a, b, c });

    while (!q.empty()) {
        int a = q.front().a;
        int b = q.front().b;
        int c = q.front().c;
        q.pop();

        if (visited[0][0][0]) break;

        for (int i = 5; i >= 0; i--)
        {
            int na = a - scv3[i][0];
            int nb = b - scv3[i][1];
            int nc = c - scv3[i][2];
            if (na < 0) na = 0;
            if (nb < 0) nb = 0;
            if (nc < 0) nc = 0;

            if (visited[na][nb][nc]) continue;

            visited[na][nb][nc] = visited[a][b][c] + 1;

            q.push({ na, nb, nc });
        }
    }
    return visited[0][0][0] - 1;
}
int main()
{
    int n;

    cin >> n;

    vector<int> scv(3,0);
    visited.resize(70, vector<vector<int>>(70, vector<int>(70, 0)));
    for (int i = 0;i < n;i++) {
        int tmp;
        cin >> tmp;
        scv[i] = tmp;
    }

    cout << bfs(scv[0], scv[1], scv[2]);

    return 0;
}