import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 14889 - 스타트와 링크
	public static int N, ans = Integer.MAX_VALUE;
	public static int[] team1, team2;
	public static int[][] teams;
	
	// 팀 조합 생성 및 시너지 계산
	public static void combi(int cnt, int start) {
		// 팀 구성이 완료되었다면
		if(cnt == N/2) {
			int score1 = 0, score2 = 0;
			int t1idx = 0, t2idx = 0;
			// 나머지 팀원 구하기
			for(int i = 1; i <= N; i++) {
				if(t1idx < N/2 && team1[t1idx] == i) t1idx++;
				else {
					team2[t2idx++] = i;
				}
			}
			
			// 팀원간의 시너지 합산
			for(int i = 0; i < N/2-1; i++) {
				for(int j = i+1; j < N/2; j++) {
					score1 += (teams[team1[i]-1][team1[j]-1] + teams[team1[j]-1][team1[i]-1]);
					score2 += (teams[team2[i]-1][team2[j]-1] + teams[team2[j]-1][team2[i]-1]);
				}
			}
			
			// 두 팀간의 점수 차이의 최소 갱신
			ans = Math.min(ans, Math.abs(score1 - score2));
			return;
		}
		
		// 팀원 뽑기
		for(int i = start; i < N; i++) {
			team1[cnt] = i + 1;
			combi(cnt + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		team1 = new int[N/2];
		team2 = new int[N/2];
		team1[0] = 1;
		teams = new int[N][N];
		
		// 시너지 입력
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				teams[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1번은 뽑아놓고 시작
		combi(1, 1);
		
		// 최소 차이 출력
		System.out.println(ans);
	}
}