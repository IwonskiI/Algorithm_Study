import java.io.*;
import java.util.*;

public class Main {

    // BOJ 20520 - Оборона крепости
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        long ans = 0;
        
        long[][] lst = new long[500001][2];
        
        int idx = 0;
        HashMap<Long, Integer> map = new HashMap<>();
        
        // 공격자의 수와 공격가능한 사람의 수를 입력
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	// 공격자 수
        	int attack = Integer.parseInt(st.nextToken());
        	// 방어자 한 사람이 처치할 수 있는 공격자 수
        	int defend = Integer.parseInt(st.nextToken());
        	// 이번 구역에서 최대로 공격자를 잡을 수 있는 방어자의 수
        	int full = attack / defend;
        	// 이번에 잡을 수 있는 최대 공격자 수를 이전에 공격한 적이 있다면
        	if(map.containsKey(defend)) {
        		// 이번 방어자 수만 추가
        		lst[map.get(defend)][0] += full;
        	}
        	// 공격한 적이 없다면
        	else {
        		// 현재 인덱스에 현재 공격자 수를 등록
        		map.put(Integer.toUnsignedLong(defend), idx);
        		// 인덱스 저장 및 방어자 수 추가
        		lst[idx][1] = defend;
        		lst[idx++][0] += full;
        		
        	}
        	// 나머지 계산
        	int remain = attack % defend;
        	// 나머지가 있다면 위와 같은 방법으로 추가
        	if(remain != 0) {
            	if(map.containsKey(remain)) {
            		lst[map.get(remain)][0] += 1;
            	}
            	else {
            		map.put(Integer.toUnsignedLong(remain), idx);
            		lst[idx][1] = remain;
            		lst[idx++][0] += 1;
            	}
        	}
        	// 총 공격 인원수 증가
        	ans += attack;
        }
        
        // 가장 많이 없앨 수 있게 정렬
        Arrays.sort(lst, (a, b) -> (int)(b[1] - a[1]));
        
        // 가장 많이 없앨 수 있는 사람부터 삭제
        idx = 0;
        // 방어자가 남아있다면 계속 진행
        while(S > 0) {
        	// 처치해야할 적이 0이면 종료
        	if(ans <= 0) break;
        	// 이번 인덱스에 처치해야하는데 필요한 인원수가 현재 인원수 보다 많다면
        	if(lst[idx][0] > S) {
        		// 남아있는 적 수에서 현재 인원수만큼만 투입해서 적 제거
        		ans -= (lst[idx][1] * S);
        		// 인원수 모두 소진
        		S = 0;
        	}
        	// 아니라면
        	else {
        		// 현재 인덱스의 처리해야하는데 필요한 인원수를 모두 투입해서 적 제거
        		ans -= (lst[idx][1] * lst[idx][0]);
        		// 이번 인덱스에 투입된 인원수 감소 후 인덱스 증가
        		S -= lst[idx++][0];
        	}
        }

        // 남은 공격자 수 출력
        System.out.println(ans);        
    }
}