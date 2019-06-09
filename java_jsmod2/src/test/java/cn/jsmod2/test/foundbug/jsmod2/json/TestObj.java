package cn.jsmod2.test.foundbug.jsmod2.json;

public class TestObj {

    private int x;

    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
