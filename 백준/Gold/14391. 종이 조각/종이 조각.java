import java.io.*;
import java.util.*;

public class Main {

    // BOJ 14391 - 종이 조각
    public static int N, M, ans;
    public static int[][] board;
    public static boolean[][] visited;
    public static List<Integer> debug = new ArrayList<>();

    // 모든 조합 구하는 함수
    public static void combi(int sum) {
        // 모든 배열 확인
        for(int r = 0; r < N; r++) {
        	for(int c = 0; c < M; c++) {
        		// 이미 해당 칸이 잘려진 종이에 포함되었다면 스킵
        		if(visited[r][c]) continue;
        		// 아니라면 방문 시작
        		visited[r][c] = true;
        		
        		// 1*1로 잘랐을 때 먼저 계산
        		combi(sum+board[r][c]);
        		
        		// 가로로 최대로 길게 자를 수 있는 길이 계산
        		int limC = c+1 >= M ? c : M-1;
        		for(int tc = c + 1; tc < M; tc++) {
        			if(visited[r][tc]) {
        				limC = tc - 1;
        				break;
        			}
        		}
        		
        		// 최대 길이부터 한칸씩 감소해가며 종이 계산
        		// 최대 길이일때 숫자 구하기
        		String numStr = ""+board[r][c];
        		for(int tc = c+1; tc <= limC; tc++) {
        			visited[r][tc] = true;
        			numStr += board[r][tc];
        		}
        		int numInt = Integer.parseInt(numStr);
        		
        		// 한 칸씩 줄여가며 다음 경우 계산
        		while(limC - c > 0) {
        			combi(sum+numInt);
        			visited[r][limC] = false;
        			numInt /= 10;
        			limC -= 1;
        		}
        		
        		// 세로도 똑같은 방법으로 계산
        		visited[r][c] = true;
        		int limR = r+1 >= N ? r : N-1;
        		for(int tr = r + 1; tr < N; tr++) {
        			if(visited[tr][c]) {
        				limR = tr - 1;
        				break;
        			}
        		}
        		
        		numStr = ""+board[r][c];
        		for(int tr = r+1; tr <= limR; tr++) {
        			visited[tr][c] = true;
        			numStr += board[tr][c];
        		}
        		numInt = Integer.parseInt(numStr);
        		
        		while(limR - r > 0) {
        			combi(sum+numInt);
        			visited[limR][c] = false;
        			numInt /= 10;
        			limR -= 1;
        		}
        		visited[r][c] = false;
        		ans = Math.max(ans, sum);
        		return;
        	}
        }
        ans = Math.max(ans, sum);
        return;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];
        visited = new boolean[N][M];
        ans = 0;
        
        for(int r = 0; r < N; r++) {
            String[] str = br.readLine().split("");
            for(int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(str[c]);
            }
            
        }
        
        // 조합 계산
        combi(0);
        
        // ans 출력
        System.out.println(ans);        
    }
}