import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] board = new int[n][m];
        
        for(int[] puddle : puddles) board[puddle[1]-1][puddle[0]-1] = -1;
        for(int r = 0; r < n; r++) {
            if(board[r][0] == -1) break;
            board[r][0] = 1;
        }
        for(int c = 0; c < m; c++) {
            if(board[0][c] == -1) break;
            board[0][c] = 1;
        }

        for(int r = 1; r < n; r++) {
            for(int c = 1; c < m; c++) {
                if(board[r][c] == -1) continue;
                int top = board[r-1][c] == -1 ? 0 : board[r-1][c];
                int left = board[r][c-1] == -1 ? 0 : board[r][c-1];
                board[r][c] = (top + left) % 1000000007;
            }
        }
        
        answer = board[n-1][m-1] % 1000000007;
        
        return answer;
    }
}