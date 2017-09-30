package time.t170924;

import java.util.Scanner;

public class Main {
    /*
    描述：
    牛牛喜欢彩色的东西,尤其是彩色的瓷砖。牛牛的房间内铺有L块正方形瓷砖。每块砖的颜色有四种可能:红、绿、蓝、黄。给定一个字符串S, 如果S的第i个字符是'R', 'G', 'B'或'Y',那么第i块瓷砖的颜色就分别是红、绿、蓝或者黄。
    牛牛决定换掉一些瓷砖的颜色,使得相邻两块瓷砖的颜色均不相同。请帮牛牛计算他最少需要换掉的瓷砖数量。
    输入描述:
    输入包括一行,一个字符串S,字符串长度length(1 ≤ length ≤ 10),字符串中每个字符串都是'R', 'G', 'B'或者'Y'。
    输入例子1:
    RRRRRR

    输出例子1:
    3
     */

    public static void main (String... args) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine().trim();
        char[] chars = data.toCharArray();
        char currentChar = chars[0];
        int currentLength = 1;
        int result = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != currentChar || i == chars.length - 1) {
                if ( i == chars.length - 1 && chars[i] == currentChar) currentLength++;
                if (currentLength >= 2) {
                    result += currentLength / 2;
                }
                currentChar = chars[i];
                currentLength = 1;
            } else {
                currentLength++;
            }
        }

        System.out.println(result);
    }
}
