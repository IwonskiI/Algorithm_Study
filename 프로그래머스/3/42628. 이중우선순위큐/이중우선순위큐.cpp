#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<string> operations) {
    set<int> s;
    for (string op : operations) {
        if (op[0] != 'D') s.insert(stoi(op.substr(2)));
        else {
            if (s.empty()) continue;
            if (op[2] == '1') s.erase(*s.rbegin());
            else s.erase(*s.begin());
        }
    }

    if (s.empty()) return {0, 0};

    return {*s.rbegin(), *s.begin()};
}