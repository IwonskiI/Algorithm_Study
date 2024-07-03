import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();
        String[] num_slst = sc.nextLine().split(" ");
        List<Integer> num_lst = new ArrayList<>();
        for (String Num : num_slst) {
            int num = Integer.parseInt(Num);
            num_lst.add(num);
        }
        Collections.sort(num_lst);
        System.out.println(num_lst.get(N / 2));

        sc.close();
    }
}
