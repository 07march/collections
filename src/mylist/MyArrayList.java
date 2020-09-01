package mylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class MyArrayList<T> implements MyList<T> {
  private final int DEFAULT_SIZE = 16;
  private Object[] objects;
  private int size;

  public MyArrayList() {
    this.objects = new Object[DEFAULT_SIZE];
    this.size = 0;
  }

  public static void main(String[] args) {
    MyArrayList<Integer> objectMyArrayList = new MyArrayList<>();
    objectMyArrayList.add(1);
    objectMyArrayList.add(2);
    objectMyArrayList.add(3);
    objectMyArrayList.add(4);
    System.out.println(objectMyArrayList);
    ListIterator<Integer> iterator = objectMyArrayList.listIterator();
//    while (iterator.hasNext()) {
//      System.out.println(iterator.next());
//    }
//    iterator.next();
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.previous());
    System.out.println(iterator.previous());


  }

  @Override
  public void add(T t) {
    if (size == objects.length) {
      grow();
    }
    objects[size++] = t;
  }

  private void grow() {
    Object[] newArr = new Object[objects.length * 2];
    System.arraycopy(objects, 0, newArr, 0, objects.length);
    objects = newArr;
  }

  @Override
  public T get(int index) {
    if (index > size) throw new IndexOutOfBoundsException("index > size");
    return (T) objects[index];
  }

  @Override
  public void deleteByObject(T t) {
    for (int i = 0; i < objects.length; i++) {
      if (objects[i].equals(t)) {
        if (objects.length - 1 - i >= 0) System.arraycopy(objects, i + 1, objects, i, objects.length - 1 - i);
        size--;
      }
    }
  }

  @Override
  public void deleteByIndex(int index) {
    if (index > size) throw new IndexOutOfBoundsException("index > size");
    if (objects.length - 1 - index >= 0)
      System.arraycopy(objects, index + 1, objects, index, objects.length - 1 - index);
    size--;
  }

  @Override
  public int size() {
    return size;
  }

  //O(1)
  //O(log n)
  //O(n/2)
  //O(n)
  //O(n*2)
  @Override
  public T set(T t, int index) {
    if (index > size) throw new IndexOutOfBoundsException("index > size");
    Object object = objects[index];
    objects[index] = t;
    return (T) object;
  }

  @Override
  public boolean contains(T t) {
    for (Object object : objects) {
      if (object == null) break;
      if (object.equals(t)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void add(T t, int index) {
    if (index > size) throw new IndexOutOfBoundsException("index > size");
    if (size == objects.length) grow();
    if (objects.length - index >= 0) System.arraycopy(objects, index, objects, index + 1, objects.length - 1 - index);
    objects[index] = t;
    size++;
  }

  @Override
  public T[] toArray() {
    Object[] objects1 = new Object[size];
    for (int i = 0; i < objects.length; i++) {
      if (objects[i] == null) break;
      objects1[i] = objects[i];
    }
    return (T[]) objects1;
  }

  @Override
  public void clear() {
    objects = new Object[DEFAULT_SIZE];
  }

  @Override
  public MyList<T> subList(int i, int ii) {
    if (i > size || ii > size) throw new IndexOutOfBoundsException("i or ii > size");
    Object[] objects1 = new Object[ii - i + 1];
    for (int j = i, jj = 0; j <= ii; j++, jj++) {
      objects1[jj] = objects[j];
    }
    return new SubList<>(objects1, 10);
  }

  @Override
  public void addFirst(T t) {

  }

  @Override
  public void addLast(T t) {

  }

  @Override
  public void deleteFirst() {

  }

  @Override
  public void deleteLast() {

  }

  @Override
  public Iterator<T> iterator() {
    return new Itr();
  }

  @Override
  public ListIterator<T> listIterator() {
    return new ListItr();
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (Object o : objects) {
      if (o == null) break;
      stringBuilder.append(o).append(", ");
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  private static class SubList<T> implements MyList<T> {
    private final int SUB_DEFAULT_SIZE = 16;
    private Object[] arr;
    private int subSize;

    public SubList(Object[] arr, int subSize) {
      this.arr = new Object[SUB_DEFAULT_SIZE];
      this.subSize = 0;
    }

    private void grow() {
      Object[] newObj = new Object[arr.length * 2];
      System.arraycopy(arr, 0, newObj, 0, arr.length);
      newObj = arr;
    }

    @Override
    public void add(T t) {

    }

    @Override
    public T get(int index) {
      if (index > subSize) throw new IndexOutOfBoundsException("index > subSize");
      return (T) arr[index];
    }

    @Override
    public void deleteByObject(T t) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i].equals(t)) {
          if (arr.length - 1 - i >= 0) System.arraycopy(arr, i + 1, arr, i, arr.length - 1 - i);
          subSize--;
        }
      }
    }

    @Override
    public void deleteByIndex(int index) {
      if (index > subSize) throw new IndexOutOfBoundsException("index > subSize");
      if (arr.length - 1 - index >= 0) System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
      subSize--;
    }

    @Override
    public int size() {
      return subSize;
    }

    @Override
    public T set(T t, int index) {
      Object ort = arr[index];
      arr[index] = t;
      return (T) ort;
    }

    @Override
    public boolean contains(T t) {
      for (Object array : arr) {
        if (array == null) break;
        if (array.equals(t)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public boolean isEmpty() {
      return subSize == 0;
    }

    @Override
    public void add(T t, int index) {

    }

    @Override
    public T[] toArray() {
      Object[] massive = new Object[subSize];
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == null) break;
        massive[i] = arr[i];
      }
      return (T[]) massive;
    }

    @Override
    public void clear() {
      arr = new Object[SUB_DEFAULT_SIZE];
    }

    @Override
    public MyList<T> subList(int i, int ii) {
      return null;
    }

    @Override
    public void addFirst(T t) {

    }

    @Override
    public void addLast(T t) {

    }

    @Override
    public void deleteFirst() {

    }

    @Override
    public void deleteLast() {

    }

    @Override
    public Iterator<T> iterator() {
      return null;
    }

    @Override
    public ListIterator<T> listIterator() {
      return null;
    }

    @Override
    public String toString() {
      return "SubList{" +
              "arr=" + Arrays.toString(arr) +
              '}';
    }
  }

  private class Itr implements Iterator<T> {
    int cursor;
    boolean isNew = true;

    @Override
    public boolean hasNext() {
      return objects[cursor] != null;
    }

    @Override
    public T next() {
      if (isNew) {
        isNew = false;
        return (T) objects[0];
      }
      Object object = objects[cursor];
      if (cursor < objects.length) {
        cursor++;
      }
      return (T) object;
    }

    @Override
    public void remove() {
      if (objects.length - 1 - cursor >= 0)
        System.arraycopy(objects, cursor + 1, objects, cursor, objects.length - 1 - cursor);
    }
  }

  private class ListItr implements ListIterator<T> {
    int cursor;
    boolean isNew = true;

    @Override
    public boolean hasNext() {
      return objects[cursor] != null;
    }

    @Override
    public T next() {
      if (isNew) {
        isNew = false;
        cursor++;
        return (T) objects[0];
      }
      Object object = objects[cursor];
      if (cursor < objects.length - 1) {
        cursor++;
      }
      return (T) object;
    }

    @Override
    public boolean hasPrevious() {
      if (isNew) return false;
      return objects[cursor - 1] != null;
    }

    @Override
    public T previous() {
      if (isNew) {
        isNew = false;
        return (T) objects[0];
      }
      Object object = objects[cursor];
      if (cursor != 0) {
        cursor--;
      }
      return (T) object;
    }

    @Override
    public int nextIndex() {
      return cursor + 1;
    }

    @Override
    public int previousIndex() {
      return cursor - 1;
    }

    @Override
    public void remove() {
      if (objects.length - 1 - cursor >= 0)
        System.arraycopy(objects, cursor + 1, objects, cursor, objects.length - 1 - cursor);
    }

    @Override
    public void set(T t) {
      if (!isNew) {
        objects[cursor] = t;
      }
    }

    @Override
    public void add(T t) {
      grow();
      if (isNew) {
        if (objects.length - 1 >= 0) System.arraycopy(objects, 0, objects, 1, objects.length - 1);
        objects[0] = t;
        isNew = false;
        return;
      }
      if (objects.length - 1 >= 0) System.arraycopy(objects, cursor, objects, cursor + 1, objects.length - 1 - cursor);
      objects[cursor] = t;
    }
  }
}
