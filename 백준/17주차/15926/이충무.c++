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

int main()
{
	int n;
	cin >> n;

	vector<char> v;

	for (int i = 0; i < n; i++) {
		char x;
		cin >> x;
		v.push_back(x);
	}

	stack<int> s;
	s.push(-1);

	int result = 0;
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		if (v[i] == '(') {
			s.push(i);
		}
		else {
			if (s.size() != 1) {
				s.pop();
				int tmp = i - s.top();
				if (result < tmp) {
					result = tmp;
				}
			}
			else {
				s.pop();
				s.push(i);
				//cout << "s.size() == 1 건너뛰기" << endl;
				continue;
			}
			
		}
	}

	cout << result;
	return 0;
}