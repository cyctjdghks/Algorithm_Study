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
int a, b;
int meltTime;
int preCheese;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0,-1, 0, 1 };
bool isEmptyMap() {
    for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
            if (m[i][j] == 1) return false;
        }
    }
    return true;
}


void bfs() {
    //없어져야 할 치즈 개수 = preCheese
    vector<vector<bool>> isAir(a, vector<bool>(b, false));
    vector<vector<int>> meltCheese(a, vector<int>(b, 0));
    queue<pair<int, int>> q;
    int cnt = 0;
    isAir[0][0] = true;
    q.push({ 0,0 });
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < a && ny < b) {
                if (!isAir[nx][ny]) {
                    if (m[nx][ny] == 0) {
                        
                        q.push({ nx,ny });
                    }
                    else {
                        m[nx][ny] = 0;
                        cnt++;
                    }
                    isAir[nx][ny] = true;
                    
                }
            }
        }
    }
    

    if (cnt != 0) {
        preCheese = cnt;
    }
    
}
int main()
{

    cin >> a >> b;

    for (int i = 0; i < a; i++) {
        vector<int> tmp;
        for (int j = 0; j < b; j++) {
            int x;
            cin >> x;
            tmp.push_back(x);
        }
        m.push_back(tmp);
    }
    meltTime = 0;

    while (!isEmptyMap()) {
        
        meltTime++;
        preCheese = 0;
        
        bfs();

    }

    cout << meltTime << endl;
    cout << preCheese << endl;
}