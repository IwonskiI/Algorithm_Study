import java.io.*;
import java.util.*;

public class Main {

    // BOJ 3687 - 성냥개비
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        // DP 배열 초기화
        long[] min = new long[101];
        Arrays.fill(min, Long.MAX_VALUE);
        // 성냥개비 갯수로 만들 수 있는 최소 숫자 저장
        min[2] = 1;
        min[3] = 7;
        min[4] = 4;
        min[5] = 2;
        // 0은 첫번째 자리에 올 수 없으므로 6개로 만들 수 있는 최소는 6
        min[6] = 6;
        min[7] = 8;
        // 2번째 자리에는 올 수 있기 때문에 8개로 만들 수 있는 최소는 2개로 1, 6개로 0을 만들어 붙인 10
        min[8] = 10;
        
        // 만들 수 있는 최소 숫자를 구한 뒤, 마지막 자리 숫자 더해주기
        String[] add = {"1", "7", "4", "2", "0", "8"};
        
        // DP 배열 계산 (9~100)
        for(int i = 9; i <= 100; i++) {
        	// 지금까지 계산한 최솟값에 마지막 1자리를 더해서 구한 뒤 그 중 최솟값 저장
        	for(int j = 2; j <= 7; j++) {
        		String line = "" + min[i-j] + add[j-2];
        		min[i] = Math.min(Long.parseLong(line), min[i]);
        	}
        }
        
        // 주어진 개수로 최소, 최대 구하기
        for(int i = 0; i < T; i++) {
        	int N = Integer.parseInt(br.readLine());
    		// 최솟값 - DP 배열 활용
    		sb.append(min[N]).append(" ");
    		
    		// 최댓값 - 그리디 (자리수를 늘리는 것이 가장 큰 수)
    		int div = N / 2;
    		// 짝수면 성냥개비를 모두 1로 만들어 자리수를 최대로 만듦
    		if(N%2 == 0) sb.append("1");
    		// 홀수면 첫번째 자리만 3개를 써서 7로 만든 뒤 나머지는 모두 1로 만들어 자리수를 늘림
    		else sb.append("7");
			for(int j = 1; j < div; j++) {
    			sb.append("1");
    		}
			sb.append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        
    }

}