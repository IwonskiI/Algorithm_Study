import java.io.*;
import java.util.*;

public class Main {

    // BOJ 17406 - 배열 돌리기 4
    public static int N, M, K, ans;
    public static int[][] board, turn_lst;
    public static boolean[] using;
    
    // 회전 연산 
    public static void perm(int cnt) {
    	// 기저 조건 - K번 다 돌렸다면
    	if(cnt == K) {
    		// 각 행의 합을 구하고 최솟값 갱신
    		for(int r = 0; r < N; r++) {
    			int sum = 0;
    			for(int c = 0; c < M; c++) {
    				sum += board[r][c];
    			}
    			ans = Math.min(ans, sum);
    		}
    		return;
    	}
    	
    	// 백트래킹 진행
    	for(int i = 0; i < K; i++) {
    		// 해당 회전 사용했으면 스킵
    		if(using[i]) continue;
    		// 사용 여부 체크
    		using[i] = true;
    		// 현재 회전 정보 가져오기
    		int[] cur = turn_lst[i];
    		// 시작좌표, 배열의 크기 계산
    		int sr = cur[0]-cur[2]-1, sc = cur[1]-cur[2]-1, size = 2*cur[2] + 1;
    		// 회전 전 배열 저장
    		int[][] prev = new int[size][size];
    		for(int r = sr, ir = 0; r < sr+size; r++, ir++) {
    			for(int c = sc, ic = 0; c < sc+size; c++, ic++) {
    				prev[ir][ic] = board[r][c];
    			}
    		}
    		// 회전 연산 실행 (테두리부터 한바퀴씩 회전)
    		while(size > 1) {
    			// 첫번째 값 미리 저장
    			int temp = board[sr][sc];
    			// 시작좌표 초기화
    			int r = sr, c = sc;
    			// 아래에 있는 값 위로 올리기
    			for(; r < sr+size-1; r++) {
    				board[r][c] = board[r+1][c];
    			}
    			// 오른쪽 값 왼쪽으로 옮기기
    			for(; c < sc+size-1; c++) {
    				board[r][c] = board[r][c+1];
    			}
    			// 위의 값 아래로 옮기기
    			for(; r > sr; r--) {
    				board[r][c] = board[r-1][c];
    			}
    			// 왼쪽 값 오른쪽으로 옮기기
    			for(; c > sc+1; c--) {
    				board[r][c] = board[r][c-1];
    			}
    			// 마지막 값은 제일 처음에 저장해둔 값 저장
    			board[r][c] = temp;
    			
    			// 회전 완료 후 한 칸 안쪽으로 이동해서 다시 회전
    			sr += 1;
    			sc += 1;
    			size -= 2;
    		}
    		// 회전 완료 후 다음 회전 계산
    		perm(cnt+1);
    		//백트래킹 - 이전 값으로 복원
    		// 시작좌표, 배열의 크기 계산
    		sr = cur[0]-cur[2]-1;
    		sc = cur[1]-cur[2]-1;
    		size = 2*cur[2] + 1;
    		for(int r = sr, ir = 0; r < sr+size; r++, ir++) {
    			for(int c = sc, ic = 0; c < sc+size; c++, ic++) {
    				board[r][c] = prev[ir][ic];
    			}
    		}
    		// 회전 사용 여부 해제
    		using[i] = false;
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 보드의 크기 N*M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 회전 연산의 개수 K
        K = Integer.parseInt(st.nextToken());
        
        // 배열을 저장
        board = new int[N][M];
        // 회전 정보를 저장
        turn_lst = new int[K][3];
        // 회전 정보의 사용 배열
        using = new boolean[K];
        
        // 배열 저장
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		board[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 회전 정보 저장
        for(int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < 3; j++) {
        		turn_lst[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 최소값을 구하는 것이므로 정수의 최댓값으로 초기화
        ans = Integer.MAX_VALUE;
        // 경우의 수 계산
        perm(0);
        
        // 최솟값 출력
        System.out.println(ans);
    }

}