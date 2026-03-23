#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<string> keymap, vector<string> targets) {
    vector<int> answer;
    vector<int> dt;
    for(int lt = 65; lt < 91; lt++) {
        int idx = 111;
        bool hit;
        for(string st : keymap){
            hit = false;
            int i = 0;
            for(char ch : st) {
                i++;
                if(i > idx) break;
                if(ch == (char)lt){
                    hit = true;
                    break;
                }
            }
            if(hit && idx > i){idx = i;}
        }
        if(idx == 111) idx = -1;
        dt.push_back(idx);
    }
    
    for(string st : targets) {
        int ans = 0;
        for(char ch : st) {
            int cnt = dt[(int)ch-65];
            if(cnt < 0){
                ans = -1;
                break;
            }
            ans += cnt;
        }
        answer.push_back(ans);
    }
    
    return answer;
}