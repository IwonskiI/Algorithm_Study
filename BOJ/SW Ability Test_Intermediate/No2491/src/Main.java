import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		br.readLine();
		st = new StringTokenizer(br.readLine());
		int cur = Integer.parseInt(st.nextToken()), prev;
		int asc = 1, dsc = 1, m_asc = 1, m_dsc = 1;
		while(st.hasMoreTokens()) {
			prev = cur;
			cur = Integer.parseInt(st.nextToken());
			if (prev <= cur) {
				asc += 1;
				if (m_asc < asc) m_asc = asc;
			}
			else asc = 1;
			if (cur <= prev) {
				dsc += 1;
				if(m_dsc < dsc) m_dsc = dsc;
			}
			else dsc = 1;
		}
		
		int ans = m_asc > m_dsc ? m_asc : m_dsc;
		System.out.println(ans);

	}

}
