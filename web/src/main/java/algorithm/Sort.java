package algorithm;

import clojure.main;
import junit.framework.TestCase;

/**
 * @Description 排序算法
 * @Author xuedong.wang
 * @Date 17/9/27.
 */
public class Sort extends TestCase {

    /**
     * 冒泡排序
     */
    public void testMsort(int[] numbers) {

        int i, j, temp, size = numbers.length;

        for (i = 0; i < size; i++) {

            for (j = 0; j < size - i - 1; j++) {

                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        sys(numbers);
    }

    /**
     * 选择排序
     * 我就从所有序列中先找到最小的，然后放到第一个位置。之后再看剩余元素中最小的，放到第二个位置……以此类推
     *
     * @param numbers
     */
    public void testSelectSort(int[] numbers) {
        int i, j, temp, index, size = numbers.length;

        for (i = 0; i < size - 1; i++) {
            index = i;
            for (j = i + 1; j < size; j++) {

                if (numbers[j] < numbers[index]) {
                    index = j;
                }
            }

            if (index != i) {
                temp = numbers[i];
                numbers[i] = numbers[index];
                numbers[index] = temp;
            }
        }
        sys(numbers);
    }

    /**
     * 插入排序
     * 每次将一个待排序的数据元素，插入到前面已经排好序的数列中的适当位置，使数列依然有序；直到待排序数据元素全部插入完为止
     *
     * @param numbers
     */
    public void insertSort(int[] numbers) {
        int i, j, t, size = numbers.length;

        for (i = 1; i < size; i++) {
            t = numbers[i]; //需要插入的数据
            j = i - 1; //插入的位置

            while (j >= 0 && t < numbers[j]) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = t;
            sys(numbers);
        }
        sys(numbers);
    }

    public static void sys(int[] numbers) {

        for (int i = 0; i < numbers.length; i++) {
            System.out.print("  " + numbers[i]);
        }
        System.out.println("  ");
    }

    public static void main(String[] args) {

        int[] numbers = {5, 23, 6, 7, 34, 20, 15, 3, 54, 12};
        Sort sort = new Sort();
        //sort.testMsort(numbers);
        //sort.testSelectSort(numbers);
        sort.insertSort(numbers);
    }

}
