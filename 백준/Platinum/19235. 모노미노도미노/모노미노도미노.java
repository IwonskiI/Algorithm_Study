import java.util.*;
import java.io.*;

public class Main {

    // BOJ 19235 - 모노미노도미노
	public static int ans = 0, block_idx = 1;
	public static int[][] b_board = new int[4][6], g_board = new int[6][4];
	
	// t : 1 = blue, 2 = green
	
	// 각 보드에 r,c의 위치에 블록을 놨을 때 가장 깊게 들어갈 수 있는 위치 계산
	public static int check(int r, int c, int t) {
		// blue_board
		if(t == 1) {
			for(int i = 0; i <= 5; i++) {
				if(b_board[r][i] != 0) return i - 1;
			}
		}
		// green_board
		else {
			for(int i = 0; i <= 5; i++) {
				if(g_board[i][c] != 0) return i - 1;
			}
		}
		return 5;
	}
	
	// 초과한 높이만큼 방향대로 이동
	public static void move(int t, int n) {
		if(t == 1) {
			for(int r = 0; r < 4; r++) {
				for(int c = 3+n; c >= n; c--) {
					b_board[r][c+(2-n)] = b_board[r][c];
					if(c < 2) {
						b_board[r][c] = 0;
					}
				}
			}
		}
		else {
			for(int r = 3+n; r >= n; r--) {
				for(int c = 0; c < 4; c++) {
					g_board[r+(2-n)][c] = g_board[r][c];
					if(r < 2) {
						g_board[r][c] = 0;
					}
				}
			}
		}
	}

	// 가득찬 줄을 찾아 점수를 증가시키고, 가득 찬 둘의 인덱스 반환
	public static int score(int t) {
		int isFilled = 1;
		if(t==1) {
			for(int c = 2; c <= 5; c++) {
				isFilled = 1;
				for(int r = 0; r < 4; r++) {
					isFilled *= b_board[r][c];
				}
				if(isFilled != 0) {
					ans++;
					return c;
				}
			}
		}
		else {
			for(int r = 2; r <= 5; r++) {
				isFilled = 1;
				for(int c = 0; c < 4; c++) {
					isFilled *= g_board[r][c];
				}
				if(isFilled != 0) {
					ans++;
					return r;
				}
			}
		}
		
		return -1;
	}

	// 가득찬 줄을 제거 
	public static void remove(int t, int n) {
		if(t == 1) {
			for(int c = n; c > 0; c--) {
				for(int r = 0; r < 4; r++) {
					b_board[r][c] = b_board[r][c - 1];
				}
			}
		}
		else {
			for(int r = n; r > 0; r--) {
				for(int c = 0; c < 4; c++) {
					g_board[r][c] = g_board[r - 1][c];
				}
			}
		}
	}

	// 떨어질 수 있는 추가 블럭을 찾아 이동
	public static boolean drop(int t) {
		boolean flag = false;
		if(t == 1) {
			for(int c = 0; c < 5; c++) {
				for(int r = 0; r < 4; r++) {
					if(b_board[r][c] != 0 && c != 5 && b_board[r][c+1] == 0) {
						if(r != 3 && b_board[r][c] == b_board[r+1][c]) {
							if(b_board[r+1][c+1] == 0) {
								b_board[r][c+1] = b_board[r][c];
								b_board[r+1][c+1] = b_board[r+1][c];
								b_board[r][c] = 0;
								b_board[r+1][c] = 0;
								flag = true;
							}
						}
						else if(r != 0 && b_board[r][c] == b_board[r-1][c]) {
							if(b_board[r-1][c+1] == 0) {
								b_board[r][c+1] = b_board[r][c];
								b_board[r-1][c+1] = b_board[r-1][c];
								b_board[r][c] = 0;
								b_board[r-1][c] = 0;
								flag = true;
							}
						}
						else {
							flag = true;
							b_board[r][c+1] = b_board[r][c];
							 if(c != 0 && b_board[r][c] == b_board[r][c-1]) {
								 b_board[r][c-1] = 0;
							 }
							 else {
								 b_board[r][c] = 0;
							 }
						}
					}
				}
			}
		}
		else {
			for(int r = 0; r < 5; r++) {
				for(int c = 0; c < 4; c++) {
					if(g_board[r][c] != 0 && r != 5 && g_board[r+1][c] == 0) {
						if(c != 3 && g_board[r][c] == g_board[r][c+1]) {
							if(g_board[r+1][c+1] == 0) {
								g_board[r+1][c] = g_board[r][c];
								g_board[r+1][c+1] = g_board[r][c+1];
								g_board[r][c] = 0;
								g_board[r][c+1] = 0;
								flag = true;
							}
						}
						else if(c != 0 && g_board[r][c] == g_board[r][c-1]) {
							if(g_board[r+1][c-1] == 0) {
								g_board[r+1][c] = g_board[r][c];
								g_board[r+1][c-1] = g_board[r][c-1];
								g_board[r][c] = 0;
								g_board[r][c-1] = 0;
								flag = true;
							}
						}
						else {
							flag = true;
							g_board[r+1][c] = g_board[r][c];
							 if(r != 0 && g_board[r][c] == g_board[r-1][c]) {
								 g_board[r-1][c] = 0;
							 }
							 else {
								 g_board[r][c] = 0;
							 }
						}
					}
				}
			}
		}
		return flag;
	}
	
