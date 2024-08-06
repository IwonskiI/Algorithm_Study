import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 23056 - 참가자 명단
	
	// 학생의 반과 이름을 저장할 객체 정의
	public static class Student {
		public int cnum;
		public String name;
		
		public Student() {}
		
		public Student(int n, String s) {
			super();
			this.cnum = n;
			this.name = s;
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	// 학생을 반별로 정의할 배열과 학생 수를 제어할 배열 선언
    	Student[][] lst = new Student[n][m];
    	int[] cnt = new int[n];
    	
    	
    	while(true) {
    		st = new StringTokenizer(br.readLine());
    		int cnum = Integer.parseInt(st.nextToken());
    		// 0 0 이면 종료
    		if(cnum == 0) break;
    		String name = st.nextToken();
    		
    		// 반별 학생 수가 m명 이하라면 추가
    		if(cnt[cnum-1] < m) {
    			lst[cnum-1][cnt[cnum-1]++] = new Student(cnum, name);
    		}
    	}
    	
    	// 홀수 반 학생 정렬 및 출력
    	for(int i = 0; i < n; i+=2) {
    		Student[] cur = lst[i];
    		Arrays.sort(cur, (o1, o2)-> {
    			if(o1 == null) {
    				return 1;
    			}
    			else if(o2 == null) {
    				return -1;
    			}
    			else if(o1.name.length() < o2.name.length()) {
    				return -1;
    			}
    			else if(o1.name.length() > o2.name.length()){
    				return 1;
    			}
    			else {
    				return o1.name.compareTo(o2.name);
    			}
    		});
    		for(int j = 0; j < cnt[i]; j++) {
    			sb.append(cur[j].cnum).append(" ").append(cur[j].name).append("\n");
    		}
    	}
    	// 짝수반 학생 정렬 및 출력
    	for(int i = 1; i < n; i+=2) {
    		Student[] cur = lst[i];
    		Arrays.sort(cur, (o1, o2)-> {
    			if(o1 == null) {
    				return 1;
    			}
    			else if(o2 == null) {
    				return -1;
    			}
    			else if(o1.name.length() < o2.name.length()) {
    				return -1;
    			}
    			else if(o1.name.length() > o2.name.length()){
    				return 1;
    			}
    			else {
    				return o1.name.compareTo(o2.name);
    			}
    		});
    		for(int j = 0; j < cnt[i]; j++) {
    			sb.append(cur[j].cnum).append(" ").append(cur[j].name).append("\n");
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
}

