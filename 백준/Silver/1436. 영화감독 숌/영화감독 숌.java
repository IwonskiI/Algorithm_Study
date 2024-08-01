import java.io.*;


public class Main {
	
	// BOJ 1436 - 영화감독 숌
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//666부터 시작
		int num = 666;
		
		while(true) {
			// 정수를 문자열로 변환
			String S = String.valueOf(num);
			
			for(int i = 0; i < S.length() - 2; i++) {
				//6이 등장한 인덱스값부터 뒤로 2개 더 6이 온다면 N에 -1하고 반복문 종료
				if(S.charAt(i) == '6' && S.charAt(i + 1) == '6' && S.charAt(i + 2) == '6') {
					N--;
					break;
				}
			}
			
			if(N == 0) break;
			num++;
		}
		System.out.println(num);
	}

}