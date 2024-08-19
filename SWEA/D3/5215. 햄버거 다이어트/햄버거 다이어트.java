import java.util.*;
import java.io.*;

public class Solution {
	
	// SWEA 5215 - 햄버거 다이어트(Next )
	public static int N, L, ans;
	public static int[] visited;
	public static int[][] lst;
	
    // 다음 순열을 생성하는 메소드
    public static boolean nextPermutation(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }
        
        if (i <= 0) {
            return false;
        }
        
        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }
        
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;
        
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        
        return true;
    }
    

    // 부분집합을 생성하는 메소드
    public static void generateSubsets() {
        int n = lst.length;
        for(int j = 0; j <= n; j++) {
            int[] binaryArray = new int[n];
            Arrays.fill(binaryArray, 0);  // 초기 상태는 공집합
            // 모든 부분집합을 만들기 위해 선택된 원소의 갯수 조절
            for(int k = 0; k < j; k++) {
            	binaryArray[binaryArray.length - 1 - k] = 1;
            }
            
            do {
                // 현재 binaryArray에 대응하는 부분집합을 출력
            	int score = 0, total = 0;
                for (int i = 0; i < n; i++) {
                	// 선택된 조합이라면
                    if (binaryArray[i] == 1) {
                    	// 만족도와 칼로리를 더함
                        score += lst[i][0];
                        total += lst[i][1];
                    }
                    // 칼로리가 제한 칼로리보다 커지면 중단
                    if(total > L) break;
                }
                // 칼로리가 제한 칼로리보다 낮고, 만족도가 더 높다면 갱신
                if(total <= L && score > ans) ans = score;
            } while (nextPermutation(binaryArray));  // 다음 순열 생성
        }
    }
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			lst = new int[N][2];
			for(int i = 0; i< N; i++) {
				st = new StringTokenizer(br.readLine());
				lst[i][0] = Integer.parseInt(st.nextToken());
				lst[i][1] = Integer.parseInt(st.nextToken());
			}
			ans = 0;
			generateSubsets();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}