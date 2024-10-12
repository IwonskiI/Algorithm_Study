import java.io.*;
import java.util.*;

public class Solution {

    // SWEA 4014 - [모의 SW 역량테스트] 활주로 건설
	public static int N, X, ans;
	public static int[][][] board;
	
	// 활주로 테스트 함수
	public static void chk_road(int r) {
		// row 기준 한 번, col 기준 한 번 테스트
		for(int b = 0; b < 2; b++) {
			// b에 따라 row 기준 col 기준 길 가져오기
			int[] road = board[b][r];
			// 처음 시작 높이
			int cur = road[0];
			// 현재 높이에서의 길이
			int len = 1;
			// 다음 길의 좌표
			int c = 1;
			// 길이 완성 되는지 확인
			boolean flag = true;
			// 길 끝까지 체크
			for(; c < N; c++) {
				// 현재 높이랑 다음 높이랑 같다면
				if(cur == road[c]) {
					// 현재 높이에서의 길이 증가
					len++;
				}
				// 현재 높이가 다음 높이보다 1 낮다면
				else if(cur+1 == road[c]) {
					// 지금까지의 현재 높이에서의 길이가 경사로를 놓을 수 있을 만큼 길이가 되는지 체크
					// len이 X보다 작다면
					if(len < X) {
						// 경사로를 놓을 수 없으므로 활주로 X
						flag = false;
						//종료
						break;
					}
					// 더 길어서 놓을 수 있다면
					else {
						// 현재 높이를 더 높아진 높이로 갱신
						cur = road[c];
						// 현재 높이에서의 길이는 1로 초기화
						len = 1;
					}
				}
				// 현재 높이가 다음 높이보다 1 높다면
				else if(cur-1 == road[c]) {
					// 다음 이어지는 길들이 X 길이만큼 같은 높이로 있는지 확인
					// 현재 길이 cnt
					int cnt = 1;
					// cnt가 X가 될 때까지 반복 (중간에 만족하지 못하면 반복문 종료)
					while(cnt < X) {
						// 다음 좌표가 범위 밖이거나, 다음좌표의 높이가 현재 높이와 같지 않아서 경사로를 놓지 못한다면,
						if(c+1 >= N || road[c+1] != road[c]) {
							// 활주로 X
							flag = false;
							// 종료
							break;
						}
						// 아니라면 X길이가 될때까지 좌표를 옮겨가며 반복
						else {
							// 현재 위치 증가
							c++;
							// 현재 길이 증가
							cnt++;
						}
					}
					// 반복문 종료 후 활주로 X flag로 설정되었다면 종료
					if(!flag) break;
					// 아니라면
					else {
						// 현재 높이는 낮아진 높이로 설정
						cur = road[c];
						// 현재 칸에는 활주로를 놓은 마지막 칸이 될 것이므로 새로 활주로를 겹칠 수 없기 때문에 길이는 0
						// (다음 칸부터 활주로를 놓을 수 있는 칸으로 증가)
						len = 0;
					}
				}
				// 높이가 같거나 1차이가 아니라면 경사로를 놔도 활주로가 될 수 없으므로 종료
				else {
					flag = false;
					break;
				}
			}
			// 일직선 테스트를 완료했을 때 활주로 건설이 가능하면 정답 증가
			if(flag) ans++;
		}
		
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        // 테스트 케이스 반복
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc = 1; tc <= T; tc++) {
        	sb.append("#").append(tc).append(" ");
        	// 현재 테스트 케이스에서의 활주로 수
        	ans = 0;
        	st = new StringTokenizer(br.readLine());
        	
        	// 보드의 크기 N*N, 활주로의 길이 X
        	N = Integer.parseInt(st.nextToken());
        	X = Integer.parseInt(st.nextToken());
        	
        	// board 선언(90도 회전한 보드도 저장할 것이므로 1차원 추가)
        	board = new int[2][N][N];
        	
        	// 초기 입력
        	for(int r = 0; r < N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c = 0; c < N; c++) {
        			int cur = Integer.parseInt(st.nextToken());
        			// 기존 배열 입력
        			board[0][r][c] = cur;
        			// 90도 회전배열 입력
        			board[1][c][r] = cur;
        		}
        	}
        	
        	// 각 줄마다 활주로 건설이 가능한지 테스트
        	for(int r = 0; r < N; r++) {
        		chk_road(r);
        	}
        	
        	// 최종 활주로 수 저장
        	sb.append(ans).append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}