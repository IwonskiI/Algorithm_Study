public class Main {
	
	// BOJ 9654 - 나부 함대 데이터
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SHIP NAME      CLASS          DEPLOYMENT IN SERVICE\n");
		sb.append("N2 Bomber      Heavy Fighter  Limited    21        \n");
		sb.append("J-Type 327     Light Combat   Unlimited  1         \n");
		sb.append("NX Cruiser     Medium Fighter Limited    18        \n");
		sb.append("N1 Starfighter Medium Fighter Unlimited  25        \n");
		sb.append("Royal Cruiser  Light Combat   Limited    4         ");
		
		System.out.println(sb.toString());
	}

}