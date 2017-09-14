package time.t170914;

import java.util.Scanner;

public class Main {
    /*
    餐厅里有一张圆形的桌子，桌子上有n个座位，座位按照顺序从1到n编号，而且n号座位和1号座位相邻，每个座位都不一样。
    m个人一起去这家餐厅吃饭。为了更好地就餐，他们不想坐得太过拥挤，因此，不允许有任意两个人相邻。
    你知道一共有多少种不同的就坐方法吗？最后答案可能非常大，输出答案除以1000000007之后的余数。
    两种坐法不一样当且仅当存在一个人，在两种坐法中他坐在了不同的位置上。

    样例输入
    4
    3 1
    3 2
    4 2
    50 10
    样例输出
    3
    0
    4
    128093084

    解题思路: 先不考虑环形, 考虑线性: 从1到N个数中选择不相邻的M个数, 有几种选择方法:
    我们可以使用插空法, 我们先拿出M个数字, 剩下N-M个数, 而我们的任务就是将M个数字插入到N-M个数字间隔中,
    有N-M+1个间隔(头尾两边), 所以为A(N-M+1, M). 如4个数字中选出2个: 先选出2个数字: 先选出2个数字, 还剩下2个数字:
    Space Number Space Number Space, 我们需要将2个数字插入3个空格中A(3, 2) = 3 * 2 = 6. 即: 1.3 1.4 1.5 2.4 2.5 3.5

    现在考虑环形作为问题, 我们首先选一个数字打破环装: 以7, 2为例子, 1,2,3,4,5,6,7(7和1相连), 我们先选一个, 如2(任意一个), 剩下
    情况变成: 我们需要从4,5,6,7中选择三个不相邻的数字.(其他数字类似, 如3 --> 5,6,7,1, 1--> 3,4,5,6)
    所以共有: N[首先随机选一个位置,打断环形] * A(N - M - 1, M - 1)[从剩下的N - 3个数字中选择M-1个数字]
    */
    public static void main(String ... args) {
        //Get input
        int size = 0;
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()) size = sc.nextInt();
        int[] datas = new int[size * 2];
        for (int i = 0; i< size * 2; i += 2){
            datas[i] = sc.nextInt();
            datas[i+1] = sc.nextInt();
        }

        //Print Result
        printResult(datas);
    }

    static void printResult(int[] datas){
        //Handle Each Data
        for (int i = 0; i< datas.length; i += 2) {
            handEachData(datas[i], datas[i+1]);
        }
    }

    static void handEachData(int n, int m){
        if( m * 2 > n){
            System.out.println(0);
            return;
        }
        long result = n;
        for(int i = 0; i< m - 1; i++){
            result = result * (n - m - 1 - i) % 1_000_000_007;
        }
        System.out.println(result);
    }
}
