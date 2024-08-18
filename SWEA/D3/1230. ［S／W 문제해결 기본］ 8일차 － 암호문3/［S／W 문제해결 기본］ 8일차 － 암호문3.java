import java.util.*;
import java.io.*;

public class Solution {
	
	// SWEA 1230 - [S/W 문제해결 기본] 8일차 - 암호문3
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 10개의 테스트케이스 진행
		for(int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 암호를 담을 리스트 선언
			List<Integer> num_lst = new ArrayList<>();
			// 초기 리스트 설정
			for(int i = 0; i < N; i++) {
				num_lst.add(Integer.parseInt(st.nextToken()));
			}
			
			// 명령어의 갯수 M
			int M = Integer.parseInt(br.readLine());
			// 명령어를 공백 단위로 쪼개기
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				char op = st.nextToken().charAt(0);
				int x, y, s;
				// 입력값에 따라 받는 값 다르게 설정
				switch (op) {
				// 삽입
				case 'I':
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					// x번째 암호문 바로 다음에 y개의 암호문 삽입
					for(int j = 0, idx = x; j < y; j++, idx++) {
						num_lst.add(idx, Integer.parseInt(st.nextToken()));
					}
					break;
				// 삭제
				case 'D':
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					// x번째 바로 다음부터 y개의 암호문 삭제
					for(int j = 0; j < y; j++) {
						num_lst.remove(x);
					}
					break;
				// 추가
				case 'A':
					x = Integer.parseInt(st.nextToken());
					// 맨 뒤에 x개의 암호문 추가
					for(int j = 0; j < x; j++) {
						num_lst.add(Integer.parseInt(st.nextToken()));
					}
					break;
				}
			}
			// 가장 앞 10개의 암호문만 출력
			sb.append("#").append(tc).append(" ");
			for(int i = 0; i < 10; i++) {
				sb.append(num_lst.get(i)).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}