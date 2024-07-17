import java.io.*;

//BOJ1259 - 펠린드롬수
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			
			String num = br.readLine();
			
			// 종료 조건
			if(num.equals("0")) break;
			
			// 입력받은 숫자를 한자리씩 끊어서 배열에 저장
			String[] num_lst = num.split("");
			
			// 펠린드롬수인지 확인하는 flag변수
			boolean flag = true;
			
			// 문자열을 순회하면서 앞과 뒤에서 비교
			// 시작 인덱스 : s / 끝 인덱스 : e
			for(int s = 0, e = num_lst.length-1; s < e; s++, e--) {
				
				//두 문자가 서로 다르다면 no 출력, flag를 false로 한 뒤 break
				if(!num_lst[s].equals(num_lst[e])) {
					System.out.println("no");
					flag = false;
					break;
				}
			}
			
			// for문 종료 후 flag가 그대로 true라면 yes 출력
			if(flag) System.out.println("yes");
		}

	}

}
