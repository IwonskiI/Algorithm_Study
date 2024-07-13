import java.util.*;
import java.io.*;


public class Main {
    
    // a:직사각형 , b:선분, c:점, d:없음음

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i = 0; i < 4; i++){
		    int sx1, sy1, ex1, ey1;
		    int sx2, sy2, ex2, ey2;
		    st = new StringTokenizer(br.readLine());
		    sx1 = Integer.parseInt(st.nextToken());
		    sy1 = Integer.parseInt(st.nextToken());
		    ex1 = Integer.parseInt(st.nextToken());
		    ey1 = Integer.parseInt(st.nextToken());
		    sx2 = Integer.parseInt(st.nextToken());
		    sy2 = Integer.parseInt(st.nextToken());
		    ex2 = Integer.parseInt(st.nextToken());
		    ey2 = Integer.parseInt(st.nextToken());
		    
		    boolean not_match = (sx1 > ex2) || (sx2 > ex1) || (sy1 > ey2) || (sy2 > ey1);
		    boolean dot = (sx1==ex2 && sy1==ey2) || (ex1==sx2 && sy1==ey2) || (ex1==sx2 && ey1==sy2) || (sx1==ex2 && ey1==sy2);
		    boolean line = (sx1==ex2) || (sy1==ey2) || (ex1==sx2) || (sy1==ey2) || (ex1==sx2) || (ey1==sy2) || (sx1==ex2) || (ey1==sy2);
		    
		    if(not_match) sb.append("d\n");
		    else if(dot) sb.append("c\n");
		    else if(line) sb.append("b\n");
		    else sb.append("a\n");
		    
		}
		
		System.out.println(sb.toString());
	}

}
