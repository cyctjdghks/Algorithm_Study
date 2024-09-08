#include <string>
#include <vector>
#include<sstream>
#include<iostream>
#include<algorithm>
using namespace std;
struct mu{
    int t;
    string name;
    string xx;
};
int totime(string a, string a2){
    int at = a[0]*10*60 + a[1]*60 + a[3]*10 + a[4];
    int at2 = a2[0]*10*60 + a2[1]*60 + a2[3]*10 + a2[4];
    
    return at2-at;
}
vector<string> split(string input, char delimiter) {
    vector<string> result;
    stringstream ss(input);
    string temp;

    while (getline(ss, temp, delimiter)) {
        result.push_back(temp);
    }

    return result;
}
string resizex(string s,int time){
    
    string temp="";
    int cnt=0;
    for(int i=0;i<s.size();i++){
        if(s[i] == '#') cnt++;
    }
    int real = s.size()-cnt;
    int count = time/real;
    int remain = time%real;
    if(count != 0){
        for(int i=0;i<count;i++){
            temp+=s;
        }
        
        for(int i=0;i<remain;i++){
            if(s[i] == '#'){
                temp+=s[i];
                remain++;
            }
            else temp+=s[i];
        }
        if(s[remain] == '#') temp+='#';
    }
    
    else{
        for(int i=0;i<time;i++){
            if(s[i] == '#'){
                temp+=s[i];
                time++;
            }
            else temp+=s[i];
        }
        if(s[time] == '#') temp+='#';
    }   
    
    
    
    return temp;
}
bool cmp(mu a,mu b){
    return a.t > b.t;
}
bool check(string ss,string s){
    string tmp="";
    int cnt=0;
    while(1){
        if(ss.find(s,cnt) != string::npos){
            
            int index = ss.find(s,cnt);
            if(ss[index+s.size()] != '#'){
                return true;
            }
            else{
                
                cnt+=index+s.size();    

            }
        }
        else break;
    }
    return false;
}
string solution(string m, vector<string> mi) {
    string answer = "";
    
    vector<mu> v;
    vector<mu> v2;
    for(int i=0;i<mi.size();i++){
        vector<string> aa;
        aa = split(mi[i],',');
        int time = totime(aa[0],aa[1]);
        mu mm;
        mm.t = time;
        mm.name = aa[2];
        mm.xx = resizex(aa[3],time);
        v.push_back(mm);
    }
    for(int i=0;i<v.size();i++){
        if(check(v[i].xx,m)) v2.push_back(v[i]);
    }
    
    sort(v2.begin(),v2.end(),cmp);
    
    
    if(v2.size()==0){
        return "(None)";
    }
    else {
        return v2[0].name;
    }
}