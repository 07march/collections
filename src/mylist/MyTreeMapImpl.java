package mylist;

import java.util.*;

public class MyTreeMapImpl<K, V> implements MyTreeMap<K, V> {
  private Entry<K, V> root;
  private int size;
  private Comparator<K> comparator;

  public MyTreeMapImpl(Comparator<K> comparator) {
    this.comparator = comparator;
  }

  public static void main(String[] args) {
    List<User> users = new ArrayList<>();
    Collections.sort(users);

    MyTreeMap<Integer, String> integerStringMyTreeMap = new MyTreeMapImpl<>(Integer::compareTo);
    integerStringMyTreeMap.put(1, "he1");
    integerStringMyTreeMap.put(2, "he2");
    integerStringMyTreeMap.put(3, "he3");
    integerStringMyTreeMap.put(4, "he4");
    System.out.println(integerStringMyTreeMap.contains(3));
    System.out.println(integerStringMyTreeMap.contains(5));
    System.out.println(integerStringMyTreeMap.set(3, "he33"));
    System.out.println(integerStringMyTreeMap.get(3));
    System.out.println(integerStringMyTreeMap.get(5));
  }

  @Override
  public boolean empty() {
    return size == 0;
  }

  @Override
  public V put(K o, V o2) {
    if (empty()) {
      root = new Entry<K, V>(o, o2, null, null, null);
      size++;
    } else {
      Entry<K, V> node = root;
      while (true) {
        if (comparator.compare(node.key, o) == 1) {
          if (node.left == null) {
            node.left = new Entry<K, V>(o, o2, null, null, node);
            size++;
            break;
          } else {
            node = node.left;
          }
        } else if (comparator.compare(node.key, o) == -1) {
          if (node.right == null) {
            node.right = new Entry<K, V>(o, o2, null, null, node);
            size++;
            break;
          } else {
            node = node.right;
          }
        } else {
          V old = node.getValue();
          node.setValue(o2);
          return old;
        }
      }
    }
    return null;
  }

  @Override
  public V set(K o, V o2) {
    if (root.equals(o)) {
      V value = root.getValue();
      root.setValue(o2);
      return value;
    } else {
      Entry<K, V> node = root;
      while (true) {
        if (comparator.compare(node.key, o) == 1) {
          if (node.left == null) break;
          if (node.left.getKey().equals(o)) {
            Entry<K, V> left = node.left;
            node.left.setValue(o2);
            return (V) left.getValue();
          } else {
            node = node.left;
          }
        } else if (comparator.compare(node.key, o) == -1) {
          if (node.right == null) break;
          if (node.right.getKey().equals(o)) {
            Entry<K, V> right = node.right;
            node.right.setValue(o2);
            return (V) right.getValue();
          } else {
            node = node.right;
          }
        }
      }
    }
    return null;
  }

  @Override
  public V get(K k) {
    if (root.equals(k)) {
      return (V) root;
    } else {
      Entry<K, V> node = root;
      while (true) {
        if (comparator.compare(node.key, k) == 1) {
          if (node.left == null) break;
          if (node.left.getKey().equals(k)) {
            return node.left.getValue();
          } else {
            node = node.left;
          }
        } else if (comparator.compare(node.key, k) == -1) {
          if (node.right == null) break;
          if (node.right.getKey().equals(k)) {
            return node.right.getValue();
          } else {
            node = node.right;
          }
        }
      }
    }
    return null;
  }

  @Override
  public boolean contains(K o) {
    if (empty()) return false;
    if (root.getKey().equals(o)) {
      return true;
    } else {
      Entry<K, V> node = root;
      while (true) {
        if (comparator.compare(node.key, o) == 1) {
          if (node.left == null) return false;
          if (node.left.getKey().equals(o)) {
            return true;
          } else {
            node = node.left;
          }
        } else if (comparator.compare(node.key, o) == -1) {
          if (node.right == null) return false;
          if (node.right.getKey().equals(o)) {
            return true;
          } else {
            node = node.right;
          }
        }
      }
    }
  }

  @Override
  public V remove(K o) {
    Entry<K, V> node = root;
    while (true) {
      if (comparator.compare(node.key, o) == 1) {
        if (node.left == null) break;
        if (node.left.getKey().equals(o)) {
          if (node.left.left == null && node.left.right == null) {
            node.left = null;
            size--;
          } else if (node.left.left != null && node.left.right != null) {
            Entry<K, V> left = node.left.left;
            Entry<K, V> right = node.left.right;
            if (right.right == null && right.left == null) {
              node.left.right = null;
              node.left = null;
              node.left = right;
              right.left = left;
              size--;
            } else {
              node.left.left = null;
              node.left = null;
              node.left = left;
              left.right = right;
              size--;
            }
          } else if (node.left.left != null) {
            Entry<K, V> left = node.left.left;
            node.left.left = null;
            node.left = null;
            node.left = left;
            size--;
          } else {
            Entry<K, V> right = node.left.right;
            node.left.right = null;
            node.left = null;
//            node.left.root = null;
            node.left = right;
            size--;
          }
        } else {
          node = node.left;
        }
      } else if (comparator.compare(node.key, o) == -1) {
        if (node.right == null) break;
        if (node.right.getKey().equals(o)) {
          if (node.right.left == null && node.right.right == null) {
            node.right = null;
            size--;
          } else if (node.right.left != null && node.right.right != null) {
            Entry<K, V> left = node.right.left;
            Entry<K, V> right = node.right.right;
            if (right.left == null && right.right == null) {
              node.right.right = null;
              node.right = null;
              node.right = right;
              right.left = left;
              size--;
            } else {
              node.right.left = null;
              node.right = null;
              node.right = left;
              left.right = right;
              size--;
            }
          } else {
            if (node.right.left != null) {
              Entry<K, V> left = node.right.left;
              node.right.left = null;
              node.right = null;
//              node.right.root = null;
              node.right = left;
              size--;
            } else if (node.right.right != null) {
              Entry<K, V> right = node.right.right;
              node.right.right = null;
              node.right = null;
//              node.right.root = null;
              node.right = right;
              size--;
            }
          }
        } else {
          node = node.right;
        }
      }
    }
    return null;
  }

  static class Entry<K, V> implements Map.Entry<K, V> {
    K key;
    V value;
    Entry<K, V> left;
    Entry<K, V> right;
    Entry<K, V> root;

    public Entry(K key, V value, Entry<K, V> left, Entry<K, V> right, Entry<K, V> root) {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
      this.root = root;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      V value1 = this.value;
      this.value = value;
      return value1;
    }
  }


}