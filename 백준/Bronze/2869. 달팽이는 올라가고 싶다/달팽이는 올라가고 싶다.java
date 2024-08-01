import java.util.*;
import java.io.*;

public class Main {

	//BOJ2869 - 달팽이는 올라가고 싶다
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// A = 올라가는 거리 , B = 미끄러지는 거리 , V = 전체 높이
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		// od = 하루에 올라갈 수 있는 거리, day = 올라가는 데 걸리는 날 
		int od = A - B;
		// 마지막날은 미끄러지지 않고 올라가기만 하면 되기 때문에 전체 길이에서 올라갈 수 있는 거리를 빼준 뒤,
		// 남은 거리를 올라가는데 몇 일이 걸리는 지 나눗셈으로 계산해준다.
		int day = (V - A) / od;
		// 몫이 0 이어도 남은 거리가 있다면 하루를 더 써야 하기 때문에 +1 해준다.
		if((V-A) % od != 0) day += 1;
		
		// 마지막 날 올라가는 거리를 빼고 계산했기 때문에 마지막 날을 더해준다.
		System.out.println(day + 1);
	}

}
