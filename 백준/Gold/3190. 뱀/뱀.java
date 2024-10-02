import java.io.*;
import java.util.*;

public class Main {

	// BOJ 3190 - 뱀
	public static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		// 사과와 뱀을 표시할 보드
		int[][] board = new int[N][N];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			// 행과 열을 계산(1행 1열부터 시작하므로 -1)
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			// 사과 위치는 1로 표현
			board[r][c] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		
		// 이동 시점과 방향을 저장
		int[][] move = new int[L][2];
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			// 이동 시간
			move[i][0] = Integer.parseInt(st.nextToken());
			// 이동 방향 - d배열 기준 오른쪽으로 회전 -> 인덱스 + 1, 왼쪽으로 회전 -> 인덱스 -1
			move[i][1] = st.nextToken().charAt(0) == 'D' ? 1 : -1;
		}
		
		// ans : 경과 시간 , dir : 현재 방향 ( 0: 우, 1: 하, 2: 좌, 3: 상)
		int ans = 0, dir = 0;
		// 현재 시간 배열의 인덱스
		int idx = 0;
		// 뱀의 몸통을 관리할 dq
		Deque<int[]> dq = new ArrayDeque<>();
		// 시작 위치 (0,0) dq에 추가
		dq.offer(new int[] {0, 0});
		// 0, 0에 뱀을 나타낼 2 저장
		board[0][0] = 2;
		// 게임이 종료될때까지 무한 반복
		while(true) {
			// 시간 경과
			ans++;
			// 가장 마지막에 추가된(머리) 좌표 확인
			int[] cur = dq.peekLast();
			// 현재 진행 방향대로 이동한 좌표 계산
			int nr = cur[0] + d[dir][0], nc = cur[1] + d[dir][1];
			// 새로 계산한 좌표가 범위 밖으로 벗어나거나(벽에 부딪히거나), 뱀의 몸통이랑 겹친다면 종료
			if(nr < 0 || N <= nr || nc < 0 || N <= nc || board[nr][nc] == 2) break;
			
			// 아니라면 새로운 좌표 머리로 추가
			dq.offer(new int[] {nr, nc});
			// 이동한 좌표가 사과가 아니라 빈 칸이라면
			if(board[nr][nc] == 0) {
				// 꼬리 제거
				int[] tail = dq.poll();
				// 꼬리부분 빈 칸 처리
				board[tail[0]][tail[1]] = 0;
			}
			// 새로운 좌표에 머리 표시
			board[nr][nc] = 2;
			// 아직 이동 좌표를 끝까지 다 돌지 않았고, 현재 이동해야할 시점과 현재 시간이 일치한다면
			if(idx < L && move[idx][0] == ans) {
				// 방향 전환
				dir = (dir + 4 + move[idx][1]) % 4;
				// 다음 이동 시간을 계산하도록 인덱스 증가
				idx++;
			}
		}
		
		// 최종 결과 출력
		System.out.println(ans);
		
	}

}