package yuanmadao.Prototype.cloneInShallow;

/**
 * �����Ľӿڣ������˿��Կ�¡����ķ���
 */
public interface OrderApi extends Cloneable {
  public int getOrderProductNum();
  public void setOrderProductNum(int num);
  /**
   * ��¡����
   * @return ����ԭ�͵�ʵ��
   */
  public OrderApi clone();
//  public OrderApi cloneOrder();
  
}