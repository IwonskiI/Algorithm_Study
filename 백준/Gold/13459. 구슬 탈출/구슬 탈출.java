import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 13459 - 구슬 탈출
	
	// 구슬의 좌표를 관리할 Class
	static class Point{
		public int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

	// board에서의 빨간 구슬과 파란 구슬의 상태와 이동횟수를 관리할 class 
	static class Status {
		public Point red, blue;
		public int lvl;
		
		public Status(int lvl, Point red, Point blue) {
			this.red = red;
			this.blue = blue;
			this.lvl = lvl;
		}
		
	}
	
	public static int R, C;
	public static char[][] board;
	public static int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	// 도착 지점의 좌표
	public static Point goal = new Point(-1, -1);
	
	// 구슬 이동 함수
	public static Point move(int dd, Point p) {
		// 현재 좌표
		int cr = p.r, cc = p.c;
		// 벽을 만날때까지 이동
		while(board[cr+d[dd][0]][cc+d[dd][1]] != '#') {
			cr += d[dd][0];
			cc += d[dd][1];
			// 이동한 좌표가 골인 지점이라면 중지
			if(cr == goal.r && cc == goal.c) return new Point(-1, -1);
		}
		// 최종 이동 좌표 반환
		return new Point(cr, cc);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기값 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		Point red = new Point(-1, -1), blue = new Point(-1, -1);
		
		// board 상태 입력
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				char cur = str.charAt(c);
				board[r][c] = cur;
				// 빨간 구슬 시작 좌표
				if(cur == 'R') {
					red.r = r;
					red.c = c;
					board[r][c] = '.';
				}
				// 파란 구슬 시작 좌표
				else if(cur == 'B') {
					blue.r = r;
					blue.c = c;
					board[r][c] = '.';
				}
				// 구멍 좌표
				else if(cur == 'O') {
					goal.r = r;
					goal.c = c;
					board[r][c] = '.';
				}
			}
		}
		
		// BFS 너비우선 탐색
		Status start = new Status(0, red, blue);
		// 중복 탐색을 방지하기 위한 HashSet (빨간공 좌표, 파란공 좌표를 문자열로 만들어서 Set으로 관리)
		HashSet<String> visited = new HashSet<>();
		
		// BFS 시작
		Deque<Status> dq = new ArrayDeque<>();
		dq.offer(start);
		// 방문 처리
		visited.add(start.red.r + " " + start.red.c + " " + start.blue.r + " " + start.blue.c);
		
		
		while(!dq.isEmpty()) {
			Status cur = dq.poll();
			Point cR = cur.red, cB = cur.blue;
			// 10번 이상 움직였으면 탐색X
			if(cur.lvl >= 10) continue;
			
			// 4방향 탐색
			for(int dd = 0; dd < 4; dd++) {
				// 새 빨간공 좌표
				Point nR = move(dd, cR);
				// 새 파란공 좌표				
				Point nB = move(dd, cB);
				// 파란공이 구멍에 빠진 경우 실패
				if(nB.r == -1 && nB.c == -1) continue;
				// 빨간공만 구멍에 빠진 경우 성공
				else if(nR.r == -1 && nR.c == -1) {
					System.out.println(1);
					return;
				}
				// 이동 후 두 공의 좌표가 같다면(겹쳤다면) 이동 방향과 이전 위치에 따른 위치 수정
				if(nR.r == nB.r && nR.c == nB.c) {
					// 상 이동
					if(dd == 0) {
						// 빨간공이 위에 있었다면 파란공을 한칸 밑으로
						if(cR.r < cB.r) nB.r += 1;
						// 파란공이 위에 있었다면 빨간공을 한칸 밑으로
						else nR.r += 1;
					}
					// 우 이동
					else if(dd == 1) {
						// 빨간공이 왼쪽에 있었다면 빨간공을 한칸 왼쪽으로
						if(cR.c < cB.c) nR.c -= 1;
						// 파란공이 왼쪽에 있었다면 파란공을 한칸 왼쪽으로
						else nB.c -= 1;
					}
					// 하 이동
					else if (dd == 2){
						// 빨간공이 위에 있었다면 빨간공을 한칸 위로
						if(cR.r < cB.r) nR.r -= 1;
						// 파란공이 위에 있었다면 파란공을 한칸 위로
						else nB.r -= 1;
					}
					// 좌 이동
					else if(dd == 3) {
						// 빨간공이 왼쪽에 있었다면 파란공을 한칸 오른쪽으로
						if(cR.c < cB.c) nB.c += 1;
						// 파란공이 왼쪽에 있었다면 빨간공을 한칸 오른쪽으로
						else nR.c += 1;
					}
				}
				// 현재 빨간공의 좌표, 파란공의 좌표로 문자열 생성
				String key = nR.r + " " + nR.c + " " + nB.r + " " + nB.c;
				// 해당 문자열 key가 이미 방문한적 있는 좌표라면 스킵
				if(!visited.contains(key)) {
					// 아니라면 key 추가
					visited.add(key);
					// 이동 횟수 증가 후 다음 탐색 대기열에 추가
					dq.offer(new Status(cur.lvl+1, nR, nB));
				}
			}
		}
		
		// 10번 넘게 차지 못하면 0 출력
		System.out.println(0);
		return;
	}

}