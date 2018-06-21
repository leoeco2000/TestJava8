package yuanmadao.Prototype.cloneInShallow;

/**
 * ���˶�������
 */
public class PersonalOrder implements OrderApi {
  private String customerName;
  private String productId;
  private int orderProductNum = 0;

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

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String toString() {
    return "�����˶����Ķ�������=" + this.customerName + "��������Ʒ��=" + this.productId + "����������Ϊ="
        + this.orderProductNum;
  }
  // public OrderApi cloneOrder() {
  // ����һ���µĶ�����Ȼ��ѱ�ʵ�������ݸ��ƹ�ȥ
  // PersonalOrder order = new PersonalOrder();
  // order.setCustomerName(this.customerName);
  // order.setProductId(this.productId);
  // order.setOrderProductNum(this.orderProductNum);
  // return order;
  // }

  public PersonalOrder clone() {
    // ��¡����������ʵ�֣�ֱ�ӵ��ø���Ŀ�¡�����Ϳ�����
    PersonalOrder obj = null;
    try {
      obj = (PersonalOrder) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return obj;
  }
}
