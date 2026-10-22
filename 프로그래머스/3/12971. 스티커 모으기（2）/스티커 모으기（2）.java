class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int N = sticker.length;
        // 최댓값 관리 dp 배열
        int[] dp1 = new int[N], dp2 = new int[N];
        
        // 직전 스티커 선택 여부 확인
        boolean[] dp_chk1 = new boolean[N], dp_chk2 = new boolean[N];
        
        if(N==1) return sticker[0];
        
        // 첫번째 스티커를 선택했다는 가정의 dp 배열
        dp1[0] = sticker[0];
        dp1[1] = dp1[0];
        dp_chk1[0] = true;
        dp_chk1[1] = false;
        
        // 첫번째 스티커를 선택하지 않았다는 가정의 dp 배열
        dp2[0] = 0;
        dp2[1] = sticker[1];
        dp_chk2[0] = false;
        dp_chk2[1] = true;
        
        // 스티커 순회
        for(int i = 2; i < N; i++) {
            // 마지막 스티커가 아니면서, 직전 스티커가 선택되었다면,
            if(dp_chk1[i-1] && i < N-1) {
                // 현재 최댓값은 (직전스티커를 선택하지 않은 값 + 현재스티커) 와 직전스티커를 선택한 값을 비교해서 큰 값
                dp1[i] = Math.max(dp1[i-2]+sticker[i], dp1[i-1]);
                // 현재 스티커를 선택한 경우가 더 크면 true 체크
                if(dp1[i-2]+sticker[i] > dp1[i-1]) dp_chk1[i] = true;
            }
            // 마지막 스티커가 아니면서, 직전 스티커가 선택되지 않았다면,
            else if (i < N-1) {
                // 현재 스티커를 선택
                dp1[i] = dp1[i-1]+sticker[i];
                // 현재 스티커 선택 true 체크
                dp_chk1[i] = true;
            }
            // 마지막 스티커는 반드시 선택 불가능 (첫번째 스티커를 선택했기 때문)
            
            // 직전 스티커가 선택되었다면,
            if(dp_chk2[i-1]) {
                // 현재 최댓값은 (직전스티커를 선택하지 않은 값 + 현재스티커) 와 직전스티커를 선택한 값을 비교해서 큰 값
                dp2[i] = Math.max(dp2[i-2]+sticker[i], dp2[i-1]);
                // 현재 스티커를 선택한 경우가 더 크면 true 체크
                if(dp2[i-2]+sticker[i] > dp2[i-1]) dp_chk2[i] = true;
            }
            // 직전 스티커가 선택되지 않았다면,
            else {
                // 현재 스티커를 선택
                dp2[i] = dp2[i-1]+sticker[i];
                // 현재 스티커 선택 true 체크
                dp_chk2[i] = true;
            }
        }
        
        // 두 배열의 마지막 값중에서 더 큰 값이 정답 (dp1에는 마지막 값이 들어가지 않기 때문에 N-2)
        answer = Math.max(dp1[N-2], dp2[N-1]);
        return answer;
    }
}