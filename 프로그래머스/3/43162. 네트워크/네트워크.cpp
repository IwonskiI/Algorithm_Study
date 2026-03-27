#include <bits/stdc++.h>

using namespace std;

bool visited[201];

void dfs(vector<vector<int>>& v, int x, int n) {
    visited[x] = true;
    for(int i = 0; i < n; i++){
        if(v[x][i] == 1 && !visited[i])
            dfs(v, i, n);
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    
    for(int i = 0; i < n; i++) {
        if(!visited[i]){
            dfs(computers, i, n);
            answer++;
        }       
    }
    
    return answer;
}