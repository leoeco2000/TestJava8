package zuoxiaolong.builder.withBuilder2;

// �ֵķ���Ľ�����
public class FatBuilder {

  public Doppelganger createDoppelganger(DoppelgangerBuilder builder) {
    return builder
    .buildBody("���ֵ�����")
    .buildHead("����ͷ")
    .buildLeftArm("���ֵ���첲")
    .buildLeftHand("���ֵ�����")
    .buildRightArm("���ֵ��Ҹ첲")
    .buildRightHand("���ֵ�����")
    .buildLeftLeg("���ֵ�����")
    .buildLeftFoot("�������")
    .buildRightLeg("���ֵ�����")
    .buildRightFoot("�����ҽ�")
    .build();
  }

}
