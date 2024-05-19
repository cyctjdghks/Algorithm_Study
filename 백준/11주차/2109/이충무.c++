#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<pair<int, int>> v;

    for (int i = 0;i < n;i++) {
        int d, p;
        cin >> p >> d;
        v.push_back(make_pair(d, p));
    }
    
    sort(v.begin(), v.end());

    priority_queue<int, vector<int>, greater<int>> pq;
    int result = 0;
    
    for (int i = 0;i < n;i++) {
        pq.push(v[i].second);
        int tmp = v[i].second;

        if (pq.size() > v[i].first) {
            pq.pop();
        }
    }

    while (!pq.empty()) {
        result += pq.top();
        pq.pop();
    }

    cout << result;

    return 0;
}
