import java.io.*;
import java.util.*;
 
public class Main {
 
    // BOJ 9328 - 열쇠
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // 테스트 케이스 수
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 0; tc < T; tc++) {
        	int ans = 0;
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	
        	// 평면도 사이즈
        	char[][] board = new char[N][M];
        	// 출입구의 좌표 리스트
        	List<int[]> exit = new ArrayList<>();
        	
        	
        	// 열쇠가 없어서 못 여는 벽 저장
        	Deque<int[]> next = new ArrayDeque<>();
        	// 가지고 있는 키 
        	int keys = 0;
        	
        	// 초기 입력
        	for(int r = 0; r < N; r++) {
        		String[] str = br.readLine().split("");
        		for(int c = 0; c < M; c++) {
        			board[r][c] = str[c].charAt(0);
        			// 테두리 중에서
        			if((r == 0 || r == N-1 || c == 0 || c == M-1) && board[r][c] != '*') {
        				// 빈칸이라면 출입구 추가
        				if(board[r][c] == '.') exit.add(new int[] {r, c});
        				// 열쇠가 있다면 열쇠 목록에 추가 (열쇠가 있는 칸도 빈칸이므로 출입구로 추가)
        				else if('a' <= board[r][c] && board[r][c] <= 'z') {
        					exit.add(new int[] {r, c});
        					keys |= (1 << (board[r][c] - 'a'));
        				}
        				// 문서라면 바로 추가 (여기도 출입구가 될 수 있으므로 추가)
        				else if(board[r][c] == '$') {
        					exit.add(new int[] {r, c});
        					// 중복 카운팅을 제거하기 위해 얻은 문서는 길로 변환
        					board[r][c] = '.';
        					ans++;
        				}
        			}
        		}
        	}
        	
        	// 초기 열쇠 입력
        	String[] keyInit = br.readLine().split("");
        	if(keyInit[0].charAt(0) != '0') {
        		for(int i = 0; i < keyInit.length; i++) {
        			int idx = keyInit[i].charAt(0) - 'a';
        			keys |= (1 << idx);
        		}
        	}
        	
        	// 방문 배열 초기화
        	List<List<HashSet<Integer>>> visited = new ArrayList<>();
        	for(int r = 0; r < N; r++) {
        		visited.add(new ArrayList<>());
        		for(int c = 0; c < M; c++) {
        			visited.get(r).add(new HashSet<>());
        		}
        	}
        	
        	// 빈칸 출입구 추가
        	Deque<int[]> dq = new ArrayDeque<>();
        	for(int i = 0; i < exit.size(); i++) {
        		int[] cur = exit.get(i);
        		dq.offer(new int[] {cur[0], cur[1], keys});
        		visited.get(cur[0]).get(cur[1]).add(keys);
        	}
        	

        	
        	// 현재 키로 출입구를 만들 수 있는 지 확인
    		for(int r = 0; r < N; r++) {
    			for(int c = 0; c < M; c++) {
    				// 테두리 중에서 벽일 때,
    				if((r == 0 || r == N-1 || c == 0 || c == M-1) && ('A' <= board[r][c] && board[r][c] <= 'Z')) {
    					// 현재 벽의 글자
						int curWall = board[r][c] - 'A';
						// 현재 키로 문을 열 수 있는지 확인 후 가능하다면,
						if((keys & (1 << curWall)) == (1 << curWall)) {
							// 탐색 queue에 추가
							dq.offer(new int[] {r, c, keys});
							// 방문 처리
        					visited.get(r).get(c).add(keys);
						}
						// 불가능하다면 탐색 후 키를 얻어서 열 수 있는지 다시 확인 하기 위해 next에 저장
						else {
							next.offer(new int[] {r, c, curWall});
						}
    				}
    			}
    		}
        	
        	// 끝까지 탐색했을 때 얻을 수 있는 최대 키 저장
        	int maxKey = keys;
        	// 이전 차례에 끝까지 탐색했을 때 얻은 최대 키
        	int prevMaxKey = -1;
        	// 이전 키와 현재키가 같다면 탐색 종료
        	while(maxKey != prevMaxKey) {
        		// 이전키에 현재 키 저장
        		prevMaxKey = maxKey;     
        		// BFS 탐색
        		while(!dq.isEmpty()) {
            		int[] cur = dq.poll();
            		int r = cur[0], c = cur[1], key = cur[2];
            		
            		for(int dd = 0; dd < 4; dd++) {
            			int nr = r + d[dd][0], nc = c + d[dd][1];
            			boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
            			// 범위 안이고, 현재 키를 들고 아직 방문하지 않았고, 다음 위치가 벽이 아니라면,
            			if(in_range && !visited.get(nr).get(nc).contains(key) && board[nr][nc] != '*') {
            				// 빈칸 도착
            				if(board[nr][nc] == '.') {
            					// 바로 이동
            					dq.offer(new int[] {nr, nc, key});
            					visited.get(nr).get(nc).add(key);
            				}
            				// 열쇠 도착
            				else if('a' <= board[nr][nc] && board[nr][nc] <= 'z') {
            					// 열쇠 추가
            					key |= (1 << (board[nr][nc] - 'a'));
            					// maxKey 갱신
            					maxKey |= key;
            					// 새로운 키로 이동
            					dq.offer(new int[] {nr, nc, key});
            					visited.get(nr).get(nc).add(key);
            				}
            				// 문 도착
            				else if('A' <= board[nr][nc] && board[nr][nc] <= 'Z') {
            					// 문 인덱스 계산
            					int wall = board[nr][nc] - 'A';
            					// 현재 키로 문을 열 수 있다면
            					if((key & (1 << wall)) == (1 << wall)) {
            						// 문 열고 이동
            						dq.offer(new int[] {nr, nc, key});
            						visited.get(nr).get(nc).add(key);
            					}
            					// 안된다면
            					else {
            						// 탐색 종료 후 추가로 얻은 키로 열 수 있는지 체크하기 위해 next에 저장
            						next.offer(new int[] {nr, nc, wall});
            					}
            				}
            				// 문서 도착
            				else if(board[nr][nc] == '$') {
            					// 문서 수 증가
            					ans++;
            					// 중복 체크를 피하기 위해 .으로 변경
            					board[nr][nc] = '.';
            					// 이동
            					dq.offer(new int[] {nr, nc, key});
        						visited.get(nr).get(nc).add(key);
            				}
            			}
            		}
            	}
            	
        		// 탐색 종료 후 가지고 있는 키로 막혔던 벽을 열 수 있는지 확인
            	Deque<int[]> temp = new ArrayDeque<>();
            	while(!next.isEmpty()) {
            		int[] cur = next.poll();
            		// 이번 탐색 때 얻은 키로 해당 문을 열 수 있으면
            		if((maxKey & (1 << cur[2])) == (1 << cur[2])) {
            			// dq에 다시 추가 후 재 탐색
            			dq.offer(new int[] {cur[0], cur[1], maxKey});
            			visited.get(cur[0]).get(cur[1]).add(maxKey);
            		}
            		// 아니라면 temp에 저장
            		else {
            			temp.offer(cur);
            		}
            	}
            	// next는 temp로 갱신 후 반복
            	next = temp;
        	}
        	
        	// 더이상 새로운 키를 얻지 못하고 모든 길을 탐색 완료 했을 때 얻은 문서의 수 저장
        	sb.append(ans).append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
     
}