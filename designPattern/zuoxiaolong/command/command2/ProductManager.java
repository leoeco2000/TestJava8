package zuoxiaolong.command.command2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductManager {
    
    private static final int TASK_NUMBER_IN_DAY = 4;//һ�������ɵ��ĸ����񣬶����Ƶ��ڶ���

    private List<Task> taskList;
    
    private List<Programmer> programmerList;//��Ʒ����Ӧ����ʶ���еĳ���Գ
    
    private int currentIndex;
    
    public ProductManager(Programmer... programmers) {
        super();
        if (programmers == null || programmers.length == 0) {
            throw new RuntimeException("��Ʒ��������û�г���Ա��������䲻��ȥ���޷�����������");
        }
        taskList = new ArrayList<Task>();
        programmerList = Arrays.asList(programmers);
    }
    
    //����һ������
    public void receive(Task task){
        taskList.add(task);
    }
    
    public void assign(){
        Task[] copy = new Task[taskList.size() > TASK_NUMBER_IN_DAY ? taskList.size() - TASK_NUMBER_IN_DAY : 0];
        for (int i = 0; i < TASK_NUMBER_IN_DAY && i < taskList.size(); i++) {
            taskList.get(i).handle();
        }
        System.arraycopy(taskList.toArray(), TASK_NUMBER_IN_DAY > taskList.size() ? taskList.size() : TASK_NUMBER_IN_DAY, copy, 0, copy.length);
        taskList = Arrays.asList(copy);
    }
    //��Ʒ�������ѡ�����Գ���򵥵�ѭ��ѡȡ��
    public Programmer chooseProgrammer(){
        return programmerList.get(currentIndex == programmerList.size() ? 0 : currentIndex++);
    }
    
    public void printTaskList(){
        if (taskList == null || taskList.size() == 0) {
            System.out.println("----------��ǰ������--------");
            return;
        }
        System.out.println("---------��ǰʣ�µ������б�--------");
        for (Task task : taskList) {
            System.out.println(task);
        }
        System.out.println("----------------------------------");
    }
}
