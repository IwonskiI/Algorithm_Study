import java.util.*;
import java.io.*;

public class Main{
    
    
    public static int N, M;
    public static int[][] board;
    public static int[][] island;
    public static boolean[][] visited;
    public static int[][] d = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};    
    public static int[] parents;
    public static Queue<int[]> q;
    public static PriorityQueue<Node> pq = new PriorityQueue<>(); ;
    
    static class Node implements Comparable<Node>{
        int to;
        int from;
        int value;
        
        public Node(int to, int from, int value) {
            this.to = to;
            this.from = from;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
        
    }
    
    static void makeBridge(int r, int c, int idx, int dir) {
        q = new LinkedList<>();    
        q.add(new int[] {r, c, 1});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int nr = cur[0] + d[dir][0], nc = cur[1] + d[dir][1], lv = cur[2];
            boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M); 
            if(in_range) {
                if(island[nr][nc] == 0) {
                    q.add(new int[] {nr, nc, lv+1});
                }else if(lv > 1) {        
                    pq.add(new Node(idx, island[nr][nc], lv));
                    return;
                }
            }
        }
    }

    static int shortestPath(int islandCnt) {
        int sum =0;
        int size = pq.size();
        for(int i=0; i< size; i++) {
            Node node = pq.poll();
            int x = node.from;
            int y = node.to;
            
            if(find(x) != find(y)) {
                sum += node.value;
                union(x,y);
            }
        }
        
        int rx = parents[1];
        for(int i=2; i<islandCnt; i++) {
            if(rx != find(parents[i])) {
                return 0;
            }
        }
        
        return sum;
    }
    
    static int find(int x) {
        if(parents[x] == x) return x;
        int rx = find(parents[x]);
        return rx;
        
    }
    
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if(x<y) {
            parents[y]=x;
        }
        else {
            parents[x] =y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 지도 입력
        board = new int[N][M];
        // 구분된 섬 입력
        island = new int[N][M];
        // 방문 처리
        visited = new boolean[N][M];
        // 초기값 입력
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 인덱스
        int islandNum = 1;
        // bfs 탐색
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(board[r][c] == 1 && !visited[r][c]) {
                    q = new LinkedList<>();
                    q.offer(new int[] {r, c});
                    visited[r][c] = true;
                    island[r][c] = islandNum;
                    while(!q.isEmpty()) {
                        int[] cur = q.poll();
                        int rr = cur[0], cc = cur[1];
                        for(int dd = 0; dd < 4; dd++) {
                            int nr = rr + d[dd][0], nc = cc + d[dd][1];
                            boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
                            if(in_range && board[nr][nc] == 1 && !visited[nr][nc]) {
                                q.offer(new int[] {nr, nc});
                                visited[nr][nc] = true;
                                island[nr][nc] = islandNum;
                            }
                        }
                    }
                    islandNum++;
                }
            }
        }
        
        // 다리 만들기
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(island[r][c]!=0) {
                    for(int dd = 0; dd < 4; dd++) {
                        int nr = r + d[dd][0], nc = c + d[dd][1];
                        boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < M);
                        if(in_range && island[nr][nc] == 0)
                            makeBridge(nr, nc, island[r][c], dd);
                    }
                }
            }
        }
        parents = new int[islandNum];
        for(int i = 1; i < islandNum; i++) {
            parents[i] = i;
        } 
        int answer = shortestPath(islandNum);
        System.out.println(answer == 0 ? -1 : answer);
    }

}