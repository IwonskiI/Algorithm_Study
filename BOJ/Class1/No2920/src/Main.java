import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean asc = true;
		boolean mixed = false;
		for(int i = 0; i < 8; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(i == 0 && temp == 1) asc = true;
			else if(i == 0 && temp == 8) asc = false;
			else if(i == 0 || ((asc && temp != i+1) || (!asc && temp != 8-i))) {
				mixed = true;
				break;
			}
		}
		if (mixed) System.out.println("mixed");
		else if(asc) System.out.println("ascending");
		else System.out.println("descending");
	}

}
