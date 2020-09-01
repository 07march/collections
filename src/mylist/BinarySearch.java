package mylist;

import java.util.Collections;

//  08.12.2019 Реализовать двоичный поиск
public class BinarySearch {
  public static void main(String[] args) {
    Integer[] integers = new Integer[10000];
    for (int i = 0; i < integers.length; i++) {
      integers[0] = i;
    }
    BinarySearch binarySearch = new BinarySearch();
    binarySearch.biSerach(integers, 43);
  }

  public boolean biSerach(Integer[] e, int a) { //43
    int current = e.length / 2;
    int start = 0;
    int end = e.length;
    while (true) {
      if (current == a || end == a) {
        System.out.println("true");
        return true;
      } else {
        if (a > current) {//25 - 50
          start = current + 1;
          end = end - 1;
        }
        if (a < current) {
          end = current;
          current = end / 2;
        }
      }
    }
  }
}
