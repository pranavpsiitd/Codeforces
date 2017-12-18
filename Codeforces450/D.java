package Codeforces450;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
        private long MOD = (long)(1e9+7);
        private HashMap<Long,Long> hm;
        private ArrayList<Long> divisors;
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long x = in.nextLong();
            long y = in.nextLong();
            if(y%x != 0){
                out.println(0);
                return;
            }
            y /= x;
            hm = new HashMap<>();
            divisors = fDivisors(y);


            out.println(dp(y));
        }

        private long dp(long y) {
            if(y==1)
                return 1;
            if(hm.containsKey(y))
                return hm.get(y);
            long ans = pow(y-1);
            for (Long d: divisors) {
                if(d>y)
                    break;
                if(y%d == 0)
                    ans = (ans - dp(y/d) + MOD)%MOD;
            }
            hm.put(y,ans);
            return ans;
        }

        private long pow(long l) {
            if(l == 0)
                return 1;
            long ans = 1;
            if(l%2 == 1)
                ans *= 2;
            long local = pow(l/2);
            local = (local*local)%MOD;
            ans = (ans*local)%MOD;
            return ans;
        }


        private ArrayList<Long> fDivisors(long y) {
            ArrayList<Long> al = new ArrayList<>();
            for (long i = 2; i*i <= y; i++) {
                if(i*i == y)
                    al.add(i);
                else if(y%i == 0) {
                    al.add(i);
                    al.add(y/i);
                }
            }
            al.add(y);
            Collections.sort(al);
            return al;
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

    }
}
