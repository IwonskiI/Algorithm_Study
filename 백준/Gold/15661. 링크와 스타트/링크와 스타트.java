import java.io.*;
import java.util.*;

public class Main {

    // BOJ 15661 - 링크와 스타트
    public static int N, ans;
    public static int[][] board;
    public static List<Integer> team1, team2;

    // 모든 조합 구하는 함수    
    public static void combi(int cnt) {
    	// 모든 선수의 팀 배치가 끝났다면
    	if(cnt == N) {
    		// 한 팀이라도 선수가 0명인 경우가 있다면 계산 X 후 종료
    		if(team1.size() == 0 || team2.size() == 0) return;
    		// 각 팀의 점수
    		int score1 = 0, score2 = 0;
    		// 첫번째 팀 점수 계산
    		for(int i = 0; i < team1.size(); i++) {
    			for(int j = i+1; j < team1.size(); j++) {
    				// 두 사람의 시너지 점수의 합만큼 증가
    				score1 += (board[team1.get(i)][team1.get(j)] + board[team1.get(j)][team1.get(i)]);
    			}
    		}
    		// 2번 팀도 동일하게 계산
    		for(int i = 0; i < team2.size(); i++) {
    			for(int j = i+1; j < team2.size(); j++) {
    				score2 += (board[team2.get(i)][team2.get(j)] + board[team2.get(j)][team2.get(i)]);
    			}
    		}
    		
    		// 차이의 최솟값 갱신 후 종료
    		ans = Math.min(ans, Math.abs(score1 -score2));
    		return;
    	}
    	
		//현재 선수를 1번팀에 배치
		team1.add(cnt);
		// 다음 조합 계산
		combi(cnt+1);
		// 1번팀에서 제거
		team1.remove(team1.size()-1);
		// 2번 팀에 배치
		team2.add(cnt);
		// 다음 조합계산
		combi(cnt+1);
		// 2번팀에서 제거
		team2.remove(team2.size()-1);
    	
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 인원 수
        N = Integer.parseInt(br.readLine());
        
        // 점수 초기 값 저장할 배열
        board = new int[N][N];
        // 각 팀에 배치될 인원들 리스트
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        // 차이의 최솟값 - 초기는 정수의 최댓값으로 설정
        ans = Integer.MAX_VALUE;
        
        // 시너지 점수 입력
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 모든 조합 계산
        combi(0);
        
        // ans 출력
        System.out.println(ans);        
    }
}