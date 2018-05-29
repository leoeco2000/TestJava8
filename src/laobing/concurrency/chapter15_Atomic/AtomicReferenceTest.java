package laobing.concurrency.chapter15_Atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
        User userA = new User(1,"叶开");
        User userB = new User(2,"傅红雪");

        /*定义并用userA初始化AtomicReference对象*/
        AtomicReference<User> ATOMIC_REFERENCE = new AtomicReference<User>(userA);

        User user=ATOMIC_REFERENCE.get();
        ATOMIC_REFERENCE.compareAndSet(user, userB);
        System.out.println(ATOMIC_REFERENCE.get());
    }
}
class User {
    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
    private int id;
    private String username;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "User["+this.id+"]名称："+this.username;
    }
}