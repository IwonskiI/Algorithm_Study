import java.io.*;

public class Main {

	//BOJ 16406 - Exam
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		
		int k = Integer.parseInt(br.readLine());
		String[] mine = br.readLine().split("");
		String[] friend = br.readLine().split("");
		
		//친구와 같은 개수 same / 다른 개수 diff
		int same = 0, diff = 0;
		
		for(int i = 0 ; i < mine.length; i++) {
			if(mine[i].equals(friend[i])) same++;
			else diff++;
		}
		
		// 친구가 맞은 개수가 둘이 같은 개수보다 적다면
		if(same > k) {
			// 친구가 맞은건 무조건 다 맞음
			ans += k;
			// 친구가 틀린 것도 다 맞음
			ans += diff;
		}
		// 친구가 맞은 개수가 둘이 같은 개수보다 많다면
		else {
			// 친구와 같은건 다 맞음
			ans += same;
			// 친구와 다른 것 중에서 친구만 맞은 개수를 빼고 나머진 다 맞음
			ans += diff - (k-same);
		}
		
		System.out.println(ans);
	}

}
