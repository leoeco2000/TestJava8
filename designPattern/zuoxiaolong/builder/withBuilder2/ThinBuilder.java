package zuoxiaolong.builder.withBuilder2;

// �ݵķ���Ľ�����
public class ThinBuilder {

  public Doppelganger createDoppelganger(DoppelgangerBuilder builder) {
    return builder
    .buildBody("���ݵ�����")
    .buildHead("СС��ͷ")
    .buildLeftArm("���ݵ���첲")
    .buildLeftHand("���ݵ�����")
    .buildRightArm("���ݵ��Ҹ첲")
    .buildRightHand("���ݵ�����")
    .buildLeftLeg("���ݵ�����")
    .buildLeftFoot("СС�����")
    .buildRightLeg("���ݵ�����")
    .buildRightFoot("СС���ҽ�")
    .build();
  }

}
