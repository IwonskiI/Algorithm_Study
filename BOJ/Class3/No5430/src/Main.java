import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 5430 - AC
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 명령어를 저장
			String[] op = br.readLine().split("");
			int n = Integer.parseInt(br.readLine());
			
			// 실제 배열을 뒤집을 수 없기 때문에 양방향 큐를 활용해서 앞과 뒤에서 poll
			Deque<Integer> dq = new ArrayDeque<>();
			// 여러 구분자로 Toknizer하려면 | 활용하기
			st = new StringTokenizer(br.readLine(), "[|]|,");
			
			for(int i = 0; i < n; i++) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			
			// R명령어로 배열이 뒤집히면 dir = false
			// 빈 배열에서 D명령어 사용 시, err = true 
			boolean dir = true, err = false;
			
			for(int i = 0; i < op.length; i++) {
				String cur = op[i];
				// R명령어 => dir 뒤집기
				if(cur.equals("R")) dir = !dir;
				
				// D명렁어 => dq가 비어있다면 err, 아니라면 dir 방향에 따라 앞/뒤에서 poll
				else if(cur.equals("D")) {
					if(dq.size() == 0) {
						sb.append("error\n");
						err = true;
						break;
					}
					else {
						if(dir) dq.poll();
						else dq.pollLast();
					}
				}
			}
			
			// dq에 요소가 남아있다면, dir 방향에 따라 앞/뒤에서부터 출력
			if(!dq.isEmpty()) {
				sb.append("[");
				while(dq.size() != 1) {
					if(dir)sb.append(dq.poll()).append(",");
					else sb.append(dq.pollLast()).append(",");
				}
				sb.append(dq.poll()).append("]\n");
			}
			// dq에 요소가 없고, err가 안났다면, 빈 배열 출력
			// ** 에러가 나지 않을 경우 체크 잘 해주기!
			else if(!err) {
				sb.append("[]\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
