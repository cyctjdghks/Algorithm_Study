#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;

vector<vector<int>> m(11,vector<int>(11,0));
int result = 100;
int paper[6] = { 0, };
bool check(int x, int y, int size)
{
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			if (m[x + i][y + j] == 0)
				return false;
		}
	}

	return true;
}
void dfs(int x,int y,int cnt) {
	bool flag = true;
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			if (m[i][j] == 1) {
				flag = false;
				x = i;
				y = j;
				break;
			}
		}
		if (!flag) break;
	}
	if (flag) {
		if (cnt < result) result = cnt;
		return;
	}
	

	if (cnt >= result)	return;

	for (int i = 5; i >= 1; i--){
		if (x + i > 10 || y + i > 10) continue;

		if (paper[i] >= 5) continue;

		if (check(x, y, i)){
			paper[i]++;
			for (int a = 0; a < i; a++) {
				for (int b = 0; b < i; b++)
				{
					m[x + a][y + b] = 0;
				}
			}

			dfs(x, y, cnt + 1);

			paper[i]--;
			for (int a = 0; a < i; a++) {
				for (int b = 0; b < i; b++)
				{
					m[x + a][y + b] = 1;
				}
			}
		}

	}

	return;
}
int main()
{
	for (int i = 0; i < 10; i++){
		for (int j = 0; j < 10; j++)
		{
			int tmp;
			cin >> tmp;
			m[i][j] = tmp;
		}
	}

	dfs(0, 0, 0);
	if (result == 100) result = -1;

	cout << result;

}
