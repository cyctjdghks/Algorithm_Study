#include <iostream>
#include <string>
#include<vector>
#include<cmath>
#include<algorithm>
#include<stack>
#include<map>
#include<queue>
using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> v;

    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        v.push_back(tmp);
    }
    vector<int> lis(n, 0);
    int idx = 0;
    int max = 0;
    for (int i = 0; i < n; i++) {
        lis[i] = 1;
        vector<int> arr;
        for (int j = 0; j < i; j++) {
            if (v[j] < v[i] && lis[i] < lis[j] + 1) {
                lis[i] = lis[j] + 1;
            }
        }
        if (max < lis[i]) { 
            max = lis[i]; 
            idx = i;
        }
    }
    vector<int> result;
    int tmp = max;
    for (int i = idx; i >= 0; i--) {
        if (lis[i] == tmp) {
            result.push_back(v[i]);
            tmp--;
        }
    }

    cout << max << endl;

    for (int i = result.size() - 1; i >= 0; i--) {
        cout << result[i] << " ";
    }
    

}
