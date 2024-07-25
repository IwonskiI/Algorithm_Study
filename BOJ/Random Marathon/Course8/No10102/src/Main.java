import java.io.*;


public class Main {
	
	// BOJ 10102 - 개표
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 투표 내용을 잘라서 배열로 저장
		String[] vote = br.readLine().split("");
		
		// 총점을 관리할 변수 선언
		int aCnt = 0, bCnt = 0;
		
		// 배열을 순회하며 A라면 aCnt를, B라면 bCnt를 증가
		for(int i = 0; i < N; i++) {
			if(vote[i].equals("A")) aCnt++;
			else if(vote[i].equals("B")) bCnt++;
		}
		
		// 결과 비교 후 결과에 맞는 값 출력
		if(aCnt > bCnt) System.out.println("A");
		else if(aCnt < bCnt) System.out.println("B");
		else if(aCnt == bCnt) System.out.println("Tie");

	}

}
