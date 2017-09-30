package time.t170926;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
    描述
    牛牛从生物科研工作者那里获得一段字符串数据s,牛牛需要帮助科研工作者从中找出最长的DNA序列。DNA序列指的是序列中只包括'A','T','C','G'。牛牛觉得这个问题太简单了,就把问题交给你来解决。
    例如: s = "ABCBOATER"中包含最长的DNA片段是"AT",所以最长的长度是2。

    描述: 如果一个字符串由两个相同字符串连接而成,就称这个字符串是偶串。例如"xyzxyz"和"aaaaaa"是偶串,但是"ababab"和"xyzxy"却不是。
    牛牛现在给你一个只包含小写字母的偶串s,你可以从字符串s的末尾删除1和或者多个字符,保证删除之后的字符串还是一个偶串,牛牛想知道删除之后得到最长偶串长度是多少。

    描述: 牛牛有一些字母卡片,每张卡片上都有一个小写字母,所有卡片组成一个字符串s。牛牛一直认为回文这种性质十分优雅,于是牛牛希望用这些卡片拼凑出一些回文串,但是有以下要求:
        1、每张卡片只能使用一次
        2、要求构成的回文串的数量最少
        牛牛想知道用这些字母卡片,最少能拼凑出多少个回文串。
        例如: s = "abbaa",输出1,因为最少可以拼凑出"ababa"这一个回文串
        s = "abc", 输出3,因为最少只能拼凑出"a","b","c"这三个回文串
     */
    final static int MOD = (int) (1E9+7);
    public static void main(String... args) {

        Scanner sc = new Scanner(System.in);
        /*if(sc.hasNextLine()) {    //Question 1
            String data = sc.nextLine();
            char[] chars = data.toCharArray();
            int maxLength = 0;
            int tmpLength = 0;
            char lastOne = chars[chars.length - 1];
            for (char c : chars) {
                if(c == 'A' || c == 'T' || c == 'C' || c == 'G') {
                    tmpLength++;
                    if (c == lastOne && tmpLength > maxLength)
                        maxLength = tmpLength;
                } else {
                    if (tmpLength > maxLength)
                        maxLength =tmpLength;
                    tmpLength = 0;
                }
            }
            System.out.println(maxLength);
        }*/
/*        if (sc.hasNextLine()) { //Question2
            String data = sc.nextLine().trim();
            for (int i = 0; i < data.length(); i++) {
                if (isEvenString(data, i)) {
                    System.out.println(data.length() - i - 1);
                    break;
                }
            }
        }*/
/*        int[] chars = new int[26]; //Question3
        if(sc.hasNextLine()) {
            String data = sc.nextLine().trim();
            for (int i = 0; i < data.length(); i++) {
                chars[data.charAt(i) - 'a']++;
            }
            int result = 0;
            for (int i : chars) {
                if (i != 0 && i % 2 == 1)
                    result++;
            }
            System.out.println(result);
        }*/

/*        if (sc.hasNextInt()) { //Something wrong
            int data = sc.nextInt();
            List<Integer> primes = getPrimes(data);
            long result = 1;
            for (int i : primes) {
                int nums = 0;
                for (int k = i; k <= data;  k *= i)
                    nums++;
                result = result * (nums + 1) % 1_000_000_007 ;
            }
            System.out.println(result);
        }*/
        int data = sc.nextInt();
        long result = 1;
        boolean[] flag = new boolean[data+ 1];
        for (int i = 2; i <= data; i++) {
            long count = 0;
            if (flag[i]) {                                                   //判断是否为素数
                continue;
            }
            for (int j = i + i; j <= data; j += i) {                         //将素数的倍数及幂数设置为true
                flag[j] = true;
            }
            for (int k = i; k <= data; k *= i) {                             //计算素数幂次数
                count++;
            }
            result = result * (count + 1) % MOD;
        }
        System.out.println(result);
    }

    private static List<Integer> getPrimes(int n) {
        List<Integer> result = new ArrayList<>();
        if (n >=2 ) {
            if (n >= 2) result.add(2);
            if (n >= 3) result.add(3);
            for (int i = 5; i<= n; i++) {
                int value = (int) Math.sqrt(i);
                boolean isPrimes = true;
                for (int j = 2; j <= value; j++) {
                    if (i % j == 0) {
                        isPrimes = false;
                        break;
                    }
                }
                if(isPrimes) result.add(i);
            }
        }
        return result;
    }
    private static boolean isEvenString(String data, int i) {
        int newLength = data.length() - i - 1;
        if(newLength % 2 == 1) return false;
        String tmpData = data.substring(0, newLength);
        for (int j = 0; j< newLength / 2; j++) {
            if (tmpData.charAt(j) != tmpData.charAt(j + newLength / 2))
                return false;
        }
        return true;
    }
}

