import java.io.*;
import java.util.*;

public class Main {

    // BOJ 14499 - 주사위 굴리기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        // 이동 방향(1:동/2:서/3:북/4:남)
        int[][] d = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        
        // 보드의 크기 N*M
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        // 시작 좌표
        int sr = Integer.parseInt(st.nextToken()), sc = Integer.parseInt(st.nextToken());
        // 이동 횟수
        int K = Integer.parseInt(st.nextToken());
        
        // 보드 생성
        int[][] board = new int[N][M];
        // 주사위 생성
        int[] dice = new int[6];
        // cur: 현재 윗면 / top: 북쪽면 / right: 동쪽면
        int cur = 0, top = 1, right = 2;
        // 현재 좌표
        int cr = sr, cc = sc;
        
        // 보드 입력
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		board[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 이동 명령 입력
        st = new StringTokenizer(br.readLine());
        
        // 이동 명령 진행
        for(int i = 0; i < K; i++) {
        	// 이동 명령 -1 (인덱스로 활용하기 위해)
        	int dd = Integer.parseInt(st.nextToken()) - 1;
        	// 범위 체크 (현재 위치에서 이동방향으로 이동했을 때 범위 안인지 검사)
        	boolean in_range = (0 <= cr + d[dd][0] && cr + d[dd][0] < N) && (0 <= cc + d[dd][1] && cc+d[dd][1] < M);
        	// 범위 밖이면 해당 이동 무시 후 다음 이동 진행
        	if(!in_range) continue;
        	
        	// 현재 좌표 이동
        	cr += d[dd][0];
        	cc += d[dd][1];

        	// 주사위 면을 임시로 저장할 변수
        	int temp = 0;
        	// 방향별로 주사위 이동 진행
        	switch(dd) {
        	// 동쪽
        	case 0:
        		// 이동한 곳의 보드판이 0이라면
        		if(board[cr][cc] == 0) {
        			// 보드판에 주사위 아랫면을 복사
        			board[cr][cc] = dice[right];
        		}
        		// 보드판이 0이 아니라면
        		else {
        			// 현재 주사위의 아랫면에 보드판 값을 복사
        			dice[right] = board[cr][cc];
        			// 이후 보드판은 0으로 초기화
        			board[cr][cc] = 0;
        		}
        		// 현재 주사위 면 갱신
        		// 오른쪽면 숫자 임시 저장
        		temp = right;
        		// 오른쪽으로 굴렀으므로 기존 윗면이 오른쪽을 향하게 되고
        		right = cur;
        		// 기존 오른쪽 면의 반대 숫자가 윗면이 된다.
        		cur = 5 - temp;
        		break;
        	// 다른 방향도 동일한 로직으로 진행
        	// 서쪽
        	case 1:
        		if(board[cr][cc] == 0) {
        			board[cr][cc] = dice[5-right];
        		}
        		else {
        			dice[5-right] = board[cr][cc];
        			board[cr][cc] = 0;
        		}
        		temp = right;
        		right = 5 - cur;
        		cur = temp;
        		break;
        	// 북쪽
        	case 2:
        		if(board[cr][cc] == 0) {
        			board[cr][cc] = dice[top];
        		}
        		else {
        			dice[top] = board[cr][cc];
        			board[cr][cc] = 0;
        		}
        		temp = top;
        		top = cur;
        		cur = 5 - temp;
        		break;
        	// 남쪽
        	case 3:
        		if(board[cr][cc] == 0) {
        			board[cr][cc] = dice[5-top];
        		}
        		else {
        			dice[5-top] = board[cr][cc];
        			board[cr][cc] = 0;
        		}
        		temp = top;
        		top = 5 - cur;
        		cur = temp;
        		break;
        	}
        	// 현재 윗면에 저장되어있는 숫자 출력
        	sb.append(dice[cur]).append("\n");
        }
        
        
        // 이동 종료 후 정답 출력
        System.out.println(sb.toString());
        
    }

}