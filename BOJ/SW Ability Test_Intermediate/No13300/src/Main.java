import java.util.*;
import java.io.*;


public class Main {
    
    // a:직사각형 , b:선분, c:점, d:없음음

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int [][] student = new int[6][2];
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++){
		    st = new StringTokenizer(br.readLine());
		    int gen = Integer.parseInt(st.nextToken());
		    int grade = Integer.parseInt(st.nextToken());
		    student[grade-1][gen]++;
		}
		
		for(int g = 0; g < 6; g++){
		    ans += student[g][0] / k;
		    if(student[g][0] % k != 0) ans += 1;
		    ans += student[g][1] / k;
		    if(student[g][1] % k != 0) ans += 1;
		}
		
		
		System.out.println(ans);
	}

}
