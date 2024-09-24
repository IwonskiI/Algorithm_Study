import java.util.*;
import java.io.*;

public class Main {
    
    // BOJ 7785 - 회사에 있는 사람
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        // 회사 출입 기록을 관리할 Set
        HashSet<String> set = new HashSet<>();
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String status = st.nextToken();
            // enter하면 set에 추가
            if(status.equals("enter")){
                set.add(name);
            }
            // leave라면 set에서 제거
            else{
                set.remove(name);
            }
        }
        
        // 사전 역순으로 정렬하기 위한 List
        List<String> comp = new ArrayList<>();
        for(String c_name : set) {
            comp.add(c_name);
        }
        
        // 사전 역순 정렬
        Collections.sort(comp,(a,b)-> b.compareTo(a));
        
        // 결과 저장
        for(String c_name : comp){
            sb.append(c_name).append("\n");
        }
        
        // 전체 결과 출력
        System.out.println(sb.toString());
    }
    
}