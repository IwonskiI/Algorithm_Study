import java.util.*;
import java.io.*;

public class Solution {

	// SWEA 2112 - [모의 SW 역량테스트] 보호 필름
	
	// 전역변수 설정
	// D : 높이 / W : 가로 / K : 기준 / c_cnt : 조합 개수
	public static int D, W, K, c_cnt;
	// 약품을 넣을 줄을 저장할 rows 배열
	public static int[] rows;
	// 조합에서 사용여부를 표시할 using 배열
	public static boolean[] using;
	// 현재상태의 film과 원본 origin 배열
	public static int[][] film, origin;
	
	public static boolean check(int col) {
		int prev = film[0][col];
		int cnt = 1;
		
		for(int i = 1; i < D; i++) {
			if(i > W - K && (W - i)+cnt < K) break;
			if(prev == film[i][col]) {
				cnt++;
				if(cnt == K) return true;
			}
			else {
				prev = film[i][col];
				cnt = 1;
			}
		}
		
		return false;
	}
	
	// 모든 줄에 대해서 기준을 통과하는지 검사
	public static boolean issuccess() {
		// 각 줄에 대해서 검사
		for(int col = 0; col < W; col++) {
			// 해당 줄이 통과하는지 확인하는 flag 변수
			boolean flag = false;
			// 현재 연속 중인 칸
			int prev = film[0][col];
			// 현재까지 연속된 필름 수
			int cnt = 1;
			
			// K가 1이라면 무조건 통과
			if(cnt == K) return true;
			
			// 해당 줄 검사
			for(int i = 1; i < D; i++) {
				// 이후 모든 칸이 같은 종류여도 cnt가 부족하다면 중단
				if(i > D - K && (D - i)+cnt < K) break;
				// 연속중인 타입과 현재 타입이 같다면 
				if(prev == film[i][col]) {
					// 연속 필름 수 증가
					cnt++;
					// 연속된 필름수가 기준치가 되었다면
					if(cnt == K) {
						// 해당 줄 통과로 표시 후 종료
						flag = true;
						break;
					}
				}
				// 연속중인 타입과 현재 타입이 다르다면
				else {
					// 현재 타입을 연속중인 타입으로 갱신
					prev = film[i][col];
					// 연속 필름 수 초기화
					cnt = 1;
				}
			}
			// 통과되지 않은 줄이 있다면 바로 false로 종료
			if(!flag) return false;
		}
		
		// 모든 줄이 통과했다면 true 반환
		return true;
	}
	
	// 해당 줄 염색
	public static void change(int row, int type) {
		for(int i = 0; i < W; i++) {
			// type이 2라면 원상복구, 아니라면 해당 타입으로 염색
			int t = type == 2 ? origin[row][i] : type;
			film[row][i] = t;
		}
	}
	
	// 조합 생성 및 테스트
	public static boolean combi(int start, int cnt) {
		// 뽑아야하는 수만큼 뽑았다면 
		if(cnt == c_cnt) {
			// 테스트를 통과하면 true 반환
			if(issuccess()) return true;
			// 불가능하다면 false 반환
			else return false;
		}
		
		// 아직 조합이 완성되지 않았다면 조합 생성
		for(int i = start; i < D; i++) {
			if(using[i]) continue;
			using[i] = true;
			// 선택된 줄을 0번으로 염색
			change(i, 0);
			// 다음 경우의 수 테스트
			if(combi(i+1, cnt+1)) return true;
			// 선택된 줄을 1번으로 염색
			change(i, 1);
			// 다음 경우의 수 테스트
			if(combi(i+1, cnt+1)) return true;
			// 둘 다 불가능하다면 원상 복구
			change(i, 2);
			// 사용 해제
			using[i] = false;
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 배열 초기화
			origin = new int[D][W];
			film = new int[D][W];
			rows = new int[D];
			using = new boolean[D];
			
			// 초기 상태 입력
			for(int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < W; c++) {
					film[r][c] = origin[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 모든 값에서 실패하면 모든 줄을 염색해야하므로 초기 값은 D로 설정
			int ans = D;
			
			// 0부터 시작해서 순차적으로 조합 생성 후 염색 시작
			for(int i = 0; i < D; i++) {
				// 조합의 원소 수를 i로 설정
				c_cnt = i;
				// 현재 염색약의 수로 테스트에 통과했다면
				if(combi(0, 0)) {
					// 현재 값을 정답에 저장 후 종료
					ans = i;
					break;
				}
			}
			
			// 정답 저장
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		// 최종 정답 출력
		System.out.println(sb.toString());
	}

}