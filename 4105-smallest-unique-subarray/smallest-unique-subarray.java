import java.util.*;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 1315423911L;
    private static final long BASE2 = 2654435761L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        p1[0] = 1;
        p2[0] = 1;

        for (int i = 0; i < n; i++) {
            long val = nums[i] + 1L;
            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;
            h1[i + 1] = (h1[i] * BASE1 + val) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + val) % MOD2;
        }

        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (hasUniqueSubarray(mid, n, p1, p2, h1, h2)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueSubarray(
            int len, int n,
            long[] p1, long[] p2,
            long[] h1, long[] h2
    ) {
        long mul1 = p1[len];
        long mul2 = p2[len];
        Map<Long, Integer> freq = new HashMap<>(n - len + 1);

        for (int l = 0; l + len <= n; l++) {
            int r = l + len;
            long sub1 = (h1[r] - (h1[l] * mul1) % MOD1 + MOD1) % MOD1;
            long sub2 = (h2[r] - (h2[l] * mul2) % MOD2 + MOD2) % MOD2;
            long key = (sub1 << 32) ^ sub2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}