package mylist;

import java.util.Iterator;
import java.util.ListIterator;

public interface MyList<T> {
  void add(T t);
  T get(int index);
  void deleteByObject(T t);
  void deleteByIndex(int index);
  int size();

  T set(T t, int index);
  boolean contains(T t);
  boolean isEmpty();
  void add(T t, int index);

  T[] toArray();
  void clear();
  MyList<T> subList(int i, int ii);

  void addFirst(T t);
  void addLast(T t);
  void deleteFirst();
  void deleteLast();

  Iterator<T> iterator();
  ListIterator<T> listIterator();
}
