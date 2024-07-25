import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 20215 Cutting Corners
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		double diag = Math.sqrt(w*w + h*h);
		double rect = w + h;
		
		System.out.println(rect - diag);
		
	}

}
