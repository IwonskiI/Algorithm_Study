#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    int cycle = 0, cnt = 0;
    
    while(s != "1") {
        string tmp = "";
        cycle++;
        for(int i = 0; i < s.size(); i++) {
            if(s[i] == '0') cnt++;
            else tmp = s[i] + tmp;
        }
        int len = tmp.size();
        s = "";
        while(len > 0) {
            s = to_string(len%2) + s;
            len /= 2;
        }
    }
    
    answer = {cycle, cnt};
    return answer;
}