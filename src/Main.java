import mylist.MyList;
import mylist.MyArrayList;

public class Main {

  public static void main(String[] args) {
    MyList<Integer> integerMyList = new MyArrayList<>();
    integerMyList.add(1);
    integerMyList.add(2);
    integerMyList.add(3);
    integerMyList.add(4);

    MyList<Integer> integerMyList1 = integerMyList.subList(1, 2);
    System.out.println(integerMyList1);
    System.out.println(integerMyList1.size());

  }
}
