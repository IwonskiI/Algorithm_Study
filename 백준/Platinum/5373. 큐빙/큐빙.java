import java.io.*;
import java.util.*;

public class Main {

    // BOJ 5373 - 큐빙
	
	// 한 면 회전 함수
    public static char[][] rotate(char[][] arr, char dir){
        char[][] rotate = new char[3][3];
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
            	// 시계 방향
                if(dir == '+') rotate[r][c] = arr[2-c][r];
                // 반시계 방향
                else if(dir == '-') rotate[r][c] = arr[c][2-r];
            }
        }
        // 회전 배열 반환
        return rotate;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());
        // 각 면과 연결된 모서리 좌표 저장
        int[][][][] sides = new int[][][][] {
        	{{{1, 2, 0}, {1, 2, 1}, {1, 2, 2}}, {{2, 0, 0}, {2, 1, 0}, {2, 2, 0}}, {{4, 0, 2}, {4, 0, 1}, {4, 0, 0}}, {{3, 2, 2}, {3, 1, 2}, {3, 0, 2}}},
        	{{{0, 0, 2}, {0, 0, 1}, {0, 0, 0}}, {{3, 0, 2}, {3, 0, 1}, {3, 0, 0}}, {{5, 2, 0}, {5, 2, 1}, {5, 2 ,2}}, {{2, 0, 2}, {2, 0, 1}, {2, 0, 0}}},
        	{{{0, 2, 2}, {0, 1, 2}, {0, 0, 2}}, {{1, 2, 2}, {1, 1, 2}, {1, 0, 2}}, {{5, 2, 2}, {5, 1, 2}, {5, 0 ,2}}, {{4, 2, 2}, {4, 1, 2}, {4, 0, 2}}},
        	{{{1, 0, 0}, {1, 1, 0}, {1, 2, 0}}, {{0, 0, 0}, {0, 1, 0}, {0, 2, 0}}, {{4, 0, 0}, {4, 1, 0}, {4, 2, 0}}, {{5, 0, 0}, {5, 1, 0}, {5, 2 ,0}}},
        	{{{0, 2, 0}, {0, 2, 1}, {0, 2, 2}}, {{2, 2, 0}, {2, 2, 1}, {2, 2, 2}}, {{5, 0, 2}, {5, 0, 1}, {5, 0 ,0}}, {{3, 2, 0}, {3, 2, 1}, {3, 2, 2}}},
        	{{{4, 2, 0}, {4, 2, 1}, {4, 2, 2}}, {{2, 2, 2}, {2, 1, 2}, {2, 0, 2}}, {{1, 0, 2}, {1, 0, 1}, {1, 0, 0}}, {{3, 0, 0}, {3, 1, 0}, {3, 2, 0}}}
        };
        
        // 각 회전 면을 정수로 매핑
        HashMap<Character, Integer> s_map = new HashMap<>();
        s_map.put('U', 0);
        s_map.put('B', 1);
        s_map.put('R', 2);
        s_map.put('L', 3);
        s_map.put('F', 4);
        s_map.put('D', 5);
        
        for(int tc = 0; tc < T; tc++) {
        	// 큐브 전개도
        	//   1
            // 3 0 2
            //   4
            //   5
            
        	// 큐브 색 배치
            char[][][] cube = new char[][][] {
    	            {{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}}, // 윗면 0 [1][2][0-2], [2][0-2][0], [4][0][0-2], [3][0-2][2] 
    	            {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}}, // 뒷면 1 [0][0][0-2], [2][0][0-2], [5][2][0-2], [3][0][0-2]
    	            {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}}, // 우면 2 [0][0-2][2], [1][0-2][2], [5][0-2][2], [4][0-2][2]
    	            {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}}, // 좌면 3 [0][0-2][0], [1][0-2][0]. [5][0-2][0], [4][0-2][0]
    	            {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}}, // 앞면 4 [0][2][0-2], [2][2][0-2], [5][0][0-2], [3][2][0-2]
    	            {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}}  // 밑면 5 [4][2][0-2], [2][0-2][2], [1][0][0-2], [3][0-2][0]
                };
            // 회전 횟수
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                String cur = st.nextToken();
                // 회전 면
                int side = s_map.get(cur.charAt(0));
                // 방향
                char dir = cur.charAt(1);
                
                // 한면 먼저 회전해서 저장
                cube[side] = rotate(cube[side], dir);
                
                // 옆면 모서리 처리
                int[][][] temps = sides[side];
                char[] temp;
                
                switch(dir) {
                // 시계 방향
                case '+':
                	temp = new char[]{cube[temps[3][0][0]][temps[3][0][1]][temps[3][0][2]], cube[temps[3][1][0]][temps[3][1][1]][temps[3][1][2]], cube[temps[3][2][0]][temps[3][2][1]][temps[3][2][2]]};
                	for(int dd = 2; dd >= 0; dd--) {
                		for(int dr = 0; dr < 3; dr++) {
                			cube[temps[dd+1][dr][0]][temps[dd+1][dr][1]][temps[dd+1][dr][2]] = cube[temps[dd][dr][0]][temps[dd][dr][1]][temps[dd][dr][2]];
                		}
                	}
                	for(int dr = 0; dr < 3; dr++) {
            			cube[temps[0][dr][0]][temps[0][dr][1]][temps[0][dr][2]] = temp[dr];
            		}
                	break;
                // 반시계 방향
                case '-':
                	temp = new char[]{cube[temps[0][0][0]][temps[0][0][1]][temps[0][0][2]], cube[temps[0][1][0]][temps[0][1][1]][temps[0][1][2]], cube[temps[0][2][0]][temps[0][2][1]][temps[0][2][2]]};
                	for(int dd = 1; dd <= 3; dd++) {
                		for(int dr = 0; dr < 3; dr++) {
                			cube[temps[dd-1][dr][0]][temps[dd-1][dr][1]][temps[dd-1][dr][2]] = cube[temps[dd][dr][0]][temps[dd][dr][1]][temps[dd][dr][2]];
                		}
                	}
                	for(int dr = 0; dr < 3; dr++) {
            			cube[temps[3][dr][0]][temps[3][dr][1]][temps[3][dr][2]] = temp[dr];
            		}
                	break;
                }
                
            }
            // 회전 종료 후 윗면 저장
            for(int r = 0; r < 3; r++) {
            	for(int c = 0; c < 3; c++) {
            		sb.append(cube[0][r][c]);
            	}
            	sb.append("\n");
            }
        }
        
        // 최종 정답 출력
        System.out.println(sb.toString());
        
    }

}