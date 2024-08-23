import java.util.*;
import java.io.*;

public class Solution {
	// SWEA 1824 - 혁진이의 프로그램 검증
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[][] d = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			// 메모리의 크기 설정
			int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
			// 메모리의 구성 저장
			char[][] board = new char[R][C];
			// 방문 배열을 비트마스크로 최적화 (memory 4비트 + dir 2비트 = 6비트로 표현 가능)
			boolean[][][] visited = new boolean[R][C][64];			
			for(int r = 0; r < R; r++) {
				String str = br.readLine();
				for(int c = 0; c < C; c++) {
					board[r][c] = str.charAt(c);
				}
			}
			// ? 이동을 처리하기 위한 stack 선언
			Deque<int[]> stack = new ArrayDeque<>();
			// 초기 설정값 입력 (memory, dir은 추후 ? 로 인한 추가 이동때 기존 메모리와 방향을 기억하기 위함)
			stack.offer(new int[] {0, 0, 0, 0});  // {row, col, memory, dir}
			// @를 찾았는지 체크할 변수
			boolean found = false;
			// @를 만나지 못하고 stack이 비어버리면 NO 출력
			while (!stack.isEmpty()) {
				int[] cur = stack.pollLast();
				int r = cur[0];
				int c = cur[1];
				int memory = cur[2];
				int dir = cur[3];
				// @를 만나면 found를 true로 하고 break
				if (board[r][c] == '@') {
					found = true;
					break;
				}
				
				// 비트마스크로 상태 관리
				int state = (memory << 2) | dir;
				
				if (visited[r][c][state]) continue;  // 이미 방문한 상태라면 스킵
				visited[r][c][state] = true;
				
				// 숫자 처리
				if (Character.isDigit(board[r][c])) {
					memory = board[r][c] - '0';
				} else {
					switch (board[r][c]) {
						// 방향 전환
						case '<': dir = 2; break;
						case '>': dir = 0; break;
						case '^': dir = 3; break;
						case 'v': dir = 1; break;
						// 메모리에 따른 방향 전환
						case '_': dir = (memory == 0) ? 0 : 2; break;
						case '|': dir = (memory == 0) ? 1 : 3; break;
						// 메모리 증가. 16 이상이라면 나머지 처리
						case '+': memory = (memory + 1) % 16; break;
						// 메모리 감소. 음수가 된다면 15로 처리
						case '-': memory = (memory == 0) ? 15 : memory - 1; break;
						// 4방향 모두 추가 (기존 방향은 dfs를 위해 마지막에 추가 해줄 것이기 때문에 스킵)
						case '?':
							for (int dd = 0; dd < 4; dd++) {
								int nr = (r + d[dd][0] + R) % R;
								int nc = (c + d[dd][1] + C) % C;
								stack.offer(new int[] {nr, nc, memory, dd});
							}
							continue;
					}
				}
				
				// 다음 칸으로 이동
				int nr = (r + d[dir][0] + R) % R;
				int nc = (c + d[dir][1] + C) % C;
				stack.offer(new int[] {nr, nc, memory, dir});
			}
			// 반복문 종료 후 found에 따라 YES / NO 저장
			sb.append(found ? "YES\n" : "NO\n");
		}
		// 전체 값 출력
		System.out.println(sb.toString());
	}
}
