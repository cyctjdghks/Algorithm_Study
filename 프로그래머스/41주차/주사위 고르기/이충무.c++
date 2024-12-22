#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int targetDiceNum;
vector<vector<int>> realDice;
vector<int> DiceListA;
vector<int> DiceListB;
int maxWinNum;
vector<int> winA;

void makeList(vector<int> v,bool isA, int deep, int Num) 
{
    if (deep==v.size())
    {
        if (isA)
        {
            DiceListA.push_back(Num);
        }
        else
        {
            DiceListB.push_back(Num);
        }
        return;    
    }
    else
    {
        for (int i=0; i<6; i++)
        {
            makeList(v, isA, deep+1,Num+realDice[v[deep]][i]);
        }
    }
}

void calc(vector<int> av,vector<int> bv)
{
    DiceListA=vector<int>(0);
    DiceListB=vector<int>(0);
    makeList(av, true, 0, 0);
    makeList(bv, false, 0, 0);
    sort(DiceListA.begin(),DiceListA.end());
    sort(DiceListB.begin(),DiceListB.end());
    int aIdx=0, bIdx=0;
    int winNum=0;
    while (aIdx<DiceListA.size()&&bIdx<DiceListB.size()) //주사위 합 비교
    {
        if (DiceListA[aIdx]>DiceListB[bIdx])
        {
            bIdx+=1;
        }
        else
        {    
            winNum+=bIdx;
            aIdx+=1;
        }
    }    
    if (bIdx==DiceListB.size())
    {
        winNum+=bIdx*(DiceListA.size()-aIdx);
    }
    if (winNum>maxWinNum)
    {
        maxWinNum=winNum;
        winA=av;
    }
}

void combi(vector<int> av,vector<int> bv, int nowD)
{
    vector<int> tD;
    if (av.size()==targetDiceNum)
    {
        tD=bv;
        for (int i=nowD; i<realDice.size(); i++)
        {
            tD.push_back(i);
        }
        calc(av,tD);
    }
    else if (bv.size()==targetDiceNum)
    {
        tD=av;
        for (int i=nowD; i<realDice.size(); i++)
        {
            tD.push_back(i);
        }
        calc(tD,bv);
    }
    else
    {
        tD=av;
        tD.push_back(nowD);
        combi(tD, bv, nowD+1);
        tD=bv;
        tD.push_back(nowD);
        combi(av, tD, nowD+1);
    }
    return;
}

vector<int> solution(vector<vector<int>> dice) {
    realDice=dice;
    targetDiceNum=dice.size()/2;
    combi(vector<int>(0),vector<int>(0), 0);
    for (int i=0; i<winA.size(); i++)
    {
        winA[i]+=1;
    }
    return winA;
}