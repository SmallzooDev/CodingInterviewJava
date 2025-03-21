package problems.leetcode.hIndex;

import java.util.Arrays;
import java.util.Collections;

/**
 * Example 1:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 */
public class Solution {
    public int hIndex(int[] citations) {
        int hIndex = citations.length;
        Integer[] boxedArray = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArray, Collections.reverseOrder());

        boolean isFound = false;
        while (!isFound) {
            int counter = 0;
            for (int i : boxedArray) {
                if (i >= hIndex) {
                    counter++;
                    if (counter >= hIndex) {
                        isFound = true;
                        break;
                    }
                } else {
                    break;
                }
            }

            if (!isFound) {
                hIndex--;
            }
        }

        return hIndex;
    }
    public int hIndex2(int[] citations) {
        Integer[] boxedArray = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArray, Collections.reverseOrder());

        for (int i = 0; i < boxedArray.length; i++) {
            // i는 0부터 시작하므로, (i+1)은 현재까지 확인한 논문 수
            // boxedArray[i]는 현재 논문의 인용 횟수
            if (boxedArray[i] < i + 1) {
                return i; // i개의 논문이 i회 이상 인용됨
            }
        }

        return boxedArray.length;
    }
}
