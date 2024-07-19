import java.io.*;


public class Main {
	
	// BOJ 28702 - FizzBuzz
	
	//3의 배수, 5의 배수 판정 함수 
	public static String fb(int num) {
		if(num % 3 == 0 && num % 5 == 0) return "FizzBuzz";
		else if(num % 3 == 0) return "Fizz";
		else if(num % 5 == 0) return "Buzz";
		else return Integer.toString(num);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		// 3의 배수, 5의 배수만 문자열로 나오기 때문에 반드시 3번 안에 숫자가 한번은 나옴
		// 이를 활용해서 마지막에 올 숫자가 뭔지 계산한 뒤, 해당 숫자가 3의 배수, 5의 배수인지 판정
		if(!(str.equals("Fizz")||str.equals("Buzz")||str.equals("FizzBuzz"))) {
			System.out.println(fb(Integer.parseInt(str)+3));
			return;
		}
		str = br.readLine();
		if(!(str.equals("Fizz")||str.equals("Buzz")||str.equals("FizzBuzz"))) {
			System.out.println(fb(Integer.parseInt(str)+2));
			return;
		}
		str = br.readLine();
		if(!(str.equals("Fizz")||str.equals("Buzz")||str.equals("FizzBuzz"))) {
			System.out.println(fb(Integer.parseInt(str)+1));
			return;
		}
	}

}
