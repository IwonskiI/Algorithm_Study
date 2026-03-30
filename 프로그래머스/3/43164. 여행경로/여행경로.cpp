#include <bits/stdc++.h>

using namespace std;

map<string, set<pair<string, int>>> route;
vector<bool> visited;
int total_cnt;

void dfs(vector<string>& answer, string cur, int cur_len) {
    if(cur_len == total_cnt+1) return;
    
    answer.push_back(cur);
    set<pair<string, int>> cur_routes = route[cur];
    for(pair<string, int> cur_route : cur_routes) {
        if(!visited[cur_route.second]){
            visited[cur_route.second] = true;
            dfs(answer, cur_route.first, cur_len+1);
            if(answer.size() == total_cnt+1) return;
            visited[cur_route.second] = false;
        }
    }
    if(answer.size() == total_cnt+1) return;
    answer.pop_back();
    
    
    
}

vector<string> solution(vector<vector<string>> tickets) {
    vector<string> answer;
    int idx = 0;
    
    for(vector<string> ticket : tickets) {
        string dep = ticket[0], arv = ticket[1];
        route[dep].insert(make_pair(arv, idx));
        idx++;
    }
    total_cnt = idx;
    visited.assign(total_cnt, false);
    
    dfs(answer, "ICN", 0);
    
    
    
    return answer;
}