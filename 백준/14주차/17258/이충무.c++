#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;

int n, m, k, t, cnt[301], dp[301][301];
vector<pair<int, int>> v;
int recur(int idx, int num, int prev) {
	if (idx == v.size()) return 0;
	if (dp[idx][num]) return dp[idx][num];

	int cost = max(0, t - v[idx].second);
	int real_cost = (prev >= cost) ? 0 : cost - prev;

	int& ret = dp[idx][num];
	if (num - real_cost >= 0) {
		return ret = max(recur(idx + 1, num - real_cost, cost) + v[idx].first, recur(idx + 1, num, 0));
	}
	else return ret = recur(idx + 1, num, 0);
}
int main()
{
	
	cin >> n >> m >> k >> t;
	for (int j = 0; j < m; j++) {
		int a, b;
		cin >> a >> b;
		for (int i = a; i < b; i++) {
			cnt[i]+=1;
		}
	}
	
	int tmp = cnt[1];
	int count = 1;
	for (int i = 2; i <= n; i++) {
		if (tmp != cnt[i]) {
			v.push_back({ count, tmp });
			count = 0;
			tmp = cnt[i];
		}
		count++;
	}
	v.push_back({ count, tmp });

	int result = recur(0, k, 0);
	cout << result;

	return 0;
}
