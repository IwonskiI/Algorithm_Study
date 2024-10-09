import java.io.*;
import java.util.*;
 
public class Main {
 
    // SWEA 6109 - 추억의 2048 게임
	public static int N, ans;
	public static int[][] board, n_board;
	
	public static void move(int dir) {
		n_board = new int[N][N];
		switch(dir) {
        // 위로 이동
        case 0:
            // 각 행단위로 체크
            for(int c = 0; c < N; c++) {
                // 이전 수 저장
                int prev = -1;
                // 새로운 배열의 인덱스
                int cnt = 0;
                // 각 열을 순회
                for(int r = 0; r < N; r++) {
                    // 해당 열이 0이면 스킵
                    if(board[r][c] == 0) continue;
                    // 이전 값이 -1이면(빈 값이면) 현재 값을 설정 후 진행
                    if(prev == -1) prev = board[r][c];
                    // 이전 값이 있다면
                    else {
                        // 현재 값과 동일하다면
                        if(prev == board[r][c]) {
                            // 두 값을 더해서 현재 인덱스에 입력 후 인덱스 증가
                            n_board[cnt++][c] = prev * 2;
                            // 이전 값은 초기화
                            prev = -1;
                        }
                        // 현재 값과 다르다면
                        else {
                            // 이전 값을 현재 인덱스에 입력 후 인덱스 증가
                            n_board[cnt++][c] = prev;
                            // 이전 값은 현재 값으로 설정
                            prev = board[r][c];
                        }
                    }
                }
                // 반복문 종료 후 이전 값이 남아있다면 현재 인덱스에 삽입 후 종료
                if(prev != -1) n_board[cnt][c] = prev;
            }
            break;
        case 3:
            // 행과 열, 시작점과 종료지점만 다르고 로직은 동일
            for(int r = 0; r < N; r++) {
                int prev = -1;
                int cnt = 0;
                for(int c = 0; c < N; c++) {
                    if(board[r][c] == 0) continue;
                    if(prev == -1) prev = board[r][c];
                    else {
                        if(prev == board[r][c]) {
                            n_board[r][cnt++] = prev * 2;
                            prev = -1;
                        }
                        else {
                            n_board[r][cnt++] = prev;
                            prev = board[r][c];
                        }
                    }
                }
                if(prev != -1) n_board[r][cnt] = prev;
            }
            break;
        case 2:
            for(int c = 0; c < N; c++) {
                int prev = -1;
                int cnt = N-1;
                for(int r = N-1; r >= 0; r--) {
                    if(board[r][c] == 0) continue;
                    if(prev == -1) prev = board[r][c];
                    else {
                        if(prev == board[r][c]) {
                            n_board[cnt--][c] = prev * 2;
                            prev = -1;
                        }
                        else {
                            n_board[cnt--][c] = prev;
                            prev = board[r][c];
                        }
                    }
                }
                if(prev != -1) n_board[cnt][c] = prev;
            }
            break;
        case 1:
            for(int r = 0; r < N; r++) {
                int prev = -1;
                int cnt = N-1;
                for(int c = N-1; c >= 0; c--) {
                    if(board[r][c] == 0) continue;
                    if(prev == -1) prev = board[r][c];
                    else {
                        if(prev == board[r][c]) {
                            n_board[r][cnt--] = prev * 2;
                            prev = -1;
                        }
                        else {
                            n_board[r][cnt--] = prev;
                            prev = board[r][c];
                        }
                    }
                }
                if(prev != -1) n_board[r][cnt--] = prev;
            }
            break;
        }
		board = n_board;
	}
	
	// 모든 시뮬레이션 진행을 위한 백트래킹
	public static void backtrack(int cnt) {
		// 5번 다 움직였으면 현재 보드에서 최대 값 계산
		if(cnt == 5) {
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					ans = Math.max(board[r][c], ans);
				}
			}
			return;
		}
		
		int[][] prev_board = new int[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				prev_board[r][c] = board[r][c];
			}
		}
		
		
		// 백트래킹 - 현재 방향(i)대로 이동
		for(int i = 0; i < 4; i++) {
			move(i);
			// 재귀로 다음 이동 계산
			backtrack(cnt + 1);
			// 백트래킹 - 현재 반대 방향으로 이동해서 원상복구
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					board[r][c] = prev_board[r][c];
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        // 초기 값 입력
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
         
        // 이동 시작
        ans = 0;
        backtrack(0);
        
        // 최대값 출력
        System.out.println(ans);
    }
     
}