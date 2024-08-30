import java.io.*;
import java.util.*;

public class Solution {
	
	// N :보드의 크기, ans_chip/ans_line : 정답의 칩 연결 개수와 선 개수
	// chip_cnt/line_cnt : 현재 진행중인 연결 칩 개수과 선 개수
	public static int N, ans_chip, ans_line, chip_cnt, line_cnt;
	// 4방향 탐색
	public static int[][] board, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	// 프로세서의 좌표를 관리할 리스트
	public static ArrayList<int[]> chip;
	
	// 선 연결 함수 (cnt : 현재 칩 인덱스)
	public static void connect(int cnt) {
		// 모든 칩을 순회 완료 했을 때,
		if(cnt == chip.size()) {
			// 저장된 칩 개수보다 현재 칩이 많이 연결 되었으면 선과 칩 개수 갱신
			if(ans_chip < chip_cnt) {
				ans_chip = chip_cnt;
				ans_line = line_cnt;
			} 
			// 저장된 칩 개수는 같지만, 선이 더 적게 연결 되었다면 선 개수 갱신
			else if(ans_chip == chip_cnt && ans_line > line_cnt) {
				ans_line = line_cnt;
			}
			// 종료
			return;
		}
		
		// 아직 칩 순회 완료 전이라면,
		// 현재 칩 좌표
		int[] cur = chip.get(cnt);
		int r = cur[0], c = cur[1];
		// 4방향 탐색 (dfs)
		for(int dd = 0; dd < 4; dd++) {
			// 새 좌표 설정
			int nr = r, nc = c;
			// 현재 방향으로 연결된 선의 개수
			int l_cnt = 0;
			// 가로 막혔는지 확인할 flag 변수
			boolean fin = false;
			// 끝 지점에 도달할 때 까지
			while(!(nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1)) {
				// 좌표 갱신 (현재 방향으로 이동)
				nr += d[dd][0];
				nc += d[dd][1];
				// 다른 선이나 프로세서를 만났을 경우 중단
				if(board[nr][nc] == 1) {
					// 한칸 이전으로 이동
					nr -= d[dd][0];
					nc -= d[dd][1];
					// 막혔다는 표시 true
					fin = true;
					// 진행 종료
					break;
				}
				// 막히지 않았다면 선으로 표시 후 선 증가
				board[nr][nc] = 1;
				l_cnt++;
			}
			// 반복문 종료 후 막혀서 선을 연결하지 못했다면,
			if(fin) {
				// 기존에 연결했던 선 제거
				while(nr != r || nc != c) {
					board[nr][nc] = 0;
					nr -= d[dd][0];
					nc -= d[dd][1];
				}
			}
			// 선이 연결 되었다면
			else {
				// 현재 연결 된 선을 총 선에 더해줌
				line_cnt += l_cnt;
				// 연결된 칩 개수 증가
				chip_cnt++;
			}
			// 다음 칩 연결 시작
			connect(cnt+1);
			//만약 선이 연결 되었었다면 다른 방향 탐색을 위해 연결 해제
			if(!fin) {
				// 선 연결 해제
				while(nr != r || nc != c) {
					board[nr][nc] = 0;
					nr -= d[dd][0];
					nc -= d[dd][1];
				}
				// 연결된 칩 개수 감소 
				chip_cnt--;
				// 연결했던 선만큼 다시 빼주기
				line_cnt -= l_cnt;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			ans_chip = Integer.MIN_VALUE;
			ans_line = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			chip = new ArrayList<>();
			// 초기 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
					// 프로세서이면서 끝부분에 이미 연결된 프로세서가 아니라면 프로세서 관리 리스트에 추가
					if(board[r][c] == 1 && (!(r == 0 || r == N - 1 || c == 0 || c == N - 1))) {
						chip.add(new int[] {r, c});
					}
				}
			}
			// 연결 선, 연결 프로세서 초기화
			line_cnt = 0;
			chip_cnt = 0;
			// 연결 시작
			connect(0);
			
			// 최종 연결 선 저장
			sb.append(ans_line).append("\n");
		}
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}

}
