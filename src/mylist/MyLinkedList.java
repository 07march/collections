package mylist;

import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<T> implements MyList<T> {
  private Node<T> root;
  private int size;

  public MyLinkedList() {
    this.size = 0;
  }

  public static void main(String[] args) {
    MyList<User> integerMyList = new MyLinkedList<>();
    integerMyList.add(new User("Test1"));
    integerMyList.add(new User("Test2"));
    integerMyList.add(new User("Test3"));
    integerMyList.add(new User("Test4"));
    integerMyList.add(new User("Test5"));
    System.out.println(integerMyList);
//    integerMyList.addFirst(new User("Nov"));
//    System.out.println(integerMyList);
//    integerMyList.add(new User("Test23"), 2);
//    System.out.println(integerMyList);
//    integerMyList.deleteByObject(new User("Test4"));
//    System.out.println(integerMyList);
//    integerMyList.clear();
//    integerMyList.deleteLast();
//    System.out.println(integerMyList);
//    integerMyList.deleteByObject(new User("Test1"));
//    System.out.println(integerMyList);
    Iterator<User> iteratorList = integerMyList.iterator();
    System.out.println(iteratorList.next());
    System.out.println(iteratorList.next());
    iteratorList.remove();
    System.out.println(integerMyList);

  }

  @Override
  public void add(T t) {
    addLast(t);
  }

  @Override
  public String toString() {
    return "MyLinkedList{" +
            "root=" + root +
            ", size=" + size +
            '}';
  }

  @Override
  public T get(int index) {
    int count = 0;
    Node<T> node = root;
    while (count < size) {
      if (count == index) return (T) node;
      count++;
      if (node.next == null) break;
      node = node.next;
    }
    return null;
  }

  @Override
  public void deleteByObject(T t) {
    if (isEmpty()) throw new IndexOutOfBoundsException("isEmpty");
    Node<T> node = root;
    if (node.t.equals(t)) {
      node.t = null;
      root = node.next;//@009
      node.next = null;
      size--;
    } else {
      while (true) {
        if (node.next.t.equals(t)) {
          Node<T> next = node.next.next;
          node.next.next = null;
          node.next.t = null;
          node.next = next;
          size--;
          return;
        }
        node = node.next;
      }
    }
  }

  @Override
  public void deleteByIndex(int index) {
    if (isEmpty() || index > size || index < 0) throw new IndexOutOfBoundsException("Error");
    Node<T> node = root;
    int count = 0;
    if (index == 0) {
      deleteFirst();
    }
    while (true) {
      if (count + 1 == index) {
        if (node.next.next == null) {
          node.next.t = null;
          node.next = null;
          size--;
          return;
        } else {
          Node<T> next = node.next.next;
          node.next.t = null;
          node.next = next;
          size--;
          return;
        }
      }
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T set(T t, int index) {
    int count = 0;
    Node<T> node = root;
    while (count < size) {
      if (count == index) {
        node.t = t;
      }
      count++;
      if (node.next == null) break;
      node = node.next;
    }
    return null;
  }

  @Override
  public boolean contains(T t) {
    Node<T> a = root;
    if (a.t.equals(t)) {
      return true;
    } else {
      while (a != null) {
        if (a.t.equals(t)) {
          return true;
        } else {
          a = a.next;
        }
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
    int count = 0;
    Node<T> a = root;
    while (count < size) {
      if (count == index) {
        a.t = t;
        size++;
      }
      count++;
      if (a.next == null) break;
      a = a.next;
    }
  }

  @Override
  public T[] toArray() {
    return null;
  }

  @Override
  public void clear() {
    while (size > 0) {
      Node<T> noda = this.root;
      noda = noda.next;
      noda.t = null;
      noda.next = null;
      size--;
    }
    root = root.next;
  }

  @Override
  public MyList<T> subList(int i, int ii) {
    return null;
  }

  @Override
  public void addFirst(T t) {
    if (isEmpty()) {
      root = new Node<>(t, null);
      size++;
    } else {
      root = new Node<>(t, root);
      size++;
    }
  }

  @Override
  public void addLast(T t) {
    if (isEmpty()) {
      addFirst(t);
      return;
    }
    if (size == 1) {
      root.next = new Node<>(t, null);
      size++;
      return;
    }
    Node<T> root = this.root;
    while (true) {
      if (root.next == null) {
        root.next = new Node<>(t, null);
        size++;
        return;
      }
      root = root.next;
    }
  }

  @Override
  public void deleteFirst() {
    if (isEmpty()) throw new IndexOutOfBoundsException();
    if (root.next == null) {
      root.t = null;
      root = null;
      size--;
    } else {
      root.t = null;
      Node<T> next = root.next;
      root.next = null;
      root = next;
      size--;
    }
  }

  @Override
  public void deleteLast() {
    if (isEmpty()) throw new IndexOutOfBoundsException();
    Node<T> root = this.root;
    if (size == 1) {
      deleteFirst();
      return;
    }
    while (true) {
      if (root.next.next == null) {
        root.next.t = null;
        root.next = null;
        size--;
        return;
      }
      root = root.next;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new IterList();
  }

  @Override
  public ListIterator<T> listIterator() {
    return new IterLinkedList();
  }

  class Node<T> {
    T t;
    Node<T> next;

    public Node(T t, Node<T> next) {
      this.t = t;
      this.next = next;
    }

    @Override
    public String toString() {
      return "Node{" +
              "t=" + t +
              ", next=" + next +
              '}';
    }
  }

  private class IterList implements Iterator<T> {
    private Node<T> current;
    boolean isNew = true;

    @Override
    public boolean hasNext() {
      return current.next !=null;
    }

    @Override
    public T next() {
      if (isNew) {
        current = root;
        isNew = false;
        return current.t;
      }
      current = current.next;
      return current.t;
    }

    @Override
    public void remove() {
      deleteByObject(current.t);
    }
  }

  private class IterLinkedList implements ListIterator<T> {
    private Node<T> current;
    boolean isNew = true;
    int count = 0;

    @Override
    public boolean hasNext() {
      return current.next != null;
    }

    @Override
    public T next() {
      if (isNew) {
        current = root;
        isNew = false;
        return current.t;
      }
      current = current.next;
      return current.t;
    }

    @Override
    public boolean hasPrevious() {
      return Boolean.parseBoolean(null);
    }

    @Override
    public T previous() {
      return null;
    }

    @Override
    public int nextIndex() {
      return count + 1;
    }

    @Override
    public int previousIndex() {
      return Integer.parseInt(null);
    }

    @Override
    public void remove() {
      MyLinkedList.this.deleteByObject(current.t);
    }

    @Override
    public void set(T t) {
      MyLinkedList.this.set(t, count);
    }

    @Override
    public void add(T t) {
      addLast(t);
    }
  }
}
