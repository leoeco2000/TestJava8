package yuanmadao.Interpreter.testWithNoPattern;

public class Client {
  public static void main(String[] args) throws Exception {
    String path = "data/peizhi.xml";
    ReadAppXml reader = new ReadAppXml();
    reader.read(path);
  }
}
