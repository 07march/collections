package mylist;

public class MyAsocArrayImpl<K, V> implements MyAsocArray<K, V> {
  private static final int INIT_SIZE = 50;
  private Object[] keyArray;
  private Object[] valueArray;
  private int size;

  public MyAsocArrayImpl() {
    this.size = 0;
    this.keyArray = new Object[INIT_SIZE];
    this.valueArray = new Object[INIT_SIZE];
  }

  public static void main(String[] args) {
    MyAsocArray<String, Integer> stringIntegerMyAsocArray = new MyAsocArrayImpl<>();
    stringIntegerMyAsocArray.put("1", 1);
    stringIntegerMyAsocArray.put("2", 2);
    stringIntegerMyAsocArray.put("3", 3);
    stringIntegerMyAsocArray.put("4", 4);
    System.out.println(stringIntegerMyAsocArray.contains("3"));
    System.out.println(stringIntegerMyAsocArray.contains("10"));
    System.out.println(stringIntegerMyAsocArray.get("2"));
    System.out.println(stringIntegerMyAsocArray.get("aaa"));
    System.out.println(stringIntegerMyAsocArray.set("4", 44));
    System.out.println(stringIntegerMyAsocArray.get("4"));
    System.out.println(stringIntegerMyAsocArray.remove("1"));
    System.out.println(stringIntegerMyAsocArray.contains("1"));
  }

  @Override
  public void put(K k, V v) {
    keyArray[size] = k;
    valueArray[size] = v;
    size++;
  }

  @Override
  public V set(K k, V v) {
    for (int i = 0; i < keyArray.length; i++) {
      if (keyArray[i].equals(k)) {
        Object o = valueArray[i];
        valueArray[i] = v;
        return (V) o;
      }
    }
    return null;
  }

  @Override
  public V get(K k) {
    for (int i = 0; i < keyArray.length; i++) {
      if (keyArray[i] == null) break;
      if (keyArray[i].equals(k)) return (V) valueArray[i];
    }
    return null;
  }

  @Override
  public V remove(K k) {
    for (int i = 0; i < keyArray.length; i++) {
      if (keyArray[i] == null) break;
      if (keyArray[i].equals(k)) {
        Object o = valueArray[i];
        if (keyArray.length - 1 - i >= 0)
          System.arraycopy(keyArray, i + 1, keyArray, i, keyArray.length - 1 - i);
        if (valueArray.length - 1 - i >= 0)
          System.arraycopy(valueArray, i + 1, valueArray, i, valueArray.length - 1 - i);
        return (V) o;
      }
    }
    return null;
  }

  @Override
  public boolean contains(K k) {
    for (Object o : keyArray) {
      if (o == null) break;
      if (o.equals(k)) return true;
    }
    return false;
  }

  @Override
  public K[] arrayKey() {
    int count = 0;
    for (Object o : keyArray) {
      if (o == null) break;
      count++;
    }
    Object[] qwwerty = new Object[count];
    System.arraycopy(keyArray, 0, qwwerty, 0, qwwerty.length);
    return (K[]) qwwerty;
  }

  @Override
  public V[] arrayValue() {
    int count = 0;
    for (Object o : valueArray) {
      if (o == null) break;
      ;
      count++;
    }
    Object[] rest = new Object[count];
    System.arraycopy(valueArray, 0, rest, 0, rest.length);
    return (V[]) rest;
  }
}
