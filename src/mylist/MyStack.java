package mylist;

public interface MyStack<E> {
  void push(E e);
  E peek();
  E pop();
  boolean empty();
}
