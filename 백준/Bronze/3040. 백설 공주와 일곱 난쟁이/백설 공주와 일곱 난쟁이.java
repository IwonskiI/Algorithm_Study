import java.io.*;
import java.util.Arrays;

public class Main {
	
	// BOJ 3040 - 백설 공주와 일곱 난쟁이
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 아홉난쟁이의 키 총합을 구함
		int sum = 0;
		// 난쟁이들의 키를 저장
		int[] num_lst = new int[9];
		for(int i = 0; i < 9; i++) {
			num_lst[i] = Integer.parseInt(br.readLine());
			sum += num_lst[i];
		}
		
		// 투포인터를 활용하기 위해서 키 순서대로 정렬
		Arrays.sort(num_lst);
		
		// 잘못온 두 난쟁이의 키의 합
		int wrong = sum - 100;
		
		// 앞 좌표와 뒷 좌표 초기값 설정 (투포인터)
		int s = 0, e = 8;
		// 잘못된 두 난쟁이의 키의 합 초기값 설정
		int temp = num_lst[s] + num_lst[e];
		while(s < e) {
			// 두 난쟁이의 키 합이 잘못된 두 난쟁이 키의 합보다 작다면 앞 포인터를 증가
			if(temp < wrong) s++;
			// 두 난쟁이의 키 합이 잘못된 두 난쟁이 키의 합보다 크다면 뒷 포인터를 증가
			else if(temp > wrong) e--;
			// 잘못된 두 난쟁이의 키의 합 갱신
			temp = num_lst[s] + num_lst[e];
			if(temp == wrong) break;
		}
		
		// 잘못된 두 난쟁이를 제외한 나머지 난쟁이들을 출력
		for(int i = 0; i < 9; i++) {
			if(i == s || i == e) continue;
			sb.append(num_lst[i]).append("\n");
		}
		
		
		System.out.println(sb.toString());
	}
	
}