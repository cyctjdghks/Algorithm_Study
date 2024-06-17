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
int resultA;
int resultB;
int goalA;
int goalB;
void check(int time, vector<pair<int, int>> v) {
    for (int i = 0; i < v.size(); i++) {
        if (time == v[i].second && v[i].first == 1) {
            goalA++;
        }
        if (time == v[i].second && v[i].first == 2) {
            goalB++;
        }
    }
}
string timeToString(int time) {
    string tenHour = to_string(time / (60 * 10));
    time = time % 600;
    string hour = to_string(time / 60);
    time = time % 60;
    string tenSecond = to_string(time / 10);
    time = time % 10;
    string second = to_string(time);
    //cout << tenHour << " " << hour << " " << tenSecond << " " << second << endl;
    return tenHour + hour + ":" + tenSecond + second;
}
int main()
{
    int n;
    cin >> n;
    int maxTime = 60 * 48;
    vector<pair<int, int>> v;
    resultA = 0;
    resultB = 0;
    goalA = 0;
    goalB = 0;
    for (int i = 0; i < n; i++) {
        int x;
        string time;
        cin >> x >> time;
        int timetoInt = (time[0] - '0') * 10 * 60 + (time[1] - '0') * 60 + (time[3] - '0') * 10 + (time[4] - '0');
        v.push_back({ x,timetoInt });
    }

    
    
    for (int i = 0; i < maxTime; i++) {
        check(i,v);

        if (goalA > goalB) resultA++;
        if (goalA < goalB) resultB++;
    }

    

    string a = timeToString(resultA);
    string b = timeToString(resultB);

    cout << a << endl;
    cout << b << endl;
    
}
