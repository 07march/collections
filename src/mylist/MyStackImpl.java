package mylist;

//  06.12.2019 Реализовать расширение массива
public class MyStackImpl<E> implements MyStack<E> {
  private Object[] arr;
  private int size = 0;

  public MyStackImpl() {
    this.arr = new Object[3];
  }

  public static void main(String[] args) {
    MyStackImpl<Integer> integerMyStack = new MyStackImpl<>();
    integerMyStack.push(1);
    integerMyStack.push(2);
    integerMyStack.push(3);
//    integerMyStack.push(4);
//    integerMyStack.push(5);
    System.out.println(integerMyStack.pop());
    System.out.println(integerMyStack.pop());
    System.out.println(integerMyStack.peek());
    System.out.println(integerMyStack.peek());
    System.out.println(integerMyStack.pop());
    System.out.println(integerMyStack.empty());
    //  06.12.2019 Добавить проверку на -1 индекс
    System.out.println(integerMyStack.pop());
  }

  private void grow() {
    Object[] grows = new Object[arr.length * 2];
    System.arraycopy(arr, 0, grows, 0, arr.length);
    arr = grows;
  }

  @Override
  public void push(E e) {
    if (size == arr.length) {
      grow();
    }
    arr[size++] = e;
  }

  @Override
  public E peek() {
    if (empty()) throw new IndexOutOfBoundsException("empty");
    return (E) arr[size - 1];
  }

  @Override
  public E pop() {
    if (empty()) throw new IndexOutOfBoundsException("empty");
    Object o = arr[size - 1];
    arr[size - 1] = null;
    size--;
    return (E) o;

  }

  @Override
  public boolean empty() {
    return size == 0;
  }
}
