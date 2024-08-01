import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(br.readLine());
		if(n > C*R) sb.append(0);
		else{
		    int x = 1, y = 1;
    		int sx = 2, ex = C, sy = 1, ey = R;
    		
    		char[] dir = {'u', 'r', 'd', 'l'};
    		int dd = 0;
    		for(int i = 2; i <= n; i++){
    		    if(dir[dd] == 'u'){
    		        y++;
    		        if(y == ey){
    		            dd = (dd + 1) % 4;
    		            ey -= 1;
    		        }
    		    }
    		    else if(dir[dd] == 'r'){
    		        x++;
    		        if(x == ex){
    		            dd = (dd + 1) % 4;
    		            ex -= 1;
    		        }
    		    }
    		    else if(dir[dd] == 'd') {
    		        y--;
    		        if(y == sy){
    		            dd = (dd + 1) % 4;
    		            sy += 1;
    		        }
    		    }
    		    else if(dir[dd] == 'l') {
    		        x--;
    		        if(x == sx){
    		            dd = (dd + 1) % 4;
    		            sx += 1;
    		        }
    		    }
    		}
		    sb.append(x).append(" ").append(y);
		}
		System.out.println(sb.toString());
	}

}
