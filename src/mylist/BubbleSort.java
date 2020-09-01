package mylist;

import java.util.Arrays;

public class BubbleSort<E> {


  public static void main(String[] args) {
    Integer[] integers = new Integer[5];
    for (int i = 0, j = integers.length; i < integers.length; i++, j--) {
      integers[i] = j;
    }
    BubbleSort<Integer> integerBubbleSort = new BubbleSort<>();
    Integer[] sort = integerBubbleSort.sort(integers);
    System.out.println(Arrays.toString(sort));
  }


  //O(n^2)
  //5 4 3 2 1 - 25
  public Integer[] sort(Integer[] e) {
    System.out.println(Arrays.toString(e));
    for (int i = e.length - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (e[j] > e[j + 1]) {
          swap(e, j);
        }
      }
    }
    return e;
  }

  private void swap(Integer[] e, int i) {
    Integer integer = e[i];
    e[i] = e[i + 1];
    e[i + 1] = integer;
  }
}
