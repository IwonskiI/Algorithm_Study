import java.io.*;
import java.util.*;

public class Solution {
	
    // SWEA 5653 - [모의 SW 역량테스트] 줄기세포배양
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] board = new int[N+2*K][M+2*K];
            int answer = 0;
            
            // 세포 정보를 저장할 리스트
            // cell = [x좌표, y좌표, 에너지, 경과시간, 상태];
            List<int[]> cellList = new ArrayList<>();

            // Initialize cells and cellMap
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                	int energy = Integer.parseInt(st.nextToken());
                    // 0인 칸은 스킵
                    if (energy != 0) {
                    	board[i+K][j+K] = energy;
                        cellList.add(new int[]{i+K, j+K, energy, 0, 0});
                    }
                }
            }

            // 4방향 탐색을 위한 델타값
            int[] dr = {1, -1, 0, 0};
            int[] dc = {0, 0, 1, -1};

            // 시간 진행
            for (int k = 1; k <= K; k++) {
            	// 새로 추가되는 세포들을 저장할 리스트
//            	// 4방향 탐색을 하며 바로 기존 리스트에 넣어주면 추가되자마자 경과시간이 1 증가하므로 별도로 관리
//            	List<int[]> newCellList = new ArrayList<>();
            	// 같은 칸에 번식이 진행될 경우 에너지가 더 높은 세포가 번식하도록 우선순위 큐로 관리
            	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[2]-o1[2]);
            	
            	// 활성화된 세포를 우선순위 큐에 삽입
            	for(int[] cell : cellList) {
            		if(cell[4] == 1) {
            			pq.offer(new int[] {cell[0], cell[1], cell[2], cell[3], cell[4]});
            		}
            	}
            	
            	// 번식 진행 후 기존 셀 리스트의 경과시간 증가 및 활성화/죽음 관리
            	for(int idx = 0; idx < cellList.size(); idx++) {
            		// 경과시간 1 증가
            		cellList.get(idx)[3]++;
            		// 경과시간이 증가한 뒤, 에너지와 경과시간이 같다면 활성화 상태로 전환
            		if(cellList.get(idx)[3] == cellList.get(idx)[2]) cellList.get(idx)[4] = 1;
            		// 경과시간이 증가한 뒤, 경과시간이 에너지의 2배(활성화 된 후 한번 더 에너지만큼 지남)가 된다면 죽음
            		else if(cellList.get(idx)[3] == cellList.get(idx)[2]*2) {
            			// 죽은 세포는 리스트에서 삭제
            			cellList.remove(idx);
            			// 삭제한 뒤 인덱스를 한칸 앞으로 당겨옴
            			idx--;
            		}
            	}
            	
            	// 우선순위 큐가 빌 때 까지 4방향 탐색을 통해 번식 진행
            	while(!pq.isEmpty()) {
            		int[] cell = pq.poll();
            		for(int d = 0; d < 4; d++) {
            			int nr = cell[0] + dr[d], nc = cell[1] + dc[d];
            			// 만약 이미 해당 칸에 세포가 존재한다면 스킵
            			if(board[nr][nc] != 0) continue;
            			// 번식 진행(전 에너지 그대로 번식)
            			board[nr][nc] = cell[2];
            			// 번식한 세포는 새로운 리스트에 추가 (새 x,y 좌표, 이전 세포의 에너지, 경과시간은 0, 상태는 비활성화로 추가)
            			cellList.add(new int[] {nr, nc, cell[2], 0, 0});
            		}
            	}
            	
            	
//            	// 새로 번식한 세포 추가
//            	for(int[] cell : newCellList) {
//            		cellList.add(new int[] {cell[0], cell[1], cell[2], cell[3], cell[4]});
//            	}
            }

            sb.append("#").append(t).append(" ").append(cellList.size()).append("\n");
        }

        System.out.println(sb.toString());
    }
}
