#include <bits/stdc++.h>

using namespace std;

string solution(string s) {
    string answer = "";
    stringstream ss(s);
    string token;
    
    while(getline(ss, token, ' ')) {
        if(token.size() == 0) answer += " ";
        else{
            if((int)'a' <= (int)token[0] && (int)token[0] <= (int)'z')
                token[0] = (char)(token[0] - 'a' + 'A');
            for(int i = 1; i < token.size(); i++) {
                if((int)'A' <= (int)token[i] && (int)token[i] <= (int)'Z')
                    token[i] = (char)(token[i] - 'A' + 'a');
            }
            answer += token + " ";
        }
    }
    if(token != "") answer.replace(answer.size()-1, 1, "");
    
    return answer;
}