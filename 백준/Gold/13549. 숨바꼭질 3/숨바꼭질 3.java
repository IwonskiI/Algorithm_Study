import java.util.*;
import java.io.*;

public class Main {

	// BOJ 13549 - 숨바꼭질 3
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
		// n에서 k로 도달하는 경우는 역으로 k에서 도달할 수 있는 위치 중 n에 도달하는 방법을 찾으면 됨.
		Deque<int[]> dq = new ArrayDeque<>();
		// 이동할 시간을 나타낼 level
		int level = 0;
		// 몇초에 도착했는지 나타낼 배열, -1로 초기화
		int[] visited = new int[100001];
		for(int i = 0; i < 100001; i++) {
			visited[i] = -1;
		}
		dq.offer(new int[] {k,level});
		visited[k] = 0;
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int pos = cur[0], lv = cur[1];
			// 순간이동(2배)으로 이동할 수 있는 위치 우선 고려
			// 2배로 간다는 말은 반대로 말하면 나누기 2를 하는 것이므로 2로 나눠떨어지는 지 먼저 확인
			// 나누어 떨어진다면, pos가 범위안에 있는지, pos가 아직 방문 전인지 확인
			int n_pos = pos;
			while(n_pos % 2 == 0 && 0 <= n_pos/2 && n_pos/2 <= 100000 && (visited[n_pos/2] == -1 || visited[n_pos/2] > lv)) {
				n_pos /= 2;
				visited[n_pos] = lv;
				dq.offer(new int[] {n_pos, lv});
				// 순간이동 중, 목적지에 도착하면 종료
				if(n_pos == n) {
					System.out.println(lv);
					return;
				}
			}
			
			// 순간이동으로 도달하지 못할 경우 1초를 소모해 +1, -1위치로 이동
			// 마찬가지로 pos가 범위안에 있는자, pos가 아직 방문 전인지 확인
			if(0 <= pos-1 && pos-1 <= 100000 && visited[pos-1] == -1) {
				dq.offer(new int[] {pos-1, lv+1});
				visited[pos-1] = lv+1;
			}
			if(0 <= pos+1 && pos+1 <= 100000 && visited[pos+1] == -1) {
				dq.offer(new int[] {pos+1, lv+1});
				visited[pos+1] = lv+1;
			}
		}
		
		System.out.println(visited[n]);
	}
}
