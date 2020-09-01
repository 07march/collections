package mylist;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {
  @Override
  public int compare(User o1, User o2) {
    if (o1.getName().length() > o2.getName().length()) {
      return 1;
    } else if(o1.getName().length() == o2.getName().length()){
      return 0;
    }
    return -1;
  }
}
