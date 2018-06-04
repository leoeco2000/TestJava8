package yuanmadao.memento.ImplCancelable;

public class SubstractCommand extends AbstractCommand {
  private int opeNum;

  public SubstractCommand(int opeNum) {
    this.opeNum = opeNum;
  }

  public void execute() {
    this.operation.substract(opeNum);
  }
}
