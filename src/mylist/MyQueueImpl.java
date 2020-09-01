package mylist;

//  06.12.2019 Расширять массив при заполнении
public class MyQueueImpl<E> implements MyQueue<E> {
  private Object[] obj;
  private int size;

  public MyQueueImpl() {
    this.obj = new Object[16];
  }

  public static void main(String[] args) {
    MyQueueImpl<Integer> myQueue = new MyQueueImpl<>();
    myQueue.add(1);
    myQueue.add(2);
    myQueue.add(3);
    System.out.println(myQueue.poll());
    System.out.println(myQueue.poll());
    System.out.println(myQueue.peek());
    System.out.println(myQueue.poll());
    //  06.12.2019 Проверка на null
    System.out.println(myQueue.poll());
  }

  private void grow() {
    Object[] grows = new Object[obj.length * 2];
    System.arraycopy(obj, 0, grows, 0, obj.length);
    obj = grows;
  }

  @Override
  public void add(E o) {
    if (size == obj.length) {
      grow();
    }
    obj[size++] = o;
  }

  @Override
  public E poll() {
   if (obj[0] == null) throw new IndexOutOfBoundsException("массив пуст");
    Object o = obj[0];
    System.arraycopy(obj, 1, obj, 0, obj.length - 1);
    return (E) o;
  }

  //  06.12.2019 Реализовать метод
  @Override
  public E peek() {
    Object o = obj[0];
    return (E) o;
  }

}
