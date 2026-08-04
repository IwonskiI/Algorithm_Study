import java.util.*;

// Trie 자료 구조
class Node{
    Node[] child;
    // int[] cnt;
    HashMap<Integer,Integer> cnt;
    
    public Node() {
        // 다음 글자 저장
        child = new Node[26];
        // 남은 글자수에 따라 패턴 수 저장
        // cnt = new int[10001];
        cnt = new HashMap<>();
    }
}

class Solution {
    public int[] solution(String[] words, String[] queries) {
        
        // 정방형 Trie
        Node f_root = new Node();
        // 역방형 Trie
        Node b_root = new Node();
    
        StringBuilder sb;
        // 전체 ? 대비 글자수에 따른 패턴 수 저장
        int[] len_cnt = new int[10001];
        
        // 단어 Trie 저장
        for(String word : words) {
            // 글자 수 패턴 저장
            int w_len = word.length();
            len_cnt[w_len]++;
            
            // 뒤집은 문자열
            sb = new StringBuilder(word);
            String back = sb.reverse().toString();
            
            // 정방향
            Node forward = f_root;
            // 역방향
            Node backward = b_root;
            
            for(int i = 0; i < word.length(); i++) {
                // 글자 인덱스 구하기
                int f_idx = word.charAt(i) - 'a', b_idx = back.charAt(i) - 'a';
                // 정방형 저장
                // 처음 등장하는 글자라면 초기화
                if(forward.child[f_idx] == null) forward.child[f_idx] = new Node();
                // 현재 글자로 이동
                forward = forward.child[f_idx];
                // 현재 글자에 남은 글자수를 인덱스로 하는 배열에 패턴 수 저장
                // forward.cnt[w_len-i-1]++;
                forward.cnt.put(w_len-i-1, forward.cnt.getOrDefault(w_len-i-1,0)+1);
                
                // 역방향도 마찬가지로 저장
                if(backward.child[b_idx] == null) backward.child[b_idx] = new Node();
                backward = backward.child[b_idx];
                // backward.cnt[w_len-i-1]++;
                backward.cnt.put(w_len-i-1, backward.cnt.getOrDefault(w_len-i-1,0)+1);
            }
        }
        
        
        // Query 계산
        int qs_len = queries.length;
        int[] answer = new int[qs_len];
        
        for(int i = 0; i < qs_len; i++) {
            String q = queries[i];
            int q_len = q.length();
            // 변수 초기화
            Node search = null;
            String s_word = "";
            
            // 앞 뒤 모두 ?라면 전체 ?인 쿼리 -> 글자수 패턴만 찾아서 return
            if(q.charAt(0) == '?' && q.charAt(q_len-1) == '?') answer[i] = len_cnt[q_len];
            // 아니라면
            else{
                // 첫글자부터 ?라면 뒤집어서 역방향 탐색
                if(q.charAt(0) == '?') {sb = new StringBuilder(q); search = b_root; s_word = sb.reverse().toString();}
                // 아니라면 앞에서부터 정방향 탐색
                else {search = f_root; s_word = q;}
                
                // search노드로 탐색 시작
                for(int j = 0; j < q_len; j++) {
                    // 현재 글자
                    char cur_char = s_word.charAt(j);
                    // 현재 글자가 ?라면 더이상 탐색하지 않고, 남은 글자수만큼 같은 패턴을 가진 수를 찾아 리턴
                    if(cur_char == '?') {
                        // answer[i] = search.cnt[q_len - j];
                        answer[i] = search.cnt.getOrDefault(q_len - j, 0);
                        break;
                    }
                    // 아니라면 계속 탐색
                    // 현재 글자의 인덱스 계산
                    int idx = cur_char - 'a';
                    // 만약 저장된 패턴이 없다면
                    if(search.child[idx] == null) {
                        // 해당 일치 패턴 없으므로 0 저장 후 탐색 종료
                        answer[i] = 0;
                        break;
                    }
                    // 패턴이 있다면 child로 이동후 재 탐색
                    search = search.child[idx];
                }
            }
        }
        
        return answer;
    }
}