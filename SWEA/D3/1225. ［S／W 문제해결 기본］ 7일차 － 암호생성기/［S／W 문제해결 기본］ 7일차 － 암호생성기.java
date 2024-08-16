import java.io.*;
import java.util.*;
 
public class Solution {
     
	// SWEA 10726 - 이진수 표현 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
//        int T = Integer.parseInt(br.readLine());
        // 10번의 테스트케이스 진행
        for(int tc = 1; tc <= 10; tc++) {
        	// 테스트 케이스 번호 입력
        	br.readLine();
        	// 수열을 저장할 리스트
        	List<Integer> num_lst = new LinkedList<>();
        	st = new StringTokenizer(br.readLine());
        	// 수열 입력
        	for(int i = 0; i < 8; i++) {
        		num_lst.add(Integer.parseInt(st.nextToken()));
        	}
        	// 사이클마다 빼주는 수
        	int cnt = 1;
        	
        	// 현재 수에서 cnt를 빼준 수가 0보다 작거나 같아질 때까지 반복
        	while(true) {
        		// 제일 앞 숫자를 제거
        		int cur = num_lst.remove(0);
        		// 현재 숫자에 cnt 빼주기
        		cur -= cnt;
        		// 뺀 숫자가 0보다 작거나 같아진다면
        		if(cur <= 0) {
        			// 제일 뒤에 0을 추가해주고 종료
        			num_lst.add(0);
        			break;
        		}
        		// 아니라면 뺀 숫자를 삽입
        		num_lst.add(cur);
        		// cnt는 1부터 5까지 순환할 수 있도록 5로나눈 나머지에 1 더해줌
        		cnt = cnt % 5 + 1;
        	}
        	
        	// 앞에서부터 수열 저장
        	sb.append("#").append(tc).append(" ");
        	for(int i = 0; i < 8; i++) {
        		sb.append(num_lst.get(i)).append(" ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
    }
 
}