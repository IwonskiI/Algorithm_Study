import java.io.*;
import java.util.*;

public class Main {

    // BOJ 1799 - 비숍
    public static int N, w_ans, b_ans;
    public static int[][] board;
    
    // 현재 위치에 비숍을 놓을 수 있는지 확인
    public static boolean test(int r, int c) {
    	// 위에서부터 탐색하고 내려오기 때문에 위쪽 대각선만 확인하면 됨
        int[][] d = {{-1, -1}, {-1, 1}};
        
        // 좌상단 대각, 우상단 대각에 비숍이 있는지 확인
        for(int dd = 0; dd < 2; dd++) {
        	// 대각 좌표 계산
            int nr = r, nc = c;
            // 범위를 벗어나거나 비숍을 찾을 때 까지 반복
            while(true) {
                nr += d[dd][0];
                nc += d[dd][1];
                boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
                // 범위 밖이면 반대 대각 확인을 위해 종료
                if(!in_range) break;
                // 비숍을 발견하면 그 칸에는 비숍을 놓을 수 없으므로 false return
                if(board[nr][nc] == 2) return false;
            }
        }
        // 비숍을 놓을 수 있다면 true return
        return true;
    }
    
    // 비숍을 놓을 수 있는 자리에 백트래킹
    // 흰 칸 먼저 계산
    public static void w_count(int idx, int cnt) {
    	// 현재 좌표의 인덱스
        int i = idx;
        // 한칸씩 스킵하면서 확인할 것이기 때문에 i+2 증가
        for(; i < N*N; i+=2) {
            int r = i / N, c = i % N;
            // N이 짝수 칸이라면 흰검 칸이 엇갈리게 나오지 않음 -> +1 해주면 됨
            if(N%2 == 0 && r%2 == 1) c++;
            // 비숍을 놓을 수 없는 칸이라면 스킵
            if(board[r][c] == 0) continue;
            // 비숍을 놓을 수 있다면
            if(test(r, c)) {
            	// 비숍을 놓고 다음 칸부터 재 탐색
                board[r][c] = 2;
                w_count(i+2, cnt + 1);
                // 탐색이 끝나면 비숍 제거
                board[r][c] = 1;
            }
        }
        
        // 마지막 칸까지 탐색을 완료하면 현재 놓은 비숍의 수와 기존 최댓값을 비교 후 갱신
        if(i >= N*N) {
            w_ans = Math.max(w_ans, cnt);
            return;
        }
    }
    
    // 검은 칸도 동일하게 진행
    public static void b_count(int idx, int cnt) {
        int i = idx;
        for(; i < N*N; i+=2) {
            int r = i / N, c = i % N;
            // 검은 칸은 기존 칸에서 +1한 값이므로 +1 증가해주면 범위를 벗어날 수 있다.
            // c--로 원래 칸으로 설정
            // 이외에는 동일한 로직 적용
            if(N%2 == 0 && r%2 == 1) c--;
            if(board[r][c] == 0) continue;
            if(test(r, c)) {
                board[r][c] = 2;
                b_count(i+2, cnt + 1);
                board[r][c] = 1;
            }
        }
        
        if(i >= N*N) {
            b_ans = Math.max(b_ans, cnt);
            return;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        
        // 보드판 초기 입력
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 비숍 수 구하기
        // 체스판은 검은색과 흰색이 번갈아가면서 나오고, 검은 칸의 비숍과 흰칸의 비숍은 서로 영향을 주지 않음
        // 두 경우를 따로 계산해서 합해서 최대 비숍의 수 계산
        w_count(0, 0);
        b_count(1, 0);
        
        // 탐색 종료 후 정답 출력
        System.out.println(w_ans + b_ans);
        
    }

}