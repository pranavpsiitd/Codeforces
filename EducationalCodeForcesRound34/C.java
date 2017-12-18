package EducationalCodeForcesRound34;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Pranav ps on 12-12-2017.
 */
public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            if(!tm.containsKey(arr[i]))
                tm.put(arr[i],0);
            tm.put(arr[i],tm.get(arr[i])+1);
        }
        TreeMap<Integer,Integer> tmD = new TreeMap<>();
        int ans = 0;
        while(!tm.isEmpty()){
            for(Map.Entry<Integer,Integer> entry: tm.entrySet()){
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                tmD.put(key,value-1);
                if(value == 1)
                    tmD.remove(key);
            }
            ans++;
            tm = tmD;
            tmD = new TreeMap<>();
        }
        System.out.println(ans);
    }
}
