import java.io.*;
import java.util.*;

public class Main {

	// BOJ 11047 - 동전 0	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 동전의 종류 N
        int N = Integer.parseInt(st.nextToken());
        // 만들어야하는 가치 K
        int K = Integer.parseInt(st.nextToken());
        
        // 동전 종류를 담은 coin
        int[] coin = new int[N];
        
        // 동전 종류 입력
        for(int i = 0; i < N; i++) {
        	coin[i] = Integer.parseInt(br.readLine());
        }
        
        // 필요한 동전의 개수 ans, 현재 확인 중인 동전의 index
        int ans = 0, idx = N-1;
        
        // K원을 모두 만들 때 까지 반복
        while(K > 0) {
        	// K원에서 현재 동전을 최대로 뽑아 낼 수 있는 만큼 뽑기
        	ans += K / coin[idx];
        	// 현재 동전만큼 뽑고 남은 가치를 K에 저장
        	K %= coin[idx];
        	// 다음 큰 동전 확인
        	idx--;
        }
        
        // 전체 동전 개수 저장
        sb.append(ans);
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }
}