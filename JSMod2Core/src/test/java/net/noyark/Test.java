package net.noyark;


public class Test {


    private Integer i = 0;

    public void main(String[] args) {
        synchronized (i) {
            i++;
        }
    }
}
