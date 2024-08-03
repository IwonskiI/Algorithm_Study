import java.util.*;
import java.io.*;

public class Main {

    // BOJ 2729 - 이진수 덧셈
    public static String addBin(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int maxLen = Math.max(len1, len2);
        StringBuilder str = new StringBuilder();
        // 올림수 계산
        int carry = 0;
        
        for (int i = 0; i < maxLen; i++) {
        	// 뒤에서부터 자릿수 계산
            int bit1 = (i < len1) ? num1.charAt(len1 - 1 - i) - '0' : 0;
            int bit2 = (i < len2) ? num2.charAt(len2 - 1 - i) - '0' : 0;
            // 자릿수 + 올림수 계산
            int sum = bit1 + bit2 + carry;
            // 올림수는 2 이상이면 1 올리면 되므로 몫으로 계산
            carry = sum / 2;
            // 자릿수는 나머지로 계산
            str.append(sum % 2);
        }
        
        // 남은 올림수 처리
        if (carry == 1) {
            str.append(carry);
        }
        
        return str.reverse().toString();
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            String num1 = st.nextToken();
            String num2 = st.nextToken();
            
            // 정규표현식으로 앞자리 0 제거
            String res = addBin(num1, num2).replaceFirst("^0+", "");
            // 빈 문자열이 되면 0으로 설정
            if (res.isEmpty()) {
                res = "0";
            }
            sb.append(res).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}
