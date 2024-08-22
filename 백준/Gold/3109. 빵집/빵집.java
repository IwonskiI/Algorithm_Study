import java.util.*;
import java.io.*;

public class Main {
    // BOJ 3109 - 빵집
	public static int R, C, ans;
	public static int[][] board;
	public static int[][] d = new int[][] {{-1, 1}, {0, 1}, {1, 1}};
	
	// DFS 탐색
	public static boolean DFS(int r , int c) {
		// 마지막 열에 도착하면 종료
		if(c == C - 1) {
			board[r][c] = 1;
			return true;
		}
		// 마지막 열이 아니라면 현재 경로 1로 저장
		board[r][c] = 1;
		// 으상향 대각, 오른쪽, 우하향 대각 순서로 탐색
		for(int dd = 0; dd < 3; dd++) {
			int nr = r  + d[dd][0], nc = c + d[dd][1];
			boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
			// 좌표가 범위 안이면서, 아직 방문 전이고, 해당 위치로 가서 끝점까지 도달 가능하면 true 반환
			if(in_range && board[nr][nc] == 0 && DFS(nr, nc)) return true;
			// 만약 범위 안이면서 이미 불가능한 지점이라고 판단되면 해당 칸도 -1로 저장하고 스킵
			else if(in_range && board[nr][nc] == -1) {
				board[r][c] = -1;
				continue;
			}
			// 만약 불가능하다면 다음 선택지 탐색
			else
				continue;
		}
		// 탐색을 마쳐도 끝점에 도달하지 못했다면 경로 탐색 취소
		board[r][c] = -1;
		// 탐색 실패로 false 반환
		return false;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        ans = 0;
        // 길 초기화 (0 은 비어있는 길, 1은 지나온 길, -1은 불가능한 길)
        for(int r = 0; r < R; r++) {
        	String[] str = br.readLine().split("");
        	for(int c = 0; c < C; c++) {
        		if(str[c].equals("x"))
        			board[r][c] = 1;
        	}
        }
        
        // 첫 열에서 DFS 탐색
        for(int r = 0; r < R; r++) {
        	// DFS에서 true를 반환하면 (끝까지 경로를 찾으면) ans 증가
        	if(DFS(r, 0)) ans++;
        }
        
        // 결과 출력
        System.out.println(ans);
    }
}