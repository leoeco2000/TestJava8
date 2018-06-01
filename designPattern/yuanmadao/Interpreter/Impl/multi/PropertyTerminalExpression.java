package yuanmadao.Interpreter.Impl.multi;

/**
 * 属性作为终结符对应的解释器
 */
public class PropertyTerminalExpression extends ReadXmlExpression {
  /**
   * 属性的名字
   */
  private String propName;

  public PropertyTerminalExpression(String propName) {
    this.propName = propName;
  }

  public String[] interpret(Context c) {
    // 然后需要去获取这个元素的值
    String[] ss = new String[1];
    ss[0] = c.getPreEles().get(0).getAttribute(this.propName);
    return ss;
  }
}

