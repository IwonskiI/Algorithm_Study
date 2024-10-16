import java.io.*;
import java.util.*;

public class Main {

    // BOJ 2800 - 괄호 제거
	public static boolean[] deleted;
	public static List<int[]> pos;
	public static String str;
	public static HashSet<String> dupSet;
	public static List<String> res;
	
	// 괄호 위치의 모든 조합 구하기
	public static void combi(int cnt, boolean is_b) {
		// 모든 괄호를 다 확인했다면
		if(cnt == -1) {
			// 괄호를 한개라도 제거했다면 (최소 1쌍의 괄호는 제거해야함)
			if(is_b) {
				// 제거된 괄호를 빼고 문자열에 추가한 뒤 sb에 저장
				String temp = "";
				for(int i = 0; i < str.length(); i++) {
					// 삭제되지 않았다면 문자열에 추가
					if(!deleted[i]) temp += str.charAt(i);
				}
				// 완성된 수식이 set에 없다면
				if(!dupSet.contains(temp)) {
					// 추가가 완료된 문자열 sb에 저장
					res.add(temp);
					// set에 현재 수식 저장
					dupSet.add(temp);
				}
			}
			// 종료
			return;
		}
		
		// 괄호를 제거하지 않은 상태로 다음 괄호 체크
		combi(cnt-1, is_b);
		// 현재 레벨의 괄호 좌표 가져오기
		int[] bracket = pos.get(cnt);
		// 현재 레벨의 괄호 제거
		deleted[bracket[0]] = true;
		deleted[bracket[1]] = true;
		// 괄호를 제거하고 다음 레벨의 괄호 탐색
		combi(cnt-1, true);
		// 제거된 괄호 원상 복구
		deleted[bracket[0]] = false;
		deleted[bracket[1]] = false;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        str = br.readLine();
        
        // 삭제된 괄호를 표시할 boolean 배열
        deleted = new boolean[str.length()];
        // 괄호의 좌표를 저장할 pos list
        pos = new ArrayList<>();
        // 괄호의 쌍을 찾을 stack
        Deque<Integer> stack = new ArrayDeque<>();
        // 똑같은 수식을 저장하지 않기 위한 Set
        dupSet = new HashSet<>();
        // 결과를 저장할 res 배열
        res = new ArrayList<>();
        
        // 문자열 검색
        for(int i = 0; i < str.length(); i++) {
        	// 현재 문자
        	char cur = str.charAt(i);
        	// 열리는 괄호라면
        	if(cur == '(') {
        		// 현재 인덱스를 스택에 저장
        		stack.offer(i);
        	}
        	// 닫히는 괄호라면
        	else if(cur == ')') {
        		// 가장 마지막에 추가한 열린 괄호의 인덱스를 뽑아서 현재 인덱스와 같이 저장
        		pos.add(new int[] {stack.pollLast(), i});
        	}
        	
        }
        
        // 가능한 모든 조합 계산
        combi(pos.size()-1, false);
        
        // 사전 순 출력을 위한 정렬
        Collections.sort(res);
        
        // 정렬된 문자열을 저장
        for(String ans : res) {
        	sb.append(ans).append("\n");
        }
        
        // 최종 결과 출력
        System.out.println(sb.toString());
    }

}