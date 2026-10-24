import java.util.*;

class Solution {
    // 시간초과 코드
//     public int recursive (int n, long tot, int cnt, int goal, int x) {
//         if(cnt == goal){
//             tot *= 3;
//             tot += x;
//             if(n == tot) return 1;
//             else return 0;
//         }
//         int res = 0;
//         for(int i = x - (goal - cnt)*2; i >= 0; i--) {
//             tot *= 3;
//             tot += i;
//             res += recursive(n, tot, cnt+1, goal, x-i);
//             tot -= i;
//             tot /= 3;
//         }
        
//         return res;
//     }    
//     public int solution(int n) {
//         int answer =0;
//         int[][] minmax = new int[20][2];
        
//         for(int i = 1; i < 20; i++) {
//             minmax[i][0] = (int) Math.pow(3, i) + (2 * i);
//             int sum = 1;
//             if(i < 19) {
//                 for(int j = 1; j <= i; j++) {sum *= 3; sum += 2;}
//                 minmax[i][1] = sum;
//             }
//             else minmax[i][1] = Integer.MAX_VALUE;
//         }
        
        
//         int cnt = 0;
//         for(int i = 1; i < 20; i++) {
//             if(minmax[i][0] <= n && n <= minmax[i][1]) {
//                 cnt = i;
//                 break;
//             }
//             else if (n < minmax[i][0]) break;
//         }
        
//         if(cnt == 0) return 0;
        
//         // answer = recursive(n, 1, 1, cnt, 2*cnt);
//         answer = rec(n, cnt + 1, 2 * cnt);

//         return answer;
//     }
    
    // Claude.ai 답변 (주석만 추가)
    // n안에서 *가 몇 번 더 나올 수 있는 지 계산
    int log3(int n) {
        int s = 0;
        // 나눠지는 횟수 카운트
        while (n >= 3) { n /= 3; s++; }
        return s;
    }

    // 재귀함수로 숫자를 역으로 계산하며 가능한 경우 카운트
    // n : 확인할 숫자, p : 남은 '+' 갯수
    int dfs(int n, int p) {
        // n이 1까지 도달했을 때, +갯수가 0개, 즉 모든 기호를 다 제거했다면 정상 문자열, 아니라면 불가능
        if (n == 1) return p == 0 ? 1 : 0;
        // 가지치기 : 남은 * 개수와 + 갯수를 비교했을 때, 남은 *로 +를 다 제거하지 못하는 경우
        // log3(n) 은 가능한 잔여 * 갯수, * 1개당 +가 2개이므로 *2 처리,
        // p(+) 갯수가 남은 *갯수*2보다 커서 +가 남게 되면 불가능
        if (p > 2 * log3(n)) return 0;

        // 가능한 경우의 수 저장할 변수 res
        int res = 0;
        
        // n = X * 3 + pl 꼴
        // X = (n-pl) / 3 형태 (n-pl은 3의 배수) -> pl = n mod 3 => pl은 n%3, n%3+3, n%3+6...
        for (int pl = n % 3; pl < n; pl += 3) {
            // 새로운 p(np)는 현재 남은 기존 p에 분리한 pl을 더하고, *하나를 제거하기 위해 2개를 소모
            int np = p + pl - 2;
            // 만약 새로운p(np)가 음수(남아있는 p가 없어서 더 써야하는 상황)라면, 스킵
            if (np < 0) continue;
            // 새로운 nn은 pl만큼을 빼고 * 한개를 없앤 상황
            int nn = (n - pl) / 3;
            // 새로운 n(nn) 기준으로 np(+) 갯수가 남은 *갯수 * 2보다 커서 +가 남게되면 불가능
            if (np > 2 * log3(nn)) break;
            // 새로운 n과 새로운 p로 더 1이 될때까지 진행
            res += dfs(nn, np);
        }
        return res;
    }

    public int solution(int n) {
        return dfs(n, 0);
    }
}