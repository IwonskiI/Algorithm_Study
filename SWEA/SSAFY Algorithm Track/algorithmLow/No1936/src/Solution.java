import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if ((a == 1 && b == 2) || (a == 2 && b == 3) || (a == 3 && b == 1))
            System.err.println('B');
        else if ((a == 1 && b == 3) || (a == 2 && b == 1) || (a == 3 && b == 2))
            System.out.println('A');

        sc.close();
    }
}
