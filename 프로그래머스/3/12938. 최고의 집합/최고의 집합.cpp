#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
    vector<int> answer;
    int avg = s / n;
    int left = s % n;
    if(avg <= 0) return {-1};
    for(int i = 0; i < n - left; i++) {
        answer.push_back(avg);
    }
    for(int i = 0; i < left; i++) {
        answer.push_back(avg+1);
    }
    return answer;
}