package zuoxiaolong.builder.withBuilder2;

// 瘦的分身的建造者
public class ThinBuilder {

  public Doppelganger createDoppelganger(DoppelgangerBuilder builder) {
    return builder
    .buildBody("瘦瘦的身体")
    .buildHead("小小的头")
    .buildLeftArm("瘦瘦的左胳膊")
    .buildLeftHand("瘦瘦的左手")
    .buildRightArm("瘦瘦的右胳膊")
    .buildRightHand("瘦瘦的右手")
    .buildLeftLeg("瘦瘦的左腿")
    .buildLeftFoot("小小的左脚")
    .buildRightLeg("瘦瘦的右腿")
    .buildRightFoot("小小的右脚")
    .build();
  }

}
