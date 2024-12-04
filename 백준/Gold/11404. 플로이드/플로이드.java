import java.io.*;
import java.util.*;

public class Main {

    // BOJ 11404 - 플로이드
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        long[][] dist = new long[N][N];
        
        // 초기 거리 무한으로 설정
        for(int i = 0; i < N; i++) {
        	Arrays.fill(dist[i], 10000001);
        	dist[i][i] = 0L;
        }
        
        // 두 도시간의 거리 입력
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken())-1;
        	int b = Integer.parseInt(st.nextToken())-1;
        	long c = Long.parseLong(st.nextToken());
        	// 등록할 노선이 기존 노선보다 적은 비용일때만 등록
        	if(dist[a][b] > c) dist[a][b] = c;
        }
        
        // 플로이드-워셜 알고리즘 적용
       
        // 거쳐가는 노드
        for(int k = 0; k < N; k++) {
        	// 출발 노드
        	for(int i = 0; i < N; i++) {
        		// 도착 노드
        		for(int j = 0; j < N; j++) {
        			if(dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
        		}                   
        	}
        }
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		// 연결이 안된 노선이라면 0 추가
        		if(dist[i][j] == 10000001) sb.append("0 ");
        		// 아니라면 비용 추가
        		else sb.append(dist[i][j]).append(" ");
        	}
        	sb.append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        
    }

}