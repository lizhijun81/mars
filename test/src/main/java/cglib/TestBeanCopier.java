package cglib;

import net.sf.cglib.beans.BeanCopier;

/**
 * 用来对象之间拷贝属性
 */
public class TestBeanCopier {
    public static void main(String[] args) {
        Other other = new Other();
        other.setUsername("xiaoming");
        other.setPassword("23456");

        Myth myth = new Myth();

        System.out.println(other);
        System.out.println(myth);

        BeanCopier beanCopier = BeanCopier.create(other.getClass(), myth.getClass(), false);
        beanCopier.copy(other, myth, null);

        System.out.println(other);
        System.out.println(myth);
    }


}


class Other {
    private String username;
    private String password;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Other{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}

class Myth {
    private String username;
    private String password;
    private String remark;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Myth{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}