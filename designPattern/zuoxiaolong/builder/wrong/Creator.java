package zuoxiaolong.builder.wrong;

// 制造过程
public class Creator {

  public static void main(String[] args) {
    Soul soul = new Soul();
    // 制造一个瘦小的看守所的人员
    soul.createDoppelganger("看守所人员1");
    soul.createBody("瘦瘦的身体");
    soul.createHead("小小的头");
    soul.createLeftArm("瘦瘦的左胳膊");
    soul.createLeftHand("瘦瘦的左手");
    soul.createRightArm("瘦瘦的右胳膊");
    soul.createRightHand("瘦瘦的右手");
    soul.createLeftLeg("瘦瘦的左腿");
    soul.createLeftFoot("小小的左脚");
    soul.createRightLeg("瘦瘦的右腿");
    soul.createRightFoot("小小的右脚");
    System.out.println("获得了" + soul.getDoppelganger());

    System.out.println("----------------------------------------");

    // 制造一个胖胖的看守所的人员
    soul.createDoppelganger("看守所人员2");
    soul.createBody("胖胖的身体");
    soul.createHead("大大的头");
    soul.createLeftArm("胖胖的左胳膊");
    soul.createLeftHand("胖胖的左手");
    soul.createRightArm("胖胖的右胳膊");
    soul.createRightHand("胖胖的右手");
    soul.createLeftLeg("胖胖的左腿");
    soul.createLeftFoot("大大的左脚");
    soul.createRightLeg("胖胖的右腿");
    soul.createRightFoot("大大的右脚");
    System.out.println("获得了" + soul.getDoppelganger());
  }
}
