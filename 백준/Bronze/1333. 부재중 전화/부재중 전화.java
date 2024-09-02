import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 1333 - 부재중 전화
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 노래의 곡 수
		int N = Integer.parseInt(st.nextToken());
		// 노래의 길이
		int L = Integer.parseInt(st.nextToken());
		// 벨소리의 간격
		int D = Integer.parseInt(st.nextToken());
		
		// 현재 시간 , 벨소리가 울리는 시간
		int time = 0, call = 0;
		// 노래가 나오고 있는지 여부
		boolean msg = true;
		
		// 앨범을 한번 들을 때 까지 걸리는 시간 ( N * (L+5) - 노래 시간 + 노래가 안나오는 사이 시간)
		while(time < N * (L+5)) {
			// 노래가 나와야한다면 노래 시간 추가
			if(msg) time += L;
			// 노래가 끝난 시점이라면 쉬는시간 추가
			else time += 5;
			
			// 노래 종료시점
			if(msg) {
				// 노래가 진행되는 동안 울린 벨소리 계산
				while(time > call+D) call += D;
			}
			// 대기 시간 종료 시점
			else {
				// 대기시간 중에 울린 벨소리 계산 
				while(time > call+D) {
					call += D;
					// 벨소리가 울렸을 때, 노래가 멈춘 뒤이고, 다음 노래가 시작하기 전이라면
					if(time - 5 <= call && call < time) {
						// 벨소리가 울린 시간 출력 후 종료
						System.out.println(call);
						return;
					}
				}
			}
			// 노래 상태 변경
			msg = !msg;
		}
		
		// 만약 앨범이 종료될 때 까지 벨소리를 못들었다면 앨범이 끝나고 노래를 들을 수 있는 가장 빠른 시간 출력
		while(time % D != 0) time++;
		System.out.println(time);
	}

}
