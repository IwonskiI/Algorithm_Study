#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> triangle) {
    int answer = 0;
    vector<vector<int>> sum_tri;
    for(vector<int> tri : triangle) {
        vector<int> new_tri;
        for(int t : tri){
            new_tri.push_back(t);
        }
        sum_tri.push_back(new_tri);
    }
    
    for(int i = 0; i < triangle.size()-1; i++) {
        for(int j = 0; j < triangle[i].size(); j++) {
            sum_tri[i+1][j] = max(sum_tri[i+1][j], sum_tri[i][j] + triangle[i+1][j]);
            sum_tri[i+1][j+1] = max(sum_tri[i+1][j+1], sum_tri[i][j] + triangle[i+1][j+1]);
        }
    }
    for(int f : sum_tri[triangle.size()-1]){
        answer = max(answer, f);
    }
    return answer;
}