package zuoxiaolong.builder.withBuilder;

// 分身制造者接口
public abstract class DoppelgangerBuilder {

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

  public abstract Doppelganger build();

  public abstract DoppelgangerBuilder buildBody(String body);

  public abstract DoppelgangerBuilder buildHead(String head);

  public abstract DoppelgangerBuilder buildLeftArm(String leftArm);

  public abstract DoppelgangerBuilder buildLeftHand(String leftHand);

  public abstract DoppelgangerBuilder buildRightArm(String rightArm);

  public abstract DoppelgangerBuilder buildRightHand(String rightHand);

  public abstract DoppelgangerBuilder buildLeftLeg(String leftLeg);

  public abstract DoppelgangerBuilder buildLeftFoot(String leftFoot);

  public abstract DoppelgangerBuilder buildRightLeg(String rightLeg);

  public abstract DoppelgangerBuilder buildRightFoot(String rightFoot);

}
