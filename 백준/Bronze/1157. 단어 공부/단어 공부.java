import java.io.*;
import java.util.*;

public class Main{
	
	
	// BOJ 1157 - 단어 공부
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> dict = new HashMap<>();
        
        String word = br.readLine().toUpperCase();
        // 최대 갯수 저장
        int max = 0;
        // 최대 단어(정답) 저장
        String ans = "";
        
        // 모든 글자 확인
        for(int i = 0; i < word.length(); i++) {
        	// 현재 글자
        	char cur = word.charAt(i);
        	// 현재까지 나온 cur의 갯수 + 1(현재 글자)
        	int cnt = dict.getOrDefault(cur, 0) + 1;
        	// 글자 수 업데이트
        	dict.put(cur, cnt);
        	// 현재 글자의 갯수가 이전의 최대갯수보다 크다면 현재 글자 저장, 최대글자수가 같다면 ? 저장, 아니라면 기존 글자 저장 
        	ans = cnt > max ? Character.toString(cur) : cnt == max ? "?" : ans;
        	// 최대 글자수 업데이트 (기존 글자수보다 현재 글자수가 더 크다면 업데이트)
        	max = cnt > max ? cnt : max;
        }
        
        // 저장된 정답 출력
        System.out.println(ans);
        
    }
}