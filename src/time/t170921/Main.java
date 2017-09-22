package time.t170921;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String... args) {
        //Init Data
        int[] datas = {1, 34, 56, 34, 62, 123, 57, 19};
        System.out.println("Before Sort");
        Arrays.stream(datas)
                .forEach(t -> System.out.print(" " + t));

        //Sort
        /*int[] result = straightInsertionSort(datas);*/
/*        int[] result = *//*shellSort(datas);
                bubbleSort(datas);
                bubbleSortImprove(datas);*//*
                simpleSelectSort(datas);*/
        /*quickSort(datas, 0, datas.length - 1);*/
        System.out.println("");
        /*heapSort(datas);*/
        /*mergeSort(datas);*/
        int[] result = bucketSort(datas);
        //Print Result
        System.out.println("After Sort");
        Arrays.stream(result)
                .forEach(t -> System.out.print(" " + t));
        System.out.println();
        System.out.println(randomizedSelect(datas, 0, datas.length - 1, 1));
    }

    /**
     *  直接插入排序算法, 稳定的排序算法
     *  时间复杂度:
     *  最好情况:O(n), 排好序的情况下, 每次都插入到最后一个.
     *  最差情况:O(n^2), 每次都插入到最前面, 1 + 2 + ... + n: O(n^2/2) = O(n^2)
     *  平均情况:O(n^2)
     * @param datas
     * @return
     */
    private static int[] straightInsertionSort(int[] datas) {
        int[] result = Arrays.copyOf(datas, datas.length);   //Store result
        for (int i = 1; i < result.length; i++) {
            if (result[i] < result[i - 1]) {                  //The Order Wrong
                int tmpValue = result[i];
                int position = 0;
                for(position = i -1; position >= 0 && tmpValue < result[position]; position--)  //Find right position to insert
                {
                    result[position + 1] = result[position];
                }
                result[position + 1] = tmpValue;
            }
        }
        return  result;
    }

    /**
     * 希尔排序, 直接插入排序的一种改进, 不稳定的
     * 时间复杂度: 希尔排序时间复杂度依赖增量的取值
     * 最好情况:O(n^1.3)
     * 最坏情况: O(n^2)
     * 平均情况:O(nlogn) - O(n^2)
     *
     *
     * @param datas
     * @return
     */
    private static int[] shellSort(int[] datas) {
        int[] result = Arrays.copyOf(datas, datas.length);   //Store result
        int increment = datas.length;   //Increment define
        while (true) {
            increment = increment / 2;   //Default increment take half every time
            for (int x = 0; x < increment; x++) {
                for (int i = x + increment; i < datas.length; i = i + increment) {  //Implemented by straight insert sorting
                    int tmpValue = datas[i];
                    int position = 0;
                    for(position = i - increment; position >= 0 && result[position] > tmpValue; position = position - increment) {
                        result[position + increment] = result[position];
                    }
                    result[position + increment] = tmpValue;
                }
            }
            if (increment == 1) {
                break;
            }
        }
        return  result;
    }

    /**
     * 冒泡排序， 稳定的
     * 时间复杂度:
     * 最好情况: O(n),
     * 最坏情况: O(n^2)
     * 平均情况: O(n^2)
     *
     * @param datas
     * @return
     */
    private static int[] bubbleSort(int[] datas) {
        int[] result = Arrays.copyOf(datas, datas.length);
        for (int i =0 ; i< datas.length; i++) {
            for(int j = 0; j < datas.length - i - 1; j++) {
                if(result[j] > result[j+1]) { //Swap
                    int tmp = result[j] ;
                    result[j] = result[j+1] ;
                    result[j+1] = tmp;
                }
            }
        }
        return result;
    }

    /**
     * 冒泡算法的改进，每次便利寻找最大值和最小值， 减少一半的遍历次数
     *
     * @param datas
     * @return
     */
    private static int[] bubbleSortImprove(int[] datas) {
        int[] result = Arrays.copyOf(datas, datas.length);
        int low = 0, high = datas.length - 1, j = 0, tmp = 0;
        while (low < high) {
            for (j = low; j < high; j++)  //Get Max value
                if (result[j] > result[j+1]) {
                    tmp = result[j] ;
                    result[j] = result[j+1] ;
                    result[j+1] = tmp;
                }
            --high;
            for ( j=high; j>low; j--)  //Get Mini Value
                if (result[j]<result[j-1]) {
                    tmp = result[j];
                    result[j]=result[j-1];
                    result[j-1]=tmp;
                }
            ++low;
        }
        return result;
    }

    /**
     * 快速排序算法， 不稳定的
     *时间复杂性:
     * 最好情况: O(nlogn)
     * 最差情况: O(n^2)
     * 平均情况: O(nlogn)
     *
     *
     * @param datas
     * @param start
     * @param end
     */
    private static void quickSort(int[] datas, int start, int end) {
        if (start < end) {
            int splitPoint = partition(datas, start, end);    //Swap number between splitPoint
            quickSort(datas, start, splitPoint - 1);     //Sort left side
            quickSort(datas, splitPoint + 1, end);      //Sort right side
        }
    }

    /**
     * Swap the data between splitPoint, make sure the numbers
     * between start : splitPoint is less than splitPoint's number
     * the numbers between splitPoint : end is larger than splitPoint's number.
     *
     * @param datas
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] datas, int start, int end) {
        int i = start, j = end + 1, x = datas[start];   //Here use datas[start] as split Point
        while (true) {
            while (datas[++i] < x && i < end);
            while (datas[--j] > x);
            if(i >= j) break;
            int tmp = datas[i]; //Swap two values.
            datas[i] = datas[j];
            datas[j] = tmp;
        }
        datas[start] = datas[j];
        datas[j] = x;
        return j;
    }

    /**
     * 简单选择排序， 稳定的
     * 时间复杂度:
     * 最好情况: O(n^2)
     * 最差情况: O(n^2)
     * 平均情况: O(n^2)
     *
     * @param datas
     * @return
     */
    private static int[] simpleSelectSort(int[] datas) {
        int[] result = Arrays.copyOf(datas, datas.length);
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++){
                if(result[i] > result[j]) {   //Swap
                    int tmp = result[i];
                    result[i] = result[j];
                    result[j] = tmp;
                }
            }
        }
        return result;
    }

    /**
     * 堆排序, 利用堆的特性进行选择排序 不稳定
     * 时间复杂度：
     * 最好,最坏,平均: O(nlogn)
     *
     * @param datas
     */
    private static void heapSort(int[] datas) {
        buildHeap(datas);   //Init array
        for ( int i = datas.length - 1; i >= 0; i--) {
            int tmp = datas[0];     //Swap root with last one
            datas[0] = datas[i];
            datas[i] = tmp;
            heapAdjust(datas, 0, i - 1);
        }
    }

    private static void buildHeap(int[] datas) {
        int size = datas.length - 1;
        for (int i = size / 2; i >= 0; i--) {
            heapAdjust(datas, i, size);
        }
    }

    private static void heapAdjust(int[] data, int i, int size) {
        int lChild = 2 * i + 1;
        int rChild = 2 * i + 2;
        int max = i;
        if( i <= size / 2) {
            if (lChild <= size && data[lChild] > data[max]) {
                max = lChild;
            }
            if (rChild <= size && data[rChild] > data[max]) {
                max = rChild;
            }
            if (max != i) {  //Current max value is not at root
                int tmp = data[i];  //Swap
                data[i] = data[max];
                data[max] = tmp;
                heapAdjust(data, max, size);    //Make sure the struct is right
            }
        }
    }


    /**
     * 归并排序算法, 稳定的
     *
     * 时间复杂度:
     * 最好,最坏,平均: O(nlogn)
     * @param datas
     */
    private static void mergeSort(int[] datas) {
        for (int gap = 1; gap < datas.length; gap = 2 * gap) {
            mergePass(datas, gap, datas.length);     //Merge two gag's length sorted array to a sorted array.
        }
    }

    private static void mergePass(int[] array, int gap, int length) {
        int i = 0;
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {    //Merge two adjacent gap length's datas
            merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }
        if (i + gap - 1 < length) {
            //If left datas' length is larger than a gap which means
            // the left datas need to sort. else, the data is already sorted.
            merge(array, i, i + gap - 1, length - 1);
        }
    }

    private static void merge(int[] array, int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        int[] array2 = new int[high - low + 1];
        while (i <= mid && j <= high) {  //Merge target number into array2
            if (array[i] <= array[j]) {
                array2[k] = array[i];
                i++;
                k++;
            } else {
                array2[k] = array[j];
                j++;
                k++;
            }
        }
        while (i <= mid) { //Left array still have some datas.
            array2[k] = array[i];
            i++;
            k++;
        }
        while (j <= high) { //Right array still have some datas.
            array2[k] = array[j];
            j++;
            k++;
        }
        for (k = 0, i = low; i <= high; i++, k++) { //Store result
            array[i] = array2[k];
        }
    }

    /**
     * 简单桶排序算法, 对空间的要求极大, 牺牲空间换时间
     * 时间复杂度: 对于n个数据, m个桶
     * 最好情况: O(n)
     * 平均情况: O(n+c) C=n*(logn-logm)
     *
     *
     * @param datas
     * @return
     */
    private static int[] bucketSort(int[] datas) {
        //Get max value
        int max = Integer.MIN_VALUE;
        for (int i : datas) {
            if (i > max) {
                max = i;
            }
        }
        int[] buckets = new int[max + 1];
        for (int data : datas) {
            buckets[data]++;
        }
        //Get Result
        int[] result = new int[datas.length];
        int i = 0;
        for (int j = 0; j < buckets.length; j++) {
            int tmp = buckets[j];
            if (tmp != 0) {
                while(tmp > 0) {
                    result[i++] = j;
                    tmp--;
                }
            }
        }
        return result;
    }

    /**
     * 从data中选择第k大的数据
     *
     * @param data 数据
     * @param p 开始点, 初始情况请赋值0
     * @param r 结束点, 初始情况请赋值最大值
     * @param k 第k大
     * @return
     */
    private static int randomizedSelect(int[] data, int p, int r, int k) {
        if (p == r) return data[p];
        int i = randomizedPartition(data, p, r), j = i - p + 1;
        if (k <= j)
            return randomizedSelect(data, p ,i , k);
        else
            return randomizedSelect(data, i + 1, r, k - j);
    }

    /**
     * 改方法可用于快速选择的优化
     *
     * @param data
     * @param p
     * @param r
     * @return
     */
    private static int randomizedPartition(int[] data, int p, int r) {
        Random random = new Random(System.currentTimeMillis());
        int i = random.nextInt(r -  p) + r;
        return partition(data, p, r);
    }
}
