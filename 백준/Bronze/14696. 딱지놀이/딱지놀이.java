import java.util.*;
import java.io.*;


public class Main {
    
    // a:직사각형 , b:선분, c:점, d:없음음

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < N; tc++){
		    int[] a_lst = new int[5];
		    int[] b_lst = new int[5];
		    st = new StringTokenizer(br.readLine());
		    st.nextToken();
		    while(st.hasMoreTokens()){
		        a_lst[Integer.parseInt(st.nextToken())] += 1;
		    }
		    st = new StringTokenizer(br.readLine());
		    st.nextToken();
		    while(st.hasMoreTokens()){
		        b_lst[Integer.parseInt(st.nextToken())] += 1;
		    }
		    boolean b_flag = true;
		    for(int i = 4; i > 0; i--){
		        b_flag = true;
		        if(a_lst[i] > b_lst[i]) sb.append("A\n");
		        else if (a_lst[i] < b_lst[i]) sb.append("B\n");
		        else b_flag = false;
		        if(b_flag) break;
		    }
		    if (!b_flag) sb.append("D\n");
		}
		
		System.out.println(sb.toString());
	}

}
