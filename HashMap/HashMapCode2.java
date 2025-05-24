package HashMap;

import java.util.*;

class HashMapCode2 {
    public static void main(String[] args) {
        HashMap<String, Integer> map=new HashMap<>();
        map.put("India", 1);
        map.put("USA", 2);
        map.put("London", 3);


        ArrayList<String> keys=map.keySet();
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(keys.get(i)+" "+map.get(keys.get(i)));
        }
    }

    static class HashMap<K, V> {

        class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n;
        private int N;
        private LinkedList<Node> buckets[];


        @SuppressWarnings("unchecked")
        public HashMap(){
            this.N=4;
            this.buckets=new LinkedList[4];

            for (int i = 0; i < 4; i++) {
                this.buckets[i]=new LinkedList<>();
            }
        }

        private int hasFunction(K key){
            int bi=key.hashCode();
            return Math.abs(bi)%N;
        }

        private int searchInLL(K key, int bi){
            LinkedList<Node> ll=buckets[bi];
            for (int i = 0; i < ll.size(); i++) {
                if(ll.get(i).key==key){
                    return i;
                }
            }
            return -1;
        }

        private void rehash(){
            LinkedList<Node> oldBuckets[]=buckets;
            buckets=new LinkedList[N+2];

            for (int i = 0; i < N/2; i++) {
                buckets[i]=new LinkedList<>();
            }

            for (int i = 0; i < oldBuckets.length; i++) {
                LinkedList<Node> ll=oldBuckets[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node=ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value){
            int bi=hasFunction(key);
            int di=searchInLL(key,bi); //data index

            if (di==-1) {
                // key doesn't exist
                buckets[bi].add(new Node(key,value));
                n++;
            }
            else{
                Node node=buckets[bi].get(di);
                node.value=value;
            }

            double lambda=(double)n/N;

            if (lambda>2.0) {
                // rehashing
                rehash();
            }
        }

        public V get(K key){
            int bi=hasFunction(key);
            int di=searchInLL(key,bi); //data index

            if (di==-1) {
                // key doesn't exist
                return null;
            }
            else{
                Node node=buckets[bi].get(di);
                return node.value;
            }
        }

        public boolean containsKey(K key){
            int bi=hasFunction(key);
            int di=searchInLL(key,bi); //data index

            if (di==-1) {
                // key doesn't exist
                return false;
            }
            else{
                return true;
            }
        }

        public V remove(K key){
            int bi=hasFunction(key);
            int di=searchInLL(key,bi); //data index

            if (di==-1) {
                return null;
            }
            else{
                Node node=buckets[bi].remove(di);
                return node.value;
            }
        }

        public boolean isEmpty(){
            return n==0;
        }

        public ArrayList<K> keySet(){
            ArrayList<K> keys =new ArrayList<>();

            for (int i = 0; i < buckets.length; i++) {
                LinkedList<Node> ll=buckets[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node=ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }

    }
}