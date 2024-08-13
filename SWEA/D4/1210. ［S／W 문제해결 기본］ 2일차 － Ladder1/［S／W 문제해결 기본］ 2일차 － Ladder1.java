import java.io.*;
import java.util.*;

class Solution {
	
	// SWEA - [S/W 문제해결 기본] 2일차 - Ladder1
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 테스트 케이스 수 입력
		
		// 테스트 케이스 10회 반복
		for(int tc = 1; tc <= 10; tc++) {
			// 테스트케이스 번호 입력 - 필요 없어서 스킵
			br.readLine();
			// 현재 위치를 표시할 좌표
			int[] pos = new int[] {0, 0};
			// 사다리를 나타낼 board
			int[][] board = new int[100][100];
			// 사다리를 입력받으며 2(도착지)의 위치 기록
			for(int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 2) {
						pos[0] = i;
						pos[1] = j;
					}
				}
			}
			
			// 도착지부터 역으로 사다리를 올라가서 출발지를 찾음
			while(pos[0] != 0) {
				// 오른쪽 쪽으로 아직 더 갈 수 있고, 오른쪽으로 길이 있다면
				if(pos[1]+1 < 100 && board[pos[0]][pos[1]+1] == 1) {
					// 오른쪽으로 한 칸 이동
					pos[1]++;
					// 위로 올라가는 사다리를 만날때까지 계속 이동
					while(board[pos[0]-1][pos[1]]==0) {
						pos[1]++;
					}
				}
				// 왼쪽도 동일한 작업 진행
				else if(pos[1] -1 >= 0 && board[pos[0]][pos[1]-1] == 1) {
					pos[1]--;
					while(board[pos[0]-1][pos[1]]==0) {
						pos[1]--;
					}
				}
				// 위로 한 칸씩 올라감
				pos[0]--;
			}
			sb.append("#").append(tc).append(" ").append(pos[1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
