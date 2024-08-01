import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			String num = br.readLine();
			if(num.equals("0")) break;
			String[] num_lst = num.split("");
			boolean flag = true;
			for(int s = 0, e = num_lst.length-1; s < e; s++, e--) {
				if(!num_lst[s].equals(num_lst[e])) {
					System.out.println("no");
					flag = false;
					break;
				}
			}
			if(flag) System.out.println("yes");
		}

	}

}
