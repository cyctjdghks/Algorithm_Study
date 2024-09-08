#include <string>
#include <vector>
#include<iostream>

using namespace std;

int solution(int n, vector<int> tops) {
    int answer = 0;
    int dp[tops.size()*2+1];
    
    dp[0]=1;
    dp[1]=2;
    if(tops[0] == 1) dp[1]=3;
    for(int i = 2; i<=2*n; i++){
        //1 0 3 1 5 2
        if(i%2 == 1 && tops[(i-1)/2] == 1){
            dp[i] = dp[i-1] + dp[i-1] + dp[i-2];
        }
        else{
            dp[i] = dp[i-1] + dp[i-2];
        }
        dp[i] %= 10007;
    }  
    answer= dp[2*n];
    return answer;
}