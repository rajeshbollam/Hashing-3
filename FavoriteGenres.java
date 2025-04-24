//The main idea here is to create a song to genre mapping for O(1) lookup of the genre for a song
//Now we iterate through each user and map their songs to the genre in a contMap and we also tke the maximun value of that count map
//When we have the count map. we iterate through the keys and find the maximum count for the genres and add those genres to the result user keu
//Time Complexity: O(m*n) where m is the number of users and n is the number of songs
//Space Complexity: O(n)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution{
    public Map<String, List<String>> favoriteGenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap){
        HashMap <String, List<String>> result = new HashMap<>();
        //first make song to genre map
        HashMap<String, String> songToGenreMap = new HashMap<>();
        for(String genre: genreMap.keySet()){
            List<String> songs = genreMap.get(genre);
            for(String song: songs){
                songToGenreMap.put(song, genre);
            }
        }
        for(String user: userMap.keySet()){
            result.put(user, new ArrayList<>());
            HashMap<String, Integer> countMap = new HashMap<>();
            Integer max = 0;
            List<String> songs = userMap.get(user);
            for(String song: songs){
                String genre = songToGenreMap.get(song);
                countMap.put(genre, countMap.getOrDefault(genre, 0) + 1);
                max = Math.max(max, countMap.get(genre));
            }

            for(String genre: countMap.keySet()){
                if(countMap.get(genre) == max){
                    result.get(user).add(genre);
                }
            }
        }
        return result;
    
    }
}
