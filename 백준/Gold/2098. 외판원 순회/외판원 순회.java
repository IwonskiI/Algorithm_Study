import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 2098 - 외판원 순회
	public static int N, full, INF = 100000000;
	public static int[][] len, dp;
	
	public static int tsp(int x, int chk) {
		// 모든 도시를 방문완료 했다면
		if(chk == full) {
			// 마지막 도시에서 첫 시작 도시로 돌아가는 길이 없다면 inf
			if(len[x][0] == 0) return INF;
			// 있다면 해당 길의 길이를 return
			else return len[x][0];
		}
		
		// 이미 해당 상태로 해당 도시를 방문한 적이 있다면 이전 계산 값 return
		if(dp[x][chk] != -1) return dp[x][chk];
		
		// 방문 처리 및 이후 최솟값을 찾기 위해 최대로 설정
		dp[x][chk] = INF;
		
		// 모든 도시 방문 체크
		for(int i = 0; i < N; i++) {
			// 이미 방문했던 도시거나, 해당 도시로 연결되는 길이 없다면 스킵
			if((chk & (1 << i)) != 0 || len[x][i] == 0) continue;
			
			// 현재 도시를 방문 체크 
			int next = chk | (1 << i);
			
			// 현재 위치에 특정 도시를 방문했을 때의 최소 값 계산
			// 기존 값과 현재 선택한 도시를 다음으로 선택했을 때의 값 중 작은 값을 저장
			dp[x][chk] = Math.min(dp[x][chk], tsp(i, next) + len[x][i]);
		}
		
		// 현재 위치에서 가장 최소가 되는 값을 반환
		return dp[x][chk];
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 도시의 개수 N
        N = Integer.parseInt(br.readLine());
        // 각 도시간의 이동 거리 len
        len = new int[N][N];
        
        // 각 도시 사이의 거리 입력
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		len[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 모든 도시를 방문했을 때의 비트 계산
        full = (1 << N) - 1;
        // 특정 방문 상태로 
        dp = new int[N][full];
        for(int i = 0; i < N; i++) {
        	Arrays.fill(dp[i], -1);
        }
        
        // 0번째 도시부터 시작해서 0번인덱스인 1만 채우고 외판원 순회 시작 -> 최종 결과는 순회 완료 후 드는 비용 
        System.out.println(tsp(0, 1));
    }
}