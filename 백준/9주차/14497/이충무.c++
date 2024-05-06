#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;
int dy[4] = { -1,1,0,0 };
int dx[4] = { 0,0,-1,1 };
int visited[302][302];
int main()
{
    
    int n,m;
    vector<string> vec;
    cin >> n >> m;

    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    x1--;
    y1--;
    x2--;
    y2--;
    for (int i = 0; i < n;i++) {
        string temp;
        cin >> temp;
        vec.push_back(temp);
    }
    int turn = 1;


    while (1)
    {
        fill(&visited[0][0], &visited[0][0] + 302 * 302, 0);

        queue<pair<int, int>> q;
        visited[x1][y1] = turn;
        q.push(make_pair(x1, y1));

        while (!q.empty())
        {
            int x = q.front().first;
            int y = q.front().second;
            q.pop();

            if (x == x2 && y == y2)
            {
                cout << visited[x][y];
                return 0;
            }

            for (int j = 0; j < 4; j++)
            {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;

                if (vec[nx][ny] == '1')
                {
                    vec[nx][ny] = '0';

                    visited[nx][ny] = turn;
                    continue;
                }

                visited[nx][ny] = turn;
                q.push(make_pair(nx, ny));
            }
        }
        turn++;
    }


    cout << turn;

    return 0;
}