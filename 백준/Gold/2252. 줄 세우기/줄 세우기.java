import java.util.*;
import java.io.*;

public class Main {

    // BOJ 2252 - 줄 세우기
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 연결된 간선의 수 (인덱스 번호보다 앞에 있어야하는 번호의 수)
        int[] in = new int[N];
        // 이미 추가 된 원소인지 체크
        boolean[] check = new boolean[N];
        // 순서를 저장하는 리스트
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        
        // 리스트 초기화
        for(int i = 0; i < N; i++) {
        	edges.add(new ArrayList<>());
        }
        
        // 순서 입력
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int front = Integer.parseInt(st.nextToken()) - 1;
        	int back = Integer.parseInt(st.nextToken()) - 1;
        	
        	// 뒤에 서야하는 사람을 추가
        	edges.get(front).add(back);
        	// 앞에 나와야하는 사람의 수 증가
        	in[back]++;
        }
        
        // 총 인원수 체크
        int cnt = N;
        
        // 모든 인원이 나올 때 까지 진행
        while(cnt > 0) {
        	// 현재 가장 앞에 설 수 있는 사람 탐색
        	for(int i = 0; i < N; i++) {
        		// 이미 선 사람이라면 스킵
        		if(check[i]) continue;
        		// 내 앞에 더이상 서야하는 사람이 없다면
        		if(in[i] == 0) {
        			// 줄 서기
        			sb.append(i+1).append(" ");
        			// 현재 사람보다 뒤에 서야하는 사람의 리스트
        			ArrayList<Integer> temp = edges.get(i);
        			// 현재 사람이 앞으로 나와야 설 수 있는 사람들의 앞에 나와야하는 사람의 수 감소
        			for(int j = 0; j < temp.size(); j++) {
        				in[temp.get(j)]--;
        			}
        			// 줄을 섰음을 체크
        			check[i] = true;
        			// 줄 선 사람 한 명 증가
        			cnt--;
        			// 처음부터 다시 탐색
        			break;
        		}
        	}
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
        
    }

}