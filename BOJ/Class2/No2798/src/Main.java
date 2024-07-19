import java.io.*;
import java.util.*;


public class Main {
	
	//BOJ2798 - 블랙잭
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] card_lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			card_lst[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬 후 앞에서부터 3개의 카드를 뽑아 조합하며 M보다 크지 않을 때 까지 반복
		Arrays.sort(card_lst);
		
		int ans = 0;
		
		// 첫 번째 카드는 1번째 부터 N-2번째까지 중에서 고름
		for(int i = 0; i < N-2; i++) {
			// cur = 현재 카드 합
			int cur = card_lst[i];
			// 카드를 골라서 더했는데 이미 M을 초과한다면 반복문 종료
			if(cur > M) break;
			
			// 2 번째 카드는 첫 번째 카드 다음 카드부터 N-1번째 카드 중에서 고름
			for(int j = i+1; j < N-1; j++) {
				
				cur += card_lst[j];
				if(cur > M) {
					// 두 번째 카드까지 골랐을 때 이미 초과했다면 그 카드를 제외하고 첫 번째 카드부터 다시 뽑기
					cur -= card_lst[j];
					break;
				}
				
				// 3 번째 카드는 두 번째 카드 다음 카드부터 N번째 카드 중에서 고름
				for(int k = j+1; k < N; k++) {
					
					cur += card_lst[k];
					if(cur > M) {
						// 세 번째 카드까지 골랐을 때 이미 초과했다면 그 카드를 제외하고 두 번째 카드부터 다시 뽑기
						cur -= card_lst[k];
						break;
					}
					
					// 3개의 카드를 모두 뽑았을 때 현재 최대값보다 크다면 값 변경
					ans = cur > ans ? cur : ans;
					
					// 다시 두 번째 카드부터 뽑기 위해 마지막 카드 제거
					cur -= card_lst[k];
				}
				
				// 다시 첫 번째 카드부터 뽑기 위해 두 번째 카드 제거
				cur -= card_lst[j];
			}
		}
		
		System.out.println(ans);

	}

}
