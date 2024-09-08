#include <string>
#include <vector>
#include<algorithm>
using namespace std;

int solution(vector<int> money) {
    int answer = 0;
    
    int dp[1000001];
    //첫번째 집을 골랐을 경우
    dp[0] = money[0];
    dp[1] = money[0];
    
    for(int i=2;i<money.size()-1;i++){
        dp[i] = max (dp[i-2] + money[i], dp[i-1]);
    }
    
    int first = dp[money.size()-2];
    
    //첫번째 집을 고르지 않은 경우
    dp[0]=0;
    dp[1] = money[1];
    
    for(int i=2;i<money.size();i++){
        dp[i] = max (dp[i-2] + money[i], dp[i-1]);
    }
    
    int second = dp[money.size()-1];
    
    answer = max(first, second);
    
    
    return answer;
}