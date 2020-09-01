package mylist;

import java.util.Objects;

public class User implements Comparable<User> {
  private String name;

  public String getName() {
    return name;
  }

  public User(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
            "name=" + name +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public static void main(String[] args) {
    User aa = new User("aa");
    User aaa = new User("aaa");
    int i = aa.compareTo(aa);
    System.out.println(i);
  }

  @Override
  public int compareTo(User o) {
    if (this.name.length() > o.name.length()) {
      return 1;
    } else if(this.name.length() == o.name.length()) return 0;
    return -1;
  }
}
