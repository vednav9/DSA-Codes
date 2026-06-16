**Topological Sort** is a **core graph pattern** used in many interview problems like **course scheduling, task ordering, build systems, dependency resolution**, etc.

I’ll teach it **step-by-step from the basics**, then cover the three subpatterns you listed:

1. **What topological sorting is**
2. **Why it only works for DAGs**
3. **Kahn’s Algorithm (BFS)**
4. **DFS-based topological sort**
5. **Cycle detection in directed graphs**
6. **How to recognize when to use it**

---

# 1️⃣ What is Topological Sort?

A **topological order** is a linear ordering of nodes such that:

> For every directed edge `u → v`, node **u appears before v** in the order.

Example:

Graph:

```
A → C
B → C
C → D
```

Valid topological order:

```
A B C D
```

or

```
B A C D
```

But **not**:

```
C A B D ❌
```

Because `A` and `B` must come before `C`.

---

# 2️⃣ Why Topological Sort Only Works for DAG

Topological sorting only works for **DAGs**:

```
Directed Acyclic Graph
```

If a cycle exists:

```
A → B → C → A
```

There is **no valid order**, because each node depends on another in the cycle.

So **cycle detection is crucial**.

---

# 3️⃣ Kahn’s Algorithm (BFS Topological Sort)

This is the **most common interview solution**.

### Core idea

A node can appear in the ordering **only when all its prerequisites are finished**.

So we track **indegree**.

```
indegree = number of incoming edges
```

---

## Algorithm Steps

### Step 1: Build graph

Adjacency list.

```
u → v
```

means `u` must come before `v`.

---

### Step 2: Compute indegree

Example:

```
A → C
B → C
C → D
```

Indegree table:

```
A = 0
B = 0
C = 2
D = 1
```

---

### Step 3: Start with nodes having indegree 0

These nodes have **no prerequisites**.

Put them in a queue.

---

### Step 4: Process queue

1. Remove node
2. Add it to result
3. Reduce indegree of its neighbors
4. If neighbor becomes `0`, add to queue

---

## Example

Graph:

```
0 → 1
0 → 2
1 → 3
2 → 3
```

Start:

```
queue = [0]
```

Process:

```
0 → result [0]
queue = [1,2]

1 → result [0,1]
queue = [2]

2 → result [0,1,2]
queue = [3]

3 → result [0,1,2,3]
```

---

# 4️⃣ Java Code — Kahn's Algorithm

```java
public int[] topoSort(int n, int[][] edges) {

    List<List<Integer>> graph = new ArrayList<>();
    for(int i=0;i<n;i++) graph.add(new ArrayList<>());

    int[] indegree = new int[n];

    for(int[] e: edges){
        graph.get(e[0]).add(e[1]);
        indegree[e[1]]++;
    }

    Queue<Integer> q = new LinkedList<>();

    for(int i=0;i<n;i++){
        if(indegree[i]==0)
            q.offer(i);
    }

    int[] result = new int[n];
    int idx=0;

    while(!q.isEmpty()){

        int node = q.poll();
        result[idx++] = node;

        for(int nei: graph.get(node)){
            indegree[nei]--;

            if(indegree[nei]==0)
                q.offer(nei);
        }
    }

    return result;
}
```

---

# 5️⃣ Detecting Cycle using Kahn’s Algorithm

If there is a cycle:

Some nodes will **never reach indegree 0**.

So we check:

```
processedNodes != totalNodes
```

---

### Modified Code

```java
if(idx != n){
    System.out.println("Cycle detected");
}
```

Example problem:

**LeetCode 207 – Course Schedule**

---

# 6️⃣ DFS-Based Topological Sort

Another way is using **DFS + stack**.

Idea:

```
Process children first, then push node.
```

This ensures prerequisites appear before dependents.

---

## Example

Graph:

```
5 → 2
5 → 0
4 → 0
4 → 1
2 → 3
3 → 1
```

DFS order produces:

```
5 4 2 3 1 0
```

---

# 7️⃣ DFS Topological Sort Algorithm

Steps:

1. Run DFS on each node
2. After finishing DFS for node → push to stack
3. Reverse stack = topological order

---

## Java Code — DFS Topological Sort

```java
public List<Integer> topoSortDFS(int n, List<List<Integer>> graph){

    boolean[] visited = new boolean[n];
    Stack<Integer> stack = new Stack<>();

    for(int i=0;i<n;i++){
        if(!visited[i])
            dfs(i, graph, visited, stack);
    }

    List<Integer> result = new ArrayList<>();

    while(!stack.isEmpty())
        result.add(stack.pop());

    return result;
}

private void dfs(int node,
                 List<List<Integer>> graph,
                 boolean[] visited,
                 Stack<Integer> stack){

    visited[node] = true;

    for(int nei: graph.get(node)){
        if(!visited[nei])
            dfs(nei, graph, visited, stack);
    }

    stack.push(node);
}
```

---

# 8️⃣ Cycle Detection in Directed Graph (DFS Method)

For directed graphs we use **three states**:

| State | Meaning     |
| ----- | ----------- |
| 0     | not visited |
| 1     | visiting    |
| 2     | visited     |

If during DFS we reach a node with state `1`, a **cycle exists**.

---

### Java Code

```java
boolean dfs(int node,
            List<List<Integer>> graph,
            int[] state){

    if(state[node]==1)
        return true;

    if(state[node]==2)
        return false;

    state[node]=1;

    for(int nei: graph.get(node)){
        if(dfs(nei, graph, state))
            return true;
    }

    state[node]=2;

    return false;
}
```

---

# 9️⃣ When to Use Topological Sort

If problem mentions:

```
courses
prerequisites
dependencies
build order
task scheduling
compile order
```

Think immediately:

```
Directed graph + topological sorting
```

---

# 🔟 Common Interview Problems

| Problem                     | Pattern   |
| --------------------------- | --------- |
| Course Schedule (LC 207)    | BFS topo  |
| Course Schedule II (LC 210) | BFS topo  |
| Alien Dictionary            | topo sort |
| Parallel Courses            | BFS topo  |
| Minimum semesters           | BFS topo  |

---

# Key Concept Summary

Topological sort =

```
Order tasks respecting dependencies
```

Two main approaches:

| Method           | Idea                  |
| ---------------- | --------------------- |
| Kahn’s algorithm | BFS using indegree    |
| DFS topo         | postorder DFS + stack |

Cycle detection ensures graph is DAG.