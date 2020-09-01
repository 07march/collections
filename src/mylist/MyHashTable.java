package mylist;

import java.util.Map;
import java.util.Set;

public interface MyHashTable<K, V> {
  void put(K k, V v);
  V set(K k, V v);
  V get(K k);
  V remove(K k);
  boolean contains(K k);
  Set<K> keySet();
  Set<V> valueSet();
  Set<Map.Entry<K, V>> entrySet();
}
