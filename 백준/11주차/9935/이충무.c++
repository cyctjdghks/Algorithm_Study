#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include<stack>
using namespace std;

int main() {

    string str;
    string bomb;

    cin >> str;
    cin >> bomb;

    string tmp = "";
    for (int i = 0;i < str.size();i++) {

        bool flag = true;
        tmp += str[i];
        if (tmp.length() < bomb.size())  continue;
        for (int j = 0;j < bomb.size();j++) {
            if (tmp[tmp.size() - 1 - j] != bomb[bomb.size() - 1 - j]) {
                flag = false;
                break;
            }
        }

        if (flag)  tmp.erase(tmp.end() - bomb.size(), tmp.end());

    }


    if (tmp.size() == 0) {
        cout << "FRULA";
    }
    else {
        cout << tmp;
    }

    return 0;
}
