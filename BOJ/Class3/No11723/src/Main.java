import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 11723 - 집합
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 집합을 관리할 boolean 배열 선언
		// 집합에 포함되면 true / 아니면 false로 저장
		boolean[] set = new boolean[21];
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int num;
			switch(op) {
			// 집합에 add 하면 해당 번호의 인덱스를 true로 설정
			case "add":
				num = Integer.parseInt(st.nextToken());
				set[num] = true;
				break;
			// 집합에 remove 하면 해당 번호의 인덱스를 false로 설정
			case "remove":
				num = Integer.parseInt(st.nextToken());
				set[num] = false; 
				break;
			// 해당 번호의 인덱스를 확인하고 true면 1을, false면 0을 추가
			case "check":
				num = Integer.parseInt(st.nextToken());
				if(set[num]) sb.append("1\n");
				else sb.append("0\n");
				break;
			// 해당 번호의 인덱스의 값을 반대로 전환
			case "toggle":
				num = Integer.parseInt(st.nextToken());
				set[num] = !set[num];
				break;
			// 집합 전체를 true로 설정
			case "all":
				for(int j = 1; j <= 20; j++) {
					set[j] = true; 
				}
				break;
			// 집합 전체를 false로 설정
			case "empty":
				for(int j = 1; j <= 20; j++) {
					set[j] = false; 
				}
				break;
			}
		}
		
		System.out.println(sb.toString());

	}

}
