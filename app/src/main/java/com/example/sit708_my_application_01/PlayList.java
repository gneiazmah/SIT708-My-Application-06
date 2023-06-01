package com.example.sit708_my_application_01;

public class PlayList {

    private Integer ID;
    private String Name;

    public PlayList(){}

    public PlayList(Integer ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
