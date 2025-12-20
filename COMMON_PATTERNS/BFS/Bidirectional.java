package COMMON_PATTERNS.BFS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bidirectional {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            // Always expand the smaller frontier
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> next = new HashSet<>();

            for (String word : beginSet) {
                char[] arr = word.toCharArray();

                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old)
                            continue;

                        arr[i] = c;
                        String newWord = new String(arr);

                        // If frontiers meet
                        if (endSet.contains(newWord)) {
                            return level + 1;
                        }

                        if (!visited.contains(newWord) && dict.contains(newWord)) {
                            visited.add(newWord);
                            next.add(newWord);
                        }
                    }

                    arr[i] = old; // restore
                }
            }

            beginSet = next;
            level++;
        }

        return 0;
    }
}