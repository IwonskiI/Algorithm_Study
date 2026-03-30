#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;

    queue<int> days;
    
    for(int i = 0; i < progresses.size(); i++) {
        int left = (100 - progresses[i]) / speeds[i];
        if((100 - progresses[i]) % speeds[i] > 0) left++;
        days.push(left);
    }
    
    int cnt = 1, prev = days.front();
    days.pop();
    while(!days.empty()) {
        if(days.front() <= prev) cnt++;
        else{
            answer.push_back(cnt);
            cnt = 1;
            prev = days.front();
        }
        days.pop();
    }
    
    if(cnt > 0) answer.push_back(cnt);
    
    return answer;
}