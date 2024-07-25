import java.io.*;
import java.util.*;


public class Main {
	
	// BOJ 10825 - 국영수
	
	// 학생 정보를 담을 class 구현
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
		
		// 학생을 관리할 리스트 구현
		List<Student> stu_lst = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Student stu = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			stu_lst.add(stu);
		}
		
		
		// 리스트에 담긴 학생들을 기준에 맞게 정렬
		Collections.sort(stu_lst, (o1, o2)->{
			// 수학, 영어, 국어 점수가 다 같다면
			if(o1.math == o2.math && o1.eng == o2.eng && o1.kor == o2.kor) {
				// 이름을 사전 순서로 정렬
				return o1.name.compareTo(o2.name);
			}
			// 영어, 국어 점수만 같다면
			else if(o1.eng == o2.eng && o1.kor == o2.kor) {
				// 수학 점수를 내림차순으로 정렬
				return o2.math - o1.math;
			}
			// 국어 점수만 같다면
			else if(o1.kor == o2.kor) {
				// 영어 점수를 오름차순으로 정렬
				return o1.eng - o2.eng;
			}
			// 다 다르다면
			else {
				// 국어 점수를 내림차순으로 정렬
				return o2.kor - o1.kor;
			}
			});
		
		for(int i = 0; i < N; i++) {
			sb.append(stu_lst.get(i).name).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
