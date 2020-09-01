package mylist;

import java.util.Iterator;
import java.util.ListIterator;

public class MyDLinkedList<E> implements MyList<E> {
  private Node<E> head;
  private Node<E> tail;
  private int size;

  public static void main(String[] args) {
    MyList<Integer> integers = new MyDLinkedList<>();
    integers.add(1);
    integers.add(2);
    integers.add(3);
    integers.add(4);
    integers.add(5);
//    integers.deleteByObject(3);
    System.out.println(integers);
    ListIterator<Integer> iterator = integers.listIterator();
//    System.out.println(iterator.next());
//    System.out.println(iterator.next());
//    System.out.println(iterator.next());
//    integers.deleteByObject(4);
    System.out.println(iterator.next());
//    System.out.println(iterator.next());
    iterator.remove();
    System.out.println(iterator.next());
    System.out.println(integers);
//    System.out.println(iterator.previous());
//    System.out.println(iterator.previous());
//    int b = iterator.previous();
//    System.out.println(b);
//    System.out.println(iterator.previous());
//    System.out.println(integers);

//    MyList<Integer> integerMyList = new MyDLinkedList<>();
//    integerMyList.add(1);
//    integerMyList.add(2);
//    integerMyList.add(3);
//    integerMyList.add(4);
//    integerMyList.add(5);
//    System.out.println(integerMyList);
//    integerMyList.clear();
//    System.out.println(integerMyList);
//    integerMyList.add(6);
//    integerMyList.add(7);
//    integerMyList.addFirst(0);
//    integerMyList.addFirst(-1);
//    integerMyList.addFirst(-2);
//    integerMyList.addFirst(-3);
//    System.out.println(integerMyList.get(1));
//    System.out.println(integerMyList.get(7));
//    System.out.println(integerMyList);
//    System.out.println(integerMyList.contains(23));
//    integerMyList.set(44, 7);
//    integerMyList.addLast(55);
//    System.out.println(integerMyList);
//    integerMyList.add(33, 3);
//    integerMyList.deleteByIndex(3);
//    System.out.println(integerMyList);
//    integerMyList.deleteByObject(3);
//    System.out.println(integerMyList);
//    integerMyList.deleteLast();
//    System.out.println(integerMyList);
  }

  @Override
  public void add(E e) {
    addLast(e);
  }

  @Override
  public E get(int index) {
    if (isEmpty() || index < 0 || index > size) throw new IndexOutOfBoundsException();
    if (index == 0) {
      return head.e;
    }
    if (index == size) {
      return tail.e;
    }
    if (index < size / 2) {
      int count = 0;
      Node<E> head = this.head;
      while (true) {
        if (index == count) {
          return head.e;
        }
        count++;
        head = head.next;
      }
    } else {
      int count = size - 1;
      Node<E> tail = this.tail;
      while (true) {
        if (index == count) {
          return tail.e;
        }
        count--;
        tail = tail.prev;
      }
    }
  }

  @Override
  public void deleteByObject(E e) {
    if (isEmpty()) throw new IndexOutOfBoundsException("isEmpty");
    if (head.e.equals(e)) {
      Node<E> next = head.next;
      head.e = null;
      head.next = null;
      head = next;
      size--;
    }
    if (tail.e.equals(e)) {
      tail.e = null;
      Node<E> prev = tail.prev;
      tail.prev = null;
      tail = prev;
      prev.next = null;
      size--;
    } else {
      Node<E> current = head;
      while (current != null) {
        if (current.e.equals(e)) {
          Node<E> next = current.next;
          Node<E> prev = current.prev;
          current.prev = null;
          current.next = null;
          current.e = null;
          prev.next = next;
          next.prev = prev;
          size--;
          return;
        }
        current = current.next;
      }
    }
  }

  @Override
  public void deleteByIndex(int index) {
    if (isEmpty() || index > size || index < 0) throw new IndexOutOfBoundsException("Error");
    int count = 0;
    if (index == 0) {
      deleteFirst();
    }
    if (index == size) {
      deleteLast();
    }
    Node<E> next = head.next;
    while (true) {
      if (count + 1 == index) {
//      if (index < size / 2) {
        next.next.e = null;
        next.next.next.prev = null;
        Node<E> next1 = next.next.next;
        next.next = null;
        next.next = next1;
        size--;
        return;
      }
      next = next.next;
      //       else {
//        Node<E> prev = tail.prev.prev;
//        tail.prev.e = null;
//        tail.prev = null;
//        tail.prev = prev;
//        size--;
//      }
    }
  }


  @Override
  public int size() {
    return size;
  }

  @Override
  public E set(E e, int index) {
    if (isEmpty() || index < 0 || index > size) throw new IndexOutOfBoundsException();
    if (index == 0) {
      E e1 = head.e;
      head.e = e;
      return e1;
    }
    if (index == size) {
      E e1 = tail.e;
      tail.e = e;
      return e1;
    }
    if (index < size / 2) {
      int count = 0;
      Node<E> head = this.head;
      while (true) {
        if (index == count) {
          E e1 = head.e;
          head.e = e;
          return e1;
        }
        count++;
        head = head.next;
      }
    } else {
      int count = size - 1;
      Node<E> head = this.tail;
      while (true) {
        if (index == count) {
          E e1 = head.e;
          head.e = e;
          return e1;
        }
        count--;
        head = head.prev;
      }
    }
  }

