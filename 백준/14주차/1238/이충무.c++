#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;
#define num first
#define cost second
#define INF 20000000
struct cmp {
	bool operator() (pair<int, int> a, pair<int, int> b) {
		return a.cost > b.cost;
	}
};
vector<int> dijkstra(int start,vector<vector<int>> map,int n) {
	vector<int> dist(n + 1, INF);
	priority_queue<pair<int, int>, vector<pair<int, int>>, cmp> pq;
	
	pq.push({ start, 0 });
	dist[start] = 0;

	while (!pq.empty()) {
		int now = pq.top().num;
		int nowCost = pq.top().cost;
		pq.pop();

		for (int i = 1; i <= n; ++i) {

			if (map[now][i] == INF)
				continue;

			if (dist[i] > nowCost + map[now][i]) {
				dist[i] = nowCost + map[now][i];
				pq.push({ i, nowCost + map[now][i] });
			}
		}
	}


	return dist;
}
int main()
{
	int n, m, x;
	cin >> n >> m >> x;

	vector<vector<int>> map(n + 1, vector<int>(n + 1, INF));
	vector<vector<int>> map2(n + 1, vector<int>(n + 1, INF));

	for (int i = 0; i < m; i++) {
		int from, to, d;
		cin >> from >> to >> d;
		map[from][to] = d;
		map2[to][from] = d;
	}

	int result = 0;
	vector<int> dist(n + 1, 0);
	vector<int> xtoi = dijkstra(x, map, n);
	vector<int> itox = dijkstra(x, map2, n);


	for (int i = 1; i <= n; i++) {
		if (i == x) continue;
		dist[i] += xtoi[i] + itox[i];
		if (dist[i] > result) result = dist[i];
	}


	cout << result;

	return 0;
}
