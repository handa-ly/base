package ftl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
    *@ClassName: TestClass
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/6/15 14:57
    */
    
    
public class TestClass {
  public static void main(String[] args) {
    long time = 1592186124867l;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    java.util.Date date = new Date(time);
    String str = sdf.format(date);
    System.out.println(str);
  }
}
