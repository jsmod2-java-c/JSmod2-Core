package net.noyark.test.foundbug.jsmod2;

public class ObjectC{

    public int s = 12;

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "ObjectC{" +
                "s=" + s +
                '}';
    }
}
