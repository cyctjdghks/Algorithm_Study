#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include<stack>
using namespace std;

int main() {

    int n;
    cin >> n;
    vector<pair<int, int>> v;
    for (int i = 0;i < n;i++) {
        int time, cup;
        cin >> time >> cup;
        v.push_back({ time,cup });
    }

    sort(v.begin(), v.end());

    priority_queue<int, vector<int>, greater<int>> pq;

    for (int i = 0;i < n;i++) {
        pq.push(v[i].second);
        if (pq.size() > v[i].first) {
            pq.pop();
        }
    }
    int result = 0;
    while (!pq.empty()) {
        result += pq.top();
        pq.pop();
    }

    cout << result;
    return 0;
}
