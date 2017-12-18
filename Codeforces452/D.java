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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long n = in.nextLong();
//            int max9 = 0;
//            long ten = 10;
//            while(ten <= 2*n){
//                max9++;
//                ten *= 10;
//            }
//            long mod = (long)(Math.pow(10,max9));
//            long x = n/mod;
//            long end = n%mod;
//            long num2x = Math.max(0,end - (mod/2-1));
//            if(end == mod-1)
//                num2x--;
//            long numx2 = mod/2;
//            long numx = (end == 0)?0:(mod-1-end-1);
//            if(end == mod-1)
//                numx++;
//            if(max9 == 0)
//                out.println(((n)*(n-1))/2);
//            else
//                out.println(numx2*(x*x) + numx*x + num2x*((long)2)*x + num2x);

            //After Editorial
            long sum = n + n - 1;
            if(sum < 9){
                out.println(n*(n-1)/2);
                return;
            }
            String num = Long.toString(sum);
            int numDigits = num.length();
            long tenPow = (long)(Math.pow(10,numDigits));
            if(sum == tenPow-1){
                out.println(1);
                return;
            }
            tenPow /= 10;
            long nines = tenPow-1;
            long ans = 0;
            for (int i = 0; i < 10; i++) {
                if(nines <= n+1)
                    ans += (nines/2);
                else if(nines > 2*n-1){

                }
                else{
                    ans += 1 + (2*n-1 - nines)/2;
                }
                nines += tenPow;
            }
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
