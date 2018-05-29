package TestDesignPattern.dhDemo.factoryMethod;

public class VolunteerFactory implements IFactory {

  public LeiFeng createLeiFeng() {
    return new Volunteer();
  }

}
