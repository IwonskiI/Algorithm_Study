import java.util.*;
import java.io.*;
    
public class Main {
        
    // BOJ 1400 - 화물차
	
	// 0: 상, 1: 하, 2: 우, 3: 좌
	public static int[][] d = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        // 0 0이 입력될때까지 반복
        while(true) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            // 0 0이면 종료
            if(N == 0 && M == 0) break;
            // 걸린 시간 ans
            int ans = -1;
            // 시작점 좌표 sr, sc
            int sr = -1, sc = -1;
            // 초기 입력
            char[][] board = new char[N][M];
            boolean[][] visited = new boolean[N][M];
            for(int r = 0; r < N; r++) {
            	String[] str = br.readLine().split("");
            	for(int c = 0; c < M; c++) {
            		board[r][c] = str[c].charAt(0);
            		// 시작점 좌표 저장
            		if(board[r][c] == 'A') {
            			sr = r;
            			sc = c;
            		}
                }
            }
            // 신호 상태 저장 (0~9번까지 신호등 존재 가능 - 10칸 선언)
            // 0 : 신호등 방향(0:남북,세로 / 1: 동서,가로) , 1 : 초기상태 유지 시간, 2: 변경후상태 유지 시간, 3: 두 상태가 한 바퀴 도는데 걸리는 시간
            int[][] sign = new int[10][4];
            // 빈 줄이 나올 때까지 입력 반복
            while(true) {
            	String temp = br.readLine();
            	if(temp.length() == 0) break;
            	st = new StringTokenizer(temp);
            	// 신호등의 번호 입력
            	int cur = Integer.parseInt(st.nextToken());
            	// 신호등 초기 방향 : 0 - 남북(세로), 1 - 동서(가로)
            	sign[cur][0] = st.nextToken().equals("|") ? 0 : 1;
            	// 초기 상태가 가로 방향이라면 순서대로 시간 입력
            	if(sign[cur][0] == 1) {
                	sign[cur][1] = Integer.parseInt(st.nextToken());
                	sign[cur][2] = Integer.parseInt(st.nextToken());
            	}
            	// 초기 상태가 세로 방향이라면 세로 시간을 뒤에 저장하고, 가로 시간을 앞에 저장
            	else {
                	sign[cur][2] = Integer.parseInt(st.nextToken());
                	sign[cur][1] = Integer.parseInt(st.nextToken());
            	}
            	// 한 사이클에 걸리는 시간 저장
            	sign[cur][3] = sign[cur][1] + sign[cur][2];
            }
            if(sr == -1 && sc == -1) sb.append("impossible\n");
            
            else{
            // BFS 탐색
            Deque<int[]> dq = new ArrayDeque<>();
            // dq에 시작점, 시작시간 추가 및 방문 처리
            dq.offer(new int[] {sr, sc, 0});
            visited[sr][sc] = true;
            // dq가 빌 때까지 반복
            while(!dq.isEmpty()) {
            	int[] cur = dq.poll();
            	int r = cur[0], c = cur[1], t = cur[2];
            	// 현재 위치가 도착지점이라면 현재 시간을 저장 후 반복문 종료
            	if(board[r][c] == 'B') {
            		ans = t;
            		break;
            	}
            	// 4방향 탐색
            	for(int dd = 0; dd < 4; dd++) {
            		int nr = r + d[dd][0], nc = c + d[dd][1];
            		boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
            		// 범위 안이고, 아직 방문 전이고, .이 아니라면
            		if(in_range && !visited[nr][nc] && board[nr][nc] != '.') {
            			// 이동할 수 있는 길(도착지점)이라면 이동
            			if(board[nr][nc] == '#' || board[nr][nc] == 'B' || board[nr][nc] == 'A') {
            				dq.offer(new int[] {nr, nc, t+1});
                			visited[nr][nc] = true;
            			}
            			// 신호등이라면
            			else if ('0' <= board[nr][nc] && board[nr][nc] <= '9') {
            				// 신호등 번호 인식
            				int s_num = Character.getNumericValue(board[nr][nc]);
            				// 사이클 제거 후 남는 시간 계산
            				int left_t = t % sign[s_num][3];
            				// 초기 방향
            				int dir = sign[s_num][0];
            				// 남는 시간이 초기방향이 유지되는 시간보다 크다면 방향 변경
            				if(left_t >= sign[s_num][1]) dir = dir == 1 ? 0 : 1;
            				// 현재 진행방향과 신호의 방향이 일치하다면 새로운 좌표로 이동
            				// dd가 2보다 작으면(0,1) -> 세로로 이동중 + dir == 0 -> 남북(세로) 신호 ON
            				// dd가 2보다 크거나 같으면(2, 3) -> 가로로 이동중 + dir == 1 -> 동서(가로) 신호 ON
            				if((dd < 2 && dir == 0) || (dd >= 2 && dir == 1)) {
            					dq.offer(new int[] {nr, nc, t+1});
            					visited[nr][nc] = true;
            				}
            				// 일치하지 않는다면 시간만 증가 후 그 자리에 정지
            				else {
            					dq.offer(new int[] {r, c, t+1});
            				}
            			}
            		}
            	}
            }
            
            // ans가 갱신되었다면 답 저장
            if(ans != -1) sb.append(ans).append("\n");
            // 탐색 종료 후 ans가 갱신되지 않아서 -1이라면 impossible 저장
            else sb.append("impossible\n");
            }
        }
        
        // 결과 출력
        System.out.println(sb.toString());
    }
    
}