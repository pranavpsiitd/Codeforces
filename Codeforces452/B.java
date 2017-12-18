package Codeforces452;

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
public class B {
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
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            int[] p = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
            int MODU = 12;
            boolean flag = false;
            int n29 = 0;
            for (int i = 0; i < 12; i++) {
                int k = i;
                for (int j = 0; j < n; j++) {
                    if(p[k] == 28){
                        if(arr[j] == 28){

                        }
                        else if(arr[j] == 29){
                            n29++;
                        }
                        else{
                            break;
                        }
                    }
                    else if(arr[j] != p[k]){
                        break;
                    }
                    k = (k+1)%MODU;
                    if(j == n-1){
                        flag = true;
                    }
                }
            }
            if(n29 > 1)
                flag = false;
            String ans = flag?"YES":"NO";
            out.println(ans);
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
