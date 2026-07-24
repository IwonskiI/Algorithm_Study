import java.util.*;

class Genre {
    String genre;
    int total_plays;
    PriorityQueue<int[]> pq;
    
    Genre(String g, int tp){
        this.genre = g;
        this.total_plays = tp;
        this.pq = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            else return b[1] - a[1];
        });
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        int genre_cnt = 0, song_cnt = genres.length;
        
        HashMap<String, Genre> genre_map = new HashMap<>();
        
        for(int i = 0; i < song_cnt; i++) {
            String cur_genre = genres[i];
            int cur_plays = plays[i];
            Genre prev_genre = null;
            if(genre_map.containsKey(cur_genre)) {
                prev_genre = genre_map.get(cur_genre);
                prev_genre.total_plays += cur_plays;
            }
            else {
                prev_genre = new Genre(cur_genre, cur_plays);
                genre_cnt++;
            }
            prev_genre.pq.offer(new int[] {i, cur_plays});
            genre_map.put(cur_genre, prev_genre);
        }
        
        PriorityQueue<Genre> g_pq = new PriorityQueue<>((a, b) -> {
            return b.total_plays - a.total_plays;
        });
            
        for(String key : genre_map.keySet()) {
            Genre cur_g = genre_map.get(key);
            g_pq.offer(cur_g);
        }
        
        
        ArrayList<Integer> answer_lst = new ArrayList<>();
        while(!g_pq.isEmpty()) {
            Genre cur_g = g_pq.poll();
            answer_lst.add(cur_g.pq.poll()[0]);
            if(!cur_g.pq.isEmpty()) answer_lst.add(cur_g.pq.poll()[0]);
        }
        
        int[] answer = new int[answer_lst.size()];
        for(int i = 0; i < answer_lst.size(); i++){
            answer[i] = answer_lst.get(i);
        }
        
        return answer;
    }
}