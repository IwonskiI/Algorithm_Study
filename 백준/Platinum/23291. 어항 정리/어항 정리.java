import java.io.*;
import java.util.*;

public class Main {

    // BOJ 23291 - 어항 정리
	public static int K, R, C;
	public static int[] line;
	public static int[][] bowl, n_bowl, bowl2, n_bowl2;
	
	// 첫번째 회전 계산(90도 회전)
	public static void rotate(int sc, int w, int h) {
		for(int r = 0; r < h; r++) {
			for(int c = 0; c < w; c++) {
				bowl[w-c][r+w+sc] = bowl[r][c+sc];
			}
		}
	}
	
	// 두번째 회전 계산 (180도 회전)
	public static void rotate2(int sc, int w, int h) {
		for(int r = 0; r < h; r++) {
			for(int c = 0; c < w; c++) {
				bowl2[h-1-r+h][w-1-c+sc+w] = bowl2[r][c+sc];
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = (int)Math.sqrt(R) + 1;
        int size = (int)Math.pow(C, 2) - C;
        if(R < size) C--;
        
        
        K = Integer.parseInt(st.nextToken());
        int max = 0, min = 10001;
        
        line = new int[R];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < R; i++) {
        	line[i] = Integer.parseInt(st.nextToken());
        	min = Math.min(min, line[i]);
        	max = Math.max(max, line[i]);
        }
        
        int ans = 0;
        while(true) {
        	// 어항의 최댓값과 최소값의 차이가 K이하라면 어항 정렬 종료 후 횟수 출력
            if(Math.abs(max - min) <= K) {
            	System.out.println(ans);
            	break;
            }
        	ans++;
        	// 최소값에 물고기 추가
            bowl = new int[C][R];
            for(int i = 0; i < R; i++) {
            	bowl[0][i] = line[i];
            	if(line[i] == min) bowl[0][i]++;
            }
            
            // 어항 쌓기
            int idx = 0, w = 1, h = 1;
            while(true) {
            	rotate(idx, w, h);
            	idx += w;
            	h++;
            	if(idx + w -1 + h >= R) break;
            	rotate(idx, w, h);
            	idx += w;
            	w++;
            	if(idx + w - 1 + h >= R) break;
            }
            
            // 물고기 수 조정
            n_bowl = new int[C][R-idx];
            int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            for(int r = 0; r < C; r++) {
            	for(int c = 0; c < R-idx; c++) {
            		int cur = bowl[r][c+idx];
            		for(int dd = 0; dd < 4; dd++) {
            			int nr = r + d[dd][0], nc = c + idx + d[dd][1];
            			boolean in_range = (0 <= nr && nr < C) && (idx <= nc && nc < R);
            			if(in_range && bowl[nr][nc] != 0 && bowl[r][c+idx] > bowl[nr][nc]) {
            				int give = (bowl[r][c+idx] - bowl[nr][nc])/5;
            				if(give > 0) {
            					cur -= give;
            					n_bowl[nr][nc-idx] += give;
            				}
            			}
            		}
            		n_bowl[r][c] += cur;
            	}
            }
            
            // 일렬로 배치
            bowl2 = new int[4][R];
            int line_idx = 0;
            for(int c = 0; c < R-idx; c++) {
            	for(int r = 0; r < C; r++) {
            		if(n_bowl[r][c] == 0) break;
            		bowl2[0][line_idx++] = n_bowl[r][c];
            	}
            }
            
            // 두번째 어항 쌓기
            idx = 0;
            w = R/2;
            h = 1;
            for(int i = 0; i < 2; i++) {
            	rotate2(idx, w, h);
            	idx += w;
            	h*=2;
            	w /= 2;
            }
            
            // 두번째 물고기 수 조정
            n_bowl2 = new int[h][R-idx];
            for(int r = 0; r < h; r++) {
            	for(int c = 0; c < R-idx; c++) {
            		int cur = bowl2[r][c+idx];
            		for(int dd = 0; dd < 4; dd++) {
            			int nr = r + d[dd][0], nc = c + idx + d[dd][1];
            			boolean in_range = (0 <= nr && nr < h) && (idx <= nc && nc < R);
            			if(in_range && bowl2[nr][nc] != 0 && bowl2[r][c+idx] > bowl2[nr][nc]) {
            				int give = (bowl2[r][c+idx] - bowl2[nr][nc])/5;
            				if(give > 0) {
            					cur -= give;
            					n_bowl2[nr][nc-idx] += give;
            				}
            			}
            		}
            		n_bowl2[r][c] += cur;
            	}
            }
            
            // 두번째 일렬로 놓기
            line_idx = 0;
            max = 0;
            min = 10001;
            for(int c = 0; c < R-idx; c++) {
            	for(int r = 0; r < h; r++) {
            		line[line_idx++] = n_bowl2[r][c];
            		min = Math.min(min, n_bowl2[r][c]);
            		max = Math.max(max, n_bowl2[r][c]);
            	}
            }
        }
        
    }

}