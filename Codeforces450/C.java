package Codeforces450;

import java.util.*;

/**
 * Created by Pranav ps on 16-12-2017.
 */
public class C {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] A = new int[n+1];
//        for (int i = 0; i < n; i++) {
//            Codeforces451.Codeforces452.A[arr[i]] = 1;
//        }
        SegmentTree seg = new SegmentTree(n+1,A);
        seg.build(1,0,n+1);
        HashSet<Integer> records = new HashSet<>();
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            int greater = seg.query(num+1, n+1, 1,0,n+1);
            if(greater == 0)
                records.add(num);
            else if(greater == 1)
                candidates.add(num);
            seg.modify(num,1,1,0,n+1);
        }

        A = new int[n+1];
        for (Integer num: candidates) {
            A[num] = 1;
        }
        seg = new SegmentTree(n+1,A);
        seg.build(1,0,n+1);
        int maxSoFar = Integer.MIN_VALUE;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int local = seg.query(1,arr[i],1,0,n+1);
            if(records.contains(arr[i]))
                local--;
            if(local > maxSoFar){
                ans = arr[i];
                maxSoFar = local;
            }
            else if(local == maxSoFar && arr[i] < ans)
                ans = arr[i];
            seg.modify(arr[i],0,1,0,n+1);
        }
        System.out.println(ans);
    }

    static class SegmentTree {
        int n;
        int[] A;
        int[] seg;//4*n

        public SegmentTree(int n, int[] A){
            this.n = n;
            this.A = A;
            seg = new int[4*n];
        }

        int combine(int lft, int rgt){
            return lft + rgt;
        }

        void build(int id, int l, int r ){//id = 1, l = 0, r = n
            if(r - l < 2){
                seg[id] = A[l];
                return;
            }

            int mid = l + (r-l)/2;
            build(id << 1, l, mid);
            build(id << 1|1, mid, r);

            seg[id] = combine(seg[id << 1],seg[id << 1|1]);
        }

        void modify(int idx, int val, int id, int l, int r){//id = 1, l = 0, r = n
            if(r - l < 2){
                seg[id] += val - A[idx];
                A[idx] = val;
                return;
            }

            int mid = l + (r-l)/2;
            if(idx < mid){
                modify(idx, val,id << 1, l , mid);
            }
            else{
                modify(idx, val, id << 1 | 1, mid, r);
            }

            seg[id] = combine(seg[id << 1], seg[id << 1|1]);
        }

        int query(int ql, int qr, int id, int l, int r){//id = 1, l = 0, r = n
            if(ql >= r || l >= qr){
                return 0;//identity
            }
            if(ql <= l && r <= qr){
                return seg[id];
            }
            int mid = l + (r-l)/2;

            return combine(query(ql,qr,id << 1, l , mid), query(ql,qr,id << 1|1, mid, r));
        }
    }

}
