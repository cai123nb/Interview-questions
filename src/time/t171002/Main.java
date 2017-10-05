package time.t171002;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /* Question1
    黑默丁格曾经提出了一个约德尔测试，将约德尔人的历史的每个阶段都用一个字符表达出来。(包括可写字符,不包括空格。)。
    然后将这个字符串转化为一个01串。转化规则是如果这个字符如果是字母或者数字，这个字符变为1,其它变为0。
    然后将这个01串和黑默丁格观测星空得到的01串做比较，得到一个相似率。相似率越高,则约德尔的未来越光明。

    样例输入
    @!%12dgsa
    010111100
    样例输出
    66.67%
     */

    /* Question2
     有n个格子，从左到右放成一排，编号为1-n。
     共有m次操作，有3种操作类型：
     1.修改一个格子的权值，
     2.求连续一段格子权值和，
     3.求连续一段格子的最大值。
     对于每个2、3操作输出你所求出的结果。
     输入
     输入第一行两个整数，n表示格子个数，m表示操作次数，n和m中间用空格隔开；
     接下来输入n行，每行一个整数表示一个格子的权值
     接下来输入m行，每行有三个整数，中间用空格隔开；第一个是选择的操作类型1-3,第二和第三个整数是操作格子的编号。
     输出
     若执行1操作则无输出
     若执行2和3操作则输出一个整数

     样例输入
     3 3
     7
     8
     9
     2 1 3
     3 1 3
     2 1 2
     样例输出
     24
     9
     15
  */
    /* Question3
    大学的同学来自全国各地，对于远离家乡步入陌生大学校园的大一新生来说，碰到老乡是多么激动的一件事，
    于是大家都热衷于问身边的同学是否与自己同乡，来自新疆的小赛尤其热衷。但是大家都不告诉小赛他们来自哪里，
    只是说与谁同乡，从所给的信息中，你能告诉小赛有多少人确定是她的同乡吗？
    输入
    每个测试实例首先包括2个整数，N（1 <= N <= 1000），M(0 <= M <= N*(N-1)/2)，代表现有N个人（用1~N编号）和M组关系；
    在接下来的M行里，每行包括2个整数，a，b，代表a跟b是同乡；
    当N = 0，M = 0输入结束；
    已知1表示小赛本人。

    输出
    对于每个测试实例，输出一个整数，代表确定是小赛同乡的人数。

    样例输入
    3 1
    2 3
    5 4
    1 2
    3 4
    2 5
    3 2
    0 0
    样例输出
    0
    4
     */

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        /* Question1
        String str = sc.nextLine().trim().toLowerCase();
        String str2 = sc.nextLine().trim();
        int same = 0;
        for(int i = 0; i < str.length(); i++){
            char c1 = str.charAt(i), c2 = str2.charAt(i);
            if ((c1 <= '9' && c1 >= '0') || (c1 <= 'z' && c1 >='a')) {
                if (c2 == '1') same++;
            } else {
                if (c2 == '0') same++;
            }
        }

        float percent = (float) 100 * same / str.length();
        System.out.printf("%.2f%%", percent); */

/*        Question2
        int boxSize = sc.nextInt(), operationSize = sc.nextInt();
        int[] boxes = new int [boxSize];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < boxSize; i++) {
            boxes[i] = sc.nextInt();
        }

        for (int i = 0; i < operationSize; i++) {
            int operationType = sc.nextInt();
            if (operationType == 1) {
                boxes[sc.nextInt() - 1] = sc.nextInt();
            } else if (operationType == 2){
                result.add(getSum(boxes, sc.nextInt(), sc.nextInt()));
            } else {
                result.add(getMax(boxes, sc.nextInt(), sc.nextInt()));
            }
        }

        result.stream()
                .forEach(System.out::println);*/
        List<Integer> result = new ArrayList<>();
        while(true) {
            int peopleNums = sc.nextInt(), relationNums = sc.nextInt();
            if (peopleNums == 0 && relationNums == 0)
                break;
            int[] peopleSigns = new int[peopleNums];
            for (int i = 0; i < peopleNums; i++) { //Init People Signs, same sign mean friends
                peopleSigns[i] = i;
            }
            //Handle relation
            for (int i = 0; i < relationNums; i++) {
                int friend1 = sc.nextInt(), friend2 = sc.nextInt();
                int friendSign = Math.max(peopleSigns[friend1 - 1], peopleSigns[friend2 - 1]);
                int friendSign2 = peopleSigns[friend1 - 1] + peopleSigns[friend2 - 1] - friendSign;
                for (int j = 0; j < peopleNums; j++) {
                    if (peopleSigns[j] == friendSign2) {
                        peopleSigns[j] = friendSign;
                    }
                }
            }
            int friendNums = 0;
            for (int i = 1; i < peopleNums; i++) {
                if (peopleSigns[i] == peopleSigns[0])
                    friendNums++;
            }
            result.add(friendNums);
        }

        result.stream()
                .forEach(System.out::println);
    }

    private static int getSum(int[] datas, int startPoint, int endPoint) {
        int result = 0;
        for (int i = startPoint - 1; i < endPoint; i++) {
            result += datas[i];
        }
        return result;
    }

    private static int getMax(int[] datas, int startPoint, int endPoint) {
        int result = Integer.MIN_VALUE;
        for (int i = startPoint - 1; i < endPoint; i++) {
            if (result < datas[i]) {
                result = datas[i];
            }
        }
        return result;
    }
}
