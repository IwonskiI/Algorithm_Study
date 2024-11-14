import java.io.*;
import java.util.*;

public class Main {

    // BOJ 10875 - 뱀
	public static int L;
	public static ArrayList<int[]> line = new ArrayList<>();
	
	public static int check(int[] cur, int time) {
		// 몇 초동안 이동 가능한지 체크
		int res = time;
		// 이동 정보
		int dir = cur[0], sr = cur[1], sc = cur[2], er = cur[3], ec = cur[4];
		// 범위 체크
		if(er < 0 || L*2 + 1 <= er || ec < 0 || L*2 + 1 <= ec) {
			if(er < 0) res = Math.min(time + er, res);
			else if(L*2 + 1 <= er) res = Math.min(res, time - (er - (2*L+1))-1);
			else if(ec < 0) res = Math.min(res, time + ec);
			else if(L*2 + 1 <= ec) res = Math.min(res, time - (ec - (2*L+1))-1);
		}
		// 이미 이동한 선들에 대해서 겹치는 부분 탐색
		int l_idx = 0;
		for(int[] l : line) {
			// 마지막 선은 연결되어있는 선이므로 탐색 X
			if(++l_idx == line.size()) break;
			int ldir = l[0];
			int lsr = l[1], lsc = l[2], ler = l[3], lec = l[4];
			if(ldir == 2 || ldir == 3) {
				lsr = l[3];
				lsc = l[4];
				ler = l[1];
				lec = l[2];
			}
			// 진행방향 - 가로
			if(dir == 0 || dir == 2) {
				// 평행하고 높이가 같을 때
				if((ldir == 0 || ldir == 2) && lsr == sr) {
					// 왼쪽에서 다가 올 때
					if(dir == 0 && sc < lsc && lsc <= ec) {
						res = Math.min(time - (ec - lsc+1), res);
					}
					// 오른쪽에서 다가 올 때
					else if(dir == 2 && lec < sc && ec <= lec) {
						res = Math.min(res, time - (lec+1 - ec));
					}
				}
				// 수직이고 교차할 때
				else if((lsr <= sr && sr <= ler) && ((sc <= lsc && lsc <= ec && dir == 0) || (dir == 2 && ec <= lsc && lsc <= sc))) {
					// 왼쪽에서 다가 올 때
					if(dir == 0) res = Math.min(res, time - (ec - lsc+1));
					// 오른쪽에서 다가 올 때
					else res = Math.min(res, time - (lec+1 - ec));
				}
			}
			// 진행방향 - 세로
			else {
				// 평행하고 열이 같을 때
				if((ldir == 1 || ldir == 3) && lsc == sc) {
					// 위에서 다가 올 때
					if(dir == 1 && sr < lsr && lsr <= er) {
						res = Math.min(time - (er - lsr+1), res);
					}
					// 아래에서 다가 올 때
					else if(dir == 3 && ler < sr && er <= ler) {
						res = Math.min(res, time - (ler+1 - er));
					}
				}
				// 수직이고 교차할 때
				else if((lsc <= sc && sc <= lec) && ((sr <= lsr && lsr <= er && dir == 1) || (er <= lsr && lsr <= sr && dir == 3))) {
					// 위에서 다가 올 때
					if(dir == 1) res = Math.min(res, time - (er - lsr+1));
					// 아래에서 다가 올 때
					else res = Math.min(res, time - (ler+1 - er));
				}
			}
		}
		return res;
	}
	
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        
        int cr = L, cc = L;
        
        // 우 - 하 - 좌 - 상
        int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dd = 0;
        long ans = 0;
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int time = Integer.parseInt(st.nextToken());
        	char dir = st.nextToken().charAt(0);
        	
        	int[] cur = new int[] {dd, cr, cc, cr+(d[dd][0]*time), cc+(d[dd][1]*time)};
        	// 이동 시간 계산
        	int move = check(cur, time);
        	// 이동한 시간 추가
        	ans += move;
        	// 이동한 시간이 기존 이동할 시간보다 작다면 죽은 것
        	if(move != time) {
        		// 결과 출력
        		System.out.println(ans+1);
        		return;
        	}
        	// 아니라면 현재 이동 경로 저장
        	line.add(cur);
        	// 좌표 이동
        	cr += d[dd][0]*time;
        	cc += d[dd][1]*time;
        	
        	// 방향 전환
        	if(dir == 'L') dd += 3;
        	else dd += 1;
        	dd %= 4;
        	
        }
        // 마지막 회전까지 부딪히지 않았다면 최종 이동 진행
        int time = 2*L+1;
        ans += check(new int[] {dd, cr, cc, cr+(d[dd][0]*time), cc+(d[dd][1]*time)}, time);
        System.out.println(ans+1);
    }

}