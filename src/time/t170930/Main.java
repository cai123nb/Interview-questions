package time.t170930;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
    描述:
    小B的目的很简单:控制服务器的内存区域，试图在内存中装入从1到n之间的n个自然数，以覆盖内存区域。
    可能是小B对编程理解上的问题，病毒似乎没有完全成功。可能是由于保护机制的原因，内存写入只接受二进制的形式，
    所以十进制表达中除0和1之外的其他值都没有成功写入内存。小B希望知道，究竟有多少数成功的写入了服务器的内存！
     样例输入
    10
    20
    样例输出
    2
    3
     */
    public static void main(String... args) {
        //Get Input
        List<Long> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLong()) {
            result.add(handle1(sc.nextLong()));
        }
        result.stream()
                .forEach(System.out::println);
    }
    private static long handle1(long data) {
        String strData = data + "";
        int length = strData.length();
        long result = 0;
        for (int i = 0; i < length; i++) {  //Like Search in Binary Tree
            if (strData.charAt(i) > '1') {
                result += Math.pow(2, length - i) - 1; //The character before i digit, is all fit requests.
                break;
            } else if (strData.charAt(i) == '1') {
                result += Math.pow(2, length - i -1); //The 0 subtree is fit requests.
            }
        }
        return result;
    }

    private static long handle2(long data) {
        //How many digit
        int digits = 0;
        long tmpData = data;
        while (tmpData > 0) {
            tmpData /= 10;
            digits++;
        }

        //Add before digit numbers
        long result = 0;
        for (int i = 1; i < digits; i++) {
            result += Math.pow(2, (i - 1));
        }

        //Judge current digit
        StringBuilder maxSb = new StringBuilder("1");
        StringBuilder minSb = new StringBuilder("1");
        for (int i = 1; i < digits; i++) {
            maxSb.append("1");
            minSb.append("0");
        }
        long fittedMax = Long.valueOf(maxSb.toString());
        long fitteedMin = Long.valueOf(minSb.toString());
        if (data >= fittedMax) {
            result += Math.pow(2, digits - 1);
        } else if (data == fitteedMin) {
            result += 1;
        } else {
            //Judge CurrentMax
            String strData = data + "";
            for (int i = 1; i < digits; i++) {
                if(strData.charAt(i) - '1' > 0) {
                    result += Math.pow(2, digits - i);
                    break;
                } else if(strData.charAt(i) == '1') {
                    result += Math.pow(2, digits - i - 1);
                    if (i == digits -1)
                        result += 1;
                }
            }
        }
        return result;
    }
}
