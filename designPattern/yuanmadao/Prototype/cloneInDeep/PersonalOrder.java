package yuanmadao.Prototype.cloneInDeep;

public class PersonalOrder implements OrderApi, Cloneable {
  private String customerName;
  private int orderProductNum = 0;
  /**
   * 产品对象
   */
  private Product product = null;

  public int getOrderProductNum() {
    return this.orderProductNum;
  }

  public void setOrderProductNum(int num) {
    this.orderProductNum = num;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public String toString() {
    // 简单点输出
    return "订购产品是=" + this.product.getName() + "，订购数量为=" + this.orderProductNum;
  }

  public Object clone() {
    PersonalOrder obj = null;
    try {
      obj = (PersonalOrder) super.clone();
      // 下面这一句话不可少
      obj.setProduct((Product) this.product.clone());
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return obj;
  }
}
