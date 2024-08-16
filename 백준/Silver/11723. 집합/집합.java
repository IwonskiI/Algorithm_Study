import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 11723 - 집합
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        // 비트 연산을 할 변수
        int mask = 0;
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int num;
			switch(op) {
			case "add":
				// num-1만큼 왼쪽으로 비트열 이등을 통해 해당 자리수를 1로 만들고
				// or 연산을 통해 해당 자리를 1로 채워준다.
				num = Integer.parseInt(st.nextToken());
                mask |= (1 << (num - 1));
				break;
			case "remove":
				// num-1만큼 왼쪽으로 비트열 이등을 통해 해당 자리수를 1로 만들고
				// not 연산을 통해 해당 자리만 0으로 바꿔준 뒤 and 연산을 통해
				// 해당 비트만 제거해준다.
				num = Integer.parseInt(st.nextToken());
                mask &= ~(1 << (num - 1));
				break;
			case "check":
				// num-1만큼 왼쪽으로 비트열 이등을 통해 해당 자리수를 1로 만들고
				// and 연산을 통해 해당 자리의 비트를 확인햇을 때, 0이라면 존재 X, 0이상이라면 존재 O
				num = Integer.parseInt(st.nextToken());
				if((mask & (1 << (num - 1))) == 0) sb.append("0\n");
				else sb.append("1\n");
				break;
			case "toggle":
				// num-1만큼 왼쪽으로 비트열 이등을 통해 해당 자리수를 1로 만들고
				// xor 연산을 통해 해당 자리만 0으로 바꿔준다.
				num = Integer.parseInt(st.nextToken());
				mask ^= (1 << (num - 1));				
				break;
			case "all":
				// 모든 자리를 1로 만들어준다.
				mask = (1<<20) - 1;
				break;
			case "empty":
				// 모든 자리를 0으로 만들어준다.
                mask = 0;
				break;
			}
		}
		
		System.out.println(sb.toString());

	}

}
