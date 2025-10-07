class Solution {
    public int solution(String s) {
        int answer = 0;
        
        int len = s.length();
        String res = "";
        int i;
        for(i = 0; i < len; i ++){
            if(s.charAt(i) == 'z'){
                res += '0';
                i += 3;
            }
            else if(s.charAt(i) == 'o'){
                res += '1';
                i += 2;
            }
            else if(s.charAt(i) == 't'){
                i += 1;
                if(s.charAt(i) == 'w'){
                    res += '2';
                    i += 1;
                }
                else {
                    res += '3';
                    i += 3;
                }
            }
            else if(s.charAt(i) == 'f'){
                i += 1;
                if(s.charAt(i) == 'o'){
                    res += '4';
                }
                else {
                    res += '5';
                }
                i += 2;
            }
            else if(s.charAt(i) == 's'){
                i += 1;
                if(s.charAt(i) == 'i'){
                    res += '6';
                    i += 1;
                }
                else {
                    res += '7';
                    i += 3;
                }
            }
            else if(s.charAt(i) == 'e'){
                res += '8';
                i += 4;
            }
            else if(s.charAt(i) == 'n'){
                res += '9';
                i += 3;
            }
            else {
                res += s.charAt(i);
            }
        }
        
        answer = Integer.parseInt(res);
        return answer;
    }
}