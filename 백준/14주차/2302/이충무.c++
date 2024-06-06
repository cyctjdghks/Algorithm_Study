#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;

int main()
{
	int n,m;
	cin >> n >> m;

	vector<int> gj;

	for (int i = 0; i < m; i++) {
		int tmp;
		cin >> tmp;
		gj.push_back(tmp);
	}

	if (n == m) {
		cout << 1;
		return 0;
	}

	int dp[41];
	dp[0] = 1;
	dp[1] = 1;
	dp[2] = 2;

	for(int i=3;i<=n;i++){
		dp[i] = dp[i - 1] + dp[i - 2];
	}

	bool gojung[41] = { false };

	for (int i = 0; i < m; i++) {
		gojung[gj[i]] = true;
	}

	long long cnt = 0;
	long long result = 1;
	for (int i = 1; i <= n; i++) {

		if (gojung[i]) {
			result *= dp[cnt];
			cnt = 0;
		}
		else cnt++;
	}
	result *= dp[cnt];
	cout << result;

	return 0;
}
