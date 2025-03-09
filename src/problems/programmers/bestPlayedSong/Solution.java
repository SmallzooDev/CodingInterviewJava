package problems.programmers.bestPlayedSong;

import java.util.*;

public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genreMap = new HashMap<>();
        HashMap<String, PriorityQueue<Map.Entry<Integer, Integer>>> gatheringMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];

            genreMap.put(genre, genreMap.getOrDefault(genre, 0) + playCount);
            gatheringMap.putIfAbsent(genre, new PriorityQueue<>(
                    (a, b) -> {
                        if (b.getValue().equals(a.getValue())) {
                            return a.getKey() - b.getKey();
                        }
                        return b.getValue() - a.getValue();
                    }
            ));
            gatheringMap.get(genre).offer(new AbstractMap.SimpleEntry<>(i, playCount));
        }

        List<String> sortedGenres = new ArrayList<>(genreMap.keySet());
        sortedGenres.sort((a, b) -> genreMap.get(b) - genreMap.get(a));

        List<Integer> bestAlbum = new ArrayList<>();

        for (String genre : sortedGenres) {
            PriorityQueue<Map.Entry<Integer, Integer>> pq = gatheringMap.get(genre);
            int count = 0;
            while (!pq.isEmpty() && count < 2) {
                bestAlbum.add(pq.poll().getKey());
                count++;
            }
        }

        return bestAlbum.stream().mapToInt(i -> i).toArray();
    }
}
