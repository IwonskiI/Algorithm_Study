import java.util.*;
import java.io.*;

public class Main {

	// BOJ 1987 - 알파벳
	// 정답을 저장할 변수 및 board 크기 정의
	public static int ans, R, C;
	public static char[][] board;
	// 방문 배열
	public static boolean[][] visited;
	// 방문했던 문자인지 관리할 Set
	public static HashSet<Character> map = new HashSet<>();
	// 4방향 탐색
	public static int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	// dfs 탐색
	public static void dfs(int r, int c, int dist) {
		// 막다른 길을 만났는지 확인할 변수 - 더 이상 진행이 불가능하면 true, 진행 가능하면 false
		boolean complete = true;
		// 4방향 탐색
		for(int dd = 0; dd < 4; dd++) {
			// 새 좌표 계산
			int nr = r + d[dd][0], nc = c + d[dd][1];
			// 범위 체크
			boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
			// 범위 안이라면,
			if(in_range) {
				// 새 좌표의 칸의 글자를 이미 방문한 적이 있는지 체크 (있다면 true, 없다면 false)
				// 한 방향이라도 진행할 수 있으면 아직 거리 계산 X
				complete &= map.contains(board[nr][nc]);
				// 아직 방문하지 않은 글자이고, 해당 칸을 방문 전이라면,
				if(!map.contains(board[nr][nc]) && !visited[nr][nc]) {
					// 해당 칸 방문 처리
					visited[nr][nc] = true;
					// 해당 문자 방문 처리
					map.add(board[nr][nc]);
					// 다음 칸으로 이동 (거리는 1 증가)
					dfs(nr, nc, dist+1);
					// 탐색 끝나면 해당 글자 삭제
					map.remove(board[nr][nc]);
					// 방문 처리도 취소
					visited[nr][nc] = false;
				}
			}
		}
		// 마주한 모든 칸이 이미 방문했거나 같은 글자라서 탐색이 완료되었다면
		if(complete) {
			// 현재 답과 지금까지의 거리를 비교해서 더 큰 값 저장
			if(ans < dist) ans = dist;
			// dfs 종료
			return;
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        ans = 0;
        // 초기 입력 처리
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        for(int r = 0; r < R; r++) {
        	String str = br.readLine();
        	for(int c = 0; c < C; c++) {
        		board[r][c] = str.charAt(c);
        	}
        }
        
        // 첫 번째 칸 방문 처리 및 방문 글자 추가
        visited[0][0] = true;
        map.add(board[0][0]);
        // dfs 탐색 시작
        dfs(0, 0, 1);
        // 답 출력
        System.out.println(ans);
        
    }
}