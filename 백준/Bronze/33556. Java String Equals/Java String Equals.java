import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String a = br.readLine();
        String b = br.readLine();
        
        if(a.equals("null")){
            sb.append("NullPointerException\nNullPointerException");
        }
        
        else {
            if(a.equals(b)) sb.append("true");
            else sb.append("false");
            sb.append("\n");
            if(a.equalsIgnoreCase(b) && !b.equals("null")) sb.append("true");
            else sb.append("false");
        }
        
        System.out.println(sb.toString());
        
    }
}