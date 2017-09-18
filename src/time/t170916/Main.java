package time.t170916;

import java.util.*;

public class Main {
    /*
    继MIUI8推出手机分身功能之后，MIUI9计划推出一个电话号码分身的功能：首先将电话号码中的每个数字加上8取个位，然后使用对应的大写字母代替
    （"ZERO", "ONE", "TWO", "THREE", "FOUR",
    "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"），
    然后随机打乱这些字母，所生成的字符串即为电话号码对应的分身。

    样例输入
    4
    EIGHT
    ZEROTWOONE
    OHWETENRTEO
    OHEWTIEGTHENRTEO
    样例输出
    0
    234
    345
    0345

    解题思路: 递归的查找从0开始向大数字开始查找, 每次找到匹配的消除, 直到最后一个, 比较大小, 选择较小的一方.
    */
    private static final long MAX = Long.MAX_VALUE;
    private static final char[][] CHARS =  {{'Z', 'E', 'R', 'O'},
                                                {'O', 'N', 'E'},
                                                {'T', 'W', 'O'},
                                                {'T', 'H', 'R', 'E', 'E'},
                                                {'F', 'O', 'U', 'R'},
                                                {'F', 'I', 'V', 'E'},
                                                {'S', 'I', 'X'},
                                                {'S', 'E', 'V', 'E', 'N'},
                                                {'E', 'I', 'G', 'H', 'T'},
                                                {'N', 'I', 'N', 'E'}};

    public static void main(String ... args) {
        //Get input
        int size = 0;
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()){
            size = sc.nextInt();
            sc.nextLine();
            String[] datas = new String[size];
            for(int i = 0; i< size; i++){
                datas[i] = sc.nextLine();
            }
            //Print Result
            printResult(datas);
        }
    }

    static void printResult(String[] datas){
        for(String oneData : datas){
            System.out.println(getMinNumberString(oneData));
        }
    }

    static String getMinNumberString(String data){
        char[] chars = data.toUpperCase().trim().toCharArray();
        long result = getMinNumber(chars, chars.length, 0);
        int zeroNum = getZeroNumber(chars.length, result);
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < zeroNum; i++){
            sb.append("0");
        }
        sb.append(result);
        return sb.toString();
    }

    static long getMinNumber(char[] datas, int leftLength, long currentValue){
        char[] myDatas = copyArray(datas);
        if(leftLength <= 5) {   //Only one world left
            if(leftLength == 0) return currentValue;
            if(leftLength < 3) return MAX;
            List<Integer> matchNumber = containNumber(myDatas);
            if(matchNumber.size() == 0) return MAX;
            int tmpValue = matchNumber.get(0) >= 8 ? matchNumber.get(0) - 8 : matchNumber.get(0) + 2;
            return currentValue * 10 + tmpValue;
        }
        List<Integer> matchNumber = containNumber(myDatas);
        if(matchNumber.size() == 0 && leftLength > 0)
            return MAX;
        long smaller = MAX;
        Iterator<Integer> it = matchNumber.iterator();
        while (it.hasNext()){
            char[] copyData = copyArray(myDatas);
            int data = it.next();
            //Remove number
            leftLength = removeUsedNumber(copyData, data);
            int tmpValue = data >= 8 ? data - 8 : data+2;
            long result = getMinNumber(copyData, leftLength, currentValue * 10 + tmpValue);
            smaller = smaller < result ? smaller : result;
        }
        return smaller;
    }

    static List<Integer> containNumber(char[] datas) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if(containChars(datas, CHARS[i])){
                result.add(i);
            }
        }

        return result;
    }

    static boolean containChars(char[] a, char[] b){
        char[] copyA = copyArray(a);
        char[] copyB = copyArray(b);
        for(int j = 0; j < copyB.length; j++){
            for(int i = 0; i < copyA.length; i++){
                if(copyA[i] == copyB[j]){
                    copyB[j] = '1';
                    copyA[i] = '2';
                    break;
                }
            }
        }
        boolean isContain = true;
        for( char c : copyB){
            if(c != '1'){
                return false;
            }
        }
        return true;
    }

    static char[] copyArray(char[] target){
        char[] result = Arrays.copyOf(target, target.length);
        return result;
    }

    static int removeUsedNumber(char[] datas, int number){
        char[] removed = CHARS[number];
        for(char c : removed){
            for(int i = 0; i< datas.length; i++){
                if(datas[i] == c){
                    datas[i] = '2';
                    break;
                }
            }
        }
        int length = 0;
        for( char c : datas){
            if(c != '2') length++;
        }
        return length;
    }

    static int getZeroNumber(int length, long data) {
        String str = String.valueOf(data);
        int size = str.length(), tmpLength = 0;
        for(int i = 0; i < size; i++){
            int data1 = (int) data % 10;
            int tmpValue = (data1 + 8) % 10;
            tmpLength += CHARS[tmpValue].length;
            data/= 10;
        }
        return (length - tmpLength) / 4;
    }
}
