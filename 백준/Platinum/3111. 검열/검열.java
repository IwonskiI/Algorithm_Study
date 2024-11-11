import java.io.*;

public class Main {

    // BOJ 3111 - 검열
	public static int txtlen, tarlen;
	public static String text, pat;
	
	// 인덱스 접근을 위한 스택 클래스 생성
	public static class St {
		// 스택을 구현할 배열
		char[] stack = new char[300001];
		// 현재 top을 나타낼 idx
		int idx = 0;
		
		// 기본 생성자
		public St() { }
		
		// stack에 추가
		public void push(char a) {
			stack[idx++] = a;
		}
		
		// stack에서 제거
		public char pop() {
			char top = stack[--idx];
			stack[idx] = 0;
			return top;
		}
		
		// 현재 stack의 크기 반환
		public int size() {
			return idx;
		}
		
		// 왼쪽 스택을 위한 체크 함수
		public boolean leftchk() {
			// 현재 크기가 목표 문자열보다 작다면 무조건 포함 안하므로 return false
			if(this.size() < tarlen) return false;
			// 가장 위의 원소부터 탐색 시작
			int find = idx - 1;
			// pattern의 끝자리부터 거꾸로 확인하면서 stack에 패턴문자가 포함되었는지 확인
			for(int i = tarlen - 1; i >= 0; --i) {
				// 문자가 다르다면(패턴이 아니라면) return false
				if(stack[find--] != pat.charAt(i)) return false;
			}
			// 패턴이 있었다면 lstack에서 패턴을 제거
			for(int i = 0; i < tarlen; i++) this.pop();
			// 제거 후 찾음 표시로 return true
			return true;
		}
		
		// 오른쪽 스택을 위한 체크 함수 (로직은 왼쪽과 동일)
		public boolean rightchk() {
			if(this.size() < tarlen) return false;
			int find = idx - 1;
			// 오른쪽 함수는 역순으로 탐색하므로 패턴 문자를 순서대로 탐색하면 됨
			for(int i = 0; i < tarlen; i++) {
				if(stack[find--] != pat.charAt(i)) return false;
			}
			for(int i = 0; i < tarlen; i++) this.pop();
			return true;			
		}
		
		// 완성된 문자 배열을 출력
		public String toString() {
			StringBuilder sbr = new StringBuilder();
			for(int i = 0; i < this.size(); i++) {
				sbr.append(stack[i]);
			}
			return sbr.toString();
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 패턴과 문자열 입력
        pat = br.readLine(); 
        text = br.readLine();
        
        // 패턴과 문자열의 길이 계산
        txtlen = text.length();
        tarlen = pat.length();
        
        // 왼쪽 stack과 오른쪽 stack 생성
        St lq = new St(), rq = new St();
        
        // 왼쪽 스택의 현재 인덱스와 오른쪽 스택의 현재 인덱스
        int lidx = 0, ridx = text.length() - 1;
        
        // 왼쪽 인덱스가 오른쪽 인덱스 이하이면 계속 반복
        while(lidx <= ridx) {
        	// 왼쪽 인덱스가 오른쪽 인덱스 이하이면 계속 반복
        	while(lidx <= ridx) {
        		// 문자열의 현재 글자를 lq로 push
        		lq.push(text.charAt(lidx++));
        		// lq를 체크(만약 패턴을 찾았다면 다음 오른쪽 스택에서 탐색 시작)
        		if(lq.leftchk()) break;
        	}
        	// 오른쪽 스택을 확인할때도 동일한 로직 진행
        	while(lidx <= ridx) {
        		rq.push(text.charAt(ridx--));
        		if(rq.rightchk()) break;
        	}
        }
        
        // 왼쪽 인덱스가 오른쪽 인덱스를 초과했다면
        // 오른쪽 스택의 문자를 모두 왼쪽으로 추가
        while(rq.size() > 0) {
        	// 문자열을 함치는 중에도 패턴이 생기는지 확인
        	lq.push(rq.pop());
        	// 패턴이 생긴다면 삭제하면서 추가
        	lq.leftchk();
        }
        
        // 왼족스택에 최종 저장된 문자열을 출력
        sb.append(lq.toString());
                
        // 최종 정답 출력
        System.out.println(sb.toString());
        
    }

}