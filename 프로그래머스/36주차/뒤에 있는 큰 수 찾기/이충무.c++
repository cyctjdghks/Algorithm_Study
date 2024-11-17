#include <string>
#include <vector>
#include<algorithm>
#include<stack>
using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer;
    stack<int> s;
    int maxValue=-1;
    for(int i=numbers.size()-1;i>=0;i--){
        if(s.size()==0) answer.push_back(-1);
        else{
            while(!s.empty()){
                if(s.top() > numbers[i]){
                    answer.push_back(s.top());
                    break; 
                }
                s.pop();
            }
            if(s.empty()){
                answer.push_back(-1);
            }
            
        }
        s.push(numbers[i]);
        
        
    }
    reverse(answer.begin(),answer.end());
    return answer;
}