package time.t170906;

import java.util.Scanner;

public class Main1 {
    /*
    描述
    对于n * m的乘法表, 给出一个数k, 乘法表中元素按照不减顺序排列之后, 第k个元素是多少.
    如:
    输入: 2 3 4  (--> 乘法表: 1 2 3
                              2 4 6)
    返回 3.
     */
    public static void main(String... args) {
        //Get Input
        long xSize = 0, ySize = 0, order = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLong()) {
            ySize = scanner.nextLong();
            xSize = scanner.nextLong();
            order = scanner.nextLong();
        }
        //Do calculate
        System.out.print(getValueByOrder(xSize, ySize, order));
    }

    public static long getValueByOrder(final long xSize, final long ySize, final long order) {
        long min = 1, max = xSize * ySize, mid = 0;
        while (min <= max) {
            mid = (min + max) / 2;
            //Get mid number's order
            long midOrder = getOrder(xSize, ySize, mid);
            if (order <= midOrder) { //If required order is smaller.
                max = mid - 1;
            } else {    //If required order is bigger.
                min = mid + 1;
            }
        }
        return min;
    }

    public static long getOrder(final long x, final long y, final long data) {
        long result = 0;
        for (int i = 1; i <= x; i++) {
            if (data >= i * y) {
                result += y;
            } else {
                result += data / i;
            }
        }
        return result;
    }
}
