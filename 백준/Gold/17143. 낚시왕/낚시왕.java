import java.io.*;
import java.util.*;

public class Main {
    
    //BOJ 17143 - 낚시왕
    public static int R, C, M;
    public static int[][] board, shark;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new int[R][C];
        shark = new int[M][6];
        
        // 상어의 상태 입력
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            // 위치
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            // 속도
            int s = Integer.parseInt(st.nextToken());
            // 위:0 / 아래:1 / 오른쪽:2 / 왼쪽:3
            int d = Integer.parseInt(st.nextToken()) - 1;
            // 크기
            int z = Integer.parseInt(st.nextToken());
            
            // 현재 상태의 상어 저장
            // 마지막 값 (살아있으면 1, 아니면 0)
            shark[i] = new int[] {r, c, s, d, z, 1};
            // i번째 상어 저장
            board[r][c] = i+1;
        }
        
        // 낚시꾼이 잡은 상어의 크기 합
        int ans = 0;
        
        // 낚시꾼의 위치 i
        for(int i = 0; i < C; i++){
            // 현재 가장 가까운 상어 구하기
            for(int j = 0; j < R; j++){
                // 가장 가까운 상어를 발견하면
                if(board[j][i] != 0){
                    // 해당 상어 크기 더하기
                    ans += shark[board[j][i]-1][4];
                    // 해당 상어 죽음 표시
                    shark[board[j][i]-1][5] = 0;
                    // 해당 칸 빈칸 처리
                    board[j][i] = 0;
                    // 상어 잡기 끝
                    break;
                }
            }
            
            // 상어 이동 시작
            int[][] n_board = new int[R][C];
            for(int j = 0; j < M; j++){
                // 현재 상어 정보
                // 0:row, 1:col, 2:속도, 3:방향, 4:크기, 5:생존
                int[] cur = shark[j];
                
                // 상어가 죽은 상태라면 스킵
                if(cur[5] == 0) continue;
                // 아니라면 이동
                // 다시 원래 위치로 돌아오기 위한 이동 칸수
                int pivotR = (R-1)*2, pivotC = (C-1)*2;
                // 이동 방향에 따라 이동 진행
                switch(cur[3]) {
                case 0: // 상
                	// 현재 위치에서 속력만큼 위로 이동
                    cur[0] -= cur[2];
                    // 속도가 0이 아니고 제자리로 돌아왔다면 방향은 반대로
                    if(cur[0] == pivotR && cur[2] != 0) cur[3] = 1;
                    // 반복 후 남은 거리만큼 추가로 얼마나 더 이동했는지 계산
                    cur[0] %= pivotR;
                    // 현재 위치가 음수이거나 범위 밖이면 처리
                    while(cur[0] < 0 || R <= cur[0]){
                    	// 음수라면
                        if(cur[0] < 0) {
                        	// 절댓값 처리
                            cur[0] = Math.abs(cur[0]);
                            // 방향 전환
                            cur[3] = 1;
                        }
                        // 범위 밖이라면
                        else {
                        	// 범위 안으로 다시 반대방향으로 이동
                            cur[0] = pivotR - cur[0];
                            // 방향 다시 전환
                            cur[3] = 0;
                        }
                    }
        	        break;
                case 1: // 하
                	// 현재 위치에서 속력만큼 아래로 이동
                    cur[0] += cur[2];
                    // 속도가 0이 아니고 제자리로 돌아왔다면 방향은 반대로
                    if(cur[0] == pivotR && cur[2] != 0) cur[3] = 0;
                    // 반복 후 남은 거리만큼 추가로 얼마나 더 이동했는지 계산
                    cur[0] %= pivotR;
                    // 범위 밖인 경우만 고려 (아래로 이동할 때는 음수로는 가는 경우는 없음)
                    while(R <= cur[0]){
                    	// 범위 안으로 반대 방향으로 이동
                        cur[0] = pivotR - cur[0];
                        // 방향 전환
                        cur[3] = 0;
                    }
        	        break;
        	    // 가로열에 대해서도 같은 로직 적용
                case 2: // 우
                    cur[1] += cur[2];
                    if(cur[1] == pivotC && cur[2] != 0) cur[3] = 2;
                    cur[1] %= pivotC;
                    while(C <= cur[1]){
                        cur[1] = pivotC - cur[1];
                        cur[3] = 3;
                    }
        	        break;
                case 3: // 좌
                    cur[1] -= cur[2];
                    if(cur[1] == pivotC && cur[2] != 0) cur[3] = 3;
                    cur[1] %= pivotC;
                    while(cur[1] < 0 || C <= cur[1]){
                        if(cur[1] < 0) {
                            cur[1] = Math.abs(cur[1]);
                            cur[3] = 2;
                        }
                        else {
                            cur[1] = pivotC - cur[1];
                            cur[3] = 3;
                        }
                    }
        	        break;                    
                }
                
                // 이동한 현재 위치에 이미 다른 상어가 있다면
                if(n_board[cur[0]][cur[1]] != 0) {
                	// 이전 상어의 상태 구하기
                	int before = n_board[cur[0]][cur[1]] - 1;
                	// 이전 상어가 현재 상어보다 작다면
                	if(shark[before][4] < cur[4]) {
                		// 이전 상어 사망
                		shark[before][5] = 0;
                		// 현재 위치에는 현재 상어 저장
                		n_board[cur[0]][cur[1]] = j+1;
                	}
                	// 현재 상어가 더 작다면
                	else {
                		// 현재 상어 사망
                		cur[5] = 0;
                		// 현재 위치에는 기존 그대로 상어 유지
                	}
                }
                // 다른 상어가 없다면 바로 저장
                else n_board[cur[0]][cur[1]] = j+1;
            }
            // 새로 이동한 보드 갱신
            board = n_board;
        }
        
        // 최종 잡은 상어의 무게 출력
        System.out.println(ans);
    }
}