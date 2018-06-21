package zuoxiaolong.command.command1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductManager {

  private static final int TASK_NUMBER_IN_DAY = 4;// 一天最多分派掉四个任务，多了推到第二天

  private List<Task> taskList;

  public ProductManager() {
    super();
    taskList = new ArrayList<Task>();
  }

  // 接受一个任务
  public void receive(Task task) {
    taskList.add(task);
  }

  // 分配给程序猿任务，督促程序猿完成
  public void assign() {
    Task[] copy =
        new Task[taskList.size() > TASK_NUMBER_IN_DAY ? taskList.size() - TASK_NUMBER_IN_DAY : 0];
    for (int i = 0; i < TASK_NUMBER_IN_DAY && i < taskList.size(); i++) {
      taskList.get(i).handle();
    }
    System.arraycopy(taskList.toArray(),
        TASK_NUMBER_IN_DAY > taskList.size() ? taskList.size() : TASK_NUMBER_IN_DAY, copy, 0,
        copy.length);
    taskList = Arrays.asList(copy);
  }

  // 打印剩下的任务
  public void printTaskList() {
    if (taskList == null || taskList.size() == 0) {
      System.out.println("----------当前无任务--------");
      return;
    }
    System.out.println("---------当前剩下的任务列表--------");
    for (Task task : taskList) {
      System.out.println(task);
    }
    System.out.println("----------------------------------");
  }
}
