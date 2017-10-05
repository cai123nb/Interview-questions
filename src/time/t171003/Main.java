package time.t171003;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
    Question1.
    A、B两伙马贼意外地在一片沙漠中发现了一处金矿，双方都想独占金矿，但各自的实力都不足以吞下对方，
    经过谈判后，双方同意用一个公平的方式来处理这片金矿。处理的规则如下：他们把整个金矿分成n段，
    由A、B开始轮流从最左端或最右端占据一段，直到分完为止。
    马贼A想提前知道他们能分到多少金子，因此请你帮忙计算他们最后各自拥有多少金子?（两伙马贼均会采取对己方有利的策略）
    输入
    测试数据包含多组输入数据。输入数据的第一行为一个正整数T(T<=20)，表示测试数据的组数。然后是T组测试数据，
    每组测试数据的第一行包含一个整数n，下一行包含n个数（n <= 500 ），表示每段金矿的含金量，保证其数值大小不超过1000。
    输出
    对于每一组测试数据，输出一行"Case #id: sc1 sc2"，表示第id组数据时马贼A分到金子数量为sc1，马贼B分到金子数量为sc2。
    详见样例。

    样例输入
    2
    6
    4 7 2 9 5 2
    10
    140 649 340 982 105 86 56 610 340 879
    样例输出
    Case #1: 18 11
    Case #2: 3206 981

    思路:
    设dp[i][j] 为马贼A从i到j位置可以获得的所有金子,
    如果这时候是A在选: 去最大值
    dp[i][j] = max(dp[i+1][j] + a[i], dp[i][j-1] + a[j]);
    如果这时候是B再选: 我们不考虑B取最大值, 换位思考, 让A取得最小值
    dp[i][j] = min(dp[i+1][j], dp[i][j+1]). //这时候A没办法获得收益
    什么时候该A选:
    如果总数为偶数, j - i 为奇数时A在选, 如有6个, 第一次(1,6), 是由A再选, 6 - 1 = 5奇数,
        第三次可能为(1,4),(2,5),(3,6), 差为3奇数, A在选.
    如果总数为奇数, j - i 为偶数时A在选. 同理.
    总结为: If 总数 + 间隔 = 奇数, A选

    使用动态规划, 每次把结果存储数组中去, 每次递归查询前一次的结果.
     */

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[][] result = new int[size][2];
        for (int i = 0; i < size; i++) {
            //Get one group data
            int nums = sc.nextInt();
            int[] data = new int[nums];
            int sum = 0;
            for (int j = 0; j < nums; j++) {
                data[j] = sc.nextInt();
                sum += data[j];
            }

            //Dynamic planning
            int[][] dp = new int[nums][nums];
            //Init value
            if (nums % 2 == 1) {    //间隔为0
                for(int j = 0; j < nums; j++) {
                    dp[j][j] = data[j];
                }
            }

            //Calculate
            for (int interval = 1; interval < nums; interval++) {
                for (int start = 0; start + interval < nums; start++) {
                    if ((nums + interval) % 2 == 1) { //A Select, chose max one
                        dp[start][start + interval] = Math.max(dp[start + 1][start + interval] + data[start],
                                dp[start][start + interval - 1] + data[start + interval]);
                    } else {    //B select
                        dp[start][start + interval] = Math.min(dp[start + 1][start + interval],
                                dp[start][start + interval - 1]);
                    }
                }
            }

            //Restore result
            result[i][0] = dp[0][nums - 1];
            result[i][1] = sum - dp[0][nums - 1];
        }

        for (int i = 0; i < size; i++) {
            System.out.println("Case#" + (i + 1) + ": " + result[i][0] + " " + result[i][1]);
        }
    }
}
