#include <string>
#include <vector>
#include<algorithm>
#include<unordered_map>
#include<iostream>
using namespace std;

int solution(int k, vector<int> tangerine) {
    int answer = 0;
    unordered_map<int,int> h;
    
    for(int i=0;i<tangerine.size();i++){
        h[tangerine[i]]++;
    }
    vector<int> v;
    for(auto p : h){
        v.push_back(p.second);
    }
    
    sort(v.begin(),v.end(),greater<int>());
    int cnt=k;
    int idx=0;
    while(cnt > 0){
        cnt-=v[idx];
        idx++;
        answer++;
    }
    
    
    
    return answer;
}