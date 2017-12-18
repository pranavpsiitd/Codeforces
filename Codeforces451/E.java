package Codeforces451;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        private int limit = (int)(1e9);
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] arr = new int[n];
            ArrayList<Integer> squares = new ArrayList<>();
            int save = 0;
            for (int i = 0; i*i <= limit && i*i >= 0; i++) {
                squares.add(i*i);
                save = i;
            }
            save++;
            squares.add(save*save);
            int[] sq = new int[squares.size()];
            for(int i = 0; i < squares.size(); i++)
                sq[i] = squares.get(i);
            int numperfect = 0;
            int numZeroes = 0;
            ArrayList<Integer> steps = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                if(arr[i] == 0) {
                    numZeroes++;
                    numperfect++;
                }
                else{
                    int idx = Arrays.binarySearch(sq,arr[i]);
                    if(idx >= 0)
                        numperfect++;
                    else{
                        idx = -idx - 1;
//                        if(idx == sq.length){
//                            steps.add(arr[i] - sq[sq.length-1]);
//                        }
//                        else
                            steps.add(Math.min(arr[i]-sq[idx-1],sq[idx] - arr[i]));
                    }
                }
            }
            int x = numperfect - n/2;
            if(x == 0)
                out.println(0);
            else if(x < 0){
                Collections.sort(steps);
                long ans = 0;
                for (int i = 0; i < n/2 - numperfect; i++) {
                    ans = ans + (long)(steps.get(i));
                }
                out.println(ans);
            }
            else{
                if(numZeroes > n/2){
                    int ans = numperfect-numZeroes;
                    ans += (x-ans)*2;
                    out.println(ans);
                }
                else{
                    out.println(x);
                }
            }
//            out.println(ans);
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
