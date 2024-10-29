import java.io.*;
import java.util.*;

public class Main {
	
	// BOJ 17081 - RPG Extreme
	// 보드의 크기 N, M
	public static int N, M;
	// 초기 정보
	public static int hp = 20, max_hp = 20, atk = 2, def = 2, lv = 1, exp = 0, weapon = 0, armor = 0, turn = 0;
	// 결과 문자열
	public static String res = "";
	// 보드판
	public static char[][] board;
	// 장신구 집합
	public static HashSet<String> acc = new HashSet<>();
	// 이동 매핑
	public static HashMap<Character, int[]> move = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		
		// 이동 좌표 매핑
		move.put('L', new int[] {0, -1});
		move.put('R', new int[] {0, 1});
		move.put('U', new int[] {-1, 0});
		move.put('D', new int[] {1, 0});
		
		// 초기 좌표 sr, sc
		int sr = 0, sc = 0;
		// 몬스터 수, 아이템 상자 수
		int mon_cnt = 0, box_cnt = 0;
		
		// 보드 입력
		for(int r = 0; r < N; r++) {
			String str = br.readLine();
			for(int c = 0; c < M; c++) {
				board[r][c] = str.charAt(c);
				// 플레이어 첫 위치 저장
				if(board[r][c] == '@') {
					sr = r;
					sc = c;
					// 빈칸 처리
					board[r][c] = '.';
				}
				// 몬스터 수 카운트
				else if(board[r][c] == '&' || board[r][c] == 'M') {
					mon_cnt++;
				}
				// 아이템 박스 수 카운트
				else if(board[r][c] == 'B') {
					box_cnt++;
				}
			}
		}
		
		// 이동 커맨드
		String command = br.readLine();
		
		// 몬스터 좌표 - 이름 매핑
		HashMap<String, String> mon_pos = new HashMap<>();
		// 몬스터 이름 - 정보 매핑
		HashMap<String, int[]> monster = new HashMap<>();
		// 아이템 상자 좌표 - 정보 매핑
		HashMap<String, String[]> box = new HashMap<>();
		
