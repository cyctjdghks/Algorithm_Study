#include <string>
#include <vector>
#include <queue>
#include<iostream>
#include <map>
#define INF 500001
using namespace std;

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<int> answer;
    vector<vector<int>> maps(n + 1);

    for (vector<int> road : roads) {
        maps[road[0]].push_back(road[1]);
        maps[road[1]].push_back(road[0]);
    }
    
    map<int, int> s;
    for (int source : sources) {
        s[source] = INF;
    }

    queue<pair<int, int>> q;
    vector<bool> visited(n + 1, false);

    q.push({ destination, 0 });
    visited[destination] = true;

    while (!q.empty()) {
        pair<int, int> p = q.front();
        int pos = p.first;
        int cost = p.second;
        q.pop();

        if (s[pos]) {
            s[pos] = cost;
        }
        
        for (int i = 0; i < maps[pos].size(); i++) {
            if (!visited[maps[pos][i]]) {
                q.push({ maps[pos][i], cost + 1 });
                visited[maps[pos][i]] = true;
            }
        }
    }

    for (int source : sources) {
        if (s[source] == INF)
            answer.push_back(-1);
        else
            answer.push_back(s[source]);
    }

    return answer;
}