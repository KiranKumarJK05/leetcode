
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] cost = new int[n][n];

        // Step 1: Calculate cost for every substring
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {

                int minCost = Integer.MAX_VALUE;

                // Try every valid divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group
                    for (int g = 0; g < d; g++) {

                        int left = i + g;
                        int right = i + len - d + g;

                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][i + len - 1] = minCost;
            }
        }

        // Step 2: DP initialization
        int INF = 1000000000;

        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // Step 3: Divide string into k substrings
        for (int parts = 1; parts <= k; parts++) {

            for (int i = 2 * parts; i <= n; i++) {

                for (int prev = 2 * (parts - 1);
                     prev < i - 1; prev++) {

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        dp[prev][parts - 1] +
                        cost[prev][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
