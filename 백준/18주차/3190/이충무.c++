#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;

int n, k, l;
int mp[101][101];
queue<pair<int, char>> q;
int dir = 0;
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

int main()
{
	cin >> n;
	cin >> k;


	for (int i = 0; i < k; i++) {
		int a, b;
		cin >> a >> b;
		mp[a][b] = 2;
	}

	cin >> l;


	for (int i = 0; i < l; i++) {
		int a;
		char b;
		cin >> a >> b;
		q.push({ a,b });
	}

	mp[1][1] = 1;
	int time = 0;
	deque<pair<int, int>> dq;
	dq.push_front({ 1, 1 });


	while (1) {
		time++;
		int r = dq.front().first + dx[dir];
		int c = dq.front().second + dy[dir];
		/*cout << dq.size() << endl;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				cout << mp[i][j] << " ";
			}
			cout << endl;
		}
		cout << endl;*/
		//cout << r << " " << c << endl;
		if (r <= 0 || r > n || c <= 0 || c > n || mp[r][c] == 1) {
			cout << time;
			return 0;
		}
			
		if (!dq.empty() &&mp[r][c] != 2) {
			mp[dq.back().first][dq.back().second] = 0;
			dq.pop_back();
		}
		mp[r][c] = 1;
		dq.push_front({ r, c });

		if (!q.empty() && time == q.front().first) {
			char ch = q.front().second;
			if (ch == 'D')
				dir = (dir + 1) % 4;
			else dir = (dir + 3) % 4;
			q.pop();
		}
	}
	cout << time;
	return 0;
}
