package zuoxiaolong.builder.withBuilder;

// 制造过程
public class Creator {

  public static void main(String[] args) {
    Soul soul = new Soul();
    // 制造一个瘦小的看守所的人员
    System.out.println("获得了" + soul.createDoppelganger(new ThinBuilder("看守所人员1")));
    System.out.println("----------------------------------------");

    // 制造一个胖胖的看守所的人员
    System.out.println("获得了" + soul.createDoppelganger(new FatBuilder("看守所人员2")));
  }
}
