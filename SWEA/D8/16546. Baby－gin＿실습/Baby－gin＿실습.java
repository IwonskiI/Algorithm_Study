import java.io.*;

public class Solution {
	
	// SWEA 16546 - Baby-gin
	
	public static int[] numbers = new int[6], num_lst;
	public static boolean[] visited;
	
	// triplet 체크 함수
	public static boolean chk_triplet(int a, int b, int c) {
		if (a == b && b == c) return true;
		else return false;
	}
	
	
	// run 체크 함수
	public static boolean chk_run(int a, int b, int c) {
		if(a + 1 == b && b + 1 == c) return true;
		else return false;
	}
	
	// 순열 생성
	public static boolean perm(int cnt) {
		// 6자리 순열이 완성되었다면 조건 확인
		if(cnt == 6) {
			// 앞 3자리가 run이나 triplet이고, 뒷 3자리가 run이나 triplet이면 return true
			if((chk_triplet(numbers[0],numbers[1], numbers[2]) || chk_run(numbers[0],numbers[1], numbers[2])) 
					&& (chk_triplet(numbers[3],numbers[4], numbers[5]) || chk_run(numbers[3],numbers[4], numbers[5]))) 
				return true;
			// 아니라면 return false
			else 
				return false;
		}
		// 순열 완성 전이라면 재귀를 통해 순열 완성
		else {
			for(int i = 0; i < 6; i++) {
				if(visited[i]) continue;
				numbers[cnt] = num_lst[i];
				visited[i] = true;
				// 만약 완성된 순열이 조건을 만족한다면, return true
				if(perm(cnt+1)) return true;
				// 아니라면 순열 재성성을 위해 반복문 진행
				visited[i] = false;
			}
		}
		
		// return true하지 못하고 반복문이 끝났다면 return false
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 수열을 한자리씩 나누기
			String[] num_str = br.readLine().split("");
			num_lst = new int[6];
			visited = new boolean[6];
			// 나눈 문자열을 숫자로 저장
			for(int i = 0; i < 6; i++) {
				num_lst[i] = Integer.parseInt(num_str[i]);
			}
			
			// 순열을 만들어서 조건에 만족하는(return true) 경우는 ans = true, 아니면 ans = false
			String ans = "";
			if(perm(0)) ans = "true";
			else ans = "false";
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
