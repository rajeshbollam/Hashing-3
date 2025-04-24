//The idea here is to find all the substrings of length 10 using sliding window and add it to allSubs set. As we iterating and we find a same substring,then we add that to result
//We use rolling hash here to not create substrings multiple times
//Time Complexity: O(n) where n is the length of the string
//Space Complexity: O(n)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        if(n < 10) return new ArrayList<>();
        HashSet<Long> allSubs = new HashSet<>();
        HashSet<String> result = new HashSet<>();
        HashMap<Character, Long> map = new HashMap<>();
        map.put('A', 1l);
        map.put('C', 2l);
        map.put('G', 3l);
        map.put('T', 4l);
        long currHash = 0l;
        for(int i = 0; i<10; i++){
            currHash = currHash * 4 + map.get(s.charAt(i));
        }
        allSubs.add(currHash);
        for(int i = 1; i < n-9; i++){
            //out
            currHash = currHash - (long) (map.get(s.charAt(i-1)) * Math.pow(4,9));
            //in
            currHash = currHash * 4 + map.get(s.charAt(i+9));
            if(allSubs.contains(currHash)){
                result.add(s.substring(i, i+10));
            } else {
                allSubs.add(currHash);
            }
        }
        return new ArrayList<>(result);
    }
}