import java.io.*;
import java.util.*;
 
public class Solution {
     
	// SWEA 10726 - 이진수 표현 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	
        	// M을 2진수로 변환
        	String binary = Integer.toBinaryString(M);
        	// 마지막 비트를 셀 변수
        	int cnt = 0;
        	// 뒤에서부터 N개의 비트를 확인, 0이하로는 내려가지 않도록 추가 조건 설정
        	for(int i = binary.length()-1; i >= binary.length() - N && i >= 0; i--) {
        		// 비트가 1이라면 cnt 증가
        		if(binary.charAt(i) - '0' == 1) cnt++; 
        	}
        	
        	// 1인 비트가 N개가 아니라면 OFF, 맞다면 ON
        	if(cnt != N) sb.append("#").append(tc).append(" OFF\n");
        	else sb.append("#").append(tc).append(" ON\n");
        }
        System.out.println(sb.toString());
    }
 
}