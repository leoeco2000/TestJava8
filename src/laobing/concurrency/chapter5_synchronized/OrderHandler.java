package laobing.concurrency.chapter5_synchronized;

class OrderHandler {
  public static void main(String[] args) {
//    final Products orderHandler = new Products();
    Products orderHandler = Products.getInstance();
    /* ����10���̣߳�ģ��10���û������µ����� */
    for (int i = 0; i < 10; i++) {
      new Thread(new Runnable() {
        public void run() {
          /* ÿ���˹�����Ʒ����Ϊ25�� */
          orderHandler.Produce(25);
        }
      }).start();
    }
    /* ����5���̣߳�ģ��5���û��ڽ���ȡ���������� */
    for (int i = 0; i < 5; i++) {
      new Thread(new Runnable() {
        public void run() {
          /* ÿ��ȡ�������а�������Ʒ��Ϊ3�� */
          orderHandler.Cancel(3);
        }
      }).start();
    }
  }
}

class Products{
  private static final Products INSTANCE = new Products();
  private Products(){ }
  public static Products getInstance() {
    return INSTANCE;
  }
  /* ��ʼĳ��Ʒ����� */
  int StockSomeGoodsNum = 200;

  /* �û��µ� */
  public synchronized void Produce(int n) {
    /* step1:�жϿ��ÿ����� */
    if (StockSomeGoodsNum >= n) {
      /* Ϊ�˸��������̼߳�ľ������ý�������һ�� */
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      /* step2:ִ�м��ٿ����� */
      StockSomeGoodsNum -= n;
      System.out.println("�û�" + Thread.currentThread().getName() + "�ɹ�������Ʒ��" + String.valueOf(n)
          + "�������ʣ��" + StockSomeGoodsNum + "��");
    } else {
      System.out.println("�û�" + Thread.currentThread().getName() + "�µ�ʧ�ܣ���治��" + String.valueOf(n)
          + "�������ʣ��" + StockSomeGoodsNum + "��");
    }
  }

  /* �û�ȡ������ */
  public synchronized void Cancel(int n) {
    StockSomeGoodsNum += n;
    System.out.println("�û�" + Thread.currentThread().getName() + "ȡ��������Ʒ��" + String.valueOf(n)
        + "�������ʣ��" + StockSomeGoodsNum + "��");
  }
}