#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

int solution(int n) {
    int answer = 0;
    string bin = "";
    while(n > 0){
        int bit = n % 2;
        n /= 2;
        bin = to_string(bit) + bin;
    }
    int pos = 0;
    int one_cnt = 0;
    for(int i = 1; i < bin.size(); i++){
        if(bin[i-1] == '0' && bin[i] == '1') {
            pos = i;
            one_cnt = 0;
        }
        else if(bin[i] == '1') one_cnt++;
    }
    int left = bin.size()-pos;
    int zero_cnt = left - one_cnt;
    if(pos == 0) {
        bin = "0" + bin;
        pos = 1;
    }
    char temp = bin[pos];bin[pos] = bin[pos-1];bin[pos-1] = temp;
    string tmp = "";
    for(int i = 0; i < pos; i++) {tmp += bin[i];}
    for(int i = 0; i < zero_cnt; i++){tmp += "0";}
    for(int i = 0; i < one_cnt; i++){tmp += "1";}

    
    answer = stoi(tmp, nullptr, 2);
    
    return answer;
}