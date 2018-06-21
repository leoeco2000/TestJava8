package zuoxiaolong.Prototype;

class FieldForClone implements Cloneable {

  private int a;

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  protected FieldForClone clone() {
    Object object = null;
    try {
      object = super.clone();
    } catch (CloneNotSupportedException exception) {
      throw new RuntimeException(exception);
    }
    return (FieldForClone) object;
  }

}


public class DeepPrototype implements Cloneable {

  private int x;
  private int y;
  private int z;
  private FieldForClone field;

  public DeepPrototype() {
    this.x = 2;
    this.y = 3;
    this.z = 4;
    this.field = new FieldForClone();
    this.field.setA(5);
  }

  public FieldForClone getField() {
    return field;
  }

  protected DeepPrototype clone() {
    Object object = null;
    try {
      object = super.clone();
      ((DeepPrototype) object).field = this.field.clone();
    } catch (CloneNotSupportedException exception) {
      throw new RuntimeException(exception);
    }
    return (DeepPrototype) object;
  }

  public String toString() {
    return "[" + x + "," + y + "," + z + "," + field.getA() + "]";
  }

  public static void main(String[] args) {
    DeepPrototype prototype1 = new DeepPrototype();
    System.out.println(prototype1);
    System.out.println(prototype1.getField());
    DeepPrototype prototype2 = prototype1.clone();
    System.out.println(prototype2);
    System.out.println(prototype2.getField());
  }

}
