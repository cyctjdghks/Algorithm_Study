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
    string s;
    cin >> s;

    bool flag = true;
    for (int i = 0;i < s.size();i++) {
        if (i + 1 <= s.size() && s[i] == 'p' && s[i+1] == 'i') {
            i++;
        }
        else if(i + 1 <= s.size() && s[i] == 'k' && s[i + 1] == 'a'){
            i++;
        }
        else if (i + 2 <= s.size() && s[i] == 'c' && s[i + 1] == 'h' && s[i+2]== 'u') {
            i+=2;
        }
        else {
            flag = false;
            break;
        }
    }
	
    if (flag) cout << "YES";
    else cout << "NO";
    return 0;
}