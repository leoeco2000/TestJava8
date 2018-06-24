package zuoxiaolong.builder.withBuilder;

// 胖的分身的建造者
public class FatBuilder extends DoppelgangerBuilder {

  public FatBuilder(String name) {
    super();
    this.name = name;
  }

  public Doppelganger build() {
    return new Doppelganger(this);
  }

  public DoppelgangerBuilder buildBody(String body) {
    System.out.println("设置" + this.name + "的胖胖的身体");
    this.body = "胖胖的身体";
    return this;
  }

  public DoppelgangerBuilder buildHead(String head) {
    System.out.println("设置" + this.name + "的大大的头");
    this.head = "大大的头";
    return this;
  }

  public DoppelgangerBuilder buildLeftArm(String leftArm) {
    System.out.println("设置" + this.name + "的胖胖的左胳膊");
    this.leftArm = "胖胖的左胳膊";
    return this;
  }

  public DoppelgangerBuilder buildLeftHand(String leftHand) {
    System.out.println("设置" + this.name + "的胖胖的左手");
    this.leftHand = "胖胖的左手";
    return this;
  }

  public DoppelgangerBuilder buildRightArm(String rightArm) {
    System.out.println("设置" + this.name + "的胖胖的右胳膊");
    this.rightArm = "胖胖的右胳膊";
    return this;
  }

  public DoppelgangerBuilder buildRightHand(String rightHand) {
    System.out.println("设置" + this.name + "的胖胖的右手");
    this.rightHand = "胖胖的右手";
    return this;
  }

  public DoppelgangerBuilder buildLeftLeg(String leftLeg) {
    System.out.println("设置" + this.name + "的胖胖的左腿");
    this.leftLeg = "胖胖的左腿";
    return this;
  }

  public DoppelgangerBuilder buildLeftFoot(String leftFoot) {
    System.out.println("设置" + this.name + "的大大的左脚");
    this.leftFoot = "大大的左脚";
    return this;
  }

  public DoppelgangerBuilder buildRightLeg(String rightLeg) {
    System.out.println("设置" + this.name + "的胖胖的右腿");
    this.rightLeg = "胖胖的右腿";
    return this;
  }

  public DoppelgangerBuilder buildRightFoot(String rightFoot) {
    System.out.println("设置" + this.name + "的大大的右脚");
    this.rightFoot = "大大的右脚";
    return this;
  }

}
