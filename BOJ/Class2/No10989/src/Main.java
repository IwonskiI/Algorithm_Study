import java.io.*;

public class Main {
	
	// BOJ 10989 - 수 정렬하기 3
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// Counting 정렬을 위한 배열 선언
		int[] arr = new int[10001];
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			// 입력된 숫자의 인덱스를 증가
			arr[Integer.parseInt(br.readLine())]++;
		}
		
		for(int i = 1; i <= 10000; i++) {
			// 배열의 값이 0이 아니라면 -> 입력된 수 중에 있다는 의미
			if(arr[i] != 0) {
				// 배열의 값만큼 인덱스를 추가
				for(int j = 0; j < arr[i]; j++) {
					sb.append(i).append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}

}
