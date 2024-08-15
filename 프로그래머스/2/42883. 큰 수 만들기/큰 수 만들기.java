class Solution {
    // Programmers - 큰 수 만들기
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
		
        // 입력받은 숫자를 리스트로 변환
		char[] array = number.toCharArray();
        // 만들어야할 숫자의 길이
		int n = array.length - k;
		// 각 자리에 올 수 있는 최대 숫자를 찾을 시작 인덱스
		int start = 0;
		
        // n개의 숫자를 뽑을 때까지 반복
		for(int i = 0; i < n; i++) {
            // 최대 값을 저장할 변수 max
			char max = '0';
            // 시작 지점부터 버릴 수 있는 위치까지 탐색
			for(int j = start; j <= i + k; j++) {
                // 최대값 갱신
				if(array[j] > max) {
					max = array[j];
                    // 시작 지점을 현재 다음위치로 갱신
					start = j + 1;
				}
			}
			sb.append(Character.toString(max));
		}
		answer = sb.toString();
        
        return answer;
    }
}