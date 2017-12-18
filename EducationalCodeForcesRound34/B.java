package EducationalCodeForcesRound34;

import java.util.Scanner;

/**
 * Created by Pranav ps on 12-12-2017.
 */
public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h1 = sc.nextInt();
        int a1 = sc.nextInt();
        int c1 = sc.nextInt();
        int h2 = sc.nextInt();
        int a2 = sc.nextInt();
        int numV = (h2%a1 == 0)?(h2/a1):((h2/a1) + 1);
        int numM = (h1%a2 == 0)?(h1/a2):((h1/a2) + 1);
        int heal = 0;
        int deficit = (numV-numM)*a2;
        while(deficit > 0){
            h1+=c1-a2;
//            int numV = (h2%a1 == 0)?(h2/a1):((h2/a1) + 1);
            numM = (h1%a2 == 0)?(h1/a2):((h1/a2) + 1);
            deficit = (numV-numM)*a2;
            heal++;
        }
        System.out.println((heal+numV));
        for (int i = 0; i < heal; i++) {
            System.out.println("HEAL");
        }
        for (int i = 0; i < numV; i++) {
            System.out.println("STRIKE");
        }
    }
}
