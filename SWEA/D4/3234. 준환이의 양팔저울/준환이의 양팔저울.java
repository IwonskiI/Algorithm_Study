import java.io.*;
import java.util.*;
 
public class Solution {
     
	// SWEA 3234 - 준환이의 양팔저울
	// N: 추의 개수 , l_sum, r_sum : 양쪽에 올려진 추 무게의 합 , ans : 가능한 경우의 수
	public static int N, l_sum, r_sum, ans;
	// 추 무게를 담은 배열
	public static int[] W;
	// 사용중인 추 표시
	public static boolean[] visited;
	// 메모이제이션 맵
	public static Map<String, Integer> memo;
	
	// 순열 생성
	public static void perm(int cnt) {
		// 3개의 추가 다 올라갔다면
		if(cnt == N) {
			// 조건을 만족한다면 횟수 증가
			if(l_sum >= r_sum) ans++;
			// 재귀 종료
			return;
		}
		// 오른쪽 추가 이미 더 무겁다면
		if(r_sum > l_sum) {
			// 재귀 종료
			return;
		}
		// 메모이제이션
		String key = cnt+","+l_sum+","+r_sum+","+Arrays.toString(visited);
		if(memo.containsKey(key)) {
			ans += memo.get(key);
			return;
		}
		
		int initialAns = ans;
		
		// 순열 생성으로 가능한 모든 경우의 수 시도
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			// 왼쪽에 먼저 올려보고, 오른쪽에도 올려보며 시도
			l_sum += W[i];
			perm(cnt+1);
			l_sum -= W[i];
			r_sum += W[i];
			perm(cnt+1);
			r_sum -= W[i];
			visited[i] = false;
		}
		memo.put(key, ans - initialAns);
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	// 추의 갯수 입력
        	N = Integer.parseInt(br.readLine());
        	// 총 가능한 경우의 수를 저장할 변수
        	ans = 0;
        	// 무게 추 입력 받기
        	W = new int[N]; 
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		W[i] = Integer.parseInt(st.nextToken());
        	}
        	visited = new boolean[N];
        	ans = 0;
        	l_sum = 0;
        	r_sum = 0;
        	memo = new HashMap<>();
        	perm(0);
        	
        	// 총 가능한 경우의 수 저장
        	sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
 
}