	// 0, 1번 줄에 위치한 블럭(초과한 블럭)이 있는지 확인
	public static int overflow(int t) {
		int h = 2;
		if(t == 1) {
			for(int r = 0; r < 4; r++) {
				for(int c = 0; c < 2; c++) {
					if(b_board[r][c] != 0) h = Math.min(h, c);
				}
			}
		}
		else {
			for(int r = 0; r < 2; r++) {
				for(int c = 0; c < 4; c++) {
					if(g_board[r][c] != 0) h = Math.min(h, r);
				}
			}
		}
		return h;
	}

	// 최종 남아있는 블럭의 수 계산
	public static int count() {
		int cnt = 0;
		for(int r = 0; r < 4; r++) {
			for(int c = 2; c < 6; c++) {
				if(b_board[r][c] != 0) cnt++;
			}
		}
		for(int r = 2; r < 6; r++) {
			for(int c = 0; c < 4; c++) {
				if(g_board[r][c] != 0) cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());

        // 블록 놓기 진행
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	// 블록의 타입, 시작 좌표 입력
        	int t = Integer.parseInt(st.nextToken());
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	// 각 블록의 놓여진 높이
        	int b_pos = -1, g_pos = -1;
        	
        	switch(t) {
        	// 1*1 블록
        	case 1:
        		b_pos = check(r, c, 1);
        		g_pos = check(r, c, 2);
        		b_board[r][b_pos] = block_idx;
        		g_board[g_pos][c] = block_idx++;
        		break;
        	// 가로 1*2 블록
        	case 2:
        		b_pos = check(r, c, 1);
        		b_board[r][b_pos] = block_idx;
        		b_board[r][b_pos - 1] = block_idx;
        		g_pos = Math.min(check(r, c, 2), check(r, c + 1, 2));
        		g_board[g_pos][c] = block_idx;
        		g_board[g_pos][c+1] = block_idx++;
        		break;
        	// 세로 2*1 블록
        	case 3:
        		b_pos = Math.min(check(r, c, 1), check(r + 1, c, 1));
        		b_board[r][b_pos] = block_idx;
        		b_board[r+1][b_pos] = block_idx;
        		g_pos = check(r, c, 2);
        		g_board[g_pos][c] = block_idx;
        		g_board[g_pos - 1][c] = block_idx++;
        		break;
        	}
        	
        	// 점수 계산
        	while(true) {
        		// 제거할 줄이 남아있을 때까지 제거
        		while(true) {
            		int b_line = score(1);
            		if(b_line == -1) break;
            		remove(1, b_line);
            	}
        		// 떨어질 수 있는 블록이 있다면 떨어트림
        		boolean check_b = drop(1);
        		// 더이상 떨어지는 블록이 없다면 종료
        		if(!check_b) break;
        	}
        	// GreenBoard에도 똑같은 알고리즘 적용
        	while(true) {
        		while(true) {
            		int g_line = score(2);
            		if(g_line == -1) break;
            		remove(2, g_line);
            	}
        		boolean check_g = drop(2);
        		if(!check_g) break;
        	}
        	
        	// 최종 블럭의 가장 높은 높이 저장
        	b_pos = overflow(1);
        	g_pos = overflow(2);
        	
        	// 초과 줄 정리
        	if(b_pos < 2) move(1, b_pos);
        	if(g_pos < 2) move(2, g_pos);
        
        }
        
        // 획득한 점수 저장
        sb.append(ans).append("\n");
        // 남아있는 블록의 총 개수 세어서 저장
        sb.append(count());
        
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}