package time.t170929;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
    描述:
    现在现在有一台机器，这台机器可以接收两种形式任务：（1）任务列表，任务列表里面有N个任务，对于第i个任务，
    机器在Ti时间开始执行，并在1个单位时间内做完。（2）临时任务，机器可以在任意时间接收一个临时任务，
    但任务列表里面的任务优先级要高于临时任务，也就是说当机器空闲的时候才会执行临时任务。
    现在机器已经接收一个任务列表。接下来会有M个临时任务，我们想知道每个临时任务何时被执行。
    为了简化问题我们可以认为这M个临时任务是独立无关即任务是可以同时执行的，互不影响的。
    输入
    输入数据有多组，每组数据第一行包括两个整数N和M（1<=N, M<=10^5）。
    接下来一行有N个不同数字T1,T2,T3.....TN（1<=T1
    接下来又M行，每行一个数字Qi（1<=Qi<=10^9），表示第i个临时任务的的接收时间。
    样例输入
    5 6
    1 2 3 5 6
    3
    2
    1
    4
    5
    6
    样例输出
    4
    4
    4
    4
    7
    7
     */

    public static void main(String... args) {
        //Get Input
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[] taskList = new int[m];
        int[] temTaskTime = new int[n];
        for (int i = 0; i < m; i++) {
            taskList[i] = sc.nextInt();
        }
        for (int j = 0; j < n; j++) {
            temTaskTime[j] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temTaskTime[i] == taskList[j]) {
                    temTaskTime[i]++;
                }
            }
        }
        Arrays.stream(temTaskTime)
                .forEach(System.out::println);

       /* //Get spare time gap
        List<Integer> spareTimes = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            if(taskList[i + 1] - taskList[i] > 1) {
                int gapTimes = taskList[i + 1] - taskList[i];
                for (int j = 1; j < gapTimes; j++) {
                    spareTimes.add(taskList[i] + j);
                }
            }
        }
        spareTimes.add(taskList[m - 1] + 1);
        //Calculate time
        int[] timeResult = new int[n];
        for (int i = 0; i < n; i++) {
            if(timeResult[i] >= taskList[m - 1] + 1) {
                timeResult[i] = temTaskTime[i];
                break;
            }
            for (int time : spareTimes) {
                if (temTaskTime[i] <= time) {
                    timeResult[i] = time;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(timeResult[i]);
        }
    }*/
    }
}
