import java.util.*;
import java.io.*;


public class Main {
	
	// BOJ 1181 - 단어 정렬
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		//단어를 추가할 dict 리스트
		List<String> dict = new ArrayList<>();
		// 중복되어서 추가하지 않는 단어의 수 count
		int minus = 0;
		
		for(int i = 0; i < n; i++) {
			String temp = br.readLine();
			if(!dict.contains(temp)) dict.add(temp);
			else minus++;
		}
		// 전체 길이 수정
		n -= minus;
		
		// 정렬 -> 길이가 다르다면 길이가 짧은것을 앞으로, 길이가 같다면 사전순서로
		Collections.sort(dict, (o1, o2) -> {
			if(o1.length() != o2.length()) return o1.length() - o2.length();
			else return o1.compareTo(o2);
		});
		
		for(int i = 0; i < n; i++) {
			sb.append(dict.get(i)).append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
