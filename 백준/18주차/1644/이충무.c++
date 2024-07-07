#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;

int n;

vector<int> eratos(int n) {
	vector<int> arr;
	vector<bool> prime(n + 1, true);

	for (int i = 2; i * i <= n; ++i) {
		if (prime[i]) {
			for (int j = i * i; j <= n; j += i) {
				prime[j] = false;
			}
		}
	}

	for (int i = 2; i <= n; ++i) {
		if (prime[i]) arr.push_back(i);
	}
	return arr;
}
int main()
{
	cin >> n;

	vector<int> v = eratos(n);

	int start = 0;
	int end = 0;
	int sum = 0;
	int cnt = 0;
	/*for (int i = 0; i < v.size(); i++) {
		cout << v[i] << " ";
	}
	cout << endl;*/

	while (1) {
		if (sum >= n) {
			sum -= v[start];
			start++;
		}
		else if(end == v.size()){
			break;
		}
		else if (sum < n) {
			//cout << sum << endl;
			sum += v[end];
			end++;
		}
		if (sum == n) {
			cnt++;
			
		}

		//if (end == v.size() && start == v.size()) break;
	}
	cout << cnt;

	return 0;
}