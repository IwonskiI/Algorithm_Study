import java.util.*;
import java.io.*;

public class Main {

	// BOJ 1931 - 회의실 배정
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		// 회의 시작 시간과 종료 시간을 입력받음
		int[][] time_lst = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			time_lst[i][0] = Integer.parseInt(st.nextToken());
			time_lst[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 회의가 빨리 끝나야 더 많은 회의를 할 수 있으므로 끝나는 시간의 오름차순으로 정렬
		// *** 끝나는 시간이 같다면 시작시간도 오름차 순으로 정렬 ****
		Arrays.sort(time_lst, (o1, o2) -> {
			if(o1[1] == o2[1]) return o1[0] - o2[0];
			else return o1[1] - o2[1];
		});
		
		// 첫 회의가 끝나는 시간 설정
		int et = time_lst[0][1], ans = 1;
		
		// 다음 회의의 시작시간이 이전 회의의 끝시간보다 크면
		// 회의 수 하나 더해주고 끝나는 시간을 다음 회의의 끝나는 시간으로 변경
		for(int i = 1; i < n; i++) {
			if(time_lst[i][0] >= et) {
				ans++;
				et = time_lst[i][1];
			}
		}
		
		System.out.println(ans);
	}
}
