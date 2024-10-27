import java.io.*;
import java.util.*;

public class Main {

    // BOJ 15685 - 드래곤 커브
	public static int ans, SIN = (int) Math.sin(Math.toRadians(90)), COS = (int) Math.cos(Math.toRadians(90));
	public static int[][] d = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	public static boolean[][] visited = new boolean[101][101];
	
	// 드래곤 커브 그리기 함수
	public static void dragon(int c, int r, int dir, int gen) {
		// 현재 커브 상태를 저장
		Deque<int[]> q = new ArrayDeque<>();
		
		// 시작점 입력
		q.offer(new int[] {r, c});
		// 드래곤 커브 표시
		visited[r][c] = true;
		// 시작 방향으로 커브 진행
		q.offer(new int[] {r + d[dir][0], c + d[dir][1]});
		// 시작 방향으로 뻗은 커브 표시
		visited[r + d[dir][0]][c + d[dir][1]] = true;
		// 세대 반복
		for(int i = 0; i < gen; i++) {
			// 새로운 커브를 저장할 임시 큐
			Deque<int[]> temp = new ArrayDeque<>();
			// 회전 축이 될 중심 좌표(마지막 좌표)
			int[] center = q.pollLast();
			int cr = center[0], cc = center[1];
			// 중심 좌표 추가
			temp.offer(center);
			// 나머지 좌표 처리
			while(!q.isEmpty()) {
				int[] cur = q.pollLast();
				// 이동할 좌표
				int or = cur[0], oc = cur[1];
				// 90도 회전한 좌표
				int nr = (oc - cc) * SIN + (or - cr) * COS + cr;
				int nc = (oc - cc) * COS - (or - cr) * SIN + cc;
				// 새로운 좌표 커브 표시
				visited[nr][nc] = true;
				// 새로운 좌표는 큐의 뒤에 추가
				temp.offer(new int[] {nr, nc});
				// 기존 좌표는 큐의 앞에 추가
				temp.offerFirst(cur);
			}
			// q를 temp로 갱신
			q = temp;
		}
		
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        ans = 0;
        int N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++) {
        	// 드래곤 커브 그리기
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int g = Integer.parseInt(st.nextToken());
        	dragon(x, y, d, g);
        }
        
        // 커브에 포함된 사각형 카운트
        for(int r = 0; r < 100; r ++) {
        	for(int c = 0; c < 100; c++) {
        		// 각 꼭지점에서 시작하는 사각형이 드래곤 커브에 포함되는지 체크
        		if(visited[r][c] && visited[r+1][c] && visited[r][c+1] && visited[r+1][c+1])
        			ans++;
        	}
        }
        
        // 네 꼭짓점이 모두 드레곤 커브인 사각형의 개수 출력
        System.out.println(ans);
    	return;
    }
}