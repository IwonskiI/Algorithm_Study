#include <string>
#include <vector>
#include <iostream>
#include <sstream>

using namespace std;

string solution(string s) {
    string answer = "";
    stringstream ss(s);
    string token;
    bool first = true;
    int min, max;
    
    while(getline(ss, token, ' ')) {
        int n = stoi(token);
        if(first) {
            min = n;
            max = n;
            first = false;
        }
        else{
            if(n > max)max = n;
            if(n < min)min = n;
        }
    }
    
    answer = to_string(min) + " " + to_string(max);
    return answer;
}