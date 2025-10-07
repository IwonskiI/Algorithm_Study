import java.util.*;

class Data {
    public int data = 0;
    public Data prev = null;
    public Data next = null;
    
    public Data(int data, Data prev, Data next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Data head = new Data(0, null, null);
        Data init = head, cur = head;
        for(int i = 1; i < n; i++){
            init.next = new Data(i, init, null);
            init = init.next;
            if(k == i) cur = init;
        }
        Deque<Data> dq = new ArrayDeque<>();
        for(int i = 0; i < cmd.length; i++) {
            String[] cur_cmd = cmd[i].split(" ");
            int cnt_cmd = 0;
            if(cur_cmd[0].equals("U")){
                cnt_cmd = Integer.parseInt(cur_cmd[1]);
                for(int j = 0; j < cnt_cmd; j++){
                    cur = cur.prev;
                }
            }
            else if(cur_cmd[0].equals("D")){
                cnt_cmd = Integer.parseInt(cur_cmd[1]);
                for(int j = 0; j < cnt_cmd; j++){
                    cur = cur.next;
                }
            }
            else if(cur_cmd[0].equals("C")){
                Data pv = cur.prev;
                Data nx = cur.next;
                dq.offer(cur);
                if(pv != null) pv.next = nx;
                else head = cur.next;
                if(nx != null) nx.prev = pv;
                if(cur.next != null) cur = nx;
                else cur = pv;
            }
            else if(cur_cmd[0].equals("Z")){
                Data tmp = dq.pollLast();
                if(tmp.next != null) tmp.next.prev = tmp;
                if(tmp.prev != null) tmp.prev.next = tmp;
                else head = tmp;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            if(head != null && i == head.data) {
                sb.append("O");
                head = head.next;
            }
            else sb.append("X");
        }
        
        answer = sb.toString();
        
        return answer;
    }
}