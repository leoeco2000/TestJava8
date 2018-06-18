package yuanmadao.Prototype.cloneInShallow;

/**
 * ��ҵ��������
 */
public class EnterpriseOrder implements OrderApi {
  private String enterpriseName;
  private String productId;
  private int orderProductNum = 0;

  public int getOrderProductNum() {
    return this.orderProductNum;
  }

  public void setOrderProductNum(int num) {
    this.orderProductNum = num;
  }

  public String getEnterpriseName() {
    return enterpriseName;
  }

  public void setEnterpriseName(String enterpriseName) {
    this.enterpriseName = enterpriseName;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String toString() {
    return "����ҵ�����Ķ�����ҵ��=" + this.enterpriseName + "��������Ʒ��=" + this.productId + "����������Ϊ="
        + this.orderProductNum;
  }

//  public OrderApi cloneOrder() {
//    // ����һ���µĶ�����Ȼ��ѱ�ʵ�������ݸ��ƹ�ȥ
//    EnterpriseOrder order = new EnterpriseOrder();
//    order.setEnterpriseName(this.enterpriseName);
//    order.setProductId(this.productId);
//    order.setOrderProductNum(this.orderProductNum);
//    return order;
//  }

  public EnterpriseOrder clone() {
    // ��¡����������ʵ�֣�ֱ�ӵ��ø���Ŀ�¡�����Ϳ�����
    EnterpriseOrder obj = null;
    try {
      obj = (EnterpriseOrder) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return obj;
  }
}
