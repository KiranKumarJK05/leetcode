
import java.util.*;

class Solution {

    static class Group {
        long value;
        int count;

        Group(long value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        Deque<Group> dq = new ArrayDeque<>();

        long cost = 0;
        long answer = 0;

        int right = nums.length - 1;

        for (int left = nums.length - 1; left >= 0; left--) {

            long num = nums[left];
            int count = 1;

            // Merge smaller values into the current value
            while (!dq.isEmpty() &&
                   dq.peekLast().value < num) {

                Group group = dq.pollLast();

                cost += (num - group.value) * group.count;

                count += group.count;
            }

            dq.offerLast(new Group(num, count));

            // Remove elements until cost becomes valid
            while (cost > k) {

                Group first = dq.pollFirst();

                cost -= first.value - nums[right];

                right--;

                if (first.count > 1) {
                    dq.offerFirst(
                        new Group(first.value, first.count - 1)
                    );
                }
            }

            // Count valid subarrays
            answer += right - left + 1;
        }

        return answer;
    }
}
