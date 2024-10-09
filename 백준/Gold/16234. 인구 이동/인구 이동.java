import java.io.*;
import java.util.*;

public class Main {

    // BOJ 16234 - 인구 이동
    public static int N, L, R, ans;
    public static int[][] board, visited, d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//    public static boolean[][] visited;
    
    public static boolean isopen(int a, int b) {
    	int sub = Math.abs(a - b);
    	if(L <= sub && sub <= R) return true;
    	else return false;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 보드의 크기 N*N
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        
        // 기준 L, R 입력
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        // 초기 인구 입력
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < N; c++) {
        		board[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 국경이 열린 날짜 저장
        ans = 0;
        
        // 문이 열리는지 확인하는 변수 (국경이 열리는지 확인해야하므로 초기값은 true)
        boolean opened = true;
        // 국경이 열리지 않을 때 까지 반복
        while(opened) {
        	// 초기 국경은 닫혀있음
        	opened = false;
        	// 방문 배열 - 국경이 열리는 순간 2로 설정, 인구 이동이 완료된 칸은 1로 설정
        	visited = new int[N][N];
        	for(int cr = 0; cr < N; cr++) {
            	for(int cc = 0; cc < N; cc++) {
            		// 이미 인구이동이 완료된 칸이면 스킵
            		if(visited[cr][cc] > 0) continue;
            		// 현재 좌표부터 인구 이동이 일어나는지 검사
            		Deque<int[]> dq = new ArrayDeque<>();
            		dq.offer(new int[] {cr, cc});
            		// 인구 이동이 일어나기 전이므로 2로 초기화
            		visited[cr][cc] = 2;
            		// 연합의 총 인원수
            		int sum = board[cr][cc];
            		// 연합된 국가 수
            		int cnt = 1;
            		// bfs 탐색 시작
            		while(!dq.isEmpty()) {
            			int[] cur = dq.poll();
            			int r = cur[0], c = cur[1];
            			// 4방향 탐색
            			for(int dd = 0; dd < 4; dd++) {
            				int nr = r + d[dd][0], nc = c + d[dd][1];
            				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
            				// 범위 안이고, 아직 방문 전 위치고, 국경이 열리는 기준이라면
            				if(in_range && visited[nr][nc] == 0 && isopen(board[r][c], board[nr][nc])) {
            					// 이번 날에는 국경이 열렸다는 표시
            					opened = true;
            					// 연합에 인구 수 더하기
            					sum += board[nr][nc];
            					// 연합된 국가 수 증가
            					cnt++;
            					// 국경이 열린 표시 2
            					visited[nr][nc] = 2;
            					// 다음 탐색을 위해 dq에 추가
            					dq.offer(new int[] {nr, nc});
            				}
            			}
            		}
            		// 현재 칸에서 열수 있는 국경을 모두 열고난 뒤, 인구 이동 시작
            		dq.offer(new int[] {cr, cc});
            		// 연합 국가의 각 인구는 평균
            		int avg = sum / cnt;
        			// 현재 위치에 평균 인구로 설정
        			board[cr][cc] = avg;
            		// 인구 이동이 완료된 칸은 1로 설정
            		visited[cr][cc] = 1;
            		// 다시 bfs 탐색
            		while(!dq.isEmpty()) {
            			int[] cur = dq.poll();
            			int r = cur[0], c = cur[1];
            			// 4방향 탐색
            			for(int dd = 0; dd < 4; dd++) {
            				int nr = r + d[dd][0], nc = c + d[dd][1];
            				boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
            				// 범위 안이고, 이번에 국경이 열렸다면
            				if(in_range && visited[nr][nc] == 2) {
            					// 해당 위치를 방문
            					dq.offer(new int[] {nr, nc});
            					// 이동 된 칸 인구수 변경
                    			board[nr][nc] = avg;
            					// 인구 이동이 완료되었다는 표시로 1 설정
            					visited[nr][nc] = 1;
            				}
            			}
            		}
            		
            	}
            }
        	// 이번 날짜에 국경이 열렸으면 ans 증가
        	if(opened) ans++;
        }
        
        // 최솟값 출력
        System.out.println(ans);
    }

}