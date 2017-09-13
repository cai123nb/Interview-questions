package time.t170913;

import java.util.Scanner;

public class Main {
    /*
    *
    1. 当n=1的时候，输出一个小写字母"o" :
    o
    2.当n≥2的时候，复制n=n-1时候的图像，并且在n-1图像的上、下、左、右都粘贴一个一模一样的图形。
    如n=2时，在原图"o"的上下左右都粘贴一个"o",所以最后输出的是这样一个图形：
      o
     ooo
      o
      样例输入:
      2 //2组数据
      2 //第一组
      3 //第二组
      样例输出:
    Case #1:
      o
     ooo
      o
    Case #2:
        o
       ooo
        o
     o  o  o
    ooooooooo
     o  o  o
        o
       ooo
        o
    *
     */

    public static void main(String ... args) {
        //Get Input
        int size;
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        int [] datas = new int[size];
        for (int i = 0; i< size; i++){
            datas[i] = scanner.nextInt();
        }

        //Print result
        printData(datas);
    }

    static void printData(int [] datas) {
        for (int i = 0; i < datas.length; i++) {
            System.out.println("Case #" + (i+1));
            printOneRecord(datas[i]);
        }
    }

    static void printOneRecord(int i) {
        //Store result
        int maxLength = (int) Math.pow(3,i - 1);
        int [][] result = new int[maxLength][maxLength];

        //Calculate Result
        getResult(result, i , maxLength, maxLength);

        //Print Result
        for (int j = 0; j < maxLength; j++) {
            for (int k = 0; k < maxLength; k++) {
                System.out.print(result[j][k] == 0 ? " " : "a");
            }
            System.out.println();
        }
    }

    static void getResult(int[][] result, int n, int leftSpace, int upSpace) {
        if( n == 1) {
            result[leftSpace - 1][upSpace - 1] = 1;
        } else {
            int currentSize = (int) Math.pow(3, n - 1);
            //Print Top
            getResult(result,n - 1, leftSpace - currentSize / 3, upSpace - currentSize / 3 * 2);
            //Print Left
            getResult(result, n - 1, leftSpace - currentSize / 3 * 2, upSpace - currentSize / 3);
            //Print Self
            getResult(result, n - 1, leftSpace - currentSize / 3, upSpace - currentSize / 3);
            //Print Right
            getResult(result, n - 1, leftSpace, upSpace - currentSize / 3);
            //Print Down
            getResult(result, n - 1, leftSpace - currentSize / 3, upSpace);
        }
    }
}
