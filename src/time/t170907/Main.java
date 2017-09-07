package time.t170907;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    /*
    电子表格坐标表示分为两种:
    第一种: RxCy, 表示x行y列.
    第二种: YYxx, YY为字母, 其中A表示第1列, B表示第2列, ... , Z表示第26列, AA表示27列, AZ表示52列以此类推.
        xx为数字表示第xx行,
    需要在两种坐标中进行装换.
    样例输入       样例输出
    2
    R23C55          BC23
    BC23            R23C55
     */
    public static void main(String... args) {
        //Get input
        int size = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
            String [] data = new String[size];
            scanner.nextLine();
            for (int i = 0; i < size; i++){
                data[i] = scanner.nextLine().trim();
            }
            //Handle Data
            printResult(data);
        }
    }

    //Print each element's transform result
    private static void printResult(String[] data) {
        //Handle each data
/*        List<String> result1 = Arrays.stream(data)
                .map(t -> transformData(t))
                .collect(Collectors.toList());
        List<String> result2 = result1.stream()
                .map(t -> transformData(t))
                .collect(Collectors.toList());
        for(int i = 0; i < result1.size(); i++){
            if(!data[i].equals(result2.get(i))){
                System.out.println("Error here: " + data[i] + " : " + result1.get(i));
            }
        }*/
        Arrays.stream(data)
                .map(t -> transformData(t))
                .forEach(System.out::println);
    }

    public static String transformData(String data){
        String pattern = "^R\\d{1,}C\\d{1,}";
        if (Pattern.matches(pattern, data)){
            //First kind like RxCy
            return firstToSecond(data);
        }
        //Second kind like YYxx
        return secondToFirst(data);
    }

    public static String firstToSecond(String data) {
        //Like RxCy to YYxx, Get x y value
        String [] values = data.split("R|C");
        int xValue = Integer.parseInt(values[1]);
        int yValue = Integer.parseInt(values[2]);
        //Transform y value to word
        StringBuilder sb = new StringBuilder();
        while (yValue != 0) {
            if(yValue % 26 == 0){
                sb.append("Z");
                yValue = yValue / 26 - 1;
            } else {
                sb.append((char)(yValue % 26 -1 + 'A'));
                yValue = yValue / 26;
            }
        }
        return sb.reverse().append(xValue).toString();
    }

    public static String secondToFirst(String data) {
        //Like YYxx to RxCy
        String yValueStr = data.split("\\d")[0];
        int yValue = 0, xValue = 0;
        for (int i = 0; i < yValueStr.length(); i++) {
            yValue += (yValueStr.charAt(i) - 'A' + 1) * (Math.pow(26, (yValueStr.length() - 1 - i)));
        }
        xValue = Integer.parseInt(data.substring(yValueStr.length()));
        return "R" + xValue + "C" + yValue;
    }

}
