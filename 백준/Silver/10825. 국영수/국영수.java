import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 10825 - 국영수
	
	static class Student {
		
		String name;
		int kor;
		int eng;
		int math;
		
		public Student() { }
		
		public Student(String name, int kor, int eng, int math) {
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		List<Student> stu_lst = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Student stu = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			stu_lst.add(stu);
		}
		
		Collections.sort(stu_lst, (o1, o2)->{
			if(o1.math == o2.math && o1.eng == o2.eng && o1.kor == o2.kor) {
				return o1.name.compareTo(o2.name);
			}
			else if(o1.eng == o2.eng && o1.kor == o2.kor) {
				return o2.math - o1.math;
			}
			else if(o1.kor == o2.kor) {
				return o1.eng - o2.eng;
			}
			else {
				return o2.kor - o1.kor;
			}
			});
		for(int i = 0; i < N; i++) {
			sb.append(stu_lst.get(i).name).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
