package temp;

interface IMoney {
  public void lendMoney();
}

class Onwer {
  private IMoney realLend;
  private IMoney falseLend;
  public IMoney lend(boolean canPay) {
    if (canPay)
      return new RealLend(); // �˴�Ӧ�����ڲ��࣬����ת�ͣ�ע���Сд
    else
      return new FalseLend(); // ͬ��
  }

  private class RealLend implements IMoney { // ע���Сд
    public void lendMoney() {
      System.out.println("���Խ�Ǯ");
    }
  }

  private class FalseLend implements IMoney { // ͬ��
    public void lendMoney() {
      System.out.println("�����Խ�Ǯ");
    }
  }
}

public class LendMoney {
  public static void main(String[] args) {
    // TODO �Զ����ɵķ������
    IMoney f = new Onwer().lend(false);
    f.lendMoney();
  }
}
