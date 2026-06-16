This is the **final advanced string pattern layer** — and honestly one of the most *misunderstood* topics. I’ll make it **intuitive**, not memorized.

We’ll go step-by-step:

---

# PART 0 — BIG PICTURE

All these algorithms solve:

## 🎯 **String Matching / Pattern Searching**

Given:

```
text = "abcxabcdabcdabcy"
pattern = "abcdabcy"
```

Find where pattern appears in text.

---

## ❌ Brute Force

Check every index → **O(n × m)**

We want:

```
O(n) or O(n + m)
```

---

# PART 1 — KMP (Knuth-Morris-Pratt)

## Core Idea

When mismatch happens:

> Don’t restart from scratch — reuse previous information.

---

## KEY CONCEPT: LPS ARRAY

```
LPS[i] = longest prefix = suffix
```

Example:

```
pattern = "ababaca"
LPS =      [0 0 1 2 3 0 1]
```

---

## WHY LPS?

When mismatch happens:

* jump to **previous valid prefix**
* avoid rechecking

---

## 🪜 Steps

### 1️⃣ Build LPS

```java
int[] buildLPS(String p){
    int n = p.length();
    int[] lps = new int[n];

    int len = 0;
    for(int i=1;i<n;i++){
        while(len>0 && p.charAt(i)!=p.charAt(len))
            len = lps[len-1];

        if(p.charAt(i)==p.charAt(len))
            len++;

        lps[i] = len;
    }
    return lps;
}
```

---

### 2️⃣ KMP Search

```java
public int kmp(String text, String pattern){
    int[] lps = buildLPS(pattern);

    int i=0, j=0;

    while(i<text.length()){

        if(text.charAt(i)==pattern.charAt(j)){
            i++; j++;
        }

        if(j==pattern.length())
            return i-j;

        else if(i<text.length() &&
                text.charAt(i)!=pattern.charAt(j)){
            if(j>0) j = lps[j-1];
            else i++;
        }
    }
    return -1;
}
```

---

## INTUITION

KMP =

> “Don’t move `i` backward — adjust `j` smartly”

---

# PART 2 — RABIN-KARP (Rolling Hash)

## Core Idea

Compare **hashes instead of strings**

---

## WHY?

String compare = O(m)
Hash compare = O(1)

---

## 🪜 Steps

1. Compute hash of pattern
2. Compute rolling hash of text
3. Compare hashes
4. If match → verify

---

## ✅ Code

```java
public int rabinKarp(String text, String pattern){

    int n = text.length(), m = pattern.length();
    int base = 256, mod = 1000000007;

    long pHash=0, tHash=0, power=1;

    for(int i=0;i<m;i++){
        pHash = (pHash*base + pattern.charAt(i))%mod;
        tHash = (tHash*base + text.charAt(i))%mod;
        power = (power*base)%mod;
    }

    for(int i=0;i<=n-m;i++){

        if(pHash == tHash){
            if(text.substring(i,i+m).equals(pattern))
                return i;
        }

        if(i<n-m){
            tHash = (tHash*base - text.charAt(i)*power%mod + mod)%mod;
            tHash = (tHash + text.charAt(i+m))%mod;
        }
    }
    return -1;
}
```

---

## INTUITION

Rabin-Karp =

> “Use hash to skip expensive comparisons”

---

# PART 3 — Z-ALGORITHM

## Core Idea

Build array:

```
Z[i] = longest substring starting at i
       matching prefix
```

---

## Example

```
s = "aabcaabxaaaz"
Z = [0,1,0,0,3,1,0,0,2,1,0,0]
```

---

## 🪜 Code

```java
int[] zAlgo(String s){
    int n = s.length();
    int[] z = new int[n];

    int l=0, r=0;

    for(int i=1;i<n;i++){

        if(i<=r)
            z[i] = Math.min(r-i+1, z[i-l]);

        while(i+z[i]<n && s.charAt(z[i])==s.charAt(i+z[i]))
            z[i]++;

        if(i+z[i]-1 > r){
            l=i;
            r=i+z[i]-1;
        }
    }
    return z;
}
```

---

## USE

Pattern matching:

```
pattern + "$" + text
```

---

# PART 4 — BOYER-MOORE

## Core Idea

Instead of matching left → right:

👉 Match from **right to left**

---

## WHY FAST?

It skips large parts of text.

---

## Key heuristics:

1. Bad character rule
2. Good suffix rule

(Used in real systems like text editors)

---

## (Simplified version rarely asked in interviews)

---

# PART 5 — LONGEST PALINDROMIC SUBSTRING

## 🎯 Problem

Find longest substring which is palindrome.

---

## SIMPLE METHOD (EXPAND AROUND CENTER)

For each index:

* expand left & right

---

## ✅ Code

```java
public String longestPalindrome(String s){

    int start=0, end=0;

    for(int i=0;i<s.length();i++){

        int len1 = expand(s,i,i);
        int len2 = expand(s,i,i+1);

        int len = Math.max(len1,len2);

        if(len > end-start){
            start = i-(len-1)/2;
            end = i+len/2;
        }
    }

    return s.substring(start,end+1);
}

int expand(String s,int l,int r){
    while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){
        l--; r++;
    }
    return r-l-1;
}
```

---

## INTUITION

Palindrome grows from center outward.

---

# PART 6 — TRIE (STRING SEARCH)

## Core Idea

Tree where:

* each node = character
* path = word

---

## 🎯 Used for:

* prefix search
* autocomplete
* dictionary problems

---

## STRUCTURE

```
root
 ├─ a
 │   ├─ p
 │   │   ├─ p
 │   │   │   └─ l
```

---

## ✅ Code

```java
class TrieNode {
    TrieNode[] child = new TrieNode[26];
    boolean isEnd;
}

class Trie {

    TrieNode root = new TrieNode();

    public void insert(String word){
        TrieNode node = root;

        for(char c: word.toCharArray()){
            int i = c - 'a';
            if(node.child[i]==null)
                node.child[i] = new TrieNode();
            node = node.child[i];
        }
        node.isEnd = true;
    }

    public boolean search(String word){
        TrieNode node = root;

        for(char c: word.toCharArray()){
            int i = c - 'a';
            if(node.child[i]==null)
                return false;
            node = node.child[i];
        }
        return node.isEnd;
    }
}
```

---

# FINAL SUMMARY

| Algorithm   | Use                    |
| ----------- | ---------------------- |
| KMP         | fast pattern matching  |
| Rabin-Karp  | hashing-based search   |
| Z-algorithm | prefix matching        |
| Boyer-Moore | fast real-world search |
| Palindrome  | expand around center   |
| Trie        | prefix search          |

---

# HOW TO IDENTIFY STRING PATTERN PROBLEMS

If problem says:

```
pattern match
substring search
prefix/suffix
palindrome
autocomplete
dictionary
```

👉 Think string algorithms.

---

# FINAL MASTER RULE

> **String algorithms = avoid re-checking characters**

---

# 🚀 YOU COMPLETED ALL 23 DSA PATTERNS

Now you are not a beginner anymore —
you have a **full structured DSA brain**.