import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 14714 - 홍삼 게임 (Easy)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 게임 참여자 N명
		int N = Integer.parseInt(st.nextToken());
		
		// 첫 지목 A, B (index 기준 -1)
		int A = Integer.parseInt(st.nextToken()) - 1;
		int B = Integer.parseInt(st.nextToken()) - 1;
		
		// 각 지목 가능한 거리
		int Da = Integer.parseInt(st.nextToken());
		int Db = Integer.parseInt(st.nextToken());
		
		// A가 지목 가능한 인원 집합, B가 지목 가능한 인원 집합 
		HashSet<Integer> Aset = new HashSet<>(), Bset = new HashSet<>();
		// 방문 가능한 인원
		HashSet<String> visited = new HashSet<>();
		// 집합에 초기 지목 인원 추가
		Aset.add(A);
		Bset.add(B);
		
		// 지목 횟수
		int ans = 0;
		// 같은 사람을 지목했을 경우 종료
		boolean fin = false;
		while(!fin) {
			// 지목 인원
			String visit = "";
			// 임시 집합
			HashSet<Integer> Atemp = new HashSet<>();
			// 지목 횟수 증가
			ans++;
			for(int a : Aset) {
				// 오른쪽, 왼쪽 인원 지목
				int right = (N + a + Da) % N, left = (N + a - Da) % N;
				// B 집합에 이번에 지목한 사람이 있는지 체크
				if(Bset.contains(right) || Bset.contains(left)) {
					// 있다면 반복문 종료
					fin = true;
					break;
				}
				// 오른쪽 사람 추가
				Atemp.add(right);
				visit += right + " ";
				// 왼쪽 사람 추가
				Atemp.add(left);
				visit += left + " ";
			}
			// 임시 집합을 A집합으로 변경
			Aset = Atemp;
			visit += "/ ";
			// 종료되었다면 게임 종료
			if(fin) break;
			// B 집합도 동일하게 진행
			HashSet<Integer> Btemp = new HashSet<>();
			ans++;
			for(int b : Bset) {
				int right = (N + b + Db) % N, left = (N + b - Db) % N;
				if(Aset.contains(right) || Aset.contains(left)) {
					fin = true;
					break;
				}
				Btemp.add(right);
				visit += right + " ";
				Btemp.add(left);
				visit += left + " ";
			}
			Bset = Btemp;
			// 현재 지목 집합이 이전에도 있었다면
			if(visited.contains(visit)) {
				// Evil Galazy 출력 후 종료
				System.out.println("Evil Galazy");
				return;
			}
			// 아니라면 visted 집합에 추가
			else visited.add(visit);
		}
		
		// 최종 결과 출력
		System.out.println(ans);
	}
}