package zuoxiaolong.builder.withBuilder2;

// 制造过程
public class Creator {

  public static void main(String[] args) {
    // 制造一个瘦小的看守所的人员
    System.out
        .println("获得了" + new ThinBuilder().createDoppelganger(new DoppelgangerBuilder("看守所人员1")));
    System.out.println("----------------------------------------");

    // 制造一个胖胖的看守所的人员
    System.out
        .println("获得了" + new FatBuilder().createDoppelganger(new DoppelgangerBuilder("看守所人员2")));
  }
}
