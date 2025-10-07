class Solution {
    
    public static int[][] d = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[] {1, 1, 1, 1, 1};
        
        for(int i = 0; i < 5; i++) {
            String[] room = places[i];
            boolean fin = false;
            for(int r = 0; r < 5; r++) {
                if(fin) break;
                String row = room[r];
                for(int c = 0; c < 5; c++) {
                    if(fin) break;
                    if(row.charAt(c) == 'P'){
                        for(int dd = 0; dd < 4; dd++){
                            int nr = r + d[dd][0], nc = c + d[dd][1];
                            boolean in_range = (0 <= nr && nr < 5) && (0 <= nc && nc < 5);
                            if(in_range) {
                                if(room[nr].charAt(nc) == 'P'){
                                    answer[i] = 0;
                                    fin = true;
                                    break;
                                }
                                if(room[nr].charAt(nc) == 'O') {
                                    int cnt = 0;
                                    for(int ddd = 0; ddd < 4; ddd++){
                                        int nnr = nr + d[ddd][0], nnc = nc + d[ddd][1];
                                        boolean in_range2 = (0 <= nnr && nnr < 5) && (0 <= nnc && nnc < 5);
                                        if(in_range2 && room[nnr].charAt(nnc) == 'P') cnt++;
                                    }
                                    if(cnt > 1) {
                                        fin = true;
                                        answer[i] = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}