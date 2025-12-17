# Prefix sum helps solve:

- Subarray sum = K

- Count subarrays

- Longest subarray with conditions

- Range updates

- 2D grids (matrix prefix sum)

- Parity (even/odd counts)

- Finding sums in O(1) after O(n) preprocessing

# PREFIX SUM — CORE IDEA

Prefix sum array ```prefix[i] = sum``` of all elements from index ```0``` to ```i```.
```
prefix[i] = arr[0] + arr[1] + ... + arr[i]
```

Then the sum of any subarray ```arr[L..R]``` becomes:
```
sum = prefix[R] - prefix[L-1]
```

Time complexity:

Build prefix: O(n)

Query subarray sum: O(1)

# 1. Basic Prefix Sum

Used for fast range-sum queries.

# 2. HashMap Prefix Sum

This is one of the MOST IMPORTANT patterns in sliding window & subarray problems.

Used for:

- Count subarrays whose ```sum = K```

- Longest subarray with sum K

- Subarray divisible by K

- Detect zero-sum subarray

Key Insight

If:
```
prefix[j] - prefix[i] = k
```

Then:
```
prefix[i] = prefix[j] - k
```

So we store prefix sums in a hashmap.

# 3. Parity Prefix (Even/Odd sums)

Useful for:

- Count subarrays with even sum

- Count subarrays with odd sum

- Number of subarrays divisible by 2

- Prefix XOR tricks

Fact:

- even + even = even

- odd + odd = even

- odd + even = odd

We track prefix sum parity.

# 5. Difference Array (Range Update Trick)

This is a genius trick used for:

Applying range updates efficiently

Range add operations

Flight bookings problem (LC 1109)

Instead of updating every element in range L..R, update only TWO positions:
```
diff[L] += val
diff[R+1] -= val
```

And then take prefix sum to apply all updates in O(n).

✔ Java Example: Range Add Queries
```arr size = n```

query: ```add +5 to range [L..R]```

Steps:

Step 1: Build diff array
```
int[] diff = new int[n+1];

for each query [L, R, val]:
    diff[L] += val;
    diff[R+1] -= val;
```

Step 2: Reconstruct final array
```
int running = 0;
int[] result = new int[n];

for (int i = 0; i < n; i++) {
    running += diff[i];
    result[i] = running;
}
```

---

| Subpattern       | Use Case                   | Data Structure        |
| ---------------- | -------------------------- | --------------------- |
| Basic prefix     | Fast subarray sum          | prefix array          |
| Hashmap prefix   | Count subarrays with sum K | HashMap<prefix, freq> |
| Parity prefix    | odd/even subarrays         | even/odd counters     |
| 2D prefix        | submatrix sum              | 2D prefix table       |
| Difference array | range updates              | diff array + prefix   |
