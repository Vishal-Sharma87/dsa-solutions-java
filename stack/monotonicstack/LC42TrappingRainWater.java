package stack.monotonicstack;

public class LC42TrappingRainWater {
    private int[] leftMax(int[] h) {
        int[] left = new int[h.length];

        int maxi = -1;

        for (int i = 0; i < h.length; i++) {
            left[i] = -1;

            if (maxi > h[i]) {
                left[i] = maxi;
            }

            maxi = Math.max(maxi, h[i]);
        }
        return left;
    }

    private int[] rightMax(int[] h) {
        int[] right = new int[h.length];

        int maxi = -1;

        for (int i = h.length - 1; i >= 0; i--) {
            right[i] = -1;

            if (maxi > h[i]) {
                right[i] = maxi;
            }

            maxi = Math.max(maxi, h[i]);
        }
        return right;
    }

    public int trap(int[] height) {
        int len = height.length;
        int trappedWater = 0;
        if (len <= 1)
            return trappedWater;

        int[] left = leftMax(height);
        int[] right = rightMax(height);

        for (int i = 0; i < len; i++) {

            if (left[i] == -1 || right[i] == -1)
                continue;

            trappedWater += Math.min(left[i], right[i]) - height[i];
        }

        return trappedWater;

    }
}
