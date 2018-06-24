package zuoxiaolong.builder.withBuilder2;

// 胖的分身的建造者
public class FatBuilder {

  public Doppelganger createDoppelganger(DoppelgangerBuilder builder) {
    return builder
    .buildBody("胖胖的身体")
    .buildHead("大大的头")
    .buildLeftArm("胖胖的左胳膊")
    .buildLeftHand("胖胖的左手")
    .buildRightArm("胖胖的右胳膊")
    .buildRightHand("胖胖的右手")
    .buildLeftLeg("胖胖的左腿")
    .buildLeftFoot("大大的左脚")
    .buildRightLeg("胖胖的右腿")
    .buildRightFoot("大大的右脚")
    .build();
  }

}
