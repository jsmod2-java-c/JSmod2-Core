package cn.jsmod2.test.foundbug.jsmod2;


public class ObjectC{

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
        return "ObjectC{" +
                "Saalo=" + Saalo +
                ", test=" + test +
                '}';
    }
}
