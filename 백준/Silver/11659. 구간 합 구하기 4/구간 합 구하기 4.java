import java.util.*;
import java.io.*;

public class Main {

	// BOJ 11659 - 구간 합 구하기 4
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 구간 합을 저장할 배열 선언
        int[] board = new int[N+1];
        st = new StringTokenizer(br.readLine());
        // 누적 합 계산
        for(int i = 0; i < N; i++) {
        	board[i+1] = board[i] + Integer.parseInt(st.nextToken());
        }

        // 합을 원하는 구간을 입력받고 
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int f = Integer.parseInt(st.nextToken());
        	int s = Integer.parseInt(st.nextToken());
        	// 끝지점 까지 누적합 - 시작지점까지 누적합을 해주면 원하는 결과 얻을 수 있음
        	sb.append(board[s] - board[f-1]).append("\n");
        }
        System.out.println(sb.toString());
        
    }
}