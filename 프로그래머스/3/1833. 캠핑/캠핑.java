import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        // 오름차순 정렬
        Arrays.sort(data, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });
        
        // x축 기준(key) y축 좌표점(val)
        TreeMap<Integer, ArrayList<Integer>> tree = new TreeMap<>();
        // x축 좌표 리스트
        ArrayList<Integer> keyList = new ArrayList<>();
				// 키(x좌표)에 값(y좌표) 배열 삽입
        int key = -1;
        for(int i = 0; i < n; i++){
            int[] cur = data[i];
            if(key != cur[0]) {
		            // key값 갱신
                key = cur[0];
                keyList.add(key);
            }
            ArrayList<Integer> val = tree.getOrDefault(key, new ArrayList<>());
            val.add(cur[1]);
            tree.put(key, val);
        }

				// 각 좌표 탐색
        for(int i = 0; i < n; i++){
            int[] cur = data[i];
            // 현재 좌표 기준 최소/최대 y값 계산
            int minval = Integer.MAX_VALUE, maxval = 0;
            // 다음 x축 좌표 값 탐색 (두번째 점 선택)
            for(int k : keyList) {
		            // 이미 이전 x축 범위이거나 평행(같은 값)이라면 스킵
                if(k <= cur[0]) continue;
                // 더 오른쪽(더 큰) 값의 value
                ArrayList<Integer> val = tree.get(k);
                // 최소값은 역순으로 계산되기때문에 기준 잡고 갱신
                int cur_minval = minval;
                // value 탐색
                for(int v = 0; v < val.size(); v++) {
                    int cur_v = val.get(v);
                    // value(y값)이 현재 좌표와 같거나(평행한 경우)
                    // 이전 x축에서 측정된 최대값보다 작거나
                    // 이전 x축에서 측정된 최소값보다 크다면 
                    // (내부에 이전 x축에서 점 포함하는 경우) 스킵
                    if(cur_v == cur[1] || cur_v < maxval || cur_v > minval) continue;
                    // 최대값 갱신
                    if(maxval < cur_v && cur_v < cur[1]) maxval = cur_v;
                    // 최소값 갱신
                    // - 기준 최소값을 바로 갱신해버리면 data의 y값도 오름차순 정렬했기 때문에,
                    //   더 탐색할 수 없음. 같은 축을 탐색할때는 기존 최소값으로 탐색 후, 마지막에 갱신
                    else if(cur[1] < cur_v && cur_v < cur_minval) cur_minval = cur_v;
                    // 조건을 만족하는 케이스이므로 answer++
                    answer++;
                }
                // 최소값 갱신 후 다음 x좌표(key) 탐색
                minval = cur_minval;
            }
        }
        return answer;
        
        // 사각형이 안 만들어지는 경우 - X나 Y좌표가 동일
        // 다른 쐐기가 포함되는 경우 - 두 X 좌표 사이와 두 Y좌표 사이에 새로운 X,Y가 존재
    }
}