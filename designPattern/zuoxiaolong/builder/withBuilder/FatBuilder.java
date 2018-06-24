package zuoxiaolong.builder.withBuilder;

// �ֵķ���Ľ�����
public class FatBuilder extends DoppelgangerBuilder {

  public FatBuilder(String name) {
    super();
    this.name = name;
  }

  public Doppelganger build() {
    return new Doppelganger(this);
  }

  public DoppelgangerBuilder buildBody(String body) {
    System.out.println("����" + this.name + "�����ֵ�����");
    this.body = "���ֵ�����";
    return this;
  }

  public DoppelgangerBuilder buildHead(String head) {
    System.out.println("����" + this.name + "�Ĵ���ͷ");
    this.head = "����ͷ";
    return this;
  }

  public DoppelgangerBuilder buildLeftArm(String leftArm) {
    System.out.println("����" + this.name + "�����ֵ���첲");
    this.leftArm = "���ֵ���첲";
    return this;
  }

  public DoppelgangerBuilder buildLeftHand(String leftHand) {
    System.out.println("����" + this.name + "�����ֵ�����");
    this.leftHand = "���ֵ�����";
    return this;
  }

  public DoppelgangerBuilder buildRightArm(String rightArm) {
    System.out.println("����" + this.name + "�����ֵ��Ҹ첲");
    this.rightArm = "���ֵ��Ҹ첲";
    return this;
  }

  public DoppelgangerBuilder buildRightHand(String rightHand) {
    System.out.println("����" + this.name + "�����ֵ�����");
    this.rightHand = "���ֵ�����";
    return this;
  }

  public DoppelgangerBuilder buildLeftLeg(String leftLeg) {
    System.out.println("����" + this.name + "�����ֵ�����");
    this.leftLeg = "���ֵ�����";
    return this;
  }

  public DoppelgangerBuilder buildLeftFoot(String leftFoot) {
    System.out.println("����" + this.name + "�Ĵ������");
    this.leftFoot = "�������";
    return this;
  }

  public DoppelgangerBuilder buildRightLeg(String rightLeg) {
    System.out.println("����" + this.name + "�����ֵ�����");
    this.rightLeg = "���ֵ�����";
    return this;
  }

  public DoppelgangerBuilder buildRightFoot(String rightFoot) {
    System.out.println("����" + this.name + "�Ĵ����ҽ�");
    this.rightFoot = "�����ҽ�";
    return this;
  }

}
