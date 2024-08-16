import java.io.*;
import java.util.*;
 
public class Solution {
     
	// SWEA 1288 - 새로운 불면증 치료법 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	// 수를 확인했는지 확인할 배열
        	Set<Integer> num_set = new HashSet<>();
        	int N = Integer.parseInt(br.readLine());
        	
        	// N을 계속 더해갈 변수
        	int new_N = N;
        	// 모든 숫자를 볼때까지 반복
        	while(true) {
        		// 각 자리를 char형으로 변환
        		char[] N_str = Integer.toString(new_N).toCharArray();
        		// 각 자리 숫자를 확인처리
        		for(int i = 0; i < N_str.length; i++) {
        			int cur = N_str[i] - '0';
        			num_set.add(cur);
        		}
        		// 모든 숫자를 다 봤다면 반복문 종료
        		if(num_set.size() == 10) break;
        		// N 더해주기
        		new_N += N;
        	}
        	
            sb.append("#").append(tc).append(" ").append(new_N).append("\n");
        }
        System.out.println(sb.toString());
    }
 
}