package time.t170923;

import java.util.Scanner;

class TrieNode{
    TrieNode[] next = new TrieNode[2]; //Two son branch 0 , 1
    int count = 0; //Store how many number in current subtree
    int digit = 0; //Store current digit(ongly 0 and 1)
    public TrieNode(int digit){
        this.digit = digit;
    }
}

public class Main {
    /**
     * 描述
     * 给定整数m以及n个数字A1, A2, …, An，将数列A中所有元素两两异或，共能得到n(n-1)/2个结果。请求出这些结果中大于m的有多少个。
     样例输入
     3 10
     6 5 10

     样例输出
     2

     正常两两遍历肯定出错, 时间超时.
     */
    public static void insert(TrieNode root, int number){
        TrieNode prev = root;
        for(int i = 31; i >= 0; i--){
            int digit = (number >> i) & 1;  //Get the Ith place number
            if(prev.next[digit] == null){
                prev.next[digit] = new TrieNode(digit);
            }
            prev = prev.next[digit];
            prev.count++; //Store number
        }
    }

    public static int query(TrieNode root, int a, int m, int k){ //Use a's number of bit to compare the other number in the tree
        if(root == null){ //If here is leaf node, return 0.
            return 0;
        }
        TrieNode prev = root;
        for(int i = k; i >= 0; i--){
            int mDigit = (m >> i) & 1;
            int aDigit = (a >> i) & 1;
            if(mDigit == 1 && aDigit == 1){
                if(prev.next[0] == null){ //If both number have number in the bit, combined number must have no number in the bit
                    return 0; //if there is no satisfied number return 0.
                } else{
                    prev = prev.next[0]; //Go to satisfied number tree
                }
            } else if(mDigit == 1 && aDigit == 0){ //If m have number in the bit, a have no, combined number must have number in the bit.
                if(prev.next[1] == null){  //If there is no satisfied number return 0
                    return 0;
                } else{
                    prev = prev.next[1]; //Go to satisfied number tree
                }
            } else if(mDigit == 0 && aDigit == 0){  //If both number have no number int the bit, XOR if there is other
                //number have number in the bit, will make it bigger
                return query(prev.next[0], a, m, i-1) + (prev.next[1] == null ? 0 : prev.next[1].count);
            } else if(mDigit == 0 && aDigit == 1){ //If a have number in the bit and m have no, XOR if there is other number
                //have no number in the bit, will make it bigger
                return query(prev.next[1], a, m, i-1) + (prev.next[0] == null ? 0 : prev.next[0].count);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        TrieNode root = new TrieNode(-1);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[]array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = sc.nextInt();
            insert(root, array[i]);
        }
        long result = 0;
        for(int number : array){
            result += query(root, number, m, 31);
        }
        System.out.println(result / 2);
    }
}