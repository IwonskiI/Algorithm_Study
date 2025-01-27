import java.io.*;
import java.util.*;

public class Main{
    
    // BOJ 23289 - 온풍기 안녕!
	// 보드 크기 및 온도, 히터의 개수, 초콜릿 먹은 수
    public static int R, C, K, h_cnt = 0, ans = 0;
    // 히터 방향 매핑
    public static int[] heat_dir = new int[] {0, 1, 3, 0, 2};
    // 온도 및 평균 온도를 기록할 보드, 방향 델타 배열
    public static int[][] board, n_board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    // 히터의 위치와 체크해야하는 좌표를 저장할 리스트
    public static List<int[]> heat = new ArrayList<>(), check = new ArrayList<>();
    // 벽의 정보를 저장할 map
    public static HashMap<String, boolean[]> wall = new HashMap<>();
    
    // 범위 체크
    public static boolean chk_range(int r, int c) {
    	if((0 <= r && r < R) && (0 <= c && c < C)) return true;
    	else return false;
    }
    
    // 히터로 온도 올리는 함수
    // 1: 오, 2: 왼, 3: 위, 4: 아래
    public static void fill(int r, int c, int dir) {
    	// 중복 방지 위한 방문 배열
    	boolean[][] visited = new boolean[R][C];
    	// 방향대로 한칸 앞에서 시작
        int nr = r+d[dir][0], nc = c + d[dir][1];
        // 모든 칸을 올리기 위한 deque
        Deque<int[]> dq = new ArrayDeque<>();
        // 탐색 시작
        dq.offer(new int[] {nr, nc, 5});
        
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            // 현재 좌표 범위 체크
            boolean in_range = chk_range(cur[0], cur[1]);
            // 범위 밖이면 스킵
            if(!in_range) continue;
            // 현재 바람세기를 추가
            board[cur[0]][cur[1]] += cur[2];
            // 현재 칸의 벽 정보 가져오기
            boolean[] c_wall = wall.getOrDefault(cur[0] + " " + cur[1], new boolean[4]);
            // 양 옆칸 벽 정보 가져오기
            int chk1 = (dir+3)%4, chk2 = (dir+1)%4;
            
            // 바람 세기가 1 초과라면
            if(cur[2] > 1){
            	// 진행 방향 기준 왼쪽 칸 벽 정보
                boolean[] chk1_wall = wall.getOrDefault((cur[0] + d[chk1][0]) + " " + (cur[1] + d[chk1][1]), new boolean[4]);
                // 범위 안이고, 방문 전이고, 벽으로 막히지 않았다면 다음 탐색에 추가 - 3칸 모두 확인
                if(chk_range(cur[0]+d[chk1][0]+d[dir][0], cur[1]+d[chk1][1]+d[dir][1]) && !visited[cur[0]+d[chk1][0]+d[dir][0]][cur[1]+d[chk1][1]+d[dir][1]] && !c_wall[chk1] && !chk1_wall[dir]) {
                	dq.offer(new int[]{cur[0]+d[chk1][0]+d[dir][0], cur[1]+d[chk1][1]+d[dir][1], cur[2]-1});
                	visited[cur[0]+d[chk1][0]+d[dir][0]][cur[1]+d[chk1][1]+d[dir][1]] = true;
                }
                if(chk_range(cur[0]+d[dir][0], cur[1]+d[dir][1]) && !visited[cur[0]+d[dir][0]][cur[1]+d[dir][1]] && !c_wall[dir]) {
                	dq.offer(new int[] {cur[0]+d[dir][0], cur[1]+d[dir][1], cur[2]-1});
                	visited[cur[0]+d[dir][0]][cur[1]+d[dir][1]] = true;
                }
                boolean[] chk2_wall = wall.getOrDefault((cur[0] + d[chk2][0]) + " " + (cur[1] + d[chk2][1]), new boolean[4]);
                if(chk_range(cur[0]+d[chk2][0]+d[dir][0], cur[1]+d[chk2][1]+d[dir][1]) && !visited[cur[0]+d[chk2][0]+d[dir][0]][cur[1]+d[chk2][1]+d[dir][1]] && !c_wall[chk2] && !chk2_wall[dir]) {
                	dq.offer(new int[]{cur[0]+d[chk2][0]+d[dir][0], cur[1]+d[chk2][1]+d[dir][1], cur[2]-1});
                	visited[cur[0]+d[chk2][0]+d[dir][0]][cur[1]+d[chk2][1]+d[dir][1]] = true;
                }
            }
        }
    }
    
    // 온도 평균 구하는 함수
    public static void average(int r, int c){
    	// 현재 칸의 온도 기록
        int cur = board[r][c];
        // 최종 온도 저장할 변수
        int cur_t = cur;
        // 현재 칸의 벽 정보를 가져오기 위한 키 생성
        String key = r + " " + c;
        // 벽 정보
        boolean[] c_wall = wall.getOrDefault(key, new boolean[4]);
        
        // 4방향 체크
        for(int dd = 0; dd < 4; dd++){
        	// 새 좌표
            int nr = r + d[dd][0], nc = c + d[dd][1];
            // 범위 체크
            boolean in_range = chk_range(nr, nc);
            // 범위 안이고, 현재 칸의 온도가 더 높고, 벽으로 막히지 않았다면
            if(in_range && board[nr][nc] < cur && !c_wall[dd]){
            	// 상승시킬 온도 계산
                int new_t = (int)((cur - board[nr][nc])/4);
                // 현재 온도에서 감소
                cur_t -= new_t;
                // 새로운 칸에 증가
                n_board[nr][nc] += new_t;
            }
        }
        
        // 현재 칸에 남은 온도 저장
        n_board[r][c] += cur_t;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        board = new int[R][C];
        n_board = new int[R][C];
        
        // 초기 입력
        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++){
                int cur = Integer.parseInt(st.nextToken());
                if(cur == 5)check.add(new int[] {r, c});
                else if(cur != 0){
                    heat.add(new int[] {r, c, heat_dir[cur]});
                    h_cnt++;
                }
            }
        }
        
        
        // 벽 정보 입력
        int W = Integer.parseInt(br.readLine());
        
        for(int w = 0; w < W; w++){
            st = new StringTokenizer(br.readLine());
            int wr = Integer.parseInt(st.nextToken())-1;
            int wc = Integer.parseInt(st.nextToken())-1;
            String key = wr + " " + wc;
            int wdir = Integer.parseInt(st.nextToken());
            
            // 벽 정보 기준 양쪽 칸 모두에 저장
            // 상하
            if(wdir == 0){
                boolean[] cur_wall = wall.getOrDefault(key, new boolean[4]);
                cur_wall[0] = true;
                wall.put(key, cur_wall);
                String t_key = (wr-1) + " " + wc;
                boolean[] top_wall = wall.getOrDefault(t_key, new boolean[4]);
                top_wall[2] = true;
                wall.put(t_key, top_wall);
            }
            // 좌우
            else {
                boolean[] cur_wall = wall.getOrDefault(key, new boolean[4]);
                cur_wall[1] = true;
                wall.put(key, cur_wall);
                String r_key = wr + " " + (wc+1);
                boolean[] right_wall = wall.getOrDefault(r_key, new boolean[4]);
                right_wall[3] = true;
                wall.put(r_key, right_wall);
            }
        }
        
        // 반복 종료 확인 변수
        boolean fin = false;
        // 아직 종료되지 않았고, 초콜릿을 100번 넘게 먹지 않았다면
        while(!fin && ans < 101){
            // 1. 모든 온풍기에서 바람 출력
            for(int i = 0; i < h_cnt; i++){
                int[] cur_h = heat.get(i);
                fill(cur_h[0], cur_h[1], cur_h[2]);
            }
            
            // 2. 온도 평균
            for(int r = 0; r < R; r++){
                for(int c = 0; c < C; c++){
                    average(r, c);
                }
            }
            board = n_board;
            n_board = new int[R][C];
            
            // 3. 테두리 -1
            board[0][0]++;
            board[0][C-1]++;
            board[R-1][0]++;
            board[R-1][C-1]++;
            for(int r = 0; r < R; r++){
                if(board[r][0] > 0) board[r][0]--;
                if(board[r][C-1] > 0) board[r][C-1]--;
            }
            for(int c = 0; c < C; c++){
                if(board[0][c] > 0) board[0][c]--;
                if(board[R-1][c] > 0) board[R-1][c]--;
            }
            // 4. 카운트 1 증가
            ans++;
            // 5. 모든 칸의 온도가 K이상 확인 후 아니면 다시 반복
            fin = true;
            for(int[] chk_cell : check){
                int chk_r = chk_cell[0], chk_c = chk_cell[1];
                if(board[chk_r][chk_c] < K) {
                    fin = false;
                    break;
                }
            }
        }
        
        // 최종 정답 출력
        System.out.println(ans);
    }
}