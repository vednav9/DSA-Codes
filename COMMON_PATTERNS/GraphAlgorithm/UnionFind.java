package GraphAlgorithm;

class UnionFind {

    int[] parent, rank;

    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];

        for(int i=0;i<n;i++)
            parent[i] = i;
    }

    public int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y){
        int px = find(x), py = find(y);

        if(px == py) return;

        if(rank[px] < rank[py])
            parent[px] = py;
        else if(rank[px] > rank[py])
            parent[py] = px;
        else{
            parent[py] = px;
            rank[px]++;
        }
    }
}