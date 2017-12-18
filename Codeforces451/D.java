package Codeforces451;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Pranav ps on 16-12-2017.
 */

/**
 * This is the template for all coding problems
 */
public class D {
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
        private int num = (int)(1e6+1);
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }

            int ans = 0;

            int[] A = new int[num];
            for (int i = 0; i < n; i++) {
                A[arr[i]]++;
            }
            SegmentTree seg = new SegmentTree(num,A);
            seg.build(1,0, num);

            for (int i = 1; i < num; i++) {
                if(A[i] > 0){
                    int x = seg.query(i,Math.min(i+m,num), 1, 0, num);
                    if(x >= k){
                        int excess = x - k + 1;
                        int y = Math.min(i+m,num)-1;
                        while (excess > 0){
                            if(A[y] > 0){
                                seg.modify(y,A[y] - 1, 1, 0, num);
                                ans++;
                                excess--;
                            }
                            else
                                y--;
                        }
                    }
                }
            }
            out.println(ans);
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

    }
}
