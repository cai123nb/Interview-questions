package time.t170918;

import java.util.Scanner;

public class Main2 {
/*
为活跃公司文化，公司计划组织一场比赛，让员工一展才艺。现有n个员工，欲选出不少于k人组成一支队伍，1＜=n＜ =12,1＜=k＜=n。
每个员工有个value值，表示其对队伍水平的贡献，-1000＜=value＜=1000，给出一个矩阵对角线为0的对称矩阵A， A[i][j]表示i,j
同在队伍中时对队伍水平的贡献，为取得最好成绩，公司领导希望知道水平最高的组队方式能够达到的水平和组队方案数。
样例输入
1       一组数据
2 1     从2个人中选出不少于1个人的组队
100 -5  个人贡献值
0 10    组队贡献值
10 0
样例输出
105 1

 */
    static int max;
    static int maxNums;
    static int allNums;
    static int useNums;
    static int[][] oneGroupData;
    static int[][] result;

    public static void main(String... args) {
        //Get input
        int size = 0;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            size = sc.nextInt();
            result = new int[size][2];  //Store results
            for(int i = 0; i < size; i++) {  //Read one group
                max = Integer.MIN_VALUE;
                maxNums = 0;
                allNums = sc.nextInt();
                useNums = sc.nextInt();
                oneGroupData = new int[allNums + 1][allNums];
                for (int j = 0; j < allNums + 1; j++){
                    for (int x = 0; x < allNums; x++) {
                        oneGroupData[j][x] = sc.nextInt();
                    }
                }
                //Handle One group data
                boolean[] isUsed = new boolean[allNums];
                getMaxValue(isUsed, 0);
                //Record result
                result[i][0] = max;
                result[i][1] = maxNums;
            }

            //Print Result
            for(int i = 0; i < size; i++){
                System.out.println(result[i][0] + " "+result[i][1]);
            }
        }
    }

    static void getMaxValue(boolean[] isUsed, int position) {
        if(position == allNums){
            //How many people in the team ?
            int peoples = 0;
            for(boolean b : isUsed) {
                if(b) peoples++;
            }
            if(peoples < useNums) return;   //Drop this group data.

            //Calculate current value
            int result = 0;
            for(int i = 0; i < isUsed.length; i++){
                if(isUsed[i]){
                    result += oneGroupData[0][i];   //Add personal work value
                    for(int j = i + 1; j < allNums; j++){
                        if(isUsed[j]){
                            result += oneGroupData[i+1][j]; //Add together work value
                        }
                    }
                }
            }
            if(result > max) {
                max = result;
                maxNums = 1;
            } else if (result == max) {
                maxNums++;
            }
            return;
        }
        isUsed[position] = true;
        getMaxValue(isUsed, position + 1);
        isUsed[position] = false;
        getMaxValue(isUsed, position + 1);
    }
}
