package net.noyark.test.foundbug.jsmod2;

public class ObjectC{

    private int Saalo = 12;

    public int getSaalo() {
        return Saalo;
    }

    public Test test;

    public void setSaalo(int saalo) {
        Saalo = saalo;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
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
