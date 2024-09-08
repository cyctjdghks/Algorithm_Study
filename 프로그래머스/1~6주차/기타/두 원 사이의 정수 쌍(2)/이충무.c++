#include <string>
#include <vector>
#include<cmath>
#include<iostream>
using namespace std;

long long solution(int r1, int r2) {
    long long answer = 0;
    
    for(int x=1;x<=r2;x++){
        long long max_y=0;
        long long min_y=0;
        
        max_y=floor(sqrt(r2*1ll*r2-x*1ll*x));    
        min_y=ceil(sqrt(r1*1ll*r1-x*1ll*x));

        answer+=(max_y-min_y+1);
    }
    answer*=4;
    return answer;
}