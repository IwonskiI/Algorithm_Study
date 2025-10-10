import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        String[] cur_date = today.split("\\.");
        int cur_year = Integer.parseInt(cur_date[0]);
        int cur_month = Integer.parseInt(cur_date[1]);
        int cur_day = Integer.parseInt(cur_date[2]);
        
        ArrayList<Integer> ans_lst = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < terms.length; i++){
            String[] cur_term = terms[i].split(" ");
            map.put(cur_term[0], Integer.parseInt(cur_term[1]));
        }
        
        for(int i = 0; i < privacies.length; i++) {
            String[] cur_privacies = privacies[i].split(" ");
            String[] p_date = cur_privacies[0].split("\\.");
            int p_year = Integer.parseInt(p_date[0]);
            int p_month = Integer.parseInt(p_date[1]);
            int p_day = Integer.parseInt(p_date[2]);
            
            int add_month = map.get(cur_privacies[1]);
            if(add_month >= 12) {
                p_year += (add_month/12);
                p_month += (add_month%12);
                if(p_month > 12) {
                    p_year += 1;
                    p_month %= 12;
                }
            }
            else if(p_month + add_month > 12){
                p_year += 1;
                p_month = (p_month + add_month) % 12;
            }
            else{
                p_month += add_month;
            }
            
            if(cur_year > p_year || (cur_year == p_year && cur_month > p_month) || (cur_year == p_year && cur_month == p_month && cur_day >= p_day)) ans_lst.add(i+1);
            
        }
        
        int[] answer = new int[ans_lst.size()];
        for(int i = 0; i < ans_lst.size(); i++) {
            answer[i] = ans_lst.get(i);
        }
        
        return answer;
    }
}