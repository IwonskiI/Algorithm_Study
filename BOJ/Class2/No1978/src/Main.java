import java.io.*;
import java.util.*;


public class Main {
	
	//BOJ1978 - 소수 찾기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] num_lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num_lst[i] = Integer.parseInt(st.nextToken());
		}
		
		// 입력값 중 최댓값을 찾기 위해 정렬
		Arrays.sort(num_lst);
		
		// 배열의 가장 마지막 요소가 최댓값
		int max_num = num_lst[N-1];
		
		// 소수를 확인하기 위한 배열 선언 (false가 소수로 나타낼 것)
		boolean[] not_prime = new boolean[max_num+1];
		
		// 0과 1은 소수가 아니므로 true로 초기화
		not_prime[0] = true;
		not_prime[1] = true;
		
		//최댓값의 제곱근 이상으로는 비교할 필요가 없으므로 limit 설정
		int lim = (int)Math.sqrt(max_num);
		//max가 제곱수일 경우를 대비해서 +1 
		if(Math.sqrt(max_num) - (double)lim != 0) lim++;
		
		//2부터 limit까지 배수를 지워주는 작업
		for(int i = 2; i <= lim; i++) {
			if(not_prime[i]) continue;
			// i를 지우면(true로 만들면) 안되기 때문에 초기값 mul은 i*2로 설정
			for(int mul = i*2; mul <= max_num; mul += i) {
				not_prime[mul] = true;
			}
		}
		
		int ans = 0;
		// 소수 일 경우(not_prime이 false일 경우)만 ans++
		for(int i = 0; i < N; i++) {
			if(!not_prime[num_lst[i]]) ans++;
		}
		
		System.out.println(ans);

	}

}
