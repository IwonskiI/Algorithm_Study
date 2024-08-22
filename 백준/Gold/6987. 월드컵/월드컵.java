import java.util.*;
import java.io.*;

public class Main {
    // BOJ 6987 - 월드컵

	// 각 팀별 매치 라인업
    static int[][] matches = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}};
    static int[][] result;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        // 4번의 케이스 검증
        for (int i = 0; i < 4; i++) {
        	// 각 경기의 결과를 저장할 배열
        	result = new int[6][3];
        	// 전체 경기수의 합 계산
        	int total = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    result[j][k] = Integer.parseInt(st.nextToken());
                    total += result[j][k];
                }
            }
            // 전체 경기수가 30경기고, 각 조합별 경기 결과가 일치한다면, 1
            if (total == 30 && backtrack(0)) sb.append(1 + " ");
            // 아니면 0
            else sb.append(0 + " ");
        }
        System.out.println(sb.toString().trim());
    }
 
    private static boolean backtrack(int count) {
    	// count는 0부터 15까지 증가 (15번의 경기)
    	// matches[count]는 각 경기의 결과 (0번에 첫번째 팀, 1번에 두번째 팀 저장)
    	// count가 15가 되었다면 15번의 경기를 모두 비교하고 올바른 경기 매칭이 되었다는 의미로 true 반환
    	if (count == 15) return true;
    	
        
    	// 3개의 if문은 각 경기의 결과 중 하나로 계산
        // 승-패 비교 후 해당 계산이 맞으면 true 반환, 아니면 다시 해당 경기를 복구
        if (result[matches[count][0]][0] > 0 && result[matches[count][1]][2] > 0) {
            result[matches[count][0]][0]--;
            result[matches[count][1]][2]--;
            if (backtrack(count + 1)) return true;
            result[matches[count][0]][0]++;
            result[matches[count][1]][2]++;
        }
 
        // 무-무 비교 후 해당 계산이 맞으면 true 반환, 아니면 다시 해당 경기를 복구
        if (result[matches[count][0]][1] > 0 && result[matches[count][1]][1] > 0) {
            result[matches[count][0]][1]--;
            result[matches[count][1]][1]--;
            if (backtrack(count + 1)) return true;
            result[matches[count][1]][1]++;
            result[matches[count][0]][1]++;
        }
 
        // 패-승 비교 후 해당 계산이 맞으면 true 반환, 아니면 다시 해당 경기를 복구
        if (result[matches[count][0]][2] > 0 && result[matches[count][1]][0] > 0) {
            result[matches[count][0]][2]--;
            result[matches[count][1]][0]--;
            if (backtrack(count + 1)) return true;
            result[matches[count][1]][0]++;
            result[matches[count][0]][2]++;
        }
        
        // 모든 경우의 수를 계산 후 잘못되었다면 false 반환
        return false;
    }
}