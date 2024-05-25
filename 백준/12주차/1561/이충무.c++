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
int main()
{
    ll n, m;
    cin >> n >> m;
    vector<ll> v;
    if (n <= m) {
        cout << n;
        return 0;
    }
    for (int i = 0; i < m; i++) {
        int tmp;
        cin >> tmp;
        v.push_back(tmp);
    }
    ll l = 0;
    ll h = n * 30;
    ll time;
    ll resultTime = 0;;
    ll cnt = 0;
    while (l <= h) {
        time = (l + h) / 2;
        cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += time / v[i] + 1;
        }
        if (cnt >= n) {
            resultTime = time;
            h = time - 1;
        }
        else l = time + 1;

    }

    cnt = 0;
    for (int i = 0; i < m; i++) {
        cnt += (resultTime - 1) / v[i] + 1;
    }

    for (int i = 0; i < m; i++) {
        if (resultTime % v[i] == 0) cnt++;
        if (cnt == n) {
            cout << i + 1;
            break;
        }
    }

}
