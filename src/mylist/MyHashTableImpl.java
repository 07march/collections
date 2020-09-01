package mylist;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 14.12.2019 Реализовать метод для расширения массива
public class MyHashTableImpl<K, V> implements MyHashTable<K, V> {
  private Node<K, V>[] table;
  private int size;

  public MyHashTableImpl() {
    this.size = 0;
    this.table = new Node[16];
  }

  public static void main(String[] args) {
    MyHashTable<String, Integer> myHashTable = new MyHashTableImpl<>();
    myHashTable.put("mes1", 1);
    myHashTable.put("mes2", 2);
    myHashTable.put("mes3", 3);
    myHashTable.put("mes2", 4);
    myHashTable.put("mes5", 3);
    System.out.println(myHashTable.get("mes2"));
    System.out.println(myHashTable.contains("mes3"));
    System.out.println(myHashTable.contains("mes5"));

    for (Map.Entry<String, Integer> stringIntegerEntry : myHashTable.entrySet()) {
      System.out.println(stringIntegerEntry.getKey());
      System.out.println(stringIntegerEntry.getValue());
    }

    System.out.println(myHashTable.keySet());
    System.out.println(myHashTable.valueSet());
  }

  private void grow() {
    Object[] objects = new Object[table.length * 2];
    System.arraycopy(table, 0, objects, 0, table.length);
    objects = table;
  }

  private int hash(K k) {
    return Math.abs(k.hashCode() % table.length);
  }

  @Override
  public void put(K k, V v) {
    if (table.length == size) {
      grow();
    }
    if (table[hash(k)] == null) {
      table[hash(k)] = new Node<>(hash(k), k, v, null);
      size++;
    } else if (table[hash(k)].next == null) {
      if (table[hash(k)].getKey().equals(k)) {
        table[hash(k)].setValue(v);
      }
      table[hash(k)].next = new Node<>(hash(k), k, v, null);
      size++;
    } else {
      Node<K, V> node = table[hash(k)];
      while (true) {
        if (table[hash(k)].next.getKey().equals(k)) {
          table[hash(k)].next.setValue(v);
          break;
        } else if (table[hash(k)].next == null) {
          table[hash(k)].next = new Node<>(hash(k), k, v, null);
          size++;
          break;
        } else {
          node = node.next;
        }
      }
    }
  }
//  14.12.2019 Реализовать случай где уже в таблице есть ноды и ос проход по списку с пом for и если хоть один эл совп, то заменяем значение

  @Override
  public V set(K k, V v) {
    if (table[hash(k)] != null && table[hash(k)].getKey().equals(k)) {
      V value = table[hash(k)].getValue();
      table[hash(k)].setValue(v);
      return value;
    }
//    else if (table[hash(k)].next != null && table[hash(k)].next.getKey().equals(k)) {
//      V value = table[hash(k)].next.getValue();
//      table[hash(k)].next.setValue(v);
//      return value;
//    }
    //  14.12.2019 -//-
    else if (table[hash(k)].next != null) {
      Node<K, V> node = table[hash(k)];
      while (table[hash(k)].next != null) {
        if (table[hash(k)].next.getKey().equals(k)) {
          V value = table[hash(k)].next.getValue();
          table[hash(k)].next.setValue(v);
          return value;
        }
        node = node.next;
      }
    }
    return null;
  }

  @Override
  public V get(K k) {
    if (table[hash(k)] != null && table[hash(k)].getKey().equals(k)) {
      return table[hash(k)].getValue();
    }
//    else if (table[hash(k)].next != null && table[hash(k)].next.getKey().equals(k)) {
////      return table[hash(k)].next.getValue();
////    }
    //  14.12.2019 -//-
    else if (table[hash(k)].next != null) {
      Node<K, V> node = table[hash(k)];
      while (table[hash(k)].next != null) {
        if (table[hash(k)].next.getKey().equals(k)) {
          return table[hash(k)].next.getValue();
        }
        node = node.next;
      }
    }
    return null;
  }

  @Override
  public V remove(K k) {
    if (table[hash(k)] != null && table[hash(k)].getKey().equals(k)) {
      Node<K, V> kvNode = table[hash(k)];
      table[hash(k)] = null;
      if (table[hash(k)].next != null) {
        table[hash(k)].next = kvNode;
        size--;
      }
    } // 14.12.2019 -//-
    else if (table[hash(k)].next != null) {
      Node<K, V> node = table[hash(k)];
      while (table[hash(k)].next != null) {
        if (table[hash(k)].next.getKey().equals(k)) {
          Node<K, V> next = table[hash(k)].next;
          table[hash(k)].next = null;
          if (table[hash(k)].next.next != null) {
            table[hash(k)].next.next = next;
          }
        }
        node = node.next;
      }
    }
    return null;
  }

  @Override
  public boolean contains(K k) {
    if (table[hash(k)] != null && table[hash(k)].getKey().equals(k)) {
      return true;
    }
    else if (table[hash(k)].next != null) {
      Node<K, V> node = table[hash(k)];
      while (table[hash(k)].next != null) {
        if (table[hash(k)].next.getKey().equals(k)) {
          return true;
        }
        node = node.next;
      }
    }
    return false;
    //  14.12.2019 -//-
  }

  @Override
  public Set<K> keySet() {
    Set<K> ks = new HashSet<>();
    for (Node<K, V> kvNode : table) {
      if (kvNode != null) {
        ks.add(kvNode.getKey());
      }
    }
    return ks;
  }

  @Override
  public Set<V> valueSet() {
    Set<V> ks = new HashSet<>();
    for (Node<K, V> kvNode : table) {
      if (kvNode != null) {
        ks.add(kvNode.getValue());
      }
    }
    return ks;
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> kv = new HashSet<>();
    for (Node<K, V> kvNode : table) {
      if (kvNode != null) {
        kv.add(kvNode);
      }
    }
    return kv;
  }

  private static class Node<K, V> implements Map.Entry<K, V> {
    int hash;
    K key;
    V value;
    Node<K, V> next;

    public Node(int hash, K key, V value, Node<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
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
