import java.io.*;
import java.util.*;

public class Main {

	//BOJ 18129 - 이상한 암호코드
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 정답을 저장할 문자열
		String ans = "";
		String str = st.nextToken();
		int k = Integer.parseInt(st.nextToken());
		// 알파벳 중복 여부를 체크할 배열
		boolean[] alpha = new boolean[26];
		
		// len = 현재 연속된 문자열 길이, prev = 이전 알파벳 코드 (대소문자 구분X)
		int len = 0, prev = -1;
		for(int i = 0; i < str.length(); i++) {
			// 문자를 아스키코드로 치환한 뒤, 0~25 사이의 숫자로 치환
			int cur = str.charAt(i);
			
			// 대문자라면 65를 빼서 0~25 사이로 치환
			if(65 <= cur && cur <= 90) cur -= 65;
			// 소문자라면 97를 빼서 0~25 사이로 치환
			else cur -= 97;
			
			// 이전 문자와 동일하다면 길이 ++
			if(prev == cur) len++;
			// 다르다면
			else {
				// 제일 첫 문자가 아니라면
				if(prev != -1) {
					// 해당 문자가 아직 나오지 않았다면
					if(!alpha[prev]) {
						// 해당 문자 방문 처리
						alpha[prev] = true;
						// 만약 문자의 길이가 k 이상이라면 1을 추가
						if(len >= k) ans += "1";
						// 아니라면 0을 추가
						else ans += "0";
					}
				}
				// 길이를 1로 초기화
				len = 1;
			}
			// prev에 현재 값을 넣고 다음 순회 시작
			prev = cur;
		}
		// 마지막 남은 문자열 처리
		// 방문 전이라면 ans에 값 추가
		if(!alpha[prev]) {
			if(len >= k) ans += "1";
			else ans += "0";
		}
		System.out.println(ans);
	}

}
