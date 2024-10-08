import java.io.*;
import java.util.*;

public class Main {

    // BOJ 1062 - 가르침
    public static int ans, N, K;
    public static String[] words;
    
    public static void combi(int cnt, int start, int bit) {
    	// 기저 조건(K개의 단어를 다 뽑았다면)
    	if(cnt == K) {
    		// 현재 글자 조합으로 읽을 수 있는 단어의 개수
    		int count = 0;
    		// N개의 단어 확인
    		for(int i = 0; i < N; i++) {
    			// 읽을 수 없으면 false로 변경
    			boolean flag = true;
    			// 앞 4글자, 뒷 4글자는 고정이므로 제외하고 탐색
    			for(int j = 4; j < words[i].length()-4; j++) {
    				// 현재 bit에 j번째 글자가 포함되지 않는다면 종료
    				if((bit & (1 << (words[i].charAt(j) - 'a'))) == 0) {
    					flag = false;
    					break;
    				}
    			}
    			// 읽을 수 있으면 count 증가
    			if(flag) count++;
    		}
    		
    		// N개의 글자를 확인한 뒤 ans와 비교해서 최댓값으로 갱신
    		ans = Math.max(ans, count);
    		// 종료
    		return;
    	}
    	
    	// 백트래킹 진행 - 단어 조합 구하기
    	for(int i = start; i < 26; i++) {
    		// 현재 조합에 이미 사용중인 단어(antic)이라면 스킵
    		if((bit & (1 << i)) != 0) continue;
    		// 아니라면 단어 갯수 증가, 인덱스 증가, bit에 현재 글자(i)를 or연산해서 재귀 호출 
    		combi(cnt+1, i+1, bit|(1<<i));
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 단어의 최댓값
        ans = 0;
        
        // 단어의 개수 N
        N = Integer.parseInt(st.nextToken());
        // 글자수의 제한 K
        K = Integer.parseInt(st.nextToken());
         
        // K가 5 이하라면 이미 불가능
        if(K < 5) {
        	System.out.println(0);
        }
        // K가 26이라면 모든 단어를 사용할 수 있으므로 N 출력
        else if(K == 26) {
        	System.out.println(N);
        }
        // 위 경우가 아니라면 계산
        else {
        	// 단어 저장
        	words = new String[N];
        	for(int i = 0; i < N; i++) {
        		words[i] = br.readLine();
        	}
        	
        	// 사용중인 단어를 나타낼 마스크
        	int mask = 0;
        	String base = "antic";
        	for(int i = 0; i < 5; i++) {
        		mask |= (1 << (base.charAt(i)-'a'));
        	}
        	// 조합 계산
        	combi(5, 0, mask);
        	// 탐색 종료 후 정답 출력
        	System.out.println(ans);
        }
        

        
    }

}