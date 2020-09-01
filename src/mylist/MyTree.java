package mylist;

public interface MyTree<E> {
  void add(E e);
  void remove(E e);
  E set(E e, E ee);
  boolean isEmpty();
  int size();
  boolean contains(E e);
  E get(E e);
}
