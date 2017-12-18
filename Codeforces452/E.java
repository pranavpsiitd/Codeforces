package Codeforces452;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Created by Pranav ps on 16-12-2017.
 */

/**
 * This is the template for all coding problems
 */
public class E {
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
            int[] arr = in.nextIntArray(n);
            TreeSet<Segment> byIdx = new TreeSet<>(Comparator.comparingInt(a -> a.idx));
            TreeSet<Segment> byLen = new TreeSet<>((Segment a, Segment b) -> {
                if(a.len == b.len){
                    return a.idx-b.idx;
                }
                return -(a.len - b.len);
            });
            int idx = 0;
            while(idx < n){
                int num = arr[idx];
                int length = 0;
                while(idx < n && arr[idx] == num) {
                    length++;
                    idx++;
                }
                Segment seg = new Segment(idx,num,length);
                byIdx.add(seg);
                byLen.add(seg);

            }

            int ans = 0;

            while(!byLen.isEmpty()){
                Segment seg = byLen.pollFirst();
                ans++;

                byIdx.remove(seg);

                Segment before = byIdx.lower(seg);
                Segment after = byIdx.higher(seg);

                merge(byIdx, byLen, before, after);
            }
            out.println(ans);
        }

        private void merge(TreeSet<Segment> byIdx, TreeSet<Segment> byLen, Segment before, Segment after) {
            if(before == null || after == null)
                return;
            if(before.val == after.val){
                byIdx.remove(before);
                byIdx.remove(after);
                byLen.remove(before);
                byLen.remove(after);
                Segment newSeg = new Segment(before.idx, before.val, before.len+after.len);
                byIdx.add(newSeg);
                byLen.add(newSeg);
            }
        }


    }

    static class Segment{
        int idx;
        int val;
        int len;

        public Segment(int idx, int val, int len) {
            this.idx = idx;
            this.val = val;
            this.len = len;
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
