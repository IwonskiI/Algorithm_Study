import java.io.*;
import java.util.*;

public class Main {
    
	// BOJ 1463 - 1로 만들기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력값 1~10^6를 인덱스로 하고, 해당 인덱스의 숫자를 만드는 데까지 걸린 연산 수를 값으로 가지는 배열 선언 
        int[] lst = new int[1000001];
        // 초기 설정은 모두 INF로 초기화
        Arrays.fill(lst, 0x7fffffff);
        
        // 1은 연산하지 않아도 1이므로 0으로 초기화
        lst[1] = 0;
        
        // 1부터 시작해서 10^6까지 역으로 계산하며 배열 채워넣기
        for(int idx = 1; idx < 1000001; idx++) {
        	// 현재 idx를 3배한 것이 10^6보다 작고, 해당 값이 현재 값+1보다 커서 갱신이 필요하다면 갱신
        	if(idx * 3 < 1000001 && lst[idx*3] > lst[idx]+1) lst[idx*3] = lst[idx]+1;
        	// 현재 idx를 2배한 것이 10^6보다 작고, 해당 값이 현재 값+1보다 커서 갱신이 필요하다면 갱신
        	if(idx * 2 < 1000001 && lst[idx*2] > lst[idx]+1) lst[idx*2] = lst[idx]+1;
        	// 현재 idx에서 1 더한 것이 10^6보다 작고, 해당 값이 현재 값+1보다 커서 갱신이 필요하다면 갱신
        	if(idx + 1 < 1000001 && lst[idx+1] > lst[idx]+1) lst[idx+1] = lst[idx]+1;
        }
        
        // 원하는 값 검색
        int n = Integer.parseInt(br.readLine());
        System.out.println(lst[n]);
    }
}