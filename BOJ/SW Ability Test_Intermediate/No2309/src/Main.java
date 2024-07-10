import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] h_lst = new int[9];
		int total = 0;
		
		for(int i = 0; i < 9; i++) {
			int temp = Integer.parseInt(br.readLine());
			h_lst[i] = temp;
			total +=  temp;
		}
		
		Arrays.sort(h_lst);
		int wrong = total - 100;
		int f_pos = 0, e_pos = 0;
		boolean flag = false;
		for(f_pos = 0; f_pos < 9; f_pos++) {
			for (e_pos = f_pos+1; e_pos < 9; e_pos++) {
				if (h_lst[f_pos] + h_lst[e_pos] == wrong) {
					flag = true;
					break;
				}
			}
			if (flag) break;
		}
		
		for(int i = 0; i < 9; i++) {
			if(i == f_pos || i == e_pos) continue;
			System.out.println(h_lst[i]);
		}

	}

}
