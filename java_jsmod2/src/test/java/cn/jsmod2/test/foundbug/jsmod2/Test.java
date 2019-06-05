package cn.jsmod2.test.foundbug.jsmod2;

public enum Test {

    TEST(1);
    private int slot = 0;

    Test(int slot){
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
