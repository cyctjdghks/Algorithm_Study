#include <string>
#include <vector>
#include<map>
#include<iostream>
using namespace std;
map<int,int> m;

int solution(vector<int> elements) {
    int answer = 0;
    
    for(int i=1;i<=elements.size();i++){
        int cnt = i;
        for(int j=0;j<elements.size();j++){
            int sum=0;
            for(int k=0;k<i;k++){
                sum+=elements[(j+k)%elements.size()];
            }
            m[sum]++;
        }
    }
    answer = m.size();
    return answer;
}