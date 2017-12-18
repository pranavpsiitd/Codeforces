package Codeforces450;

import java.util.Scanner;

/**
 * Created by Pranav ps on 15-12-2017.
 */
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int neg = 0;
        int pos = 0;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(x < 0)
                neg++;
            else
                pos++;
        }
        String ans = "";
        if(pos == 0 || pos == 1 || neg == 0|| neg == 1)
            ans = "Yes";
        else
            ans = "No";
        System.out.println(ans);
    }
}
