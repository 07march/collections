package mylist;

public interface MyTreeMap<K, V> {
  boolean empty();
  V put(K k, V v);
  V set(K k, V v);
  V get(K k);
  boolean contains(K k);
  V remove(K k);
}
