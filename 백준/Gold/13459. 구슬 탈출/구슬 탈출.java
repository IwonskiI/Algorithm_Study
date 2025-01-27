import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 13459 - 구슬 탈출
    public static int R, C, rrow, rcol, brow, bcol;
    public static int[][] board, d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 보드 크기 입력
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        // 보드 초기화
        board = new int[R][C];
        
        // 초기 상태 입력 및 구슬 위치 저장
        for(int r = 0; r < R; r++){
            String line = br.readLine();
            for(int c = 0; c < C; c++){
                char cur = line.charAt(c);
                if(cur == '#') board[r][c] = -1;
                else if(cur == 'O') board[r][c] = 1;
                else if(cur == 'R') {rrow = r; rcol = c;}
                else if(cur == 'B') {brow = r; bcol = c;}
            }
        }
        
        // 움직인 횟수 저장
        int lv = 0;
        // BFS 탐색을 위한 deque
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {rrow, rcol, brow, bcol, lv});
        
        // BFS 탐색 시작
        while(!dq.isEmpty()){
        	// 0:red_row, 1:red_col, 2:blue_row, 3:blue_col, 4:level
            int[] cur = dq.poll();
            
            // 4방향 탐색
            for(int dd = 0; dd < 4; dd++) {
            	// 새로운 빨간공, 파란공 좌표를 저장할 배열
                int[] nrpos = new int[] {cur[0], cur[1]};
                int[] nbpos = new int[] {cur[2], cur[3]};
                // 빨간공, 파란공이 구멍에 빠졌는지 체크할 변수
                boolean rfin = false, bfin = false;
                // 벽에 부딪히기 전까지 빨간공 이동
                while(board[nrpos[0] + d[dd][0]][nrpos[1] + d[dd][1]] != -1){
                    nrpos = new int[] {nrpos[0] + d[dd][0], nrpos[1] + d[dd][1]};
                    // 만약 이동한 좌표가 구멍이라면 빨간공 들어갔다고 체크후 종료
                    if(board[nrpos[0]][nrpos[1]] == 1){
                        rfin = true;
                        break;
                    }
                }
                // 파란 공도 같은 로직으로 진행
                while(board[nbpos[0] + d[dd][0]][nbpos[1] + d[dd][1]] != -1){
                    nbpos = new int[] {nbpos[0] + d[dd][0], nbpos[1] + d[dd][1]};
                    if(board[nbpos[0]][nbpos[1]] == 1){
                        bfin = true;
                        break;
                    }
                }
                // 만약 파란공이 빠졌다면 더이상 진행 X
                if(bfin) continue;
                // 파란공이 빠지지 않고 빨간공이 들어갔다면 성공 후 종료
                else if(rfin){
                    System.out.println(1);
                    return;
                }
                // 두 공이 구멍에 빠지지 않았는데 좌표가 겹친 경우
                // 기존 위치 고려하여 좌표 수정
                if(nrpos[0] == nbpos[0] && nrpos[1] == nbpos[1]) {
                	if(dd == 0) {
                		if(cur[0] < cur[2]) nbpos = new int[] {nbpos[0]+1, nbpos[1]};
                		else nrpos = new int[] {nrpos[0]+1, nrpos[1]};
                	}
                	else if(dd == 1) {
                		if(cur[1] > cur[3]) nbpos = new int[] {nbpos[0], nbpos[1]-1};
                		else nrpos = new int[] {nrpos[0], nrpos[1]-1};
                	}
                	else if(dd == 2) {
                		if(cur[0] > cur[2]) nbpos = new int[] {nbpos[0]-1, nbpos[1]};
                		else nrpos = new int[] {nrpos[0]-1, nrpos[1]};
                	}
                	else if(dd == 3) {
                		if(cur[1] < cur[3]) nbpos = new int[] {nbpos[0], nbpos[1]+1};
                		else nrpos = new int[] {nrpos[0], nrpos[1]+1};
                	}
                }
                // 이번 움직임으로 10번을 모두 움직였다면 queue에 추가하지 않고 스킵
                if(cur[4] + 1 == 10) continue;
                // 아니라면 더 움직여보기 위해 queue에 새 좌표 및 횟수 추가해서 저장
                else {
                    dq.offer(new int[] {nrpos[0], nrpos[1], nbpos[0], nbpos[1], cur[4]+1});
                }
            }
        }
        // queue가 빌 때까지 모두 탐색해도 성공하지 못했다면 0 출력 후 종료
        System.out.println(0);
        return;
    }
}