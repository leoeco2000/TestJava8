package zuoxiaolong.builder.withBuilder;

// �ݵķ���Ľ�����
public class ThinBuilder extends DoppelgangerBuilder {

  public ThinBuilder(String name) {
    super();
    this.name = name;
  }

  @Override
  public Doppelganger build() {
    return new Doppelganger(this);
  }

  public DoppelgangerBuilder buildBody(String body) {
    System.out.println("����" + this.name + "�����ݵ�����");
    this.body = "���ݵ�����";
    return this;
  }

  public DoppelgangerBuilder buildHead(String head) {
    System.out.println("����" + this.name + "��СС��ͷ");
    this.head = "СС��ͷ";
    return this;
  }

  public DoppelgangerBuilder buildLeftArm(String leftArm) {
    System.out.println("����" + this.name + "�����ݵ���첲");
    this.leftArm = "���ݵ���첲";
    return this;
  }

  public DoppelgangerBuilder buildLeftHand(String leftHand) {
    System.out.println("����" + this.name + "�����ݵ�����");
    this.leftHand = "���ݵ�����";
    return this;
  }

  public DoppelgangerBuilder buildRightArm(String rightArm) {
    System.out.println("����" + this.name + "�����ݵ��Ҹ첲");
    this.rightArm = "���ݵ��Ҹ첲";
    return this;
  }

  public DoppelgangerBuilder buildRightHand(String rightHand) {
    System.out.println("����" + this.name + "�����ݵ�����");
    this.rightHand = "���ݵ�����";
    return this;
  }

  public DoppelgangerBuilder buildLeftLeg(String leftLeg) {
    System.out.println("����" + this.name + "�����ݵ�����");
    this.leftLeg = "���ݵ�����";
    return this;
  }

  public DoppelgangerBuilder buildLeftFoot(String leftFoot) {
    System.out.println("����" + this.name + "��СС�����");
    this.leftFoot = "СС�����";
    return this;
  }

  public DoppelgangerBuilder buildRightLeg(String rightLeg) {
    System.out.println("����" + this.name + "�����ݵ�����");
    this.rightLeg = "���ݵ�����";
    return this;
  }

  public DoppelgangerBuilder buildRightFoot(String rightFoot) {
    System.out.println("����" + this.name + "��СС���ҽ�");
    this.rightFoot = "СС���ҽ�";
    return this;
  }
}
