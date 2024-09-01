import java.util.*;
import java.io.*;
    
public class Main {
        
    // BOJ 17471 - 게리맨더링
    public static int N, ans;
    public static int[] p_cnt;
    public static boolean[] visited;
    public static ArrayList<int[]> graph;
    
    // 그래프 연결 확인
    public static boolean bfs(ArrayList<Integer> city) {
    	//해당 도시 방문 확인 배열
    	boolean[] visit = new boolean[N + 1];
    	// BFS로 도시 연결 탐색
    	Deque<Integer> dq = new ArrayDeque<>();
    	// 첫번째 도시부터 시작해서 모든 도시가 연결되어있는지 체크
    	dq.offer(city.get(0));
    	// 첫 번째 도시 방문 처리
    	visit[city.get(0)] = true;
    	// bfs 탐색 시작
    	while(!dq.isEmpty()) {
    		// 현재 도시 정보 가져오기
    		int[] cur = graph.get(dq.poll());
    		// 현재 도시와 연결 된 도시들 탐색
    		for(int i = 0; i < cur.length; i++) {
    			// 이미 방문한 도시이거나, 해당 도시가 현재 그룹에 포함되지 않는다면 스킵 
    			if(visit[cur[i]] || !city.contains(cur[i])) continue;
    			// 방문 처리 및 해당 도시와 연결된 도시 탐색
    			visit[cur[i]] = true;
    			dq.offer(cur[i]);
    		}
    	}
    	// 모든 도시 연결 되었는지 체크
    	for(int i = 0; i < city.size(); i++) {
    		// 방문 안된 도시가 있다면 false return
    		if(!visit[city.get(i)]) return false;
    	}
    	// 모든 도시를 다 연결했다면 return true
    	return true;
    }
    
    // 부분 집합 구하기
    public static void powerset(int cnt) {
    	// 부분 집합 생성 (도시 나누기)를 완료했다면
    	if(cnt == N) {
    		// 두 그룹을 리스트에 담기
    		ArrayList<Integer> city1 = new ArrayList<>();
    		ArrayList<Integer> city2 = new ArrayList<>();
    		
    		// true와 false를 기준으로 두 도시로 나눠서 담음
    		for(int i = 0; i < N; i++) {
    			if(visited[i]) city1.add(i + 1);
    			else city2.add(i + 1);
    		}
    		
    		// 한 쪽으로 몰린 케이스는 제외
    		if(city1.size() == 0 || city2.size() == 0) return;
    		
    		// 두 그룹이 모두 연결된 도시라면
    		if(bfs(city1) && bfs(city2)) {
    			// 각 그룹의 인구 수 합 구하기
    			int sum1 = 0, sum2 = 0;
    			for(int i = 0; i < city1.size(); i++) {
    				sum1 += p_cnt[city1.get(i) - 1];
    			}
    			for(int i = 0; i < city2.size(); i++) {
    				sum2 += p_cnt[city2.get(i) - 1];
    			}
    			// 두 도시간의 차이 구하기
    			int sub = Math.abs(sum1 - sum2);
    			// 더 작은 값으로 갱신
    			ans = ans < sub ? ans : sub; 
    		}
    		return;
    	}
    	
    	//아직 두 그룹으로 나뉘지 않았다면, 추가 탐색
    	
    	// 해당 도시를 현재 그룹에 포함시키고 탐색
    	visited[cnt] = true;
    	powerset(cnt + 1);
    	// 해당 도시를 현재 그룹에 포함시키지 않고 탐색
    	visited[cnt] = false;
    	powerset(cnt + 1);
    }
      
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        // 각 지역별 인원수 입력
        p_cnt = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	p_cnt[i] = Integer.parseInt(st.nextToken());
        }
        // 그래프 연결 간선 입력
        graph = new ArrayList<>();
        // 1번 인덱스부터 쓰기 위해 0번에 빈 배열 삽입
        graph.add(new int[] {});
        // N번 동안 연결된 간선을 담은 정수 배열 삽입
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int M = Integer.parseInt(st.nextToken());
        	int[] linked = new int[M];
        	for(int j = 0; j < M; j++) {
        		linked[j] = Integer.parseInt(st.nextToken());
        	}
        	graph.add(linked);
        }
        
        // 부분집합 전체 구하고, 연결된 그래프라면 값 비교하기
        visited = new boolean[N];
        ans = Integer.MAX_VALUE;
        // 부분집합 구하기
        powerset(0);
        // 성립되는 길이 없어서 ans가 갱신되지 않았다면 -1 저장
        if(ans == Integer.MAX_VALUE) ans = -1;
        // 정답 저장
        sb.append(ans);
   
        // 결과 출력
        System.out.println(sb.toString());
    }
    
}