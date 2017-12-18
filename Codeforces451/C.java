package Codeforces451;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Pranav ps on 16-12-2017.
 */

/**
 * This is the template for all coding problems
 */
public class C {
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
            HashMap<String,HashSet<String>> hm = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String name = in.next();
                if(!hm.containsKey(name))
                    hm.put(name,new HashSet<>());
                int ent = in.nextInt();
                for (int j = 0; j < ent; j++) {
                    hm.get(name).add(in.next());
                }
            }
            HashMap<String,ArrayList<String>> hm2 = new HashMap<>();
            for (String s : hm.keySet()) {
                hm2.put(s,new ArrayList<>());
                ArrayList<String> al = new ArrayList<>(hm.get(s));
                for (int i = 0; i < al.size(); i++) {
                    String str1 = al.get(i);
                    boolean flag = true;
                    for (int j = 0; j < al.size(); j++) {
                        if(j == i)
                            continue;
                        String str2 = al.get(j);
                        if(isSuffix(str1,str2)){
                            flag = false;
                            break;
                        }

                    }
                    if(flag)
                        hm2.get(s).add(str1);
                }
            }
            out.println(hm2.size());
            for (String s : hm2.keySet()) {
                out.print(s);
                ArrayList<String> al = hm2.get(s);
                out.print(" ");
                out.print(al.size());
                for (int i = 0; i < al.size(); i++) {
                    out.print(" ");
                    out.print(al.get(i));
                }
                out.println();
            }
//            out.println(ans);
        }

        private boolean isSuffix(String str1, String str2) {
            if(str1.length() >= str2.length())
                return false;
            boolean flag = true;
            int j = str2.length() - 1;
            for (int i = str1.length() - 1; i >= 0; i--) {
                if(str1.charAt(i)!=str2.charAt(j)){
                    flag = false;
                    break;
                }
                j--;
            }
            return flag;
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
