package time.t170908;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
    标签的定义是将标签名放入一对尖括号中（<>）中，即构成一个起始标签，
    如。若在起始标签中标签名的前面加上一个终止斜杠（/），则构成一个对应的结束标签，
    如。起始标签和结束标签必须成对出现，一对标签内可以嵌入其他标签对，但两对标签之间不能出现交叉的情况。
    若一对标签内部未嵌入其他标签对，则可以嵌入非标签内容。

    输入
    若干行字符构成的一个XML文档内容片段，保证单个标签中的字符不被分割到两行中，且标签名与尖括号之间无空白，每行包含不超过1000个ASCII码字符。
    样例输入

    输出
    输出单行判定结果。
    若该XML片段合法，则输出Valid，否则输出Invalid。
    */

    public static void main(String... args) {
        //Get input
        Scanner cin = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while(cin.hasNextLine()){
            String str = cin.nextLine();
            if(str == null || str.trim().equals("")){
                break;
            }
            sb.append(str);
        }
        //Verify the data
        System.out.println(judge(sb.toString().trim()));
    }

    private static String judge(String data) {
        List<String> tmpData = new ArrayList<>();
        boolean canPutOtherWord = false;
        boolean isPutOtherWord = false;
        String[] dataPieces = data.split("<");
        for (int i = 0; i < dataPieces.length; i++) {
            //Skip space string and annotations
            if(!dataPieces[i].trim().equals("") && dataPieces[i].trim().charAt(0) != '!'){
                if(dataPieces[i].trim().charAt(0) == '/'){  //End Tag
                    canPutOtherWord = false;
                    isPutOtherWord = false;
                    if(!dataPieces[i].contains(">")) return "Invalid";
                    String tagContent = dataPieces[i].trim().replace("/","")
                            .replace(">", "");
                    if(tmpData.size() <= 0 || !tmpData.get(tmpData.size() - 1).equals(tagContent)){
                        return "Invalid";
                    } else {
                        tmpData.remove(tagContent);
                    }
                } else { //Start Tag or other data
                    if(!dataPieces[i].contains(">")){   //Other word
                        isPutOtherWord = true;
                        if(!canPutOtherWord) return "Invalid";
                    } else {    //Start tag
                        if(isPutOtherWord) return "Invalid";
                        String tagContent = dataPieces[i].trim().split(">")[0];
                        tmpData.add(tagContent);
                        if(dataPieces[i].trim().split(">").length > 1){
                            isPutOtherWord = true;
                        }
                        canPutOtherWord = true;
                    }
                }
            }
        }
        return tmpData.size() == 0 ? "Valid" : "Invalid";
    }
}
