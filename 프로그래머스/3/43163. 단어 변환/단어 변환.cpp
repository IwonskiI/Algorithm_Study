#include <bits/stdc++.h>

using namespace std;

bool canTransform(const string& a, const string& b) {
    int diffCount = 0;
    for (size_t i = 0; i < a.length(); i++) {
        if (a[i] != b[i]) {
            diffCount++;
        }
    }
    return diffCount == 1;
}

int solution(string begin, string target, vector<string> words) {
    if (find(words.begin(), words.end(), target) == words.end()) {
        return 0;
    }

    queue<pair<string, int>> q;
    vector<bool> visited(words.size(), false);

    q.push({begin, 0});

    while (!q.empty()) {
        string currentWord = q.front().first;
        int currentSteps = q.front().second;
        q.pop();

        if (currentWord == target) {
            return currentSteps;
        }

        for (size_t i = 0; i < words.size(); i++) {
            if (!visited[i] && canTransform(currentWord, words[i])) {
                visited[i] = true;
                q.push({words[i], currentSteps + 1});
            }
        }
    }

    return 0;
}