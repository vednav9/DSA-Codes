This is the **last foundational pillar** of DSA: **Math / Number Theory**.
These concepts often turn *hard-looking problems into easy ones* if you recognize the pattern.

---

# PART 0 — BIG PICTURE

Number theory problems usually involve:

* divisibility
* primes
* large numbers
* modular constraints
* combinations

---

# 1️⃣ SIEVE OF ERATOSTHENES (PRIME NUMBERS)

## 🎯 Problem

Find all prime numbers up to `n`.

---

## CORE IDEA

Instead of checking each number individually:

> Remove multiples of primes.

---

## 🪜 Steps

1. Assume all numbers are prime
2. Start from 2
3. Mark multiples as non-prime

---

## Example

```id="1ybt39"
n = 10

Start:
2 3 4 5 6 7 8 9 10

Remove multiples:
2 → remove 4,6,8,10
3 → remove 6,9
```

Result:

```id="gls7lw"
2,3,5,7
```

---

## ✅ Code

```java
public List<Integer> sieve(int n) {
    boolean[] prime = new boolean[n + 1];
    Arrays.fill(prime, true);

    prime[0] = prime[1] = false;

    for (int i = 2; i * i <= n; i++) {
        if (prime[i]) {
            for (int j = i * i; j <= n; j += i) {
                prime[j] = false;
            }
        }
    }

    List<Integer> res = new ArrayList<>();
    for (int i = 2; i <= n; i++) {
        if (prime[i]) res.add(i);
    }
    return res;
}
```

---

## TIME COMPLEXITY

```id="zw7ehm"
O(n log log n)
```

---

# 2️⃣ EUCLIDEAN GCD

## 🎯 Problem

Find GCD of two numbers.

---

## KEY INSIGHT

```id="t0ttfg"
gcd(a, b) = gcd(b, a % b)
```

---

## Example

```id="1xfz83"
gcd(48, 18)
= gcd(18, 12)
= gcd(12, 6)
= gcd(6, 0)
= 6
```

---

## ✅ Code

```java
public int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
}
```

---

## WHY IT WORKS

Remainder keeps reducing → reaches 0 → done.

---

# 3️⃣ FAST EXPONENTIATION (POWER)

## 🎯 Problem

Compute:

```id="56ckqn"
a^b
```

---

## ❌ Brute Force

```id="khnt4z"
O(b)
```

---

## KEY IDEA

```id="2kr7cu"
a^b = (a^(b/2))^2
```

---

## Cases

* b even:

```id="hh5r04"
a^b = (a^(b/2))²
```

* b odd:

```id="55ud84"
a^b = a × (a^(b/2))²
```

---

## ✅ Code

```java
public long power(long a, long b) {
    if (b == 0) return 1;

    long half = power(a, b / 2);

    if (b % 2 == 0)
        return half * half;
    else
        return a * half * half;
}
```

---

## TIME

```id="k4izn8"
O(log b)
```

---

# 4️⃣ MODULAR ARITHMETIC

## 🎯 Why needed?

Numbers get **very large**.

---

## Rules

```id="c8dxi5"
(a + b) % m = (a % m + b % m) % m
(a * b) % m = (a % m * b % m) % m
```

---

## Important

```id="6oz3ol"
(a / b) % m ≠ (a % m / b % m)
```

Instead use:

```id="tvh1q3"
modular inverse
```

---

# 5️⃣ MODULAR EXPONENTIATION

## 🎯 Compute

```id="yzk6d5"
(a^b) % mod
```

---

## ✅ Code

```java
public long modPow(long a, long b, int mod) {
    long res = 1;
    a %= mod;

    while (b > 0) {
        if ((b & 1) == 1)
            res = (res * a) % mod;

        a = (a * a) % mod;
        b >>= 1;
    }
    return res;
}
```

---

# 6️⃣ COMBINATORICS (nCr)

## 🎯 Problem

```id="r9j2hl"
nCr = n! / (r! (n-r)!)
```

---

## Issue

Factorials get large → use modulo

---

## Approach

Precompute factorials:

```java
long[] fact = new long[n+1];

fact[0] = 1;
for(int i=1;i<=n;i++)
    fact[i] = (fact[i-1]*i)%mod;
```

---

## nCr using mod inverse

```java
long nCr(int n, int r){
    return fact[n] *
           modInverse(fact[r]) % mod *
           modInverse(fact[n-r]) % mod;
}
```

---

# 7️⃣ MODULAR INVERSE (FERMAT)

If mod is prime:

```id="fml9jg"
a⁻¹ = a^(mod-2) % mod
```

---

## Code

```java
long modInverse(long a, int mod){
    return modPow(a, mod-2, mod);
}
```

---

# MASTER SUMMARY

| Concept | Key Idea               |
| ------- | ---------------------- |
| Sieve   | remove multiples       |
| GCD     | reduce using remainder |
| Power   | divide exponent        |
| Mod     | control overflow       |
| nCr     | factorial + inverse    |

---

# HOW TO IDENTIFY

If problem mentions:

```id="1t34je"
prime
divisible
large numbers
mod
combinations
power
```

👉 Think **Number Theory**

---

# FINAL ONE-LINE RULE

> **Math problems = reduce computation using properties**