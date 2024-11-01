import java.io.*;
import java.util.*;


public class Main {
    
    // BOJ 17136 - 색종이 붙이기
    public static int ans = 26;
    // 크기별 종이의 개수
    public static int[] paper = new int[] {0, 5, 5, 5, 5, 5};
    // 보드
    public static int[][] board = new int[10][10];
    
    // 현재 칸에서 시작해서 놓을 수 있는 최대 종이 크기
    public static int maxSize(int r, int c) {
    	// 최대는 5 * 5
        int row_min = 5, col_min = 5;
        // row 탐색 (r부터 r+4까지만 탐색)
        for(int rr = r; rr < r+5; rr++) {
        	// 가로의 최소값이 현재 세로 길이 보다 이미 작다면 더 탐색 X 
        	if(col_min < rr - r + 1) break;
        	// 범위를 벗어나거나, 1이 아니라 0이면 지금까지 탐색한 줄의 크기 저장
            if(rr < 0 || 9 < rr || board[rr][c] == 0) {
            	// row_min을 최소값으로 갱신
                row_min = rr - r;
                break;
            }
            // col도 같은 방법으로 탐색
            for(int cc = c; cc < c+5; cc++) {
            	// 범위를 벗어나거나, 현재 값이 0이면 지금까지 탐색한 칸의 크기 저장
                if(cc < 0 || 9 < cc || board[rr][cc] == 0) {
                	// 지금까지 탐색한 칸의 크기가 현재 줄보다 크다면 갱신
                    if(cc - c >= rr - r + 1)
                    	col_min = Math.min(col_min, cc - c);
                    // 아니라면 다음줄을 확인해도 색종이를 채울 수 없을 것이기 때문에 col_min을 현재 세로 탐색값 -1로 저장
                    else
                    	col_min = rr - r;
                    // 더이상 탐색은 의미 없으므로 종료
                    break;
                }
            }
        }
        // row_min과 col_min중 더 작은 값 반환
        return Math.min(row_min, col_min);
    }
    
    // 색종이 놓기 함수
    public static void paperToggle(int r, int c, int size, int flag) {
    	// r,c부터 size만큼 색종이를 놓거나 제거함 (flag가 1이면 제거 0이면 놓기)
        for(int rr = r; rr < r + size; rr++) {
            for(int cc = c; cc < c + size; cc++) {
                board[rr][cc] = flag;
            }
        }
    }
    
    // 조함 계산
    public static void combi(int start, int sum) {
    	// 이차원 배열을 일렬로 놓았을 때 인덱스를 입력
        int i = start;
        for(; i < 100; i++) {
        	// 다시 좌표로 변환
            int r = i/10, c = i%10;
            // 색종이를 놓을 수 없는 칸이면 스킵
            if(board[r][c] == 0) continue;
            // 놓을 수 있다면 현재 위치에서 최대 크기를 계산
            int size = maxSize(r, c);
            // 최대한 큰 종이로 덮는 것이 최적의 해일 가능성이 높기 때문에 큰 종이부터 놓으면서 1까지 놓아봄
            for(int j = size; j > 0; j--) {
            	// 종이를 다 쓰지 않았고, 종이를 하나 더 놔도 기존 정답보다 작을 때만 탐색
                if(paper[j] > 0 && sum+1 < ans) {
                	// 종이 감소
                    paper[j]--;
                    // 종이 놓기
                    paperToggle(r, c, j, 0);
                    // 다음 칸 탐색
                    combi(i+1, sum+1);
                    // 색종이 제거 (원상복구)
                    paperToggle(r, c, j, 1);
                    // 색종이 갯수 증가(원상복구)
                    paper[j]++;
                }
            }
            // 모든 가능한 경우를 확인했다면 return
            // (하지 않으면 종이를 안놔도 놓은것으로 판단하고 최솟값이 0으로 출력)
            return;
        }
        
        // 모든 종이를 놓고 끝점에 도달했다면 최소 종이 수 갱신
        if(i == 100) {
            ans = Math.min(ans, sum);
            return;
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 보드 초기 상황 입력
        for(int r = 0; r < 10; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < 10; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 경우의 수 계산
        combi(0, 0);
        
        // 최소 색종이 수 저장
        if(ans > 25) sb.append(-1);
        else sb.append(ans);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}