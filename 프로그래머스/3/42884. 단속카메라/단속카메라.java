import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);
        int min_r = routes[0][0], max_r = routes[0][1];
        for(int i = 1; i < routes.length; i++){
            if(max_r < routes[i][0]){
                min_r = routes[i][0];
                max_r = routes[i][1];
                answer++;
            }
            else {
                min_r = routes[i][0];
                if(routes[i][1] < max_r) max_r = routes[i][1];
            }       
        }
//         for(int[] arr : routes){
//             System.out.println(Arrays.toString(arr));
//         }
        
        return answer;
    }
}