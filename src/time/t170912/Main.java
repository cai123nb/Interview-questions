package time.t170912;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Main {
/*        *//*
            两个集合，要求 {A} + {B}。
            样例输入
            1 2         //第一个集合有1个元素, 第二个集合有2两个元素
            1           // 第一个集合内元素
            2 3         //第二个集合内元素
            1 2         同上
            1
            1 2
            样例输出, 去重排列输出
            1 2 3
            1 2
        *//*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            int a = sc.nextInt();
            int b = sc.nextInt();
            Set<Integer> setA = new TreeSet<Integer>();
            for(int i = 0; i<a; i++){
                setA.add(sc.nextInt());
            }
            for(int j = 0; j<b; j++){
                setA.add(sc.nextInt());
            }
            for(Integer i:setA){
                System.out.print(i+" ");
            }
        }
    }*/

    /*
        输入字符串S, 子串为S中任意连续的一段. 一些子串只由一种字母构成, 他想知道在S中一共有多少种这样的子串.
        例如在串"aaabbaa"中, 度度熊想找的子串有'a','aa','aaa','b','bb'五种.
            样例输入
            aaabbaa
            样例输出
            5
    */
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        String datas = sc.nextLine();
        System.out.println(getMaxSize(datas.trim()));
    }

    static int getMaxSize(String datas) {
        int length = 0;
        Set<String> used = new HashSet<>();
        used.add(datas.substring(0, 1));
        for (int i = 1; i < datas.length(); i++) {
            length = datas.charAt(i) == datas.charAt(i - 1) ? length + 1 : 0;
            used.add(datas.substring(i - length, i + 1));
        }
        return used.size();
    }
}
