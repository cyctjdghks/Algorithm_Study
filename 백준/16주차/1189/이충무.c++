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
int r, c, k;
int result = 0;
void dfs(int cnt,int x,int y, vector<vector<bool>> visited) {
	if (cnt > k) return;
	if (x == 0 && y == c - 1 && cnt == k) {
		result++;
	}

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
			if (!visited[nx][ny] && m[nx][ny] != 'T') {
				visited[nx][ny] = true;
				dfs(cnt + 1, nx, ny, visited);
				visited[nx][ny] = false;
			}

		}
	}

}
int main()
{
	cin >> r >> c >> k;
	
	for (int i = 0; i < r; i++) {
		vector<char> tmp;
		for (int j = 0; j < c; j++) {
			char x;
			cin >> x;
			tmp.push_back(x);
		}
		m.push_back(tmp);
	}

	if (m[0][c - 1] == 'T') {
		cout << 0;
		return 0;
	}

	vector<vector<bool>> visited(r, vector<bool>(c, false));
	visited[r - 1][0] = true;
	dfs(1,r-1,0,visited);

	cout << result;
	return 0;
}