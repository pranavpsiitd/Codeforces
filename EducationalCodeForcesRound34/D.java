package EducationalCodeForcesRound34;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Pranav ps on 12-12-2017.
 */
public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }
        BigInteger prefixSum = BigInteger.ZERO;
        BigInteger ans = BigInteger.ZERO;
        HashMap<BigInteger,Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            BigInteger local = BigInteger.ZERO;
            BigInteger temp = BigInteger.valueOf(arr[i]);
            local = local.add(BigInteger.valueOf(i).multiply(temp));
            local = local.subtract(prefixSum);
            BigInteger a = temp.subtract(BigInteger.ONE);
            int oneLess = (hm.containsKey(a))?hm.get(a):0;
            BigInteger b = temp.add(BigInteger.ONE);
            int oneMore = (hm.containsKey(b))?(hm.get(b)):0;

            int equal = (hm.containsKey(temp))?(hm.get(temp)):0;

            local = local.add(a.multiply(BigInteger.valueOf(oneLess)));
            local = local.add(b.multiply(BigInteger.valueOf(oneMore)));
            local = local.add(temp.multiply(BigInteger.valueOf(equal)));

            local = local.subtract(temp.multiply(BigInteger.valueOf(equal+oneLess+oneMore)));
            ans = ans.add(local);
            prefixSum = prefixSum.add(temp);
            if(!hm.containsKey(temp))
                hm.put(temp,0);
            hm.put(temp,hm.get(temp)+1);
        }
        System.out.println(ans);
    }
}
