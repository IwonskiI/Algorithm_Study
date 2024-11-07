import java.io.*;
import java.util.*;


public class Main {
    
    // BOJ 16918 - 붐버맨
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken()) - 1;
        
        // 초기 값, 가득 찬 보드, 첫번째 폭탄들이 터진 후 보드 저장
        char[][] board = new char[R][C], filled_board = new char[R][C];
        char[][] after_board = new char[R][C], first_board = new char[R][C];
        
        // 값 초기화
        for(int r = 0; r < R; r++) {
        	String str = br.readLine();
        	for(int c = 0; c < C; c++) {
        		first_board[r][c] = str.charAt(c);
        		filled_board[r][c] = board[r][c] = after_board[r][c] = 'O';
        	}
        }
        
        // 첫번째 폭탄들 터진 값 저장
        for(int r = 0; r < R; r++) {
        	for(int c = 0; c < C; c++) {
        		if(first_board[r][c] == 'O') {
        			after_board[r][c] = '.';
        			for(int dd = 0; dd < 4; dd++) {
        				int nr = r + d[dd][0], nc = c + d[dd][1];
        				boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
        				if(in_range) after_board[nr][nc] = '.';
        			}
        		}
        	}
        }
        
        // 첫번째 폭탄들 터진 값 저장
        for(int r = 0; r < R; r++) {
        	for(int c = 0; c < C; c++) {
        		if(after_board[r][c] == 'O') {
        			board[r][c] = '.';
        			for(int dd = 0; dd < 4; dd++) {
        				int nr = r + d[dd][0], nc = c + d[dd][1];
        				boolean in_range = (0 <= nr && nr < R) && (0 <= nc && nc < C);
        				if(in_range) board[nr][nc] = '.';
        			}
        		}
        	}
        }
        
        // 결과 값 계산
        for(int r = 0; r < R; r++) {
        	for(int c = 0; c < C; c++) {
        		if(N % 4 == 0) {
        			if(N== 0) sb.append(first_board[r][c]);
        			else sb.append(board[r][c]);
        		}
        		else if(N % 4 == 2) {
        			sb.append(after_board[r][c]);
        		}
        		else sb.append(filled_board[r][c]);
        	}
        	sb.append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}