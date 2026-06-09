package GP;

import java.util.*;

public class FractionalKnapsack {
    static double getMaxValue(int[] wt, int[] val, int capacity) {

        int n = wt.length;

        double[][] items = new double[n][2];

        for (int i = 0; i < n; i++) {
            items[i][0] = (double) val[i] / wt[i];
            items[i][1] = i;
        }

        Arrays.sort(items, (a, b) -> Double.compare(b[0], a[0]));

        double totalValue = 0;

        for (int i = 0; i < n; i++) {

            int index = (int) items[i][1];

            if (capacity >= wt[index]) {

                capacity -= wt[index];
                totalValue += val[index];

            } else {

                totalValue += items[i][0] * capacity;
                break;

            }

        }

        return totalValue;
    }
}