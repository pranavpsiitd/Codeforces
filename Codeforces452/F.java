package Codeforces452;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Pranav ps on 16-12-2017.
 */

/**
 * This is the template for all coding problems
 */
public class F {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        private static long MOD = (long)(1e9+7);
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            String str = in.next();

            HashMap<Character,TreeSet<Integer>> hm = new HashMap<>();
            for (int i = 0; i < n; i++) {
                char c = str.charAt(i);
                if(!hm.containsKey(c))
                    hm.put(c,new TreeSet<>());
                hm.get(c).add(i);
            }


            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = 1;
            }
            SegmentTree seg = new SegmentTree(n,A);
            seg.build(1,0,n);

            for (int i = 0; i < m; i++) {
                int l = in.nextInt();
                int r = in.nextInt();
                char c = in.next().charAt(0);
                l--;
                r--;
                int lp = binarySearch(seg,n,l);
                int rp = binarySearch(seg,n,r);

                if(hm.containsKey(c)) {
                    NavigableSet<Integer> ts = hm.get(c).subSet(lp, true, rp, true);

                    if (ts != null) {
                        ArrayList<Integer> indices = new ArrayList<>();
                        for (Integer t : ts) {
                            indices.add(t);
                        }
                        for (Integer index : indices) {
                            hm.get(c).remove(index);
                            seg.modify(index, 0, 1, 0, n);
                        }
                    }
                }

            }

//            ArrayList<Integer> indices = new ArrayList<>();
//            for (Character character : hm.keySet()) {
//                indices.addAll(hm.get(character));
//            }
//            StringBuffer sb = new StringBuffer();
//            Collections.sort(indices);
//            for (Integer index : indices) {
//                sb.append(str.charAt(index));
//            }
//            out.println(sb.toString());
            for (int i = 0; i < n; i++) {
                if(A[i] == 1)
                    out.print(str.charAt(i));
            }
            out.println();
        }

        private int binarySearch(SegmentTree seg, int n, int idx) {
            int lo = 0;
            int hi = n;
            while(lo < hi){
                int mid = lo + (hi - lo)/2;
                int count = seg.query(0,mid+1,1,0,n);
                if(count <= idx){
                    lo = mid+1;
                }
                else
                    hi = mid;
            }
            return hi;
        }


    }

    static class SegmentTree {
        int n;
        int[] A;
        int[] seg;//4*n

        public SegmentTree(int n, int[] A){
            this.n = n;
            this.A = A;
            seg = new int[4*n];
        }

        int combine(int lft, int rgt){
            return lft + rgt;
        }

        void build(int id, int l, int r ){//id = 1, l = 0, r = n
            if(r - l < 2){
                seg[id] = A[l];
                return;
            }

            int mid = l + (r-l)/2;
            build(id << 1, l, mid);
            build(id << 1|1, mid, r);

            seg[id] = combine(seg[id << 1],seg[id << 1|1]);
        }

        void modify(int idx, int val, int id, int l, int r){//id = 1, l = 0, r = n
            if(r - l < 2){
                seg[id] += val - A[idx];
                A[idx] = val;
                return;
            }

            int mid = l + (r-l)/2;
            if(idx < mid){
                modify(idx, val,id << 1, l , mid);
            }
            else{
                modify(idx, val, id << 1 | 1, mid, r);
            }

            seg[id] = combine(seg[id << 1], seg[id << 1|1]);
        }

        int query(int ql, int qr, int id, int l, int r){//id = 1, l = 0, r = n
            if(ql >= r || l >= qr){
                return 0;//identity
            }
            if(ql <= l && r <= qr){
                return seg[id];
            }
            int mid = l + (r-l)/2;

            return combine(query(ql,qr,id << 1, l , mid), query(ql,qr,id << 1|1, mid, r));
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] nextIntArray(int n){
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }
    }
}
