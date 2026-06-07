import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        
//         // 시간초과 코드
//         int answer = 0, row = board.length, col = board[0].length;
//         for(int i = 0; i < skill.length; i++) {
//             int[] cur = skill[i];
//             int t = cur[0], d = t==1 ? -cur[5] : cur[5];
//             int r1 = cur[1], c1 = cur[2], r2 = cur[3], c2 = cur[4];
//             for(int r = r1; r <= r2; r++) {
//                 for(int c = c1; c <= c2; c++) board[r][c] += d;
//             }
//         }
        
//         for(int r = 0; r < row; r++) {
//             for(int c = 0; c < col; c++) {
//                 if(board[r][c] > 0) answer++;
//             }
//         }
        
//         return answer;
        
        int answer = 0, row = board.length, col = board[0].length;
        int[][] n_board = new int[row+1][col+1];
        
        for(int i = 0; i < skill.length; i++) {
            int[] cur = skill[i];
            int t = cur[0], d = t==1 ? -cur[5] : cur[5];
            int r1 = cur[1], c1 = cur[2], r2 = cur[3], c2 = cur[4];
            n_board[r1][c1] += d;
            n_board[r1][c2+1] += -d;
            n_board[r2+1][c1] += -d;
            n_board[r2+1][c2+1] += d;
        }
        
        for(int r = 0; r < row+1; r++) {
            for(int c = 1; c < col+1; c++) {
                n_board[r][c] += n_board[r][c-1];
            }
        }
        
        for(int r = 1; r < row+1; r++) {
            for(int c = 0; c < col+1; c++) {
                n_board[r][c] += n_board[r-1][c];
            }
        }
        
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(board[r][c] + n_board[r][c] > 0) answer++;
            }
        }
        
        return answer;
    }
}