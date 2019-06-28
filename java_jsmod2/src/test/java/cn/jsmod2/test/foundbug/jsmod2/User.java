package cn.jsmod2.test.foundbug.jsmod2;


public class User {

    private int Saalo = 12;
    //test-
    private TestObject01 test = new TestObject01();

    public int getSaalo() {
        return Saalo;
    }

    public void setSaalo(int saalo) {
        Saalo = saalo;
    }

    public TestObject01 getTest() {
        return test;
    }

    public void setTest(TestObject01 test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "User{" +
                "Saalo=" + Saalo +
                ", test=" + test +
                '}';
    }

    public User next(){
        return this;
    }

    public User getFirstUser(){
        return this;
    }

    private User getThat(User user, int count, int max){
        User nextUser = user.next();
        if(nextUser != null||count != max){
            count++;
            return getThat(nextUser,count,max);
        }else{
            return user;
        }
    }

    public User getUser(int max){
        User firstUser = getFirstUser();
        return getThat(firstUser,0,max);
    }
}
