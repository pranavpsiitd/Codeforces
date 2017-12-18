package Codeforces450;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Pranav ps on 16-12-2017.
 */
public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        HashSet<Integer> hs = new HashSet<>();

        a = a%b;

        int dividend = a*10;
        int i = 1;
        int ans = -1;
        while(true){
            int remainder = 0;
            int quotient = 0;

            quotient = dividend/b;
            remainder = dividend%b;
            dividend = remainder*10;

            if(quotient == c){
                ans = i;
                break;
            }
            if(hs.contains(remainder))
                break;
            hs.add(remainder);
            i++;
        }
        System.out.println(ans);
    }
}
