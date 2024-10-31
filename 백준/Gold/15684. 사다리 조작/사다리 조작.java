import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 15684 - 사다리 조작
	public static int N, M, H, ans;
	public static boolean[][] line;
	
	// 사다리가 수직으로 연결되는지 확인
	public static boolean test() {
		for(int i = 0; i < N; i++) {
			// rpos는 출발지 번호
			int rpos = i;
			// 아래로 진행
			for(int j = 0; j < H; j++) {
				// rpos가 0(1번줄)이 아니면서 왼쪽에 길이 연결되어 있다면 왼쪽으로 한칸 이동
				if(0 < rpos && line[j][rpos-1]) rpos--;
				// rpos가 N-1(마지막줄)이 아니면서 오른쪽으로 길이 연결되어 있다면 오른쪽으로 한칸 이동
				else if(rpos < N-1 && line[j][rpos]) rpos++;
			}
			// 각 줄 탐색 후 첫 출발 위치와 도착위치가 다르다면 test 불통과 - false return
			if(i != rpos) return false;
		}
		// 모든 줄을 통과하면 true return
		return true;
	}
	
	public static void connect(int cnt, int idx) {
		// 지금까지 선택한 사다리가 기존 정답보다 적고, 각 사다리가 수직으로 연결되는 사다리라면
		if(cnt < ans && test()) {
			// 현재 연결한 사다리 개수로 갱신
			ans = Math.min(ans, cnt);
			// 더 이상 사다리를 추가로 연결할 필요 없으니 종료
			return;
		}
		
		// 아직 연결되지 않는다면
		for(int i = idx; i < H*(N-1); i++) {
			// 현재 탐색 좌표 계산
			int cr = i/(N-1), cc = i%(N-1);
			// 이미 사다리가 연결되어있는 칸이라면 스킵
			if(line[cr][cc]) continue;
			// 다음 사다리를 한개 더 연결했을 때, 기존 정답보다 적다면 진행
			// (한개를 더 연결했을 때 기존 최소값보다 크거나 같다면 굳이 탐색 진행 X)
			if(ans > cnt+1) {
				// 사다리 연결
				line[cr][cc] = true;
				// 다음 탐색 - 현재 사다리로 test를 통과하는지 검사 후 다음 사다리 연결 진행
				connect(cnt + 1, i + 1);
				// 사다리 제거
				line[cr][cc] = false;
			}
			// 이후로는 사다리를 추가하는 경우만 있으므로 더이상 탐색 X - 종료
			else return;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 세로 줄 개수 N, 기존 가로 줄 M, 높이 H
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		line = new boolean[H][N-1];
		ans = 4;
		
		// 사다리 연결
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken()) - 1;
			int start = Integer.parseInt(st.nextToken()) - 1;
			line[h][start] = true;
		}
		
		// 추가할 수 있는 사다리 조합 계산
		connect(0, 0);
		
		if(ans == 4) ans = -1;
		// 죄종 결과 저장
		sb.append(ans);
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}