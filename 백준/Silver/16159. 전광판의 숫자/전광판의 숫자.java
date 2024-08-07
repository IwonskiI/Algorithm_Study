import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 16159 - 전광판의 숫자
	public static String[][] num_dict = new String[][] {
		{"000000", "001100", "010010", "010010", "010010", "001100", "000000"}, // 0
		{"000000", "000100", "001100", "000100", "000100", "000100", "000000"}, // 1
		{"000000", "011110", "000010", "011110", "010000", "011110", "000000"}, // 2
		{"000000", "011100", "000010", "000100", "000010", "011100", "000000"}, // 3
		{"000000", "000100", "001100", "010100", "111110", "000100", "000000"}, // 4
		{"000000", "011110", "010000", "011100", "000010", "010010", "001100"}, // 5
		{"000000", "010000", "010000", "011110", "010010", "011110", "000000"}, // 6
		{"000000", "011110", "000010", "000100", "000100", "000100", "000000"}, // 7
		{"000000", "011110", "010010", "011110", "010010", "011110", "000000"}, // 8 
		{"011110", "010010", "010010", "011110", "000010", "000010", "000010"}  // 9 
	};
	static int len;

	public static void reverse(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}
	
	public static int convert(int n) {
		int res = 0;
		switch (n) {
		case 6:
			return 1;
		case 8:
			return 7;
		case 9:
			return 3;
		case 10:
			return 0;
		case 11:
			return 4;
		case 12:
			return 6;
		case 13:
			return 5;
		case 14:
			return 2;
		case 15:
			return 9;
		case 16:
			return 8;
		default:
			return -1;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] num_lst = null;
		for (int i = 0; i < 7; i++) {
			String[] line = br.readLine().split("");
			len = line.length / 6;
			if(i == 0) num_lst = new int[len]; 
			int start = 0;
			for (int k = 0; k < len; k++) {
				int num = 0;
				for(int j = start; j < start + 6; j++) {
					num += Integer.parseInt(line[j]);
				}
				num_lst[k] += num;
				start += 6;
			}
		}
		
		for(int i = 0; i < len; i++) {
			num_lst[i] = convert(num_lst[i]);
		}
		
		int idx = -1;
		for (int i = len - 1; i > 0; i--) {
			if (num_lst[i] > num_lst[i - 1]) {
				idx = i - 1;
				break;
			}
		}
		
		if (idx == -1) {
			System.out.println("The End");
		} else {
			int j = len - 1;
			while (num_lst[j] <= num_lst[idx]) j--;
			int temp = num_lst[idx];
			num_lst[idx] = num_lst[j];
			num_lst[j] = temp;
			
			reverse(num_lst, idx + 1, len - 1);
			
			for (int i = 0; i < 7; i++) {
				for (int k = 0; k < len; k++) {
					sb.append(num_dict[num_lst[k]][i]);
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
		}
	}
}
