import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 18111 - 마인크래프트
	
	// 각 높이를 차지하는 블록의 수를 저장할 map
	static Map<Integer, Integer> hmap;
	static int n, m, b;
	
	// 한 층을 깎아낼 때 드는 시간 계산
	public static int cut(int chigh) {
		return hmap.get(chigh) * 2;
	}
	
	// 한 층을 매꿔 올리는 데 걸리는 시간 계산
	public static int build(int clow) {
		// 매꿀 블록 수보다 더 많은 블록을 갖고 있어야 매꿀 수 있음 
		if(b >= hmap.get(clow)) {
			return hmap.get(clow);
		}
		// 매꿀 수 없다면 cut에서 발생할 수 있는 최대 시간보다 큰 값 return
		else {
			return 9999999;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		hmap = new HashMap<>();
		int high = -1, low = 257, ans = 0;
		
		// 가장 높은 층과 가장 낮은 층을 저장하며 값 저장
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int key = Integer.parseInt(st.nextToken());
				int before = hmap.getOrDefault(key, 0);
				hmap.put(key, before+1);
				high = high > key ? high : key;
				low = low < key ? low : key;
			}
		}
		
		// 이전 층에 남은 개수를 저장하기 위한 변수 temp
		int temp = 0;
		// 낮은 층과 높은층이 같아질때까지 반복
		while(low < high) {
			// 지어 올리는 시간과 깎아 내리는 시간 비교
			// 시간이 똑같이 걸린다면 더 높은 층을 출력해야하므로 등호 포함
			if(build(low) <= cut(high)) {
				// ans(시간)에 지어 올릴 때 걸릴 시간 추가
				ans += build(low);
				// 지어 올린 블록 수 저장
				temp = hmap.get(low);
				// 사용한 블록 수 제거
				b -= temp;
				// 한 층 쌓아올린 뒤 기존 층에 있는 블록 수 + 이번에 쌓아올린 블록 수 저장
				temp += hmap.getOrDefault(++low, 0);
				// 업데이트 된 층 개수 반영
				hmap.put(low, temp);
			}else {
				// 깎아내릴 때도 동일한 방식으로 진행
				ans += cut(high);
				temp = hmap.get(high);
				b += hmap.get(high);
				temp += hmap.getOrDefault(--high, 0);
				hmap.put(high, temp);
			}
		}
		// 시간과 현재 층 출력
		System.out.println(ans + " " +low);
	}
}
