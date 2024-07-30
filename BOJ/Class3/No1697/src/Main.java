import java.util.*;
import java.io.*;

public class Main {

	// BOJ 1697 - 숨바꼭질
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n에서 k로 가는 길을 찾아야 하므로, 반대로 k에서 n으로 가는 길을 모두 찾아 최소 값을 찾는다.
		
		int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
		// +1, -1, /2 한 값들을 q에 넣어서 bfs로 방문하며 순회
		Deque<int[]> dq = new ArrayDeque<>();
		// 이동 횟수를 체크할 level변수
		int level = 0;
		// 메모리 초과를 막기 위해 방문 확인 처리
		boolean[] visited = new boolean[100001];
		
		// 초기값 설정
		dq.offer(new int[] {k,level});
		visited[k] = true;
		
		while(true) {
			int[] cur = dq.poll();
			int pos = cur[0], lv = cur[1];
			// n에 도달했다면 그때의 lv을 출력하고 종료
			if(n == pos) {
				System.out.println(lv);
				return;
			}
			else {
				// 이동 가능한 범위를 체크하고, 해당 칸을 방문했었는지 체크한 뒤 조건에 만족하면 dq에 추가
				
				// N+1 이 K일 경우
				if(0<= pos-1 && pos-1 <= 100000 && !visited[pos-1]) {
					dq.offer(new int[] {pos-1, lv+1});
					visited[pos-1] = true;
				}
				// N-1이 K일 경우
				if(0<= pos+1 && pos+1 <= 100000 && !visited[pos+1]) {
					dq.offer(new int[] {pos+1, lv+1});
					visited[pos+1] = true;
				}
				// N*2가 K일 경우 - N을 2배해서 K가 나오므로 K가 홀수이면 확인할 필요 없음
				if(pos%2 == 0) {
					if(0<= pos/2 && pos/2 <= 100000 && !visited[pos/2]) {
						dq.offer(new int[] {pos/2, lv+1});
						visited[pos/2] = true;
					}
				}
			}
		}
	}
}
