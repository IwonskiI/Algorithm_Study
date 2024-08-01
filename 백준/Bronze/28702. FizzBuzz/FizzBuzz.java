import java.io.*;


public class Main {
	
	// BOJ 28702 - FizzBuzz
	public static String fb(int num) {
		if(num % 3 == 0 && num % 5 == 0) return "FizzBuzz";
		else if(num % 3 == 0) return "Fizz";
		else if(num % 5 == 0) return "Buzz";
		else return Integer.toString(num);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String ans = "";
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
