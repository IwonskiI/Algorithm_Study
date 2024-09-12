import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 1889 - 선물 교환
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 사람 수 입력
		int N = Integer.parseInt(br.readLine());
		
		// 사람번호의 인덱스에 받은 선물 개수를 카운트
		int[] in = new int[N+1];
		// 사람 번호 인덱스에 선물을 주는 두 사람의 번호를 각각 저장
		int[][] out = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
			// 받은 선물 개수 증가
			in[f]++; in[s]++;
			// 선물 준 사람 번호 저장
			out[i][0] = f; out[i][1] = s;
		}
		
		// 참여할 수 있는 사람의 초기 값 
		int ans = N;
		// 해당 인덱스의 사람이 참여하는지를 표시할 join 배열
		boolean[] join = new boolean[N+1];
		// 초기 상태는 모두 참여한다고 가정
		Arrays.fill(join, true);
		
		// 모든 선물을 나눠 가졌을 때, 선물을 2개 이상 받지 못한 사람은 참여 불가(제거)
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			// 받은 선물의 개수가 2보다 작다면
			if(in[i] < 2) {
				// queue에 추가
				q.add(i);
				// 참여 안함으로 체크
				join[i] = false;
				// 전체 참여 인원 감소
				ans--;
			}
		}
		
		// 빠지는 사람이 없어질 때까지 반복
		while(!q.isEmpty()) {
			// queue에 있는 사람이 빠지면서 그사람이 주는 선물 제거
			int cur = q.poll();
			// 현재 사람이 1번 선물을 주는 사람의 받은 선물 개수를 감소 시켰을 때 2보다 작아진다면,
			// 만약 그 사람이 아직 참여중인 사람이라면
			if(--in[out[cur][0]] < 2 && join[out[cur][0]]) {
				// 제거 목록에 추가
				q.add(out[cur][0]);
				// 제거 되었음을 표시
				join[out[cur][0]] = false;
				// 참여 인원 감소
				ans--;
			}
			// 위와 동일한 로직으로 2번 선물을 주는 사람도 체크
			if(--in[out[cur][1]] < 2 && join[out[cur][1]]) {
				q.add(out[cur][1]);
				join[out[cur][1]] = false;
				ans--;
			}
		}
		
		// 모든 빠질 사람을 뺀 뒤, 총 참여 인원 수 저장
		sb.append(ans).append("\n");
		// 참여하는 사람의 번호를 저장
		for(int i = 1; i <= N; i++) {
			if(join[i]) sb.append(i).append(" ");
		}
		// 최종 결과 저장
		System.out.println(sb.toString());
	}

}