#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    for(int i = 1; i <= n; i++){
        int temp = 0, cnt = i;
        while(temp < n) {
            temp += cnt;
            cnt++;
        }
        if(temp == n) answer++;
    }
    return answer;
}