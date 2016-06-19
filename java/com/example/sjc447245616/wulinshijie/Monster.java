package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/27.
 */
public class Monster {
    private int head;
    private String name;
    private String go;

    public int getHead() {
        return head;
    }

    public String getName() {
        return  name;
    }

    public String getGo() {
        return go;
    }


    public Monster(int head, String name, String go) {
        super();
        this.head = head;
        this.name = name;
        this.go = go;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + "]";
    }

}