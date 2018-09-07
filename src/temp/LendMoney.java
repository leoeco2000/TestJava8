package temp;

interface IMoney {
  public void lendMoney();
}

class Onwer {
  private IMoney realLend;
  private IMoney falseLend;
  public IMoney lend(boolean canPay) {
    if (canPay)
      return new RealLend(); // 此处应返回内部类，向上转型，注意大小写
    else
      return new FalseLend(); // 同上
  }

  private class RealLend implements IMoney { // 注意大小写
    public void lendMoney() {
      System.out.println("可以借钱");
    }
  }

  private class FalseLend implements IMoney { // 同上
    public void lendMoney() {
      System.out.println("不可以借钱");
    }
  }
}

public class LendMoney {
  public static void main(String[] args) {
    // TODO 自动生成的方法存根
    IMoney f = new Onwer().lend(false);
    f.lendMoney();
  }
}
