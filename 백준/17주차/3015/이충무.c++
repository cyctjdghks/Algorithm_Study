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

vector<vector<char>> m;

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0,-1, 0, 1 };
int r, c;
int result = 0;
int bfs(int a,int b) {
	vector<vector<bool>> visited(r, vector<bool>(c, false));
	vector<vector<int>> dist(r,vector<int>(c,0));

	int maxDist = 0;

	queue<pair<int, int>> q;
	q.push({ a,b });
	visited[a][b] = true;
	dist[a][b] = 0;
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visited[nx][ny] && m[nx][ny] == 'L') {
				visited[nx][ny] = true;
				q.push({ nx,ny });
				dist[nx][ny] = dist[x][y] + 1;
				if (maxDist < dist[nx][ny]) {
					maxDist = dist[nx][ny];
				}
			}
		}
	}

	//if (a == 0 && b == 6) {
	//	for (int i = 0; i < r; i++) {
	//		for (int j = 0; j < c; j++) {
	//			cout << dist[i][j] << " ";
	//		}
	//		cout << endl;
	//	}
	//}
	


	return maxDist;
}
int main()
{
	cin >> r >> c;
	
	for (int i = 0; i < r; i++) {
		vector<char> tmp;
		for (int j = 0; j < c; j++) {
			char x;
			cin >> x;
			tmp.push_back(x);
		}
		m.push_back(tmp);
	}
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if (m[i][j] == 'L') {
				int tmp = bfs(i, j);
				if (tmp > result) result = tmp;
			}
		}
	}
	cout << result;
	
	return 0;
}