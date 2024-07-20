import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 28444 - HI-ARC=?
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int i = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println((h*i) - (a*r*c));
		
	}

}
