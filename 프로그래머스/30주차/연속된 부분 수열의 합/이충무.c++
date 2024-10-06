#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> sequence, int k) {
    vector<int> answer;
    int end = sequence.size()-1;
    int start = sequence.size()-1;
    int sum = sequence[sequence.size()-1];
    // int resultStart = -1;
    // int resultEnd = -1;
    // bool flag=false;
    while(end >= 0){
        if(sum < k){
            start--;
            sum+=sequence[start];
        }
        if(sum == k){
            
            while(1){
                sum-=sequence[end];
                
                end--;
                start--;
                sum+=sequence[start];
                if(sum != k || start<0){
                    start++;
                    end++;
                    break;
                }
            }

            break;
        }
        if(sum > k){
            sum-=sequence[end];
            if(end <= start){
                end--;
                start--;
                sum+=sequence[end];
            }
            else{
                end--;
            } 
                
            
        }
    }
    
    
    
    answer.push_back(start);
    answer.push_back(end);
    return answer;
}