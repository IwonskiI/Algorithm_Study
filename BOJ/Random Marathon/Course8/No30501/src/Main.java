import java.io.*;


public class Main {
	
	// BOJ 30501 - 관공... 어찌하여 목만 오셨소...
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String name = br.readLine();
			// 이름에 S가 들어가면 이름 출력 후 반복문 종료
			if(name.contains("S")){
				System.out.println(name);
				break;
			}
		}

	}

}
