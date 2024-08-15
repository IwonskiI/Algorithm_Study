import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 15649 - N과 M(1)
	static int N, M;
	static int[] number;
	static List<int[]> numbers = new ArrayList<>();
	static boolean[] visited;
	
	// 순열 생성 함수
	public static void perm(int cnt) {
		if(cnt == M) {
			// 완성된 수열 저장
			int[] new_num = new int[M];
			for(int i = 0; i < M; i++) {
				new_num[i] = number[i];
			}
			numbers.add(new_num);
			return;
		}
        else {
            for(int i = 1; i <= N; i++) {
                // 이미 사용한 숫자는 스킵
                if(visited[i-1]) continue;
                // 현재 위치에 숫자 입력
                number[cnt] = i;
                // 방문 확인
                visited[i-1] = true;
                // 다음 숫자 배치를 위한 재귀함수 호출
                perm(cnt+1);
                // 방문 완료 후 다시 돌려주기
                visited[i-1] = false;
            }
        }
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		number = new int[M];
		// 순열 생성
		perm(0);
		// 순열 출력
		for(int i = 0; i < numbers.size(); i++) {
			for(int j = 0; j < M; j++) {
				sb.append(numbers.get(i)[j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
