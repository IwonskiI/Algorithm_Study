import java.util.*;
import java.io.*;

public class Main {

	// BOJ 15663 - N과 M (9)
	public static int N, M;
	// 초기 입력  배열, 순열 저장 배열
	public static int[] lst, number;
	// 방문 처리 배열
	public static boolean[] visited;
	// 정답 저장 StringBuilder
	public static StringBuilder sb = new StringBuilder();
	
	// 순열 생성 함수
	public static void perm(int cnt) {
		// 같은 자리에 같은 숫자가 2번 오지 않도록 방문 배열 하나 더 선언
		HashSet<Integer> set = new HashSet<>();
		// cnt(숫자의 개수)가 M개가 되었으면,
		if(cnt == M) {
			// number(순열)에 담긴 숫자 저장
			for(int i = 0; i < M; i++) {
				sb.append(number[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		// M개가 되지 않았으면 될 때까지 순열 생성
		for(int i = 0; i < N; i++) {
			// 만약 해당 숫자를 사용중이거나, 해당 자리에서 똑같은 숫자를 사용한 적이 있다면 continue
			if(visited[i] || set.contains(lst[i])) continue;
			// 해당 위치 숫자 방문 처리
			visited[i] = true;
			// 해당 종류 숫자 방문 처리
			set.add(lst[i]);
			// 순열 생성
			number[cnt] = lst[i];
			// 추가 순열 생성
			perm(cnt + 1);
			// 방문 처리 해제
			visited[i] = false;
			// 자리수는 해당 함수가 끝날 때까지 반복되면 안되므로 해제 X
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 초기 입력 배열
        lst = new int[N];
        // 
        number = new int[M];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        // 초기 입력
        for(int i = 0; i < N; i++) {
        	lst[i] = Integer.parseInt(st.nextToken());
        }
        // 사전순 출력 위한 정렬
        Arrays.sort(lst);
        // 순열 생성
        perm(0);
        
        // 출력
        System.out.println(sb.toString());
    }
}