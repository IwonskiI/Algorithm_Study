import java.util.*;
import java.io.*;
	
public class Main {
	
	// BOJ 1920 - 수 찾기
	public static boolean b_search(int[] lst, int key, int size) {
		// 시작 점, 끝 점 선언
		int start = 0, end = size-1;
		// 종료 조건 : 시작점이 끝점보다 커지는 순간
		while(start <= end) {
			// 중간 값 설정 : 시작점 + 끝점 /2
			int mid = (start + end) / 2;
			// 중간 값이  찾는 값이면 true반환
			if(lst[mid] == key) {
				return true;
			}
			// 찾는 값이 중간 값보다 작다면 현재의 중간값을 끝점으로 설정
			else if(lst[mid] > key) {
				end = mid -1;
			}
			// 찾는 값이 중간 값보다 크다면 현재의 중간값을 시작점으로 설정
			else {
				start = mid + 1;
			}
		}
		// 탐색이 끝나도 찾지 못했다면 false 반환
		return false;
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] num_lst = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			num_lst[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색을 찾기 위해서 입력받은 수열을 정렬
		Arrays.sort(num_lst);
		
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 찾는 값이 있다면 1 출력
			if(b_search(num_lst, num, n))sb.append(1);
			// 찾는 값이 없다면 0 출력
			else sb.append(0);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
