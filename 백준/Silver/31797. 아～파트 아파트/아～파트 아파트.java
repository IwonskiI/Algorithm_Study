import java.io.*;
import java.util.*;

public class Main {
	
	//BOJ 31797 - 아~파트 아파트
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 구해야하는 층수
		int N = Integer.parseInt(st.nextToken());
		// 사람 수
		int M = Integer.parseInt(st.nextToken());
		
		// 손의 개수
		int num = M * 2;
		int target = (N - 1) % num;
		
		// target번째 손을 넣은 사람 저장
		int ans = -1;
		
		int[][] lst = new int[M*2][2];
		int idx = 0;
		
		// M명의 손 위치 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			lst[idx][0] = f;
			lst[idx++][1] = i+1;
			lst[idx][0] = s;
			lst[idx++][1] = i+1;
		}
		
		// 높이 순서대로 정렬
		Arrays.sort(lst, (a, b) -> a[0] - b[0]);
		
		// target번째의 학생 번호 저장
		ans = lst[target][1];
		
		// 최종 결과 출력
		System.out.println(ans);
	}
}