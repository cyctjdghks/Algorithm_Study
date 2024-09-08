#include <string>
#include <vector>
#include<stack>
#include<iostream>
using namespace std;

int solution(vector<int> order) {
    int answer = 0;
    stack<int> s;
    int cur=1;
    int orderCur=0;
    int n = order.size();
    while(1){
        if(cur == n+1 || orderCur ==n) break;
        
        // cout <<orderCur << " " << cur <<" "<< s.size()<<endl;
        
        if(order[orderCur] > cur){
            s.push(cur);
            cur++;
        }
        else if(order[orderCur] < cur){
            if(s.top()==order[orderCur] && !s.empty()){
                answer++;
                s.pop();
                orderCur++;
            }
            else break;
        }
        else{
            
            answer++;
            orderCur++;
            if(cur != n){
                cur++;    
            }
   
        }
        
        
    }
    return answer;
}