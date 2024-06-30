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
#define h first 
#define cnt second 
vector<vector<int>> m;

int main()
{
	int n;
	cin >> n;

	vector<int> v;

	for (int i = 0; i < n; i++) {
		int tmp = 0;
		cin >> tmp;
		v.push_back(tmp);
	}
	ll ans = 0;
	stack<pair<int,int>> s;

	for (int i = 0; i < n; i++) {
		int cnt = 1;

		while (!s.empty() && s.top().h <= v[i]) {
			ans += s.top().cnt;
			if (s.top().h == v[i]) cnt += s.top().cnt;
			s.pop();
		}
		if (!s.empty()) ans++;
		s.push({ v[i], cnt});

	}



	cout << ans;

	return 0;
}

//참고 https://blog.thecloer.com/121