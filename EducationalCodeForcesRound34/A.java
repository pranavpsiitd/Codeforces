package EducationalCodeForcesRound34;

import java.util.Scanner;

/**
 * Created by Pranav ps on 12-12-2017.
 */
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while(n-- > 0){
            int x = sc.nextInt();
            int rem = x%3;
            String ans = null;
            if(rem == 0|| (rem == 1 && x > 6) || (rem == 2 && x > 13))
                ans = "YES";
            else
                ans = "NO";
            System.out.println(ans);
        }
    }
}
