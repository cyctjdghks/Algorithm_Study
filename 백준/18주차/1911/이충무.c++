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
	cin >> n >> l;
	vector<pair<int, int> > v;

	for (int i = 0; i < n; i++) {
		int a, b;
		cin >> a >> b;
		v.push_back({ a,b });
	}
	sort(v.begin(), v.end());
	int result = 0;
	int cur = 0;
	for (int i = 0; i < n; i++) {
		int start = v[i].first;
		int end = v[i].second;

		if (end <= cur) continue;

		cur = max(cur, start);
		int cnt = 0;
		if (cur < start) {
			cnt = (end - start) / l;
			if ((end - start) % l != 0) cnt++;
			cur = start + (l * cnt);
		}
		else {
			cnt = (end - cur) / l;
			if ((end - cur) % l != 0) cnt++;
			cur += (l * cnt);
			
		}
		result += cnt;

	}
	cout << result;


	return 0;
}
