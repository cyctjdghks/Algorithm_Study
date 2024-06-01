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
double ap;
double bp;
double ap2;
double bp2;
double dp[20][20][20];
bool isPrime(int n) {
    if (n == 0 || n == 1) return false;
    for (int i = 2; i <= sqrt(n); i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}
double calc(int time, int ag,int bg) {
    if (time == 18) {
        if (isPrime(ag) || isPrime(bg)) return 1;
        else return 0;
    }
    if (dp[time][ag][bg]) return dp[time][ag][bg];
    dp[time][ag][bg] += ap * bp * (calc(time + 1, ag + 1, bg + 1));
    dp[time][ag][bg] += ap2 * bp * (calc(time + 1, ag, bg + 1));
    dp[time][ag][bg] += ap * bp2 * (calc(time + 1, ag + 1, bg));
    dp[time][ag][bg] += ap2 * bp2 * (calc(time + 1, ag, bg));


    return dp[time][ag][bg];
}
int main()
{
    int a;
    int b;

    cin >> a >> b;
    
    ap = a * 0.01;
    bp = b * 0.01;
    ap2 = 1 - ap;
    bp2 = 1 - bp;
    cout << calc(0,0,0);

}
