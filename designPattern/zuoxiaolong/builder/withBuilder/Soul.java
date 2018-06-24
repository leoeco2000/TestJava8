package zuoxiaolong.builder.withBuilder;

// ����ָ࣬����
public class Soul {

  public Doppelganger createDoppelganger(DoppelgangerBuilder builder) {
    return builder
    .buildBody("")
    .buildHead("")
    .buildLeftArm("")
    .buildLeftHand("")
    .buildRightArm("")
    .buildRightHand("")
    .buildLeftLeg("")
    .buildLeftFoot("")
    .buildRightLeg("")
    .buildRightFoot("")
    .build();
  }

}
