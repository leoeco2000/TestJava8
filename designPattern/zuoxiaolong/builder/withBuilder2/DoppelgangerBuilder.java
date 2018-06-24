package zuoxiaolong.builder.withBuilder2;

// 分身制造者接口
public class DoppelgangerBuilder {

  protected String name;

  protected String body;

  protected String head;

  protected String leftArm;

  protected String leftHand;

  protected String rightArm;

  protected String rightHand;

  protected String leftFoot;

  protected String leftLeg;

  protected String rightFoot;

  protected String rightLeg;

  public DoppelgangerBuilder(String name) {
    super();
    this.name = name;
  }

  public Doppelganger build() {
    return new Doppelganger(this);
  }

  public DoppelgangerBuilder buildBody(String body) {
    System.out.println("设置" + this.name + body);
    this.body = body;
    return this;
  }

  public DoppelgangerBuilder buildHead(String head) {
    System.out.println("设置" + this.name + head);
    this.head = head;
    return this;
  }

  public DoppelgangerBuilder buildLeftArm(String leftArm) {
    System.out.println("设置" + this.name + leftArm);
    this.leftArm = leftArm;
    return this;
  }

  public DoppelgangerBuilder buildLeftHand(String leftHand) {
    System.out.println("设置" + this.name + leftHand);
    this.leftHand = leftHand;
    return this;
  }

  public DoppelgangerBuilder buildRightArm(String rightArm) {
    System.out.println("设置" + this.name + rightArm);
    this.rightArm = rightArm;
    return this;
  }

  public DoppelgangerBuilder buildRightHand(String rightHand) {
    System.out.println("设置" + this.name + rightHand);
    this.rightHand = rightHand;
    return this;
  }

  public DoppelgangerBuilder buildLeftLeg(String leftLeg) {
    System.out.println("设置" + this.name + leftLeg);
    this.leftLeg = leftLeg;
    return this;
  }

  public DoppelgangerBuilder buildLeftFoot(String leftFoot) {
    System.out.println("设置" + this.name + leftFoot);
    this.leftFoot = "大大的左脚";
    return this;
  }

  public DoppelgangerBuilder buildRightLeg(String rightLeg) {
    System.out.println("设置" + this.name + rightLeg);
    this.rightLeg = rightLeg;
    return this;
  }

  public DoppelgangerBuilder buildRightFoot(String rightFoot) {
    System.out.println("设置" + this.name + rightFoot);
    this.rightFoot = rightFoot;
    return this;
  }

}
