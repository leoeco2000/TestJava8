package test.reflect.reflections.detail;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

  protected List<String> stringList = new ArrayList<String>();

  public List<String> getStringList() {
    return this.stringList;
  }

  public void setStringList(List<String> stringList) {
    this.stringList = stringList;
  }

}

