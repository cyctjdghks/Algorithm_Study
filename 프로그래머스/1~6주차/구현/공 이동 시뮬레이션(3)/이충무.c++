#include <string>
#include <vector>
#include<algorithm>
#include<iostream>

using namespace std;


long long solution(int n, int m, int x, int y, vector<vector<int>> queries) {
    long long answer = -1;
        
    long long r1 = x;
    long long r2 = x;
    long long c1 = y;
    long long c2 = y; 

    for(int i=queries.size()-1; i>=0; i--){
        long long dir = queries[i][0];
        long long cnt = queries[i][1];

        if(dir==0){ 
            if(c1!=0) c1 += cnt;
            c2 = min(c2+cnt,(long long) m-1);
            if(c1>m-1) return 0;
        }
        else if(dir==1) {
            if(c2!=m-1) c2 -= cnt;
            c1 = max(c1-cnt, (long long)0);
            if(c2<0) return 0;
        }
        else if(dir==2){
            if(r1!=0) r1 += cnt;
            r2 = min(r2+cnt, (long long)n-1);
            if(r1>n-1) return 0;
        }
        else{
            if(r2!=n-1) r2 -= cnt;
            r1 = max(r1-cnt, (long long)0);
            if(r2<0) return 0;
        }
    }

    answer = (r2-r1+1)*(c2-c1+1);

    return answer;
}