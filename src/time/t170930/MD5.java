package time.t170930;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.security.MessageDigest;
import java.util.stream.Stream;

public class MD5 {
    public static void main(String[] args){
        try{
            Files.lines(Paths.get("passwordOrigin.txt"), Charset.defaultCharset())
                    .map(MD5::getMD5)
                    .forEach(str -> {
                        try {
                            Files.write(Paths.get("passwordEncoded.txt"), (str + "\n").getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //生成MD5
    public static String getMD5(String message) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位
            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5.toLowerCase();
    }

    // 二进制转十六进制
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
}
