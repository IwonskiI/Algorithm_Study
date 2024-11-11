import java.io.*;

public class Main {

    // BOJ 9935 - 문자열 폭발
	public static int txtlen, tarlen;
	public static String text, pat;
	
	// 인덱스 접근을 위한 스택 클래스 생성
	public static class St {
		// 스택을 구현할 배열
		char[] stack = new char[1000001];
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
		
		// 스택에 문자열이 있는지 확인하기 위한 함수
		public void chk() {
			// 현재 크기가 목표 문자열보다 작다면 무조건 포함 안하므로 return false
			if(this.size() < tarlen) return;
			// 가장 위의 원소부터 탐색 시작
			int find = idx - 1;
			// pattern의 끝자리부터 거꾸로 확인하면서 stack에 패턴문자가 포함되었는지 확인
			for(int i = tarlen - 1; i >= 0; --i) {
				// 문자가 다르다면(패턴이 아니라면) return false
				if(stack[find--] != pat.charAt(i)) return;
			}
			// 패턴이 있었다면 stack에서 패턴을 제거
			for(int i = 0; i < tarlen; i++) this.pop();
		}
		
		// 완성된 문자 배열을 출력
		public String toString() {
			StringBuilder sbr = new StringBuilder();
			if(this.size() == 0)sbr.append("FRULA");
			else {
				for(int i = 0; i < this.size(); i++) {
					sbr.append(stack[i]);
				}
			}
			return sbr.toString();
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 패턴과 문자열 입력
        text = br.readLine();
        pat = br.readLine(); 
        
        // 패턴과 문자열의 길이 계산
        txtlen = text.length();
        tarlen = pat.length();
        
        // stack 생성
        St stack = new St();
        
        // 패턴의 마지막 문자
        char last = pat.charAt(tarlen-1);
        
        // 문자를 입력받으면서 패턴에 맞는 문자열이 생성되면 삭제
        for(int i = 0; i < txtlen; i++) {
        	char cur = text.charAt(i);
        	stack.push(cur);
        	if(cur == last) stack.chk();
        }
        
        // 스택에 최종 저장된 문자열을 출력
        sb.append(stack.toString());
                
        // 최종 정답 출력
        System.out.println(sb.toString());
        
    }

}