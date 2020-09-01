package mylist;

import java.util.Comparator;

//  11.12.2019 реализовать удаление нод у кот есть два пот(3 случ)
public class MyTreeImpl<E> implements MyTree<E> {
  private Node<E> root;
  private int size;
  private Comparator<E> comparator;

  public MyTreeImpl(Comparator<E> comparator) {
    this.size = 0;
    this.comparator = comparator;
  }

  public static void main(String[] args) {
    MyTree<Integer> integerMyTree = new MyTreeImpl<>(Integer::compareTo);
    integerMyTree.add(56);
    integerMyTree.add(34);
    integerMyTree.add(78);
    integerMyTree.add(64);
    integerMyTree.add(81);
    integerMyTree.add(23);
    System.out.println(integerMyTree);
//    System.out.println(integerMyTree.contains(78));
//    integerMyTree.remove(34);
//    System.out.println(integerMyTree.contains(78));
//    System.out.println(integerMyTree);

    System.out.println(integerMyTree.set(81, 20));
//    System.out.println(integerMyTree.contains(78));
//    System.out.println(integerMyTree.contains(55));
    System.out.println(integerMyTree);


  }

  @Override
  public void add(E e) {
    if (isEmpty()) {
      root = new Node<>(null, null, null, e);
      size++;
    } else {
      Node<E> node = root;
      while (true) {
        if (comparator.compare(node.e, e) == 1) {
          if (node.left == null) {
            node.left = new Node<>(node, null, null, e);
            size++;
            return;
          } else {
            node = node.left;
          }
        } else if (comparator.compare(node.e, e) == -1) {
          if (node.right == null) {
            node.right = new Node<>(node, null, null, e);
            size++;
            return;
          } else {
            node = node.right;
          }
        }
      }
    }
  }

  @Override
  public void remove(E e) {
    Node<E> node = root;
    while (true) {
      if (comparator.compare(node.e, e) == 1) {
        if (node.left == null) break;
        if (node.left.e.equals(e)) {
          if (node.left.left == null && node.left.right == null) {
            node.left.e = null;
            node.left = null;
            size--;
            return;
          } else if (node.left.left != null && node.left.right != null) {
//  11.12.2019 Реализовать 3 случ
            Node<E> left = node.left.left;
            Node<E> right = node.left.right;
            if (right.left == null && right.right == null) {
              node.left.right = null;
              node.left.e = null;
              node.left = null;
              node.left = right;
              right.left = left;
              size--;
            } else {
              node.left.left = null;
              node.left.e = null;
              node.left = null;
              node.left = left;
              left.right = right;
              size--;
            }
          } else {
            if (node.left.left != null) {
              Node<E> left = node.left.left;
              node.left.left = null;
              node.left.e = null;
              node.left.root = null;
              node.left = left;
              size--;
              return;
            } else if (node.left.right != null) {
              Node<E> right = node.left.right;
              node.left.right = null;
              node.left.e = null;
              node.left.root = null;
              node.left = right;
              size--;
              return;
            }
          }
        } else {
          node = node.left;
        }
      } else if (comparator.compare(node.e, e) == -1) {
        if (node.right == null) break;
        if (node.right.e.equals(e)) {
          if (node.right.left == null && node.right.right == null) {
            node.right.e = null;
            node.right = null;
            size--;
            return;
          } else if (node.right.left != null && node.right.right != null) {
//  11.12.2019 Реализовать 3 случ
            Node<E> left = node.right.left;
            Node<E> right = node.right.right;
            if (right.left == null && right.right == null) {
              node.right.right = null;
              node.right.e = null;
              node.right = null;
              node.right = right;
              right.left = left;
              size--;
            } else {
              node.right.left = null;
              node.right.e = null;
              node.right = null;
              node.right = left;
              left.right = right;
              size--;
            }
          } else {
            if (node.right.left != null) {
              Node<E> left = node.right.left;
              node.right.left = null;
              node.right.e = null;
              node.right.root = null;
              node.right = left;
              size--;
              return;
            } else if (node.right.right != null) {
              Node<E> right = node.right.right;
              node.right.right = null;
              node.right.e = null;
              node.right.root = null;
              node.right = right;
//              node.root = right;
//              node.right.e = null;
//              node.right.root = null;
              size--;
              return;
            }
          }
        } else {
          node = node.right;
        }
      }
    }
  }

  @Override
  public E set(E e, E ee) {
    if (root.e.equals(ee)) {
      return root.e;
    }
    Node<E> node = root;
    while (true) {
      if (comparator.compare(node.e, e) == 1) {
        if (node.left == null) break;
        if (node.left.e.equals(e)) {
          E e1 = node.left.e;
          node.left.setE(ee);
          // 11.12.2019 сделать метод set что бы при изм эл сдвигать ноды
//          ( ! при замене ноды на ноду лэфт либо на ноду райт (если они обе есть) теряется одна из них )
          if (comparator.compare(node.e, ee) == -1) {
            Node<E> left = node.left;
            node.left = node.left.left;
            Node<E> right = node.right;
            node.right = left;
            node.right.right = right;
            node.right.left = null;
          }
          return e1;
        } else {
          node = node.left;
        }
      } else if (comparator.compare(node.e, e) == -1) {
        if (node.right == null) break;
        if (node.right.e.equals(e)) {
          E e1 = node.right.e;
          node.right.setE(ee);
          // 11.12.2019 сделать метод set что бы при изм эл сдвигать ноды
//          ( ! при замене ноды на ноду лэфт либо на ноду райт (если они обе есть) теряется одна из них )
          if (comparator.compare(node.e, ee) == 1) {
            Node<E> right = node.right;
            node.right = node.right.left;
            Node<E> left = node.left;
            node.left = right;
            node.left.left = left;
            node.left.right = null;
          }
          return e1;
        } else {
          node = node.right;
        }
      }
    }
    return null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean contains(E e) {
    if (root.e.equals(e)) {
      return true;
    }
    Node<E> node = root;
    while (true) {
      if (comparator.compare(node.e, e) == 1) {
        if (node.left == null) break;
        if (node.left.e.equals(e)) {
          return true;
        } else {
          node = node.left;
        }
      } else if (comparator.compare(node.e, e) == -1) {
        if (node.right == null) break;
        if (node.right.e.equals(e)) {
          return true;
        } else {
          node = node.right;
        }
      }
    }
    return false;
  }

  @Override
  public E get(E e) {
    if (root.e.equals(e)) {
      return root.e;
    }
    Node<E> node = root;
    while (true) {
      if (comparator.compare(node.e, e) == 1) {
        if (node.left == null) break;
        if (node.left.e.equals(e)) {
          return node.left.e;
        } else {
          node = node.left;
        }
      } else if (comparator.compare(node.e, e) == -1) {
        if (node.right == null) break;
        if (node.right.e.equals(e)) {
          return node.right.e;
        } else {
          node = node.right;
        }
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "MyTreeImpl{" +
            "root=" + root +
            '}';
  }

  private static class Node<E> {
    private Node<E> root;
    private Node<E> left;
    private Node<E> right;
    private E e;

    public Node(Node<E> root, Node<E> left, Node<E> right, E e) {
      this.root = root;
      this.left = left;
      this.right = right;
      this.e = e;
    }

    public E getE() {
      return e;
    }

    public void setE(E e) {
      this.e = e;
    }

    @Override
    public String toString() {
      return "Node{" +
//              "root=" + root +
              ", left=" + left +
              ", right=" + right +
              ", e=" + e +
              '}';
    }
  }
}
