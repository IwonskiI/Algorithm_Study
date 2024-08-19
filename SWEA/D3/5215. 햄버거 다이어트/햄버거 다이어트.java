import java.util.*;
import java.io.*;

public class Solution {
	
	// SWEA 5215 - 햄버거 다이어트(Combination - DFS)
	public static int N, L, ans;
	public static int[][] lst;
	
	public static void calc(int cnt, int score, int total) {
		// 제한 칼로리 초과면 그냥 return
		if(total > L) return;
		// 재료를 다 골랐다면 만족도 계산
		if(cnt == N) {
			ans = Math.max(ans, score);
			return;
		}
		// 현재 재료 선택 함
		calc(cnt + 1, score + lst[cnt][0], total + lst[cnt][1]);
		// 현재 재료 선택 안함
		calc(cnt + 1, score, total);
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
			calc(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
//    // SWEA 5215 - 햄버거 다이어트(DP)
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st;
//        // 테스트 케이스 수 입력
//        int T = Integer.parseInt(br.readLine());
//        for(int tc = 1; tc <= T; tc++) {
//            st = new StringTokenizer(br.readLine());
//            // N: 재료의 수 , L: 제한 칼로리
//            int N = Integer.parseInt(st.nextToken());
//            int L = Integer.parseInt(st.nextToken());
//            // 재료의 갯수와 제한칼로리에 따른 최적의 값구하기 위한 dp 배열
//            int [][] dp = new int[N+1][L+1];
//            // 각 재료의 만족도(value)와 칼로리(weight) 저장
//            int [] value = new int[N+1], weight = new int[N+1];
//            // dp 배열을 활용하기 위해 0번째 줄은 비워놓고 시작
//            // 만족도와 칼로리 입력
//            for(int i = 1; i <= N; i++) {
//                st = new StringTokenizer(br.readLine());
//                value[i] = Integer.parseInt(st.nextToken());
//                weight[i] = Integer.parseInt(st.nextToken());
//            }
//             
//            // dp 점화식 계산
//            // 각 아이템별 제한 칼로리를 점점 증가하며 만족도가 최대로 되는 시점을 구함
//            for(int i = 1; i <= N; i++) {
//                for(int j = 1; j <= L; j++) {
//                    // i가 1일 때는 이전의 값이 없으므로 0번째 줄 (0)을 읽어와서 저장
//                    // 이외에는 이전에 담아둔 최적의 값을 저장
//                    dp[i][j] = dp[i-1][j];
//                    // 현재 제한 칼로리가 현재 재료보다 같거나 높으면
//                    if(j - weight[i] >= 0) {
//                        // 기존에 저장했던 이전의 값과, 
//                        // 현재 제한 칼로리에서 현재 재료의 칼로리를 뺀 제한 칼로리의 최대 만족도와 현재 재료의 만족도를 더한값을 비교
//                        dp[i][j] = Math.max(dp[i-1][j],  dp[i-1][j-weight[i]]+value[i]);
//                    }
//                }
//            }
//             
//            // 최종적으로 가장 오른쪽 아래 칸(dp[N][L]에 최대 만족도가 계산됨.
//            sb.append("#").append(tc).append(" ").append(dp[N][L]).append("\n");
//        }
//         
//        // 저장된 결과값 출력
//        System.out.println(sb.toString());
//    }
     
	
//	// SWEA 5215 - 햄버거 다이어트(Next Permutation)
//	public static int N, L, ans;
//	public static int[] visited;
//	public static int[][] lst;
//	
//    // 다음 순열을 생성하는 메소드
//    public static boolean nextPermutation(int[] array) {
//        int i = array.length - 1;
//        while (i > 0 && array[i - 1] >= array[i]) {
//            i--;
//        }
//        
//        if (i <= 0) {
//            return false;
//        }
//        
//        int j = array.length - 1;
//        while (array[j] <= array[i - 1]) {
//            j--;
//        }
//        
//        int temp = array[i - 1];
//        array[i - 1] = array[j];
//        array[j] = temp;
//        
//        j = array.length - 1;
//        while (i < j) {
//            temp = array[i];
//            array[i] = array[j];
//            array[j] = temp;
//            i++;
//            j--;
//        }
//        
//        return true;
//    }
//    
//
//    // 부분집합을 생성하는 메소드
//    public static void generateSubsets() {
//        int n = lst.length;
//        for(int j = 0; j <= n; j++) {
//            int[] binaryArray = new int[n];
//            Arrays.fill(binaryArray, 0);  // 초기 상태는 공집합
//            // 모든 부분집합을 만들기 위해 선택된 원소의 갯수 조절
//            for(int k = 0; k < j; k++) {
//            	binaryArray[binaryArray.length - 1 - k] = 1;
//            }
//            
//            do {
//                // 현재 binaryArray에 대응하는 부분집합을 출력
//            	int score = 0, total = 0;
//                for (int i = 0; i < n; i++) {
//                	// 선택된 조합이라면
//                    if (binaryArray[i] == 1) {
//                    	// 만족도와 칼로리를 더함
//                        score += lst[i][0];
//                        total += lst[i][1];
//                    }
//                    // 칼로리가 제한 칼로리보다 커지면 중단
//                    if(total > L) break;
//                }
//                // 칼로리가 제한 칼로리보다 낮고, 만족도가 더 높다면 갱신
//                if(total <= L && score > ans) ans = score;
//            } while (nextPermutation(binaryArray));  // 다음 순열 생성
//        }
//    }
//	
//	public static void main(String args[]) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		// 테스트케이스 수 입력
//		int T = Integer.parseInt(br.readLine());
//		for(int tc = 1; tc <= T; tc++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			N = Integer.parseInt(st.nextToken());
//			L = Integer.parseInt(st.nextToken());
//			
//			lst = new int[N][2];
//			for(int i = 0; i< N; i++) {
//				st = new StringTokenizer(br.readLine());
//				lst[i][0] = Integer.parseInt(st.nextToken());
//				lst[i][1] = Integer.parseInt(st.nextToken());
//			}
//			ans = 0;
//			generateSubsets();
//			sb.append("#").append(tc).append(" ").append(ans).append("\n");
//		}
//		
//		System.out.println(sb.toString());
//		
//	}
}