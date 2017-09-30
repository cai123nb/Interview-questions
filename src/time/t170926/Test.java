package time.t170926;

import java.util.*;


public class Test {

    final static int MAX = (int) (1e6+5);

    final static int MOD = (int) (1E9+7);

    static boolean[] visited = new boolean[MAX];


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        in.close();

        System.out.println(helper(n));
    }

    private static long helper(int n) {

        long ans = 1;
        for(int i=2;i<=n;i++) {
            int count = 0;
            if(visited[i])
                continue;
            for(int j=i+i;j<=n;j+=i) {
                visited[j] = true;
            }

            long mi = i;
            while(mi <= n) {
                count++;
                mi = mi*i;
            }

            ans = ans * (count+1) % MOD;
        }

        return ans;
    }


}