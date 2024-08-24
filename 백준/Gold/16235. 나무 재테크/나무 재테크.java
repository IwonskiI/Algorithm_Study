import java.util.*;
import java.io.*;

public class Main {

    // BOJ 16235 - 나무 재테크 

	// 8방향 탐색 델타 값
	static int[][] d = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        // 증가할 양분을 저장할 보드
        int[][] A = new int[N][N];
        // 현재 양분을 저장할 보드
        int[][] board = new int[N][N];
        // 초기 값 설정 - 양분 입력 및 초기 양분 5로 설정
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < N; c++) {
        		A[r][c] = Integer.parseInt(st.nextToken());
        		board[r][c] = 5;
        	}
        }
        // 살아있는 나무를 관리할 dq
        Deque<int[]> dq = new ArrayDeque<>();
        
        // 초기에 심은 나무 입력
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	// r좌표, c좌표, 나무의 나이를 순서대로 입력
        	dq.offer(new int[] { Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())});
        }
        
        // K년 후 까지 시뮬레이션 진행
        for(int i = 0; i < K; i++) {
        	
    		// 봄 : pq에 담긴 나무들을 나이가 어린 순서대로 뽑아가며 양분 흡수
        	Queue<int[]> die = new LinkedList<>();
        	for(int j = 0; j < dq.size(); j++) {
        		int[] cur = dq.poll();
        		// 만약 양분이 부족해서 흡수하지 못한다면
    			if(board[cur[0]][cur[1]] < cur[2]) {
    				// 죽은 나무 리스트에 추가
    				die.offer(cur);
    				// size가 변경되었기 때문에 j--로 인덱스 조정
    				j--;
    			}
    			// 양분 흡수가 된다면
    			else {
    				// 양분 흡수
    				board[cur[0]][cur[1]] -= cur[2];
    				// 성장한 나무 다시 추가
        			dq.offer(new int[] {cur[0], cur[1], cur[2]+1});
    			}
        	}
        	
    		// 여름 : pq에 남은 나무(죽은 나무)의 나이의 반을 양분으로 추가
    		while(!die.isEmpty()) {
    			int[] cur = die.poll();
    			board[cur[0]][cur[1]] += (cur[2]/2);
    		}
        
    		// 가을 : 나무 번식 - 성장한 나무 확인
    		// 번식할 나무 체크
    		Queue<int[]> temp_lst = new LinkedList<>();
    		for(int j = 0; j < dq.size(); j++) {
    			int[] cur = dq.poll();
    			// 5의 배수라면  temp_lst에 추가 (나중에 한번에 번식)
    			if(cur[2] % 5 == 0) {
    				temp_lst.offer(cur);
    			}
    			dq.offer(cur);
    		}
    		// 번식 진행
    		while(!temp_lst.isEmpty()) {
    			int[] cur = temp_lst.poll();
    			// 8방향 번식 진행
				for(int dd = 0; dd < 8; dd++) {
					int nr = cur[0] + d[dd][0], nc = cur[1] + d[dd][1];
					boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
					// 8방향이 범위 안이라면 번식
					// offerFirst를 통해 가장 먼저 양분을 먹도록 관리
					if(in_range) dq.offerFirst(new int[] {nr, nc, 1});
				}
    		}
    		// 겨울 : 양분 추가 - 모든 나무가 번식 진행 후에 양분을 추가해줘야하므로 반복문 밖에서 진행
    		for(int rr = 0; rr < N; rr++) {
    			for(int cc = 0; cc < N; cc++) {
    				board[rr][cc] += A[rr][cc];
    			}
    		}
        }
        
        // 살아있는 나무 카운트
        System.out.println(dq.size());
    }
}
