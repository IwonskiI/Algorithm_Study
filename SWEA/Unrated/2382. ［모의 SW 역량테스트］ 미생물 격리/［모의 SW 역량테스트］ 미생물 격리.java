import java.util.*;
import java.io.*;
   
class Virus {
	int cnt;
	int prev;
	// 상 : 0, 우 : 1, 하: 2, 좌: 3;
	int dir;
	
	public Virus(int cnt, int dir, int prev) {
		this.cnt = cnt;
		this.dir = dir;
		this.prev = prev;
	}
}

public class Solution {
       
    // SWEA 2382 - [모의 SW 역량테스트] 미생물 격리
	public static int N, M, K;
	public static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	// 방향 설정 map
	public static HashMap<Integer, Integer> dirmap = new HashMap<Integer, Integer>(){
		private static final long serialVersionUID = 1L;
		{put(1, 0); put(2, 2); put(3, 3); put(4, 1);}
	};
	public static Virus[][] board, nboard;
	
	public static void move(int r, int c) {
		// 현재 미생물 정보 가져오기
		int cnt = board[r][c].cnt;
		int dd = board[r][c].dir;
		int prev = board[r][c].prev;
		Virus cur = new Virus(cnt, dd, prev);
		// 새로운 좌표
		int nr = r + d[cur.dir][0], nc = c + d[cur.dir][1];
		// 진행 좌표에 미생물이 없다면,
		if(nboard[nr][nc] == null) {
			// 끝부분(약품처리)에 도달했다면
			if(nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
				// 미생물 수 반으로 나누기
				cur.cnt /= 2;
				// 방향 반대방향으로 설정하기
				cur.dir = (cur.dir + 2) % 4; 
			}
			// 현재 미생물 수가 0이 아니라면 새 좌표에 현재 미생물 이동
			if(cur.cnt != 0) nboard[nr][nc] = cur;
		}
		// 이동하려는 칸에 미생물이 있다면
		else {
			
			// 현재 미생물의 수가 해당 칸에 합쳐진 미생물 수의 최대치보다 많다면 이동 방향을 현재 미생물 방향으로 전환
			if(cur.cnt > nboard[nr][nc].prev) {
				nboard[nr][nc].prev = cur.cnt;
				nboard[nr][nc].dir = cur.dir;
			}
			// 진행 좌표의 미생물 수에 현재 미생물 수를 더해서 저장
			nboard[nr][nc].cnt += cur.cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new Virus[N][N];
            
            for(int i = 0; i < K; i++) {
            	st = new StringTokenizer(br.readLine());
            	int r = Integer.parseInt(st.nextToken());
            	int c = Integer.parseInt(st.nextToken());
            	int cnt = Integer.parseInt(st.nextToken());
            	// 미생물 수와 방향(매핑)을 담은 미생물 객체 생성
            	Virus cur = new Virus(cnt, dirmap.get(Integer.parseInt(st.nextToken())), cnt);
            	// 해당 좌표에 미생물 저장
            	board[r][c] = cur;
            }
            
            // M시간동안 진행
            for(int i = 1; i <= M; i++) {
            	nboard = new Virus[N][N];
            	for(int r = 0; r < N; r++) {
            		for(int c = 0; c < N; c++) {
            			// 해당 좌표에 미생물이 있고, 현재 시간보다 이전에 이동한 미생물이라서 현재 시간에 이동해야한다면 
            			if(board[r][c] != null)
            				// 좌표와 시간을 전달해주고 이동
            				move(r, c);
            		}
            	}
            	for(int r = 0; r < N; r++) {
            		for(int c = 0; c < N; c++) {
            			// 해당 좌표에 미생물이 있다면, prev 갱신 
            			if(nboard[r][c] != null)
            				nboard[r][c].prev = nboard[r][c].cnt;
            		}
            	}
            	board = nboard;
            }
            // 총 미생물 수 계산
            int ans = 0;
            
            for(int r = 0; r < N; r++) {
            	for(int c = 0; c < N; c++) {
            		// 해당 칸에 미생물이 있다면
            		if(board[r][c] != null)
            			// 해당 칸의 미생물 합 누적
            			ans += board[r][c].cnt;
            	}
            }
            
            // 정답 저장
            sb.append(ans).append("\n");
        }
        // 결과 출력
        System.out.println(sb.toString());
    }
   
}