package mylist;

public interface MyAsocArray<K, V> {
  void put(K k, V v);
  V set(K k, V v);
  V get(K k);
  V remove(K k);
  boolean contains(K k);
  K[] arrayKey();
  V[] arrayValue();
}
