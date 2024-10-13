import java.io.*;
import java.util.*;

public class Main {

    // BOJ 15787 - 기차가 어둠을 헤치고 은하수를
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 열차의 개수 N, 명령어의 개수 M
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        // 열차의 좌석 정보 - 0~19까지 비트를 각 좌석으로 표시
        int[] train = new int[N];
        
        // 명령어 시작
        for(int idx = 0; idx < M; idx++) {
        	st = new StringTokenizer(br.readLine());
        	// 모드에 따라 다른 명령어 처리
        	int mod = Integer.parseInt(st.nextToken());
        	int i, x;
        	switch(mod) {
        	// i번 기차의 x번 좌석에 사람을 태우기
        	case 1:
        		i = Integer.parseInt(st.nextToken())-1;
        		x = Integer.parseInt(st.nextToken())-1;
        		// 1을 x번 시프트연산해서 train[i]랑 or연산 (train[i]의 x번째 비트가 1로 채워짐)
        		train[i] |= (1 << x);
        		break;
        	// i번 기차의 x번 좌석에 사람을 내리기
        	case 2:
        		i = Integer.parseInt(st.nextToken())-1;
        		x = Integer.parseInt(st.nextToken())-1;
        		// 1을 x번 시프트연산해서 not 연산을 한 뒤 train[i]랑 and연산 (x번째 비트를 제외한 모든 비트가 1로 채워지고 & 연산을 통해 그 자리만 0으로 바꿈)
        		train[i] &= ~(1 << x);
        		// 자리는 20번을 넘어갈 수 없으므로 넘어버린 값은 모듈러 연산을 통해 제거
        		train[i] %= (1 << 20);
        		break;
        	// i번 기차를 한 칸씩 뒤로 이동
        	case 3:
        		i = Integer.parseInt(st.nextToken())-1;
        		// 왼쪽 시프트 연산을 통해 한칸씩 이동 
        		train[i] <<= 1;
        		// 20이 넘는 부분은 모듈러 연산을 통해 삭제
        		train[i] %= (1 << 20);
        		break;
        	// i번 기차를 한 칸씩 앞으로 이동 
        	case 4:
        		i = Integer.parseInt(st.nextToken())-1;
        		// 오른쪽 시프트 연산을 통해 한칸씩 이동
        		train[i] >>= 1;
        		// 정수 타입이기때문에 소수점은 고려 X
        		break;
        	}
        }
        
        // 통과하느 열차의 수
        int ans = 0;
        // 중복 제거를 위한 hashset
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
        	// 아직 set에 등록되지 않은 좌석 배치라면 등록 후 통과
        	if(!set.contains(train[i])) {
        		set.add(train[i]);
        		ans++;
        	}
        }
        // 열차의 수 저장
        sb.append(ans);
        		
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}