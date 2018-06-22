package zuoxiaolong.builder.wrong;

// �����
public class Soul {

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

  public void createBody(String body) {
    System.out.println("����" + doppelganger.getName() + "��" + body);
    doppelganger.setBody(body);
  }

  public void createHead(String head) {
    System.out.println("����" + doppelganger.getName() + "��" + head);
    doppelganger.setHead(head);
  }

  public void createLeftArm(String leftArm) {
    System.out.println("����" + doppelganger.getName() + "��" + leftArm);
    doppelganger.setLeftArm(leftArm);
  }

  public void createLeftHand(String leftHand) {
    System.out.println("����" + doppelganger.getName() + "��" + leftHand);
    doppelganger.setLeftHand(leftHand);
  }

  public void createRightArm(String rightArm) {
    System.out.println("����" + doppelganger.getName() + "��" + rightArm);
    doppelganger.setRightArm(rightArm);
  }

  public void createRightHand(String rightHand) {
    System.out.println("����" + doppelganger.getName() + "��" + rightHand);
    doppelganger.setRightHand(rightHand);
  }

  public void createLeftLeg(String leftLeg) {
    System.out.println("����" + doppelganger.getName() + "��" + leftLeg);
    doppelganger.setLeftLeg(leftLeg);
  }

  public void createLeftFoot(String leftFoot) {
    System.out.println("����" + doppelganger.getName() + "��" + leftFoot);
    doppelganger.setLeftFoot(leftFoot);
  }

  public void createRightLeg(String rightLeg) {
    System.out.println("����" + doppelganger.getName() + "��" + rightLeg);
    doppelganger.setRightLeg(rightLeg);
  }

  public void createRightFoot(String rightFoot) {
    System.out.println("����" + doppelganger.getName() + "��" + rightFoot);
    doppelganger.setRightFoot(rightFoot);
  }

}
