package laobing.concurrency.chapter15_Atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
        User userA = new User(1,"Ҷ��");
        User userB = new User(2,"����ѩ");

        /*���岢��userA��ʼ��AtomicReference����*/
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
        return "User["+this.id+"]���ƣ�"+this.username;
    }
}