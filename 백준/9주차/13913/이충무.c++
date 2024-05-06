#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;
int path[100001];
bool visited[100001];
vector<int> result;
int bfs(int n,int k) {
    queue<pair<int,int>> q;
    q.push({ n,0 });
    visited[n] = true;
    while (!q.empty()) {
        int idx = q.front().first;
        int time = q.front().second;

        q.pop();

        if (idx == k) {
			int index = idx;
			while (index != n)
			{
				result.push_back(index);
				index = path[index];
			}
			result.push_back(n);
            return time;
        }


		if (idx + 1 <= 100000 && !visited[idx + 1]) {
			path[idx + 1] = idx;
			visited[idx + 1] = true;
			q.push({ idx + 1, time + 1 });
		}
		if (idx - 1 >= 0 && !visited[idx - 1]) {
			path[idx - 1] = idx;
			visited[idx - 1] = true;
			q.push({ idx - 1,time + 1 });
		}
		if (idx * 2 <= 100000 && !visited[idx * 2]) {
			path[idx * 2] = idx;
			visited[idx * 2] = true;
			q.push({ idx * 2,time + 1 });
		}

        
    }
}
int main()
{
    int n, k;
    cin >> n >> k;

    cout << bfs(n, k) << endl;
	for (int i = result.size() - 1; i >= 0; i--)
	{
		cout << result[i] << " ";
	}

	

    return 0;
}