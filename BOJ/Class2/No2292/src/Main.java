import java.io.*;

public class Main {
	
	//BOJ2292 - 벌집
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		
		// ans = 몇번째 겹 인지, num = 해당 층의 최대 방 번호
		int ans = 1, num = 1;
		
		//겹이 늘어날 수록 6의 배수만큼 방 번호가 증가함
		for(int i = 6; num < N; i+=6) {
			num += i;
			ans++;
		}
		
		System.out.println(ans);

	}

}
