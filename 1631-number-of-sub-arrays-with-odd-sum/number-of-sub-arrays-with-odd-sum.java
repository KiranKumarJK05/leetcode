class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0, evenCount = 1; // evenCount = 1 to account for prefix sum 0 (which is even)
        long currentSum = 0;
        long totalOddSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;
            
            if (currentSum % 2 == 1) {
                // If current prefix sum is odd, it forms an odd sum subarray 
                // with all previous even prefix sums.
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // If current prefix sum is even, it forms an odd sum subarray 
                // with all previous odd prefix sums.
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}