  @Override
  public boolean contains(E e) {
    if (isEmpty()) throw new IndexOutOfBoundsException();
    if (e.equals(head)) return true;
    if (e.equals(tail)) return true;
    Node<E> head = this.head;
    while (head != null) {
      if (e.equals(head)) return true;
      head = head.next;
    }
    return false;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void add(E e, int index) {
    Node<E> first = head;
    int count = 0;
    while (count < size) {
      if (count == index) {
        first.next = new Node<>(e, first.next, first);
        size++;
      }
      first = first.next;
      count++;
      if (first.next == null) break;
    }
  }

  @Override
  public E[] toArray() {
    Object[] e = new Object[size];
    Node<E> head = this.head;
    for (int i = 0; i < e.length; i++) {
      e[i] = head.e;
      head = head.next;
    }
    return ((E[]) e);
  }

  @Override
  public void clear() {
    Node<E> next = head;
    while (size > 0) {
      next.prev = null;
      next.e = null;
      next = next.next;
      size--;
    }
  }

  @Override
  public MyList<E> subList(int i, int ii) {
    return null;
  }

  @Override
  public void addFirst(E e) {
    if (isEmpty()) {
      Node<E> eNode = new Node<>(e, null, null);
      head = eNode;
      tail = eNode;
      size++;
    } else {
      Node<E> newNode = new Node<>(e, head, null);
      head.prev = newNode;
      head = newNode;
      size++;
    }
  }

  @Override
  public void addLast(E e) {
    if (isEmpty()) {
      Node<E> eNode = new Node<>(e, null, null);
      head = eNode;
      tail = eNode;
      size++;
      return;
    }
    if (size == 1) {
      Node<E> eNode = new Node<>(e, null, head);
      head.next = eNode;
      tail = eNode;
      size++;
    } else {
      Node<E> lastNoda = new Node<>(e, null, tail);
      tail.next = lastNoda;
      tail = lastNoda;
      size++;
    }
//    27.11.2019 Реализовать случай без цикла +
//    Node<E> head = this.head;
//    while (true) {
//      if (head.next == null) {
//        Node<E> newNode = new Node<>(e, null, head);
//        head.next = newNode;
//        this.tail = newNode;
//        size++;
//        return;
//      }
//      head = head.next;
//    }
  }

  @Override
  public void deleteFirst() {
    if (isEmpty()) throw new IndexOutOfBoundsException();
    if (head.next == null) {
      head.e = null;
      head = null;
      size--;
    } else {
      head.e = null;
      Node<E> next = head.next;
      head.next = null;
      head = next;
      size--;
    }
  }

  @Override
  public void deleteLast() {
    if (isEmpty()) throw new IndexOutOfBoundsException();
    if (size == 1) {
      deleteFirst();
    } else {
      tail.e = null;
      Node<E> prev = tail.prev;
      tail.prev = null;
      tail = prev;
      prev.next = null;
      size--;
    }
  }

  @Override
  public Iterator<E> iterator() {
    return new Itr();
  }

  @Override
  public ListIterator<E> listIterator() {
    return new ListItr();
  }

  @Override
  public String toString() {
    return "MyDLinkedList{" +
            "head=" + head +
            ", tail=" + tail +
            ", size=" + size +
            '}';
  }

  private static class Node<E> {
    E e;
    Node<E> next;
    Node<E> prev;

    public Node(E e, Node<E> next, Node<E> prev) {
      this.e = e;
      this.next = next;
      this.prev = prev;
    }

    @Override
    public String toString() {
      return "Node{" +
              "e=" + e +
              ", next=" + next +
//              ", prev=" + prev +
              '}';
    }
  }

  private class Itr implements Iterator<E> {
    boolean isNew = true;
    private Node<E> current;
    private int count = 0;

    @Override
    public boolean hasNext() {
      return current.next != null;
    }

    @Override
    public E next() {
      if (isNew) {
        current = head;
        isNew = false;
        return current.e;
      }
      current = current.next;
      return current.e;
    }

    @Override
    public void remove() {
      if (current == null) return;
      E e = current.e;
      current = current.next;
      deleteByObject(e);
    }
  }

  private class ListItr implements ListIterator<E> {
    boolean isNew = true;
    private Node<E> current;
    private int count = 0;

    @Override
    public boolean hasNext() {
      if (current == null) return false;
      return current.next != null;
    }

    @Override
    public E next() {
      if (isNew) {
        current = head;
        isNew = false;
        return current.e;
      }
      current = current.next;
      return current.e;
    }

    @Override
    public boolean hasPrevious() {
      if (current == null) return false;
      return current.prev != null;
    }

    @Override
    public E previous() {
      if (isNew) {
        current = tail;
        isNew = false;
        count = size - 1;
        return current.e;
      }
      count--;
      current = current.prev;
      return current.e;
    }

    @Override
    public int nextIndex() {
      return count + 1;
    }

    @Override
    public int previousIndex() {
      return count - 1;
    }

    @Override
    public void remove() {
      if (current == null) return;
      E e = current.e;
      current = current.next;
      deleteByObject(e);
    }

    @Override
    public void set(E e) {
      MyDLinkedList.this.set(e, count);
    }

    @Override
    public void add(E e) {
      addLast(e);
    }
  }
}
