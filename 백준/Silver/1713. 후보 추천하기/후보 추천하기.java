import java.io.*;
import java.util.*;

public class Main {
    
    // BOJ 1713 - 후보 추천하기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
		
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        // 액자를 관리할 배열 - [학생번호, 들어온순서]
        int[][] frame = new int[N+1][2];
        int idx = 1;
        // 추천수 관리 배열
        int[] liked = new int[101];
        // 액자 안에 있는지 관리할 배열
        int[] isin = new int[101];
        
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= K; i++) {
            int cur = Integer.parseInt(st.nextToken());
            // 아직 액자가 다 차지 않았다면
            if(idx <= N) {
                // 추천수 증가
                liked[cur]++;
                // 현재 액자가 아직 배치전이라면
                if(isin[cur] == 0) {
                    // 액자에 추가
                    frame[idx] = new int[] {cur, i};
                    // 현재 번호의 액자 위치 저장
                    isin[cur] = idx++;
                }
            }
            // 액자가 가득 찼다면
            else {
                // 현재 학생 추천수 증가
                liked[cur]++;
                // 현재 학생이 액자 안에 없다면
                if(isin[cur] == 0) {
                    // 삭제될 학생의 인덱스 관리
                    int top = 1;
                    // 순서대로 비교하며
                    // 추천수가 같을 때, 더 먼저 들어왔거나,
                    // 추천수가 더 작다면 top 갱신
                    for(int j = 2; j <= N; j++){
                        if((liked[frame[top][0]] == liked[frame[j][0]] && frame[j][1] < frame[top][1]) 
                        		|| (liked[frame[top][0]] > liked[frame[j][0]]))
                            top = j;
                    }
                    
                    // 액자에서 제거
                    isin[frame[top][0]] = 0;
                    // 추천수 초기화
                    liked[frame[top][0]] = 0;
                    // top 위치에 현재 숫자 저장
                    isin[cur] = top;
                    // 현재 학생 액자에 추가
                    frame[top] = new int[] {cur, i};
                }
            }
        }
        
        // 최종 결과 오름차순 정렬
        Arrays.sort(frame, (a, b) -> a[0] - b[0]);
        
        // 결과 순서대로 저장
        for(int i = 1; i <= N; i++) {
        	// 번호가 0번인 학생은 없으므로 스킵 (액자가 다 차지 않은 경우)
        	if(frame[i][0] == 0) continue;
            sb.append(frame[i][0]).append(" ");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}