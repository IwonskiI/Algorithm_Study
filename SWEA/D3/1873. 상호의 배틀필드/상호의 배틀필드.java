import java.io.*;
import java.util.*;

public class Solution {
	
	// SWEA 1873 - 상호의 배틀필드
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 포탄 발사 방향
		int[][] d = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			String[][] board = new String[H][W];
			// 현재 전차의 위치
			int pr = 0, pc = 0;
			// 현재 전차의 방향
			String dir = "";
			// 초기 맵 입력
			for(int r = 0; r < H; r++) {
				String[] str = br.readLine().split("");
				for(int c = 0; c < W; c++) {
					board[r][c] = str[c];
					// 입력값이 탱크이면 위치 및 방향 저장
					if(str[c].equals("v") || str[c].equals("<") || str[c].equals(">") || str[c].equals("^")) {
						pr = r;
						pc = c;
						dir = str[c];
					}
				}
			}
			
			// 명령어 입력
			int N = Integer.parseInt(br.readLine());
			String[] op = br.readLine().split("");
			
			for(int i = 0; i < N; i++) {
				switch(op[i]) {
				// 위로 이동
				case"U":
					// 방향은 위로 설정
					dir = "^";
					// 방향대로 돌림
					board[pr][pc] = dir;
					// 범위 안이고, 앞이 평지라면 이동
					if(pr - 1 >= 0 && board[pr - 1][pc].equals(".")) {
						board[pr][pc] = ".";
						pr -= 1;
						board[pr][pc] = dir;
					}
					break;
				// 아래로 이동
				case"D":
					// 방향은 아래로 설정
					dir = "v";
					// 방향 회전
					board[pr][pc] = dir;
					// 범위 안이고, 앞이 평지라면 이동
					if(pr + 1 < H && board[pr + 1][pc].equals(".")) {
						board[pr][pc] = ".";
						pr += 1;
						board[pr][pc] = dir;
					}
					break;
				// 왼쪽으로 이동
				case"L":
					// 방향은 왼쪽으로 설정
					dir = "<";
					// 방향 회전
					board[pr][pc] = dir;
					// 범위 안이고, 앞이 평지라면 이동
					if(pc - 1 >= 0 && board[pr][pc - 1].equals(".")) {
						board[pr][pc] = ".";
						pc -= 1;
						board[pr][pc] = dir;
					}
					break;
				// 오른쪽으로 이동
				case"R":
					// 방향은 오른쪽으로 설정
					dir = ">";
					// 방향대로 회전
					board[pr][pc] = dir;
					// 범위 안이고, 앞이 평지라면 이동
					if(pc + 1 < W && board[pr][pc + 1].equals(".")) {
						board[pr][pc] = ".";
						pc += 1;
						board[pr][pc] = dir;
					}
					break;
				// 포탄 발사
				case"S":
					int dd = -1;
					// 현재 방향대로 dd값 설정 (위: 0 / 아래: 1 / 왼쪽: 2 / 오른쪽: 3)
					switch(dir) {
					case"^":
						dd = 0;
						break;
					case"v":
						dd = 1;
						break;
					case"<":
						dd = 2;
						break;
					case">":
						dd = 3;
						break;
					}
					// 포탄 발사 좌표 설정
					int nr = pr + d[dd][0], nc = pc + d[dd][1];
					// 범위 안인지 체크
					boolean in_range = (0 <= nr && nr < H) && (0 <= nc && nc < W);
					// 범위 안일 때까지 반복
					while(in_range){
						// 앞이 강철 벽이라면 중단
						if(board[nr][nc].equals("#"))break;
						// 앞이 벽돌 벽이라면
						else if(board[nr][nc].equals("*")) {
							// 벽돌 파괴 -> 평지 설정 후 중단
							board[nr][nc] = ".";
							break;
						}
						// 강철 벽이나 벽돌 벽이 아니라면,
						// 좌표 갱신
						nr = nr + d[dd][0];
						nc = nc + d[dd][1];
						// 범위 체크
						in_range = (0 <= nr && nr < H) && (0 <= nc && nc < W);
					}
					break;
				}
			}
			// 시뮬레이션 종료 후 맵 저장
			sb.append("#").append(tc).append(" ");
			for(int r = 0; r < H; r++) {
				for(int c = 0; c < W; c++) {
					sb.append(board[r][c]);
				}
				sb.append("\n");
			}
		}
		// 전체 결과 출력
		System.out.println(sb.toString());
	}
}