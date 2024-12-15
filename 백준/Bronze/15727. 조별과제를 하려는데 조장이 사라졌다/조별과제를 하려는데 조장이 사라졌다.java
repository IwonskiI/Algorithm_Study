import java.io.*;


public class Main {
    
    // BOJ 15727 - 조별과제를 하려는데 조장이 사라졌다
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        // 최대 거리 5로 쫓아감
        int ans = N / 5;
        // 남은 거리가 있는지 확인
        int rem = N % 5;
        // 남은 거리가 있으면 1초 더 소모
        if(rem > 0){
            ans += 1;
        }
        // 최종 걸린 시간 출력
        System.out.println(ans);
    }
}