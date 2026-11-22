import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] closest = new int[26];
        Arrays.fill(closest, -1);

        assignNewParents(0, parent, s, origAdj, closest, newParent);

        List<Integer>[] finalAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            finalAdj[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        computeSubtreeSizes(0, finalAdj, ans);
        return ans;
    }

    private void assignNewParents(
            int u,
            int[] parent,
            String s,
            List<Integer>[] origAdj,
            int[] closest,
            int[] newParent) {
        int c = s.charAt(u) - 'a';
        if (u > 0) {
            newParent[u] = (closest[c] != -1) ? closest[c] : parent[u];
        }

        int prev = closest[c];
        closest[c] = u;

        for (int v : origAdj[u]) {
            assignNewParents(v, parent, s, origAdj, closest, newParent);
        }

        closest[c] = prev;
    }

    private int computeSubtreeSizes(int u, List<Integer>[] finalAdj, int[] ans) {
        int size = 1;
        for (int v : finalAdj[u]) {
            size += computeSubtreeSizes(v, finalAdj, ans);
        }
        ans[u] = size;
        return size;
    }
}