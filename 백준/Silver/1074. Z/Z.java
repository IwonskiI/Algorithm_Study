import java.util.*;
import java.io.*;

public class Main {

	// BOJ 1074 - Z
	// dfs 함수를 중단시킬 flag
	public static boolean find;
	// 정답을 저장할 변수 ans, 지금까지 칸 수를 셀 cnt
	public static int N, R, C, ans, cnt;
	
	public static void dfs(int r, int c, int lv) {
		// 목적 좌표에 도달한다면
		if(r == R && c == C) {
			// 정답 저장
			ans = ++cnt;
			// 목적지 도달 flag true
			find = true;
			// 종료
			return;
		}
		// 마지막 깊이라면
		if(lv == 0) {
			// 칸 수 증가 후 return
			cnt++;
			return;
		}
		
		// 마지막 깊이가 아니라면 Z 모양으로 탐색
		// N번 깊이만큼 탐색할 수 있도록 lv을 인수로 전달
		// 탐색 후 값을 찾았다면 return
		// Z값의 시작 위치를 2의 lv제곱으로 계산
		// 해당 범위 안에 목표 좌표가 없다면 스킵, 목표 좌표가 있는 범위만 탐색
		if(R < r + (int)Math.pow(2, lv - 1) && C < c + (int)Math.pow(2, lv - 1)) {
			dfs(r, c, lv - 1);
			if(find) return;
		}else if(R < r + (int)Math.pow(2, lv - 1) && C >= c + (int)Math.pow(2, lv - 1)) {
			// 스킵한 만큼 cnt 추가
			cnt += (int)Math.pow(4, lv - 1);
			dfs(r, c + (int)Math.pow(2, lv - 1), lv - 1);
			if(find) return;
		}else if(R >= r + (int)Math.pow(2, lv - 1) && C < c + (int)Math.pow(2, lv - 1)) {
			cnt += (int)Math.pow(4, lv - 1)*2;
			dfs(r + (int)Math.pow(2, lv - 1), c, lv - 1);
			if(find) return;
		}else {
			cnt += (int)Math.pow(4, lv - 1)*3;
			dfs(r + (int)Math.pow(2, lv - 1), c + (int)Math.pow(2, lv - 1), lv - 1);
			if(find) return;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 첫 시작은 -1 (찾으면 바로 ++cnt를 해버리기 때문)
        cnt = -1;
        find = false;
        
        // 0,0부터 N단계 탐색 시작
        dfs(0, 0, N);
        
        // 정답 출력
        System.out.println(ans);
    }
}