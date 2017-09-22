package time.t170922;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     * 描述:
     * 头条的2017校招开始了！为了这次校招，我们组织了一个规模宏大的出题团队。每个出题人都出了一些有趣的题目，
     * 而我们现在想把这些题目组合成若干场考试出来。在选题之前，我们对题目进行了盲审，并定出了每道题的难度系数。
     * 一场考试包含3道开放性题目，假设他们的难度从小到大分别为a, b, c，我们希望这3道题能满足下列条件：
     a＜= b＜= c
     b - a＜= 10
     c - b＜= 10
     所有出题人一共出了n道开放性题目。现在我们想把这n道题分布到若干场考试中（1场或多场，每道题都必须使用且只能用一次），
     然而由于上述条件的限制，可能有一些考试没法凑够3道题，因此出题人就需要多出一些适当难度的题目来让每场考试都达到要求。
     然而我们出题已经出得很累了，你能计算出我们最少还需要再出几道题吗？
     样例输入
     4
     20 35 23 40
     样例输出
     2

     思路: 使用贪心的思路去求解问题, 尽可能的使用多的元素去凑够三道题.
     1) 排序.
     2) 尽可能多的添加元素去组合.
     *
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scan.nextInt();
        }
        int res=0;
        Arrays.sort(arr);
        for(int i =0;i<n;i++){
            if(i+1<n && arr[i+1]-arr[i]>20){     //The difference between first One and second one is too big
                // Which we must give up first one (add two another methods to it).
                res+=2;
                continue;
            }else if(i+1<n && arr[i+1]-arr[i]>10){
                //We could add one method to it to combine a target one.
                res+=1;
                i=i+1;
                continue;
            }else if(i+1<n){ //The first one and second one could be use as first and second.
                if(i+2<n && arr[i+2]-arr[i+1]<=10){
                    //If there is third one and the third one is smaller, which could combine the first two
                    i=i+2;
                }else if(i+2<n){
                    //If there is third one but we couldn't use to combine the first two, we must add one
                    res+=1;
                    i=i+1;
                }else{
                    //If there is no more, we need add one.
                    res+=1;
                    i=i+1;
                }
            }else{
                //If there is no more data, we need add one
                res+=2;
            }
        }
        System.out.println(res);
    }
}


