package edu.wgu.jobjournalcapstone.Beans;

public class AuthenticationBean {

    private int id;
    public AuthenticationBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(String message) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
