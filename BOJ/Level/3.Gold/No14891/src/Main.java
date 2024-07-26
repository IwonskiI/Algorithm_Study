import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 14981 - 톱니바퀴
	static int[][] mag_lst;
	
	// 톱니바퀴를 돌리는 함수
	public static void turn (int m_num, int d) {
		switch(d) {
		// 시계 방향이면 배열을 한 칸씩 오른쪽으로 밀어주고 제일 마지막 값을 제일 첫 위치에 넣어줌
		case 1:
			int temp1 = mag_lst[m_num][7];
			for(int i = 7; i > 0; i--) {
				mag_lst[m_num][i] = mag_lst[m_num][i-1];
			}
			mag_lst[m_num][0] = temp1;
			break;
		// 반시계 방향이면 배열을 한 칸씩 왼쪽으로 밀어주고 제일 첫 값을 제일 마지막 위치에 넣어줌 
		case -1:
			int temp2 = mag_lst[m_num][0];
			for(int i = 0; i < 7; i++) {
				mag_lst[m_num][i] = mag_lst[m_num][i+1];
			}
			mag_lst[m_num][7] = temp2;
			break;
		}
	}
	
	// 돌아갈 톱니 바퀴를 확인하는 함수
	public static void turn_mag (int m_num, int d) {
		// 돌아갈 톱니바퀴를 저장할 deque
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {m_num, d});
		
		// 인접한 톱니바퀴는 반대 방향으로 돌아가야하므로 방향(부호)를 반대로 매핑
		HashMap<Integer, Integer> d_map = new HashMap<>();
		d_map.put(1, -1);
		d_map.put(-1, 1);
		
		// 인접한 톱니바퀴가 이미 돌아간 톱니바퀴인지 확인하는 배열
		boolean[] visited = new boolean[4];
		visited[m_num] = true;
		
		// 톱니바퀴 회전을 끝낼 때까지 반복
		while(!dq.isEmpty()) {
			
			int[] cur = dq.poll();
			int c_mnum = cur[0], c_dir = cur[1];
			
			// 현재 톱니바퀴의 오른쪽 톱니바퀴가 범위 안인지 && 현재 톱니바퀴와 다음 톱니바퀴가 만나는 곳이 다른 극인지 && 이미 돌렸던 톱니바퀴인지 확인
			// 모든 조건이 일치하면 다음으로 돌릴 톱니바퀴로 추가 및 방문 확인
			if(c_mnum + 1 <= 3 && mag_lst[c_mnum][2] != mag_lst[c_mnum+1][6] && !visited[c_mnum+1]) {
				dq.offer(new int[] {c_mnum+1, d_map.get(c_dir)});
				visited[c_mnum+1] = true;
			}
			// 현재 톱니바퀴의 왼쪽 톱니바퀴가 범위 안인지 && 현재 톱니바퀴와 이전 톱니바퀴가 만나는 곳이 다른 극인지 && 이미 돌렸던 톱니바퀴인지 확인
			// 모든 조건이 일치하면 다음으로 돌릴 톱니바퀴로 추가 및 방문 확인
			if(c_mnum - 1 >= 0 && mag_lst[c_mnum][6] != mag_lst[c_mnum-1][2] && !visited[c_mnum-1]) {
				dq.offer(new int[] {c_mnum-1, d_map.get(c_dir)});
				visited[c_mnum-1] = true;
			}
			// 돌릴 톱니바퀴 확인  후 현재 톱니바퀴 회전 (배열 이동)
			turn(c_mnum, c_dir);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		mag_lst = new int[4][8];
		
		for(int i = 0; i < 4; i++) {
			String[] m_lst = br.readLine().split("");
			for(int j = 0; j < 8; j++) {
				mag_lst[i][j] = Integer.parseInt(m_lst[j]);
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int mag_num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			// 톱니바퀴 번호를 배열의 인덱스로 사용할 것이므로 -1해서 전달
			turn_mag(mag_num-1, dir);
		}
		
		// 첫 번째 톱니 바퀴부터 점수를 계산하며 2의 거듭제곱으로 점수를 추가하므로 point에 2를 곱하면서 반복
		int point = 1, ans = 0;
 		for(int i = 0; i < 4; i++) {
			if(mag_lst[i][0] == 1) ans += point;
			point *= 2;
		}
		
		System.out.println(ans);

	}

}
