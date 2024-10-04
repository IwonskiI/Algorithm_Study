import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 2776 - 암기왕
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        // 테스트케이스만큼 반복
        for(int tc = 0; tc < T; tc++) {
        	// 수첩1에 적혀있는 수의 개수
        	int N = Integer.parseInt(br.readLine());
        	// 수첩 1에 적혀있는 수의 집합
        	HashSet<Integer> set = new HashSet<>();
        	// 수 입력
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		set.add(Integer.parseInt(st.nextToken()));
        	}
        	
        	// 수첩 2에 적혀있는 수의 개수
        	int M = Integer.parseInt(br.readLine());
        	// 수 입력
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < M; i++) {
        		// 수첩 2에 적혀있는 수
        		int cur = Integer.parseInt(st.nextToken());
        		// 수가 수첩 1에 적혀있던 수라면 1 추가
        		if(set.contains(cur)) sb.append("1\n");
        		// 아니라면 0 추가
        		else sb.append("0\n");
        	}
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}