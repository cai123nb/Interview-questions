package time.t170916;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

        static private String[] tar = { "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE",
                "SIX", "SEVEN", "EIGHT", "NINE" };
        // 0 2 4 6 8 7 5 3 9 1
        static private char[] ch = { 'Z', 'W', 'U', 'X', 'G', 'S', 'F', 'H', 'I', 'O' };
        static private int num[] = { 0, 2, 4, 6, 8, 7, 5, 3, 9, 1 };

        public static void main(String[] args) {
            sovle();
        }

        private static void sovle() {
            Scanner scanner = new Scanner(System.in);
            int t = Integer.parseInt(scanner.nextLine());
            String tel;
            for (int i = 0; i < t; i++) { //分别处理每行数据
                tel = scanner.nextLine(); //读取一行数据
                int cnt[] = new int[26]; //存储26个字母的频率
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < tel.length(); j++) {    //统计当前字符串26个字母的数量
                    cnt[tel.charAt(j) - 'A']++;
                }
                for (int j = 0; j < 10; j++) {
                    int nc = cnt[ch[j] - 'A'];          //统计每个字符串的数量
                    for (int k = 0; k < nc; k++) {
                        sb.append((num[j] + 2) % 10);
                    }
                    String ta = tar[num[j]];            //获取当前的字符串
                    for (int k = 0; k < ta.length(); k++) { //去掉已经选中的字符
                        cnt[ta.charAt(k) - 'A'] -= nc;
                    }
                }
                char[] cs = sb.toString().toCharArray();
                Arrays.sort(cs);
                System.out.println(cs);
            }
        }
}
