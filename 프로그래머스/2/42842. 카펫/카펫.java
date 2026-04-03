class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};
        
        int mult = (brown - 4) / 2;
        int i = 0;
        
        for(i = 1; i < mult; i++) {
            if(i * (mult - i) == yellow) break;
        }
        answer = new int[] {(mult - i)+2, i+2};
        
        return answer;
    }
}