package test.reflect.springTest;

import java.util.HashMap;
import java.util.Map;

public class Bean {

  /* Bean Id */
  private String id;
  /* Bean Class */
  private String type;
  /* Bean Property */
  private Map<String, Object> properties = new HashMap<String, Object>();
}
