package time.t170918;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    /**
     有一个分数序列 2/1,3/2,5/3,8/5,13/8,21/13,.... 求这个分数序列的前n项之和。
     样例输入
     1
     2
     样例输出
     3.5000
     */


    public static void main(String ... args) {
        //Get input
        int size;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            size = sc.nextInt();
            int [] datas = new int[size];
            for(int i = 0; i < size; i++){
                datas[i] = sc.nextInt();
            }
            //Pring Result
            printResult(datas);
        }
    }

    static private void printResult(int[] datas){
        for(int c : datas){
            System.out.println(handleOneData(c));
        }
    }

    static private String handleOneData(int c) {
        double x1 = 1.0D, x2 = 2.0D, result = 0.0D;
        for (int i = 0; i < c; i++){
            result += x2 / x1;
            double tmp = x2;
            x2 = x1 + x2;
            x1 = tmp;
        }
        DecimalFormat df = new DecimalFormat("#.0000");
        return df.format(result);
    }
}
