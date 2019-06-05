package net.noyark.oaml;

public class ValueMapping {
    private String before;
    private String After;

    public ValueMapping(String before, String after) {
        this.before = before;
        After = after;
    }
    public ValueMapping(){

    }
    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return After;
    }

    public void setAfter(String after) {
        After = after;
    }

    @Override
    public String toString() {
        return "ValueMapping{" +
                "before='" + before + '\'' +
                ", After='" + After + '\'' +
                '}';
    }
}
