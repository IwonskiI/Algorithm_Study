#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

map<string, int> comb_map;

void comb(string order, int cnt, string tmp) {
    if(tmp.size() > order.size()) {
        return;
    }
    comb_map[tmp]++;
    for(int i = cnt; i < order.size(); i++) {
        comb(order, i+1, tmp+order[i]);
    }
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    
    for(string order : orders) {
        sort(order.begin(), order.end());
        comb(order, 0, "");
    }
    
    for(int cnt: course) {
        int max_order = 0;
        for(pair<string,int> comb : comb_map) {
            if(comb.first.size() == cnt) max_order = max(max_order, comb.second);
        }
        if(max_order <= 1) continue;
        for(pair<string,int> comb : comb_map) {
            if(comb.first.size() == cnt && comb.second == max_order) answer.push_back(comb.first);
        }
    }
    sort(answer.begin(), answer.end());
    return answer;
}