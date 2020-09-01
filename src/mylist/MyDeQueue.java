package mylist;

public interface MyDeQueue<E> {
  boolean isEmpty();
  void addFirst(E e);
  void addLast(E e);
  E removeFirst();
  E removeLast();
  E pollFirst();
  E pollLast();
  E peekFirst();
  E peekLast();
  E getFirst();
  E getLast();
  E poll();
  E peek();
  void push(E e);
  void add(E e);
}
