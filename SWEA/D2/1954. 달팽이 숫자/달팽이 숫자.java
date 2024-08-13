import java.io.*;
 
class Solution
{
        // SWEA - 달팽이 숫자
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());
         
        // 테스트 케이스 반복
        for(int tc = 1; tc <= T; tc++) {
            // N: 배열의 크기, dir_cnt: 방향 인덱스
            int N = Integer.parseInt(br.readLine()), dir_cnt = 0;
            // 방향 전환 dx
            int[][] num_lst = new int[N][N], dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            // 현재 좌표 설정
            int[] pos = new int[] {0, 0};   
             
            // 1부터 N제곱 수까지 순서대로 입력
            for(int i = 1; i <= (N*N); i++) {
                // 현재 위치에 현재 i값 입력
                num_lst[pos[0]][pos[1]] = i;
                // nr, nc: 현재 진행 방향대로 움직인 새로운 좌표
                int nr = pos[0] + dir[dir_cnt][0], nc = pos[1] + dir[dir_cnt][1];
                // in_range: 새로 이동한 좌표가 배열의 범위 내인지 확인
                boolean in_range = (0 <= nr && nr < N) && (0 <= nc && nc < N);
                // 범위에서 벗어나거나, 이미 채워진 칸이라면,
                if(!in_range || num_lst[nr][nc] != 0) {
                    // 방향 전환
                    dir_cnt = (dir_cnt + 1) % 4;
                    // 새 좌표 설정
                    nr = pos[0] + dir[dir_cnt][0];
                    nc = pos[1] + dir[dir_cnt][1];
                }
                // 새 좌표를 현재 위치로 업데이트
                pos = new int[] {nr, nc};
            }
             
            // 테스트케이스 넘버 입력
            sb.append("#").append(tc).append("\n");
            // 반복문으로 저장되어있는 배열을 출력
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    sb.append(num_lst[r][c]).append(" ");
                }
                sb.append("\n");
            }
        }
         
        System.out.println(sb.toString());
    }
}