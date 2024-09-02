import java.io.*;
import java.util.*;

public class Solution {

    // SWEA 2115 - 벌꿀 채취
    public static int N, M, C, ans;
    public static int[][] board, bucket;
    public static int[] b;
    public static boolean[] selected;
    
    // 점수 계산 함수
    public static int calc(ArrayList<Integer> lst) {
        int res = 0;
        // lst에 저장된 값의 제곱의 합을 return
        for(int i = 0; i < lst.size(); i++) {
            res += (lst.get(i) * lst.get(i));
        }
        return res;
    }
    
    // 선택된 꿀들이 C를 초과하는지 체크
    public static int valid(ArrayList<Integer> lst) {
        int sum = 0;
        for(int i = 0; i < lst.size(); i++) {
            sum += lst.get(i);
        }
        // C를 초과하지 않는다면 점수 계산
        if(sum <= C) return calc(lst);
        else return -1;
    }
    
    // 부분집합 및 해당 버킷에서의 최대 점수 구하기
    public static void powerset(int cnt) {
        if(cnt == M) {
        	// 선택된 원소들을 lst에 담아서 유효성 검사 후 점수 계산
            ArrayList<Integer> lst = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                if(selected[i]) lst.add(b[i]);
            }
            // 유효하다면 점수를, 아니라면 -1을 return
            int cur = valid(lst);
            // 현재 저장된 값보다 크다면 갱신
            if(ans < cur) ans = cur;
            return;
        }
        
        // 부분집합 구하기
        selected[cnt] = true;
        powerset(cnt + 1);
        selected[cnt] = false;
        powerset(cnt + 1);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            // 꿀들의 정보 저장
            board = new int[N][N];
            // 해당 칸에서 시작한 M칸의 꿀의 점수 중 최댓값 저장
            bucket = new int[N][N-M+1];
            
            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++) {
                    int honey = Integer.parseInt(st.nextToken());
                    board[r][c] = honey;
                }
            }
            
            // 모든 칸에 대해서 부분집합으로 점수 계산
            for(int r = 0; r < N; r++) {
                ans = 0;
                b = new int[M];
                // 앞에서부터 M개의 원소를 뽑아 첫번째 버킷 생성
                for(int c = 0; c < M; c++) {
                    b[c] = board[r][c];
                }
                // 부분 집합으로 점수 계산
                selected = new boolean[M];
                powerset(0);
                bucket[r][0] = ans;
                // 슬라이딩 윈도우 기법으로 끝에서 M칸 앞까지 점수 계산
                int cnt = 0;
                for(int c = M; c < N; c++) {
                    ans = 0;
                    b[cnt] = board[r][c];
                    selected = new boolean[M];
                    powerset(0);
                    bucket[r][c - M + 1] = ans;
                    cnt++;
                    cnt %= M;
                }
            }
            
            // 각 줄의 최대값 계산
            int best[] = new int[N];
            // 가장 큰 값 f, 두번째로 큰 값 s
            int f = -1, s = -1;
            // 각 값의 가로 줄 좌표
            int frp = -1, srp = -1;
            for(int r = 0; r < N; r++) {
            	// 각 줄의 최댓값 저장
                int row_max = -1;
                for(int c = 0; c < N - M + 1; c++) {
                	// 현재 값이 가장 크다면 이전 최댓값을 두번째 값으로 이동 후 최댓값 갱신
                    if(bucket[r][c] > f) {
                        s = f;
                        srp = frp;
                        f = bucket[r][c];
                        frp = r;
                        
                    }
                    // 현재 값이 두번째로 크다면 두번째로 큰 값과 좌표 갱신
                    else if(bucket[r][c] > s) {
                        s = bucket[r][c];
                        srp = r;
                    }
                    // 각 줄의 최대값 갱신
                    if(bucket[r][c] > row_max) row_max = bucket[r][c];
                }
                // 최댓값 저장
                best[r] = row_max;
            }
            
            // 다른 줄이라면 -> 버킷이 겹칠 일 없으므로 두 값 더한 값 저장
            if(frp != srp) sb.append(f + s).append("\n");
            // 같은 줄이라면 -> 겹칠 수 도 있기 때문에 한번 더 검사
            else {
            	// 해당 줄을 제외한 나머지 줄에서 최대값을 구하고 가장 큰 값과 더함 
                int temp = 0;
                for(int i = 0; i < N; i++) {
                	if(i == frp) continue;
                	temp = temp < best[i] ? best[i] : temp;
                }
                
                temp += f;
                
                // 해당 줄에서 겹치지 않은 버킷으로 최댓값을 만들어 낼 수 있는지 계산 후 갱신
                int max = 0;
                for(int c = 0; c < N - M + 1; c++) {
                	int cur = bucket[frp][c];
                	for(int c1 = c+M; c1 < N - M + 1; c1++) {
                		max = max < cur+bucket[frp][c1] ? cur+bucket[frp][c1] : max; 
                	}
                }
                
                temp = temp < max ? max : temp;
                
                sb.append(temp).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}
