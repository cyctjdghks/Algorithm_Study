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

vector<vector<int>> m;

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0,-1, 0, 1 };
int n,l,r;
int result = 0;
bool move() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			for(int d=0;d<4;d++){
				int nx = i + dx[d];
				int ny = j + dy[d];

				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					int avgNum = abs(m[i][j] - m[nx][ny]);
					if (avgNum >= l && avgNum <= r) {
						return true;
					}
				}
			}
		}
	}
	return false;
}
void bfs(int a, int b, vector<vector<bool>>& visited) {
	queue<pair<int, int>> q;
	vector<pair<int, int>> sumv;
	int sum = m[a][b];
	visited[a][b] = true;
	

	q.push({ a,b });
	sumv.push_back({ a,b });

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
				int avgNum = abs(m[x][y] - m[nx][ny]);
				if (!visited[nx][ny] && avgNum >= l && avgNum <= r) {
					q.push({ nx,ny });
					sumv.push_back({ nx,ny });
					sum += m[nx][ny];
					visited[nx][ny] = true;
				}
			}
		}
	}
	sum = sum / sumv.size();
	for (int i = 0; i < sumv.size(); i++) {
		int x = sumv[i].first;
		int y = sumv[i].second;

		m[x][y] = sum;
	}

}
int main()
{
	cin >> n >> l >> r;
	
	for (int i = 0; i < n; i++) {
		vector<int> tmp;
		for (int j = 0; j < n; j++) {
			int x;
			cin >> x;
			tmp.push_back(x);
		}
		m.push_back(tmp);
	}


	int time = 0;

	while (move()) {
		time++;
		vector<vector<bool>> visited(n, vector<bool>(n, false));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j,visited);
				}
				
			}
		}

	}


	cout << time;
	
	return 0;
}