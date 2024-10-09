import java.io.*;
import java.util.*;

public class Main {

    // BOJ 1339 - 단어 수학
    public static int N, ans;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        int[][] lst = new int[26][2];
        
        // lst[i]가 어떤 글자를 나타내는지 표시하기 위해 인덱스 저장
        for(int i = 0; i < 26; i++) {
        	lst[i][1] = i;
        }
        
        // 단어 입력
        for(int i = 0; i < N; i++) {
        	String word = br.readLine();
        	// 단어 저장
        	words[i] = word;
        	// 각 글자를 자리수만 계산해서 더하기
        	for(int j = 0; j < word.length(); j++) {
        		// 현재 자리수만큼 10의 제곱수 계산
        		int pow = (int) Math.pow(10, word.length() - j - 1);
        		// 인덱스 계산
        		int idx = word.charAt(j) - 'A';
        		// 누적합
        		lst[idx][0] += pow;
        	}
        }
        
        // 내림차 순으로 정렬
        Arrays.sort(lst, (a, b) -> {
        	return b[0] - a[0];
        });
        
        // 각 문자가 나타내는 글자를 매핑하기 위한 map
        HashMap<Character, String> map = new HashMap<>();
        
        // 9부터 순서대로 할당
        for(int i = 9, idx = 0; i >= 0; i--, idx++) {
        	// 현재 글자 계산
        	char a = (char) (lst[idx][1]+'A');
        	// 현재 글자에 현재 i값 저장
        	map.put(a, Integer.toString(i));
        }
        
        // 각 문자를 숫자로 치환해서 더한 값 저장
        ans = 0;
        
        for(int i = 0; i < N; i++) {
        	String word = words[i];
        	String cur = "";
        	// 각 글자를 map에서 매핑되어있는 숫자로 치환
        	for(int j = 0; j < word.length(); j++) {
        		char a = word.charAt(j);
        		cur += map.get(a);
        	}
        	// 문자열을 숫자로 변환해서 ans에 더하기
        	ans += Integer.parseInt(cur);
        }
        
        // 최대 합 출력
        System.out.println(ans);
    }

}