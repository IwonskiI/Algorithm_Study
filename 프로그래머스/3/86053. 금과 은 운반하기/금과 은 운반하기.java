class Solution {
    
    // 시간 탐색 함수
    public boolean find (int a, int b, int[] g, int[] s, int[] w, int[] t, long mid) {
        // 현재 담은 전체 금, 은 합 양
        long tot_g = 0, tot_s = 0, tot = 0;
        
        // i개의 도시 탐색
        for(int i = 0; i < g.length; i++) {
            // 한번 왕복하는데 걸리는 시간 time, 왕복에 걸리는 시간 rt, 주어진 시간안에 왕복할 수 있는 횟수 cnt
            long time = t[i], rt = time * 2, cnt = mid / rt;
            // 왕복시간만큼 쓰고 남은 시간이 편도시간보다 크다면, 횟수 1번 추가
            if(mid % rt >= time) cnt++;
            // 이 도시의 트럭이 최대 횟수로 옮겼을 때 옮길 광물의 양
            long max = w[i] * cnt;
            
            // 이 시간안에 옮긴 금의 양 
            // (g[i]보다 max가 크다면, g[i]를 다 옮긴 시점에서 멈출 것이기 때문에 min값 추가)
            tot_g += Math.min((long)g[i], max);
            // 이 시간 안에 옮긴 은의 양 (g[i]와 마찬가지)
            tot_s += Math.min((long)s[i], max);
            // 이 시간 안에 옮긴 광물(금 + 은)의 양
            // (이 시간안에 옮길 수 있는 최대 광물의 양, 금 다 쓰면 은도 옮길 수 있고 같이도 옮길 수 있기 때문)
            tot += Math.min((long)g[i] + s[i], max);
        }
        
        // 주어진 시간안에 모든 도시에서 최대로 광물을 옮겼을 때, 금도 만족하고, 은도 만족하고, 두개 합도 만족한다면 true(성공)
        if(tot_g >= a && tot_s >= b && tot >= a + b) return true;
        // 아니면 false(실패)
        else return false;
    }
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {        
        // 변수 범위를 통해 이분탐색 문제임을 유추
        // 걸리는 시간을 이분탐색으로 줄여가며 필요한 금과 은을 모두 운반할 수 있는 최소 시간을 구함
        long answer = -1;
        // 이분탐색 범위 (시작은 0, 끝은 (2 * 10^5) * (2 * 10^9)/1) + 10^5 => 4 * 10^14 + 10^5 => 넉넉하게 10^14 * 5 (long타입 최대는 10^18)
        long st = 0, ed = (long) Math.pow(10, 14) * 5;
        // answer 변수에 최댓값 넣어놓고 줄여가며 탐색
        answer = ed;
        
        // 이분탐색 시작
        while(st <= ed) {
            // 중간 값 (시작점 + 끝점) / 2
            long mid = (st + ed) / 2;
            // 현재 중간 값으로 조건 만족이 가능하다면,
            if(find(a, b, g, s, w, t, mid) == true){
                // 중간값을 answer로 저장
                answer = Math.min(answer, mid);
                // 끝점을 현재 중간값 - 1로 변경
                ed = mid - 1;
            }
            // 불가능하다면 시작점을 현재 중간값 + 1로 변경해서 더 여유있게 시간 제공
            else st = mid + 1;
        }
        
        // 결과 리턴
        return answer;
    }
}