		// 몬스터 정보 입력
		for(int i = 0; i < mon_cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken()) - 1;
			String S = st.nextToken();
			int W = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			monster.put(S, new int[] {W, A, H, E});
			mon_pos.put(R+" "+C, S);
		}
		
		// 아이템 정보 입력
		for(int i = 0; i < box_cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken()) - 1;
			String T = st.nextToken();
			String eff = st.nextToken();
			box.put(R+" "+C, new String[] {T, eff});
		}
		
		// 현재 위치 세팅
		int cr = sr, cc = sc;
		// 게임 종료 확인
		boolean flag = false;
		// 죽은 이유 저장
		String reason = "";
		for(int i = 0; i < command.length(); i++) {
			// 현재 몬스터가 보스몬스터인지 표시
			boolean is_boss = false;
			// 현재 박스
			String[] cur_box;
			// 현재 몬스터
			int[] cur_mon;
			// 턴 수 기록
			turn++;
			// 이동 좌표 계산
			int[] c_move = move.get(command.charAt(i));
			cr += c_move[0];
			cc += c_move[1];
			// 범위 체크
			boolean in_range = (0 <= cr && cr < N) && (0 <= cc && cc < M);
			// 범위를 벗어난다면 원래 좌표로 복원
			if(!in_range) {
				cr -= c_move[0];
				cc -= c_move[1];
			}
			
			// 이동 후 좌표 키 설정
			String key = cr + " " + cc;
			// 이동한 좌표 체크
			switch(board[cr][cc]) {
			// 일반 길이라면 그냥 이동 후 종료
			case '.':
				break;
			// 벽이라면 원래 위치로 복원
			case '#':
				cr -= c_move[0];
				cc -= c_move[1];
				// 복원한 자리가 가시 함정이라면
				if(board[cr][cc] == '^') {
					// DX 장신구가 있다면 1만 감소
					if(acc.contains("DX")) hp -= 1;
					// 아니라면 피 5 감소
					else hp -= 5;
					// hp가 0 이하로 떨어졌다면 사망 원인 표시
					if(hp <= 0) {
						reason = "SPIKE TRAP";
					}
				}
				break;
			// 아이템 박스라면
			case 'B':
				// 아이템 정보 가져오기
				cur_box = box.get(key);
				// 아이템 종류 확인
				switch(cur_box[0]) {
				// 무기라면
				case "W":
					// 무기 정보 갱신
					weapon = Integer.parseInt(cur_box[1]);
					break;
				// 방어구라면
				case "A":
					// 방어구 정보 갱신
					armor = Integer.parseInt(cur_box[1]);
					break;
				// 장신구라면
				case "O":
					// 장신구의 개수가 4개보다 적고, 이미 갖고 있는 장신구가 아니라면
					if(acc.size() < 4 && !acc.contains(cur_box[1]))
						// 장신구 추가
						acc.add(cur_box[1]);
					break;
				}
				// 아이템 획득 이후 빈칸 처리
				board[cr][cc] = '.';
				break;
			// 가시 함정이라면
			case '^':
				// DX장신구가 있다면 피 1만 감소
				if(acc.contains("DX")) hp -= 1;
				// 없다면 5 감소
				else hp -= 5;
				// hp가 0 이하라면
				if(hp <= 0) {
					// 사망 원인 가시함정으로 저장
					reason = "SPIKE TRAP";
				}
				break;
			// 보스 몬스터라면
			case 'M':
				// 보스 몬스터 체크 후 일반 몬스터랑 같은 프로세스 진행
				is_boss = true;
				// HU 장신구가 있다면 최대 체력으로 회복
				if(acc.contains("HU")) hp = max_hp;
			// 일반 몬스터라면
			case '&':
				// 현재 좌표의 몬스터 이름 가져오기
				String mon_name = mon_pos.get(key);
				// 몬스터 이름으로 몬스터 정보 가져오기
				cur_mon = monster.get(mon_name);
				// hp, 공격력, 방어력, 경험치 저장
				int mon_hp = cur_mon[2], mon_atk = cur_mon[0], mon_def = cur_mon[1], mon_exp = cur_mon[3];
				// 공격 턴수 체크
				int turn_cnt = 0;
				// 플레이어와 몬스터 둘 중 하나가 0 이하가 될때까지 반복
				while(true) {
					// 첫 번째 턴에 CO 장신구를 가지고 있다면
					if(turn_cnt == 0 && acc.contains("CO")) {
						// 추가로 DX 장신구를 가지고 있다면 공격력의 3배 - 방어력 만큼 공격
						if(acc.contains("DX")) mon_hp -= Math.max(1, (atk+weapon)*3 - mon_def);
						// DX 장신구는 없다면 공격력의 2배 - 방어력 만큼 공격
						else mon_hp -= Math.max(1, (atk+weapon)*2 - mon_def);
					}
					// CO 장신구가 없다면 공격력 - 방어력 만큼 공격
					else mon_hp -= Math.max(1, (atk+weapon) - mon_def);
					// 공격 후, 몬스터가 죽으면 전투 종료
					if(mon_hp <= 0) break;
					// 몬스터가 죽지 않았다면 몬스터 공격 진행
					int damage = 0;
					// 첫 번째 공격이고, 보스전이면서, HU 장신구가 있다면 데미지를 입지 않음
					if(turn_cnt == 0 && is_boss && acc.contains("HU"))
						damage = 0;
					// 아니라면 공격력 - 방어력 만큼 데미지를 입음
					else damage = Math.max(1, mon_atk - (def+armor));
					// 계산한 데미지만큼 피해 입음
					hp -= damage;
					// 피해를 입은 뒤 hp가 0 이하가 되었다면 종료
					if(hp <= 0) break;
					// 종료가 아니라면 다음 턴 진행
					turn_cnt++;
				}
				// 전투 종료 후 몬스터가 죽었다면
				if(mon_hp <= 0) {
					// 해당 공간 .으로 저장
					board[cr][cc] = '.';
					// EX 장신구가 있다면 경험치 1.2배 증가
					if(acc.contains("EX")) exp += mon_exp*1.2;
					// 없다면 그냥 증가
					else exp += mon_exp;
					// 경험치 증가 후 레벨업 조건에 도달했다면
					if(exp >= lv*5) {
						// 레벨 업
						lv++;
						// 경험치 0으로 초기화
						exp = 0;
						// 최대 체력 증가
						max_hp += 5;
						// 현재 체력 최대로 회복
						hp = max_hp;
						// 공격력 2 증가
						atk += 2;
						// 방어력 2 증가
						def += 2;
					}
					// HR 장신구가 있다면 
					if(acc.contains("HR")) {
						// 3만큼 회복 후 최대 체력을 넘었다면 최대체력으로 저장
						hp = Math.min(hp+3, max_hp);
					}
					// 죽인 몬스터가 보스 몬스터라면
					if(is_boss) {
						// 승리 플래그 저장
						flag = true;
					}
				}
				// 몬스터가 죽지 않고 플레이어가 죽었다면
				else {
					// 플레이어를 죽인 몬스터 이름 저장
					reason = mon_name;
				}
				break;
			}
			// 칸에서 모든 프로세스 완료 후, 보스 몬스터를 잡은 상태라면
			if(flag) {
				// 승리 결과 저장
				res = "YOU WIN!";
				// 현재 위치에 플레이어 표시
				board[cr][cc] = '@';
				// 게임 종료
				break;
			}
			// 플레이어 hp가 0 이하라면
			else if(hp <= 0) {
				// 부활 장신구가 있다면
				if(acc.contains("RE")) {
					// 부활 장신구 제거
					acc.remove("RE");
					// 최대 hp로 회복
					hp = max_hp;
					// 초기 위치로 현재 위치 초기화 후 계속 진행
					cr = sr;
					cc = sc;
				}
				// 장신구가 없다면
				else {
					// 죽인 원인에게 죽음 저장
					res = "YOU HAVE BEEN KILLED BY " + reason + "..";
					// hp는 0으로 저장
					hp = 0;
					// 게임 종료
					break;
				}
			}
		}
		// 모든 커멘드 입력 후 게임 결과가 없다면
		if(res.equals("")) {
			// 현재 플레이어 위치 표시
			board[cr][cc] = '@';
			// 진행중 결과 저장
			res = "Press any key to continue.";
		}
		
		// 최종 보드 상태 저장
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sb.append(board[r][c]);
			}
			sb.append("\n");
		}
		// 결과 정보 저장
		sb.append("Passed Turns : ").append(turn).append("\n");
		sb.append("LV : ").append(lv).append("\n");
		sb.append("HP : ").append(hp).append("/").append(max_hp).append("\n");
		sb.append("ATT : ").append(atk).append("+").append(weapon).append("\n");
		sb.append("DEF : ").append(def).append("+").append(armor).append("\n");
		sb.append("EXP : ").append(exp).append("/").append(lv*5).append("\n");
		sb.append(res).append("\n");
		
		
		// 최종 결과 출력
		System.out.println(sb.toString());
	}
}