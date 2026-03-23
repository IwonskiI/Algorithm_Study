#include <bits/stdc++.h>

using namespace std;

bool solution(string s)
{
    bool answer = true;
    stack<int> st;
    
    for(char sl : s) {
        if(sl == '(') st.push(1);
        else{
            if(!st.empty()) st.pop();
            else return false;
        }
    }
    
    if(!st.empty()) return false;
    return answer;
}