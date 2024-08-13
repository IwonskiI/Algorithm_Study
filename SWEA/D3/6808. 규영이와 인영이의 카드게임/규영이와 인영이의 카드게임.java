import java.io.*;
import java.util.*;

class Solution {
	
	// SWEA 6808 - 규영이와 인영이의 카드게임
	
	// 규영이의 승, 패를 기록할 변수
	public static int win, lose;
	// 규영이의 카드(순서O)와 인영이의 카드 조합(순서X)
	public static int[] g_card, i_card;
	// 인영이의 카드로 만들 수 있는 순열을 담을 배열
	public static int[] number = new int[9];
	// 순열 생성을 위한 방문 확인 배열
	public static boolean[] visited = new boolean[9];
	
	// 순열 생성 및 승패 확인
	public static void perm(int cnt) {
		// 수열 완성 시, 승패 확인
		if(cnt == 9) {
			// 규영이의 점수와 인영이의 점수
			int g_score = 0, i_score = 0;
			// 점수 계산
			for(int i = 0; i < 9; i++) {
				if(g_card[i] > number[i]) g_score += (g_card[i] + number[i]);
				else i_score += (g_card[i] + number[i]);
			}
			// 규영이가 이기면 win 증가 / 지면 lose 증가
			if (g_score > i_score) win++;
			else if(g_score < i_score) lose++;
			return;
		}
		// 수열 완성을 위한 재귀
		else {
			for(int i = 0; i < 9; i++) {
				// 이미 사용한 숫자 카드는 스킵
				if(visited[i]) continue;
				// 현재 위치에 숫자카드 배치
				number[cnt] = i_card[i];
				// 방문 확인
				visited[i] = true;
				// 다음 숫자 배치를 위한 재귀함수 호출
				perm(cnt+1);
				// 방문 완료 후 다시 돌려주기
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			// 카드의 주인을 나타낼 배열 - true:규영 / false:인영
			// 카드의 인덱스는 카드 번호 - 1
			boolean[] card_lst = new boolean[18];
			
			// 규영이의 카드 순서  g_card와 인영이의 카드 배열 i_card
			g_card = new int[9];
			i_card = new int[9];
			
			// 규영이가 이긴 횟수와 진 횟수를 저장할 변수
			win = 0;
			lose = 0;
			
			// 규영이의 카드 번호 입력
			for(int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				// 인영이가 가질 카드를 확인하기 위해 규영이의 카드 표시
				card_lst[num - 1] = true;
				g_card[i] = num;
			}
			// 인영이의 카드 번호 입력
			for(int i = 0, cnt = 0; i < 18; i++) {
				if(cnt == 9) break;
				if(!card_lst[i]) i_card[cnt++] = i + 1;
			}
			
			// 인영이의 카드로 만들 수 있는 순열 생성
			perm(0);
			
			
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
