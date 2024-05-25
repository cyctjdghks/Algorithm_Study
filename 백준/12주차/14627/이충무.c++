#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;
bool check(vector<int> v,long long mid,int c) {
    long long cnt = 0;
    for (int i = 0; i < v.size(); i++) {
        cnt += v[i] / mid;
    }
    if (cnt >= c) return true;
    else return false;
}
int main()
{
    int s, c;
    cin >> s >> c;
    vector<int> v;
    long long sum = 0;
    for (int i = 0; i < s; i++) {
        int tmp;
        cin >> tmp;
        sum += (long long)tmp;
        v.push_back(tmp);
    }
    long long start = 1;
    long long end = 1000000000;
    long long result = 0;

    while (start <= end) {
        long long mid = (start + end) / 2;
        if (check(v,mid,c)) {
            start = mid + 1;
            result = mid;
        }
        else {
            end = mid - 1;
        }
    }

    long long resultSum = sum-(result*(long long)c);
    
    
    cout << resultSum;

}
