#include <string>
#include <vector>
#include<algorithm>
using namespace std;

long long solution(vector<int> sequence) {
    long long answer = 0;
    int size = sequence.size();
    long long lis[size+1];
    vector<int> plusStart;
    vector<int> minusStart;
    
    for(int i=0;i<size;i++){
        if(i%2==0){
            plusStart.push_back(sequence[i]);
            minusStart.push_back(sequence[i]*-1);
        }
        else{
            plusStart.push_back(sequence[i]*-1);
            minusStart.push_back(sequence[i]);
            
        }
    }
    
    long long maxNum = 0;
    long long plusDp[size+1];
    long long minusDp[size+1];
    
    plusDp[0] = plusStart[0];
    minusDp[0] = minusStart[0];
    
    for(int i=0;i<size;i++){
        plusDp[i] = max(plusDp[i-1]+(long long)plusStart[i],(long long)plusStart[i]);
        answer = max(answer,plusDp[i]);
    }
    for(int i=0;i<size;i++){
        minusDp[i] = max(minusDp[i-1]+(long long)minusStart[i],(long long)minusStart[i]);
        answer = max(answer,minusDp[i]);
    }
    
    
    return answer;
}