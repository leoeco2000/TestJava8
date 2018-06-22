package zuoxiaolong.builder;

// 胖的分身的建造者
public class FatBuilder implements DoppelgangerBuilder {

  private Doppelganger doppelganger;

  public void createDoppelganger(String name) {
    doppelganger = new Doppelganger(name);
  }

  public Doppelganger getDoppelganger() {
    try {
      return doppelganger;
    } finally {
      doppelganger = null;
    }
  }

  public void buildBody() {
    System.out.println("设置" + doppelganger.getName() + "的胖胖的身体");
    doppelganger.setBody("胖胖的身体");
  }

  public void buildHead() {
    System.out.println("设置" + doppelganger.getName() + "的大大的头");
    doppelganger.setHead("大大的头");
  }

  public void buildLeftArm() {
    System.out.println("设置" + doppelganger.getName() + "的胖胖的左胳膊");
    doppelganger.setLeftArm("胖胖的左胳膊");
  }

  public void buildLeftHand() {
    System.out.println("设置" + doppelganger.getName() + "的胖胖的左手");
    doppelganger.setLeftHand("胖胖的左手");
  }

  public void buildRightArm() {
    System.out.println("设置" + doppelganger.getName() + "的胖胖的右胳膊");
    doppelganger.setRightArm("胖胖的右胳膊");
  }

  public void buildRightHand() {
    System.out.println("设置" + doppelganger.getName() + "的胖胖的右手");
    doppelganger.setRightHand("胖胖的右手");
  }

  public void buildLeftLeg() {
    System.out.println("设置" + doppelganger.getName() + "的胖胖的左腿");
    doppelganger.setLeftLeg("胖胖的左腿");
  }

  public void buildLeftFoot() {
    System.out.println("设置" + doppelganger.getName() + "的大大的左脚");
    doppelganger.setLeftFoot("大大的左脚");
  }

  public void buildRightLeg() {
    System.out.println("设置" + doppelganger.getName() + "的胖胖的右腿");
    doppelganger.setRightLeg("胖胖的右腿");
  }

  public void buildRightFoot() {
    System.out.println("设置" + doppelganger.getName() + "的大大的右脚");
    doppelganger.setRightFoot("大大的右脚");
  }


}
