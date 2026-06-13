class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        // 시작점 sp , 기지국 범위 r
        int sp = 1, r = 2 * w + 1;
        
        // 기지국이 설치된 위치 순회
        for(int i = 0; i < stations.length; i++) {
            // 현재 기지국 위치
            int cur_s = stations[i];
            // 기지국 전파가 닿는 가장 왼쪽 칸
            int ep = cur_s - w;
            
            // 현재 확인 지점 ~ 기지국 왼쪽칸 확인
            // 현재 칸까지 전파가 닿지 않는다면,
            if(sp < ep) {
                // 빈 칸에 필요한 기지국 수 계산
                
                // 빈 칸 수 계산 후, 기지국 범위로 나눠서 필요한 기지국 수 측정
                answer += ((ep - sp) / r);
                // 나머지가 남는 경우, 기지국 1개 추가 설치
                if((ep - sp) % r > 0) answer++;
            }
            // 시작점은 현재 기지국의 전파가 닿는 마지막칸 + 1
            sp = cur_s + w + 1;
        }
        
        // 모든 기지국을 순회한 뒤, 마지막 체크 지점이 아직 끝이 아니라면
        if(sp < n+1) {
            // 남은 구간에 필요한 기지국 수 계산
            answer += ((n+1 - sp) / r);
            if((n+1 - sp) % r > 0) answer++;
        }
        
        return answer;
    }
}