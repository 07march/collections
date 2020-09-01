package mylist;

import java.util.Arrays;

public class MyDeQueueImpl<E> implements MyDeQueue<E> {
  private Object[] arr;
  private int size;

  public MyDeQueueImpl() {
    this.arr = new Object[16];
    this.size = 0;
  }

  public static void main(String[] args) {
    MyDeQueue<Integer> integerMyDeQueue = new MyDeQueueImpl<>();
    integerMyDeQueue.addFirst(4);
    integerMyDeQueue.addFirst(3);
    integerMyDeQueue.addFirst(2);
    integerMyDeQueue.addFirst(1);
    integerMyDeQueue.addLast(5);
    integerMyDeQueue.addLast(6);
    integerMyDeQueue.addLast(7);
    System.out.println(integerMyDeQueue);
    integerMyDeQueue.poll();
    integerMyDeQueue.pollLast();
    System.out.println(integerMyDeQueue.peek());
    integerMyDeQueue.removeFirst();
    System.out.println(integerMyDeQueue);
  }

  private void grow() {
    Object[] grows = new Object[arr.length * 2];
    System.arraycopy(arr, 0, grows, 0, arr.length);
    arr = grows;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void addFirst(E e) {
    if (isEmpty()) {
      arr[0] = e;
      size++;
    } else {
      if (size == arr.length) {
        grow();
      }
      if (arr.length - 1 >= 0) System.arraycopy(arr, 0, arr, 1, arr.length - 1);
      arr[0] = e;
      size++;
    }
  }

  @Override
  public void addLast(E e) {
    if (isEmpty()) {
      arr[0] = e;
      size++;
    } else {
      if (size == arr.length){
        grow();
      }
      arr[size] = e;
      size++;
    }
  }

  @Override
  public E removeFirst() {
    if (isEmpty()) return null;
    Object o = arr[0];
    if (arr.length - 1 >= 0) System.arraycopy(arr, 1, arr, 0, arr.length - 1);
    size--;
    return (E) o;
  }

  @Override
  public E removeLast() {
    if (isEmpty()) return null;
    Object o = arr[size];
    arr[size - 1] = null;
    size--;
    return (E) o;
  }

  @Override
  public E pollFirst() {
    return removeFirst();
  }

  @Override
  public E pollLast() {
    return removeLast();
  }

  @Override
  public E peekFirst() {
    return getFirst();
  }

  @Override
  public E peekLast() {
    return getLast();
  }

  @Override
  public E getFirst() {
    if (isEmpty()) return null;
    return (E) arr[0];
  }

  @Override
  public E getLast() {
    if (isEmpty()) return null;
    return (E) arr[size];
  }

  @Override
  public E poll() {
    return removeFirst();
  }

  @Override
  public E peek() {
    return getFirst();
  }

  @Override
  public void push(E e) {
    addFirst(e);
  }

  @Override
  public void add(E e) {
    addLast(e);
  }

  @Override
  public String toString() {
    return "MyDeQueueImpl{" +
            "arr=" + Arrays.toString(arr) +
            ", size=" + size +
            '}';
  }
}
