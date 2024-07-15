import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			int score = 0, cur = 1;
			boolean before = false;
			String[] score_lst = br.readLine().split("");
			for(int j = 0; j < score_lst.length; j++) {
				if(score_lst[j].equals("O")) {
					if(before) cur += 1;
					score += cur;
					before = true;
				}
				else {
					cur = 1;
					before = false;
				}
			}
			
			sb.append(score).append("\n");
		}
		System.out.println(sb.toString());
	}

}
