import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 11559 - Puyo Puyo
	public static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        char[][] board = new char[12][6];
        // 블록이 있는 가장 윗줄은 마지막 줄로 초기화
        int top = 11;
        boolean istop = true;
        // 초기 입력 + 가장 윗줄 찾기
        for(int r = 0; r < 12; r++) {
        	// 블록이 있는 줄을 찾으면 top 갱신
        	if(!istop && top == 11) top = r - 1;
        	String[] line = br.readLine().split("");
        	for(int c = 0; c < 6; c++) {
        		board[r][c] = line[c].charAt(0);
        		if(board[r][c] != '.') istop = false;
        	}
        }
        
        // 터질 폭탄이 없을 때까지 반복
        while(true) {
        	// 방문 배열
        	boolean[][] visited = new boolean[12][6];
        	// 터질 폭탄 (윗줄부터 제거하며 한칸씩 내리기 위해 우선순위 큐 사용)
        	PriorityQueue<int[]> bomb_lst = new PriorityQueue<>((a, b)->{
        		if(a[0] == b[0]) return a[1] - b[1];
        		else return a[0] - b[0];
        	});
        	
        	// 폭탄이 있는 가장 윗줄부터 탐색
        	for(int r = top; r < 12; r++) {
        		for(int c = 0; c < 6; c++) {
        			// 빈 칸이 아니고, 아직 방문 전이라면,
        			if(board[r][c] != '.' && !visited[r][c]) {
        				// 연결된 폭탄의 개수(초기 1) 
        				int bomb = 1;
        				// 현재 탐색중인 폭탄의 종류
        				char base = board[r][c];
        				// 탐색을 진행하기 위한 queue와 폭탄의 좌표를 담아둘 queue
        				Deque<int[]> dq = new ArrayDeque<>();
        				Queue<int[]> queue = new LinkedList<>();
        				dq.offer(new int[] {r, c});
        				queue.offer(new int[] {r, c});
        				visited[r][c] = true;
        				// bfs 탐색으로 연결된 폭탄 검사
        				while(!dq.isEmpty()) {
        					int[] cur = dq.poll();
        					int cr = cur[0], cc = cur[1];
        					for(int dd = 0; dd < 4; dd++) {
        						int nr = cr + d[dd][0], nc = cc + d[dd][1];
        						boolean in_range = (top <= nr && nr < 12) && (0 <= nc && nc < 6);
        						// 범위 안이면서 현재 탐색중인 종류와 같은 폭탄이고, 아직 방문 전이라면,
        						if(in_range && board[nr][nc] == base && !visited[nr][nc]) {
        							// 폭탄 개수 증가
        							bomb++;
        							// 방문 처리
        							visited[nr][nc] = true;
        							// 다음 탐색을 위한 queue 추가
        							dq.offer(new int[] {nr, nc});
        							// 4개 이상 연결된다면 터뜨리기 위해 좌표 저장
        							queue.offer(new int[] {nr, nc});
        						}
        					}
        				}
        				
        				// 연결된 폭탄이 4개 이상이라면
        				if(bomb >= 4) {
        					// 이번 턴에 터뜨릴 폭탄에 추가
        					while(!queue.isEmpty()) {
        						bomb_lst.offer(queue.poll());
        					}
        				}
        			}
        		}
        	}
        	// 터뜨릴 폭탄이 없다면 반복문 종료
        	if(bomb_lst.isEmpty()) break;
        	// 터뜨릴 폭탄이 있다면
        	else {
        		// 연쇄 수 증가
        		ans++;
        		// 폭탄 제거 시작
        		while(!bomb_lst.isEmpty()) {
        			int[] cur = bomb_lst.poll();
					int r = cur[0], c = cur[1];
					// 범위 안에서, 윗 줄이 빈칸이 아닐 때 까지
        			while(0 < r && !(board[r-1][c] == '.')) {
        				// 바로 윗 줄 블럭을 아래에 저장
        				board[r][c] = board[r-1][c];
        				// 윗줄로 이동
        				r--;
        			}
        			// 마지막 줄 빈칸으로 저장
        			board[r][c] = '.';
        		}
        	}
        }
        
        
        // 최종 결과 출력
        System.out.println(ans);
    }

}