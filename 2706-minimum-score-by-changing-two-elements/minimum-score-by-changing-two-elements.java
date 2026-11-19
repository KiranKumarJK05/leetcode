import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int changeSmallestTwo = nums[n - 1] - nums[2];
        int changeLargestTwo = nums[n - 3] - nums[0];
        int changeBothEnds = nums[n - 2] - nums[1];

        return Math.min(changeSmallestTwo, Math.min(changeLargestTwo, changeBothEnds));
    }
}