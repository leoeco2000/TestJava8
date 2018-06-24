package zuoxiaolong.builder.withBuilder;

// 瘦的分身的建造者
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
    System.out.println("设置" + this.name + "的瘦瘦的身体");
    this.body = "瘦瘦的身体";
    return this;
  }

  public DoppelgangerBuilder buildHead(String head) {
    System.out.println("设置" + this.name + "的小小的头");
    this.head = "小小的头";
    return this;
  }

  public DoppelgangerBuilder buildLeftArm(String leftArm) {
    System.out.println("设置" + this.name + "的瘦瘦的左胳膊");
    this.leftArm = "瘦瘦的左胳膊";
    return this;
  }

  public DoppelgangerBuilder buildLeftHand(String leftHand) {
    System.out.println("设置" + this.name + "的瘦瘦的左手");
    this.leftHand = "瘦瘦的左手";
    return this;
  }

  public DoppelgangerBuilder buildRightArm(String rightArm) {
    System.out.println("设置" + this.name + "的瘦瘦的右胳膊");
    this.rightArm = "瘦瘦的右胳膊";
    return this;
  }

  public DoppelgangerBuilder buildRightHand(String rightHand) {
    System.out.println("设置" + this.name + "的瘦瘦的右手");
    this.rightHand = "瘦瘦的右手";
    return this;
  }

  public DoppelgangerBuilder buildLeftLeg(String leftLeg) {
    System.out.println("设置" + this.name + "的瘦瘦的左腿");
    this.leftLeg = "瘦瘦的左腿";
    return this;
  }

  public DoppelgangerBuilder buildLeftFoot(String leftFoot) {
    System.out.println("设置" + this.name + "的小小的左脚");
    this.leftFoot = "小小的左脚";
    return this;
  }

  public DoppelgangerBuilder buildRightLeg(String rightLeg) {
    System.out.println("设置" + this.name + "的瘦瘦的右腿");
    this.rightLeg = "瘦瘦的右腿";
    return this;
  }

  public DoppelgangerBuilder buildRightFoot(String rightFoot) {
    System.out.println("设置" + this.name + "的小小的右脚");
    this.rightFoot = "小小的右脚";
    return this;
  }
}
