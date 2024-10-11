import java.io.*;
import java.util.*;

public class Main {

	// BOJ 1052 - 물병
	public static int N, K, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = -1;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// N의 최댓값 10^7은 23번 비트까지 채워짐
		// 24비트까지 채워두고 각 자리를 비교
		int mask = (1 << 24);
		
		while(true) {
			// 현재 mask 자리에 비트가 있으면
			if((mask & N) == mask) {
				// 현재 남은 물병이 1개라면
				if(K == 1) {
					int temp = N;
					// 이번 마스킹 이후 물을 모두 담았다면 추가 물병 필요X
					if((temp &= ~mask) == 0) {
						// 물병 0개 리턴 후 종료
						System.out.println(0);
						return;
					}
					// 추가 물병이 필요하다면 반복문 종료 후 추가 물병 수 계산
					break;
				}
				// 아직 물병이 남았다면 현재 비트수의 물 양을 물병 1개에 넣고
				K--;
				// 현재 비트 삭제
				N &= ~mask;
			}
			// 비트 한자리 앞으로 당김
			mask >>= 1;
		}
		
		// 최소 물병은 1개 이상 필요
		ans = 1;
		// 몇번 비트가 필요한지 계산
		int i = 0;
		// N을 다 담을 때까지 반복
		while(N > 0) {
			// 현재 비트에 물이 없다면 물을 담아줘야함
			if((N & 1) != 1) ans += (1 << i);
			// N을 한자리 앞으로 당김
			N >>= 1;
			// 비트 수 증가
			i++;
		}
		
		
		// 저장된 물병 수 출력
		System.out.println(ans);		
	}
}