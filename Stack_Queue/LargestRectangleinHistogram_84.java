import java.util.Stack;

public class LargestRectangleinHistogram_84 {
    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int max = 0;
        // pushing the index 0 onto the stack
        stack.push(0);

        // For each bar at index i, we check if the current bar's height is less than
        // the height of the bar at the index on top of the stack
        for (int i = 1; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                max = getMax(heights, stack, max, i);
            }
            stack.push(i);
        }

        int i = heights.length;
        while (!stack.isEmpty()) {
            max = getMax(heights, stack, max, i);
        }

        return max;

    }

    // i is used for width
    private int getMax(int[] arr, Stack<Integer> stack, int max, int i) {
        int area;
        int pop=stack.pop();
        if (stack.isEmpty()) {
            area=arr[pop]*i;
        }
        else{
            area=arr[pop]*(i-1 -stack.peek());
        }

        return Math.max(max, area);
    }
}

// int elements=heights.length;

// if(elements==0){
// return 0;
// }

// int max=0;

// while (elements!=0) {
// int j=0;
// for (int i = 0; i <=j; i++) {
// if(max<heights[i]){
// max=heights[i];
// }
// if (heights[i]>heights[i-1] && heights[i-1]!=0) {

// }
// }
// }

// return 0;