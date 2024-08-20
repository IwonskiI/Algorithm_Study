import java.util.*;
import java.io.*;


public class Main {
    
    // BOJ 16926 - 배열 돌리기 1
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];
        // 배열 입력
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 몇개의 사각형이 돌아가는지(시작점을 몇개를 잡을 지 계산)
        // 각 꼭지점을 시작점으로 잡음
        // 더 작은 수를 2로 나눈 만큼 사각형이 돌아감
        int turn = 0;
        if(N <= M) turn = N / 2;
        else turn = M / 2;
        
        // 새로운 배열을 저장할 보드
        int[][] n_board = new int[N][M];
        
        // 각 사각형마다 각각 진행
        for(int t = 0; t < turn; t++) {
        	// 가로 및 세로 길이 측정
            int r_size = N - t*2 - 1, c_size = M - t*2 - 1;
            // 전체 사각형 길이
            int size = (r_size + c_size) * 2;
            // 사각형을 일자로 펼침
            int[] n_lst = new int[size];
            // t,t 부터 시작
            int r = t, c = t, cnt = 0, d = 0;
            // 4방향으로 진행 (우 -> 하 -> 좌 -> 상)
            int[][] dd = new int[][] {{0, 1},{1, 0},{0, -1},{-1, 0}};
            // 테두리를 다 돌 때까지 반복
            while(cnt < size) {
                n_lst[cnt++] = board[r][c];
                r += dd[d][0];
                c += dd[d][1];
                // 끝 지점에 도달했을 때 방향전환
                if(d == 1 && r == r_size + 1 + t) {
                	r -= dd[d][0];
                	d++;
                	c += dd[d][1];
                }
                else if((d == 0 && c == c_size + 1 + t) || (d == 2 && c == t-1)) {
                	c -= dd[d][1];
                	d++;
                	r += dd[d][0];
                }
            }
            // r, c, d 초기화 및 cnt는 회전한 횟수만큼 증가(cnt부터 채워넣기 시작)
            r = t;
            c = t;
            d = 0;
            cnt = R%size;
            // r, c를 한번 변화 준 뒤, 다시 r,c가 t,t가 될때까지 반복(한바퀴)
            do {
                n_board[r][c] = n_lst[cnt++];
                cnt %= size;
                r += dd[d][0];
                c += dd[d][1];
                if(d == 1 && r == r_size + 1 + t) {
                	r -= dd[d][0];
                	d++;
                	c += dd[d][1];
                }
                else if((d == 0 && c == c_size + 1 + t) || (d == 2 && c == t-1)) {
                	c -= dd[d][1];
                	d++;
                	r += dd[d][0];
                }
            }while(!(r == t && c == t));
        }
        // 모든 사각형 회전 후에 새로운 배열에 저장된 결과값 저장 및 출력
        for(int r = 0; r < N; r++) {
        	for(int c = 0; c < M; c++) {
        		sb.append(n_board[r][c]).append(" ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}