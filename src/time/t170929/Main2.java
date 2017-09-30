package time.t170929;

import java.util.Scanner;
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int i,j;
        int[] maintime = new int[n];
        int[] temptime = new int[m];
        for (i = 0; i<maintime.length; i++)
            maintime[i] = sc.nextInt();

        for (i = 0; i<temptime.length; i++) {
            temptime[i] = sc.nextInt();
            for (j = 0; j<maintime.length; j++) {
                if(maintime[j] == temptime[i] )
                    temptime[i]++;
            }
            System.out.println(temptime[i]);
        }
        sc.close();
    }
}
