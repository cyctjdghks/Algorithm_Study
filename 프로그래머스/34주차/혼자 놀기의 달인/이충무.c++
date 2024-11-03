#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int n, m, ans = 11;
vector<vector<int>> beginning, target;

void flip_row(int r) {
    for (int j = 0; j < m; j++)
        beginning[r][j] = !beginning[r][j];
}

int compare_colunm(int c) {
    int cnt = 0;
    for (int i = 0; i < n; i++)
        if (beginning[i][c] == target[i][c]) cnt++;

    if (cnt == 0) return 1;
    if (cnt == n) return 0;
    else return -1;
    return true;
}

void dfs(int r, int cnt) {
    if (r == n) {
        int flag = 1;
        for (int j = 0; j < m; j++) {
            int ret = compare_colunm(j);
            if (ret == -1) { flag = 0; break; } 
            cnt += ret;
        }
        if (flag) ans = min(ans, cnt);
        return;
    }
    if (r != n) {
        dfs(r + 1, cnt);
        flip_row(r);
        dfs(r + 1, cnt + 1); 
        flip_row(r);
    }

}

int solution(vector<vector<int>> _beginning, vector<vector<int>> _target) {
    beginning = _beginning; target = _target;
    n = beginning.size(); m = beginning[0].size();
    dfs(0, 0);
    if (ans == 11) return -1;
    return ans;
}