package time.t170919;

public class Main {

    public static void main(String... args){
        System.out.println(getValue(2));
    }
    static int getValue(int a){
        int result = 0;
        switch (a){
            case 2:
                result += 1;
            case 3:
                result += 2;
        }
        return result;
    }
}
