#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int answer = 0;
    int w = 0, h = 0;
    for(vector<int> size : sizes) {
        if(size[0] > size[1]){
            int temp = size[0];
            size[0] = size[1];
            size[1] = temp;
        }
        if(w < size[0]) w = size[0];
        if(h < size[1]) h = size[1];
    }
    
    answer = w * h;
    
    return answer;
}