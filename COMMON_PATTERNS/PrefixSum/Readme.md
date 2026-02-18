Let’s **MASTER the Prefix Sum Pattern** — one of the MOST powerful patterns for subarray problems.

Prefix sum helps solve:

* Subarray sum = K
* Count subarrays
* Longest subarray with conditions
* Range updates
* 2D grids (matrix prefix sum)
* Parity (even/odd counts)
* Finding sums in O(1) after O(n) preprocessing

We will cover all subpatterns:

1. **Basic prefix sum**
2. **HashMap prefix sum**
3. **Parity prefix**
4. **2D prefix sum (matrix)**
5. **Difference array (range update)**

With diagrams + Java code.

---

# ⭐ PREFIX SUM — CORE IDEA

Prefix sum array `prefix[i]` = sum of all elements from index `0` to `i`.

```
prefix[i] = arr[0] + arr[1] + ... + arr[i]
```

Then the sum of any subarray `arr[L..R]` becomes:

```
sum = prefix[R] - prefix[L-1]
```

Time complexity:

* Build prefix: **O(n)**
* Query subarray sum: **O(1)**

---

# ⭐ 1. Basic Prefix Sum

Used for fast range-sum queries.

---

## ✔ Example

Given array and many queries (L, R) → return sum between them.

### Java Implementation

```java
public class PrefixSum {
    int[] prefix;

    public PrefixSum(int[] arr) {
        prefix = new int[arr.length];
        prefix[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }
    }

    public int rangeSum(int left, int right) {
        if (left == 0) return prefix[right];
        return prefix[right] - prefix[left - 1];
    }
}
```

### Example:

```
arr = [2, 4, 1, 3]

prefix = [2, 6, 7, 10]

sum(1,3) = prefix[3] - prefix[0] = 10 - 2 = 8
```

---

# ⭐ 2. HashMap Prefix Sum

This is one of the MOST IMPORTANT patterns in sliding window & subarray problems.

Used for:

* Count subarrays whose sum = K
* Longest subarray with sum K
* Subarray divisible by K
* Detect zero-sum subarray

### 🧠 Key Insight

If:

```
prefix[j] - prefix[i] = k
```

Then:

```
prefix[i] = prefix[j] - k
```

So we store prefix sums in a hashmap.

---

## ✔ Example: Count subarrays with sum = K (LeetCode 560)

### Java Code

```java
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // important for subarrays starting at index 0

    int prefix = 0;
    int count = 0;

    for (int num : nums) {
        prefix += num;

        if (map.containsKey(prefix - k)) {
            count += map.get(prefix - k);
        }

        map.put(prefix, map.getOrDefault(prefix, 0) + 1);
    }

    return count;
}
```

### Why this works?

We look for previous prefix sum that would create sum K.

---

# ⭐ 3. Parity Prefix (Even/Odd sums)

Useful for:

* Count subarrays with even sum
* Count subarrays with odd sum
* Number of subarrays divisible by 2
* Prefix XOR tricks

### Fact:

* even + even = even
* odd + odd = even
* odd + even = odd

We track prefix sum parity.

---

## ✔ Example: Count subarrays with even sum

```java
public int countEvenSumSubarrays(int[] nums) {
    int even = 1; // prefix = 0 is even
    int odd = 0;

    int prefix = 0;
    int count = 0;

    for (int n : nums) {
        prefix += n;

        if (prefix % 2 == 0) {
            count += even;
            even++;
        } else {
            count += odd;
            odd++;
        }
    }
    return count;
}
```

This pattern generalizes to many parity-based problems.

---

# ⭐ 4. 2D Prefix Sum (Matrix Prefix)

Used for:

* Sum of submatrix
* Range sum queries in matrices
* Image processing
* DP in grids

### 💡 Definition

For matrix `mat`:

```
prefix[i][j] = sum of rectangle from (0,0) to (i,j)
```

### Formula:

```
prefix[i][j] = mat[i][j]
             + prefix[i-1][j]
             + prefix[i][j-1]
             - prefix[i-1][j-1]
```

---

## ✔ Java Code – Build 2D prefix sum

```java
public int[][] buildPrefix(int[][] mat) {
    int n = mat.length;
    int m = mat[0].length;

    int[][] prefix = new int[n][m];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            prefix[i][j] = mat[i][j]
                + (i > 0 ? prefix[i-1][j] : 0)
                + (j > 0 ? prefix[i][j-1] : 0)
                - (i > 0 && j > 0 ? prefix[i-1][j-1] : 0);
        }
    }
    return prefix;
}
```

---

## ✔ Query Submatrix Sum

Sum of rectangle from (r1, c1) to (r2, c2):

```
sum = prefix[r2][c2]
    - prefix[r1-1][c2]
    - prefix[r2][c1-1]
    + prefix[r1-1][c1-1]
```

Same logic as 1D but 2D.

---

# ⭐ 5. Difference Array (Range Update Trick)

This is a genius trick used for:

* Applying range updates efficiently
* Range add operations
* Flight bookings problem (LC 1109)

Instead of updating every element in range `L..R`, update only TWO positions:

```
diff[L] += val
diff[R+1] -= val
```

And then take prefix sum to apply all updates in O(n).

---

## ✔ Java Example: Range Add Queries

```
arr size = n
query: add +5 to range [L..R]
```

### Steps:

### Step 1: Build diff array

```java
int[] diff = new int[n+1];

for each query [L, R, val]:
    diff[L] += val;
    diff[R+1] -= val;
```

### Step 2: Reconstruct final array

```java
int running = 0;
int[] result = new int[n];

for (int i = 0; i < n; i++) {
    running += diff[i];
    result[i] = running;
}
```

---

## ✔ Real Example: Flight Bookings (LC 1109)

```java
public int[] corpFlightBookings(int[][] bookings, int n) {
    int[] diff = new int[n + 1];

    for (int[] b : bookings) {
        int l = b[0] - 1;
        int r = b[1] - 1;
        int val = b[2];

        diff[l] += val;
        if (r + 1 < n) diff[r + 1] -= val;
    }

    int[] res = new int[n];
    int running = 0;
    for (int i = 0; i < n; i++) {
        running += diff[i];
        res[i] = running;
    }
    return res;
}
```

---

# ⭐ PREFIX SUM MASTER SUMMARY

| Subpattern       | Use Case                   | Data Structure        |
| ---------------- | -------------------------- | --------------------- |
| Basic prefix     | Fast subarray sum          | prefix array          |
| Hashmap prefix   | Count subarrays with sum K | HashMap<prefix, freq> |
| Parity prefix    | odd/even subarrays         | even/odd counters     |
| 2D prefix        | submatrix sum              | 2D prefix table       |
| Difference array | range updates              | diff array + prefix   |

---

# ⭐ IDENTIFYING PREFIX SUM IN INTERVIEW

When problem mentions:

* “Number of subarrays where sum = K”
* “Count subarrays divisible by K”
* “Longest subarray with sum constraint”
* “Find sum of many ranges quickly”
* “Apply many range updates”
* “Sum inside rectangle”

THINK → **Prefix Sum Pattern**
