import java.io.*;
import java.util.*;

public class Main {
	
	//BOJ 2467 - 용액
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] lst = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		
		//시작점 sp, 끝점 ep
		int sp = 0, ep = N-1;
		// 초기 값은 문제에서 나올 수 있는 최대 차이인 20억으로 설정
		int ans1 = -1000000000, ans2 = -1000000000;
		// 현재 최소 차이
		int min = Math.abs(ans1 + ans2);
		// sp와 ep가 겹칠 때까지 반복
		while(sp < ep) {
			// 현재 위치에서의 용액의 합 비교
			if(Math.abs(lst[sp] + lst[ep]) < min) {
				// 최소값보다 작다면 갱신
				ans1 = lst[sp];
				ans2 = lst[ep];
				min = Math.abs(lst[sp] + lst[ep]);
			}
			
			// 현재 용액의 합이 0보다 작다면 더 커져야하므로 sp를 증가
			if(lst[sp] + lst[ep] < 0) {
				sp += 1;
			}
			// 0보다 크다면 더 작아져야하므로 ep를 감소
			else if(lst[sp] + lst[ep] > 0) {
				ep -= 1;
			}
			// 0이랑 일치하면 더 이상 최적화 불가능 -> 답
			else {
				break;
			}
		}
		
		// 저장된 최솟값을 만드는 용액을 순서대로 저장
		sb.append(ans1).append(" ").append(ans2);
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}