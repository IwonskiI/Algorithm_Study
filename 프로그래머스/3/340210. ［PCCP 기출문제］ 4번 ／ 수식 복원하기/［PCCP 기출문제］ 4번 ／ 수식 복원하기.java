import java.util.*;

class Solution {
    
    public String[] solution(String[] expressions) {
        
        // pivot => 완성된 식, question => 결과를 구해야하는 식
        ArrayList<String> pivot = new ArrayList<>();
        ArrayList<String> question = new ArrayList<>();
        
        // min_base => 자릿수로 최소 진법 판단
        // base => 실제 확정적으로 계산된 진법
        int min_base = 1, base = -1;
        
        // 자릿수로만 최소 진법 판단
        for(String exp : expressions) {
            // 등호 기준으로 식 분리
            String[] sp = exp.split(" = ");
            // 풀어야하는 문제라면
            if(sp[1].equals("X")) {
                // 문제에 포함된 숫자만 판단
                String piv_t = sp[0];
                // 기호 제거하고 숫자만 판단
                piv_t = piv_t.replace(" + ","");
                piv_t = piv_t.replace(" - ","");
                // 실제 자리수에 최댓값 계산
                for(int i = 0; i < piv_t.length(); i++) {
                    min_base = Math.max(min_base, piv_t.charAt(i) - '0');
                }
                // 자릿수만 판단하고, 수식 그대로 question에 추가
                question.add(sp[0]);
            }
            // 완성된 기준 식이라면
            else {
                // 임시 변수 생성
                String piv_t = exp;
                // 기호 모두 제거
                piv_t = piv_t.replace(" + ","");
                piv_t = piv_t.replace(" - ","");
                piv_t = piv_t.replace(" = ","");
                // 실제 자리수에 최댓값 계산
                for(int i = 0; i < piv_t.length(); i++) {
                    min_base = Math.max(min_base, piv_t.charAt(i) - '0');
                }
                // 기준식은 기존 식 그대로 pivot에 추가
                pivot.add(exp);
            }
        }
        
        // 값을 계산해야하는 수 만큼 배열 생성
        String[] answer = new String[question.size()];
        // 진법이기때문에 숫자 + 1
        min_base += 1;
        
        // 최소 자릿수가 이미 8이었어서 진법이 9로 계산됐다면, 해당 수식은 9진법 확정
        if(min_base == 9) base = 9;
        // 아니라면, 기준식 순회하며 확정 진법을 구할 수 있는 지 판단
        else{
            // 기준식 순회
            for(String piv : pivot) {
                String[] sp1 = piv.split(" = ");
                // delimeter 판단
                String del = "-";
                // 딜리미터 기준 split
                String[] sp2 = sp1[0].split(" - ");
                // '-'가 딜리미터가 아니라서 쪼개지지 않았다면,
                if(sp2.length == 1) {
                    // '+' 딜리미터로 분리 (+는 정규식에 사용되므로 \2개 추가해서 사용)
                    sp2 = sp1[0].split(" \\+ ");
                    // 딜리미터 갱신
                    del = "+";
                }
                // 숫자 분리하여 숫자형으로 계산
                int x = Integer.parseInt(sp2[0]), y = Integer.parseInt(sp2[1]), v = Integer.parseInt(sp1[1]);
                // x,y는 최대 2자리 수이기 때문에 자릿수 분리, v는 최대 3자리 수지만, 3번째자리는 반드시 1(두자리 수 더해서 2 불가능)
                int x1 = x%10, y1 = y%10, x10 = x/10, y10 = y/10, v1 = v%10, v10 = v/10;

                // 덧셈 계산
                if(del.equals("+")) {
                    // 1의 자리 수 더했을 때, 올림이 생긴다면,
                    if(x1 + y1 >= min_base) {
                        // 실제 계산된 값을 보고 얼마나 올림되었는지 확인 (진법 확인 가능)
                        base = x1 + y1 - v1;
                        // 최소 진법도 갱신 (확정)
                        min_base = base;
                        // 확정 진법을 구했으므로 더이상 탐색 X
                        break;
                    }
                    // 1의 자리수에서 올림이 없었지만, 10의 자리수에서 올림이 생겼다면,
                    else if(x10 + y10 >= min_base) {
                        // 실제 계산된 값을 보고 얼마나 올림되었는지 확인 (진법 확인 가능)
                        base = x10 + y10 - (v10%10);
                        // 최소 진법도 갱신 (확정)
                        min_base = base;
                        // 확정 진법을 구했으므로 더이상 탐색 X
                        break;
                    }
                }
                // 뺄셈 계산
                else if(del.equals("-")) {
                    // 1의자리수에서 내림이 필요하다면,
                    if(x1 < y1){
                        // 계산된 1의 자리수가 얼마를 내림했을 때 나오는 값인지 계산
                        base = v1 + y1 - x1;
                        // 최소 진법도 갱신 (확정)
                        min_base = base;
                        // 확정 진법을 구했으므로 더이상 탐색 X
                        break;
                    }
                    // 음수는 나올 수 없으므로, 10의 자리수에서는 내림 불가능
                }
            }
        }

        // answer 배열 인덱스
        int idx = 0;
        // 계산된 진법으로 실제 구해야하는 값 확인
        for(String q : question) {
            // 위와 마찬가지로 계산 식 확인
            String del = "-";
            String[] sp = q.split(" - ");
            if(sp.length == 1) {
                sp = q.split(" \\+ ");
                del = "+";
            }
            // 구해야하는 값 저장할 String변수
            String ans = "";
            // 위와 마찬가지로 자리수 분리
            int x = Integer.parseInt(sp[0]), y = Integer.parseInt(sp[1]);
            int x1 = x % 10, y1 = y % 10, x10 = x / 10, y10 = y / 10;
            // 덧셈 계산
            if(del.equals("+")) {
                // 확정 진법이 안나왔다면,
                if(base == -1) {
                    // 올림이 필요한 경우라면 구할 수 없음 -> ? 저장
                    if(x1 + y1 >= min_base || x10 + y10 >= min_base) ans = "?";
                    // 올림이 필요없다면, 실제 두 수 더해서 값 계산
                    else ans += (x + y);
                }
                // 확정 진법이 나왔다면, 해당 진법으로 계산
                else {
                    // 1의 자리수 먼저 더해서 계산, 올림이 있을 수 있기 때문에 나머지 연산 처리
                    ans = ((x1 + y1) % base) + ans;
                    // 10의 자리수 계산 (앞에서 계산한 부분에서 올림이 있다면 올림 부분 더하기)
                    int v10 = ((x1 + y1) / base) + x10 + y10;
                    // 계산한 10의 자리수가 0이 아니라면 ans에 추가
                    if(v10 % base > 0) ans = (v10%base) + ans;
                    // 10의 자리수가 하나 더 올림이 생긴다면 "1" 추가
                    if(v10 / base > 0) {
                        // 만약 앞에 자리가 0이었다면 0도 추가
                        if(v10 % base == 0) ans = "0" + ans;
                        ans = "1" + ans;
                    }
                }
            }
            // 뺄셈 계산
            else {
                // 진법이 확정이 안난 상태로, 내림 계산이 필요하면 "?" 추가
                if(base == -1 && x1 < y1) ans = "?";
                // 진법이 확정 났다면, 진법으로 계산
                else {
                    // 내림이 필요한 경우라면,
                    if(x1 < y1) {
                        // 한칸 내림했다고 가정해서 ans에 추가
                        ans = (base+x1 - y1) + ans;
                        // 내림했기때문에 1을 빼고 10의 자리 수 계산, 0이 아닌 경우만 ans에 추가
                        if(x10-1-y10 > 0) ans = (x10-1-y10) + ans;
                    }
                    // 내림이 필요없다면 그냥 계산
                    else ans = (x - y) + ans;
                }
            }
            // 완전한 수식으로 answer 배열에 추가
            answer[idx++] = q + " = " + ans;
        }
        
        // answer 반환
        return answer;
    }
}