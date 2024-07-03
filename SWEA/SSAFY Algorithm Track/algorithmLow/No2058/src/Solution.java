import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] splt = sc.nextLine().split("");
        int ans = 0;
        for (int i = 0; i < splt.length; i++) {
            ans += Integer.parseInt(splt[i]);
        }
        System.out.println(ans);
        sc.close();
    }
}
