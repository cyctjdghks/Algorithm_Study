#include <string>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <set>

using namespace std;

struct Jewelry {
    string name; 
    set<int> positions;
    int size; 
};

bool cmp(const Jewelry& a, const Jewelry& b) {
    if (a.size == b.size)
        return *(a.positions.begin()) < *(b.positions.begin()); 
    return a.size < b.size; 
}

vector<int> solution(vector<string> gems) {
    vector<int> answer;
    unordered_map<string, set<int>> kindOfJew;
    vector<Jewelry> sortedJew;

    for (int i = 0; i < gems.size(); ++i)
        kindOfJew[gems[i]].insert(i);

    for (auto& jew : kindOfJew){
        int n = jew.second.size();
        sortedJew.push_back({jew.first, jew.second, n});
    }
    sort(sortedJew.begin(), sortedJew.end(), cmp);

    for (int i = sortedJew.size(); i <= gems.size(); ++i) {
        for (int j = 0; j < sortedJew.size(); ++j) {
            for (auto& pos : sortedJew[j].positions) {
                int firstStart = pos - i + 1 < 0 ? 0 : pos - i + 1;
                int lastStart = pos + i - 1 >= gems.size() ? gems.size() - i : pos;
                for (int k = firstStart; k <= lastStart; ++k) {
                    bool flag = true;
                    for (int m = 0; m < sortedJew.size(); ++m) {
                        if (find(gems.begin() + k, gems.begin() + k + i, sortedJew[m].name) == gems.begin() + k + i) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        answer.push_back(k + 1);
                        answer.push_back(k + i - 1 + 1);
                        return answer;
                    }
                }
            }
        }
    }

    return answer;
}