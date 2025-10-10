class Solution {
    
    public boolean check(String str, int len) {
        int mid = len / 2;
        if(mid == 0) return true;
        if(str.charAt(mid) == '0' && str.contains("1")) return false;
        String l_str = str.substring(0,mid), r_str = str.substring(mid+1);
        return check(l_str, mid) & check(r_str, mid);
    }
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            String bin = Long.toBinaryString(numbers[i]);
            int N = 1;
            while(bin.length() >= N) N*=2;
            StringBuilder sb = new StringBuilder();
            for(int n = bin.length(); n < N-1; n++) sb.append("0");
            sb.append(bin);
            bin = sb.toString();
            boolean success = check(bin, bin.length());
            if(bin.equals("0")) success = false;
            if(success) